/**
 *
 *	Copyright 2021 SmartThings
 *
 *	Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *	in compliance with the License. You may obtain a copy of the License at:
 *
 *		http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *	on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *	for the specific language governing permissions and limitations under the License.
 */


metadata {
    definition(name: "VIRTUAL Beautiful Window Shade Decor", namespace: "Rooms Beautiful", author: "andersonwembleyposavatz", ocfDeviceType: "oic.d.blind", mnmn: "SmartThings", vid: "generic-shade-2", runLocally: true , executeCommandsLocally: true) {
        capability "Actuator"
        capability "Battery"
        capability "Configuration"
        capability "Contact Sensor"
        capability "Refresh"
        capability "Health Check"
        capability "Window Shade"
        capability "Switch"
        capability "Switch Level"

        attribute("battLife", "enum")


    }

    preferences {

}

    tiles(scale: 2) {
        multiAttributeTile(name: "windowShade", type: "generic", width: 6, height: 4) {
            tileAttribute("device.windowShade", key: "PRIMARY_CONTROL") {
                attributeState "open", label:'${name}', action:"close", icon:"st.shades.shade-open", backgroundColor:"#79b821", nextState:"closing"
				attributeState "closed", label:'${name}', action:"open", icon:"st.shades.shade-closed", backgroundColor:"#ffffff", nextState:"opening"
				attributeState "partially open", label:'${name}', action:"close", icon:"st.shades.shade-open", backgroundColor:"#79b821", nextState:"closing"
				attributeState "opening", label:'${name}', action:"pause", icon:"st.shades.shade-opening", backgroundColor:"#79b821"
				attributeState "closing", label:'${name}', action:"pause", icon:"st.shades.shade-closing", backgroundColor:"#ffffff"
            }
            tileAttribute("device.battLife", key: "SECONDARY_CONTROL") {
                attributeState "full", icon: "https://raw.githubusercontent.com/gearsmotion789/ST-Images/master/full.png", label: ""
                attributeState "medium", icon: "https://raw.githubusercontent.com/gearsmotion789/ST-Images/master/medium.png", label: ""
                attributeState "low", icon: "https://raw.githubusercontent.com/gearsmotion789/ST-Images/master/low.png", label: ""
                attributeState "dead", icon: "https://raw.githubusercontent.com/gearsmotion789/ST-Images/master/dead.png", label: ""
            }
         }
         
        standardTile("windowShade", "device.windowShade", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
            state "open", label: 'Open', icon:"st.shades.shade-open", action: 'close', backgroundColor: "#79b821", nextState:"closing"
            state "closed", label: 'Closed', icon: "st.shades.shade-closed" , action: 'open', backgroundColor: "#ffffff", nextState:"opening"
        }
			                      
    standardTile("refresh", "device.refresh", decoration: "flat", width: 2, height: 2) {
 		state "default", action:"refresh", icon:"st.secondary.refresh"
 		
        }
        	
		valueTile("shadeLevel", "device.level", width: 4, height: 1) {
			state "level", label: 'Shade is ${currentValue}% up', defaultState: true
		}
		controlTile("levelSliderControl", "device.level", "slider", width:2, height: 1, inactiveLabel: false) {
			state "level", action:"switch level.setLevel"
		}
        
        main "windowShade"
        details "windowShade", "contact", "battery", "shadeLevel", "levelSliderControl"
    }
}

// Parse incoming device messages to generate events
def parse(String description) {
    // FYI = event.name refers to attribute name & not the tile's name

    def linkText = getLinkText(device)
    def event = zigbee.getEvent(description)
    def descMap = zigbee.parseDescriptionAsMap(description)
    def value
    def attrId

    if (event) {
        if (!descMap.attrId)
            sendEvent(name: "replay", value: "pause")

        if (event.name == "switch" || event.name == "windowShade") {
            if (event.value == "on" || event.value == "open") {
                log.info "${linkText} - Open"
                sendEvent(name: "switch", value: "on")
                sendEvent(name: "windowShade", value: "open")
            } else {
                log.info "${linkText} - Close"
                sendEvent(name: "switch", value: "off")
                sendEvent(name: "windowShade", value: "closed")
            }
        }
    } else {
        if (descMap.attrId) {
            if (descMap.clusterInt != 0xDC00) {
                value = Integer.parseInt(descMap.value, 16)
                attrId = Integer.parseInt(descMap.attrId, 16)
            }
        }

        switch (descMap.clusterInt) {
            case zigbee.POWER_CONFIGURATION_CLUSTER:
                if (attrId == BATTERY_VOLTAGE)
                    handleBatteryEvent(value)
                break;
            case CLUSTER_WINDOW_COVERING:
                if (attrId == ATTRIBUTE_POSITION_LIFT) {
                    log.info "${linkText} - Level: ${value}"
                    sendEvent(name: "level", value: value)

                    if (value == 0 || value == 100) {
                        sendEvent(name: "switch", value: value == 0 ? "off" : "on")
                        sendEvent(name: "windowShade", value: value == 0 ? "closed" : "open")
                    } else if (value > 0 && value < 100) {
                        sendEvent(name: "replay", value: "cont")
                        sendEvent(name: "windowShade", value: "partially open")
                    }
                }
                break;
            case 0xFC00:
                if (description?.startsWith('read attr -'))
                    log.info "${linkText} - Inverted: ${value}"
                else
                    log.debug "${linkText} - Inverted set to: ${invert}"
                break;
            case 0xDC00:
                value = descMap.value
                def shortAddr = value.substring(4)
                def lqi = zigbee.convertHexToInt(value.substring(2, 4))
                def rssi = (byte) zigbee.convertHexToInt(value.substring(0, 2))
                log.info "${linkText} - Parent Addr: ${shortAddr} **** LQI: ${lqi} **** RSSI: ${rssi}"
                break;
            default:
                log.warn "${linkText} - DID NOT PARSE MESSAGE for description: $description"
                log.debug descMap
                break;
        }
    }
}

def off() {
    log.debug "off()" +
        sendEvent(name: "level", value: 100)
        sendEvent(name: "windowShade", value: "closed")
        sendEvent(name: "contact", value: "closed")
}

def on() {
    log.debug "on()" +
        sendEvent(name: "level", value: 0)
        sendEvent(name: "windowShade", value: "open")
        sendEvent(name: "contact", value: "open")
}

def close() {
    log.debug "close()" +
        sendEvent(name: "level", value: 100)
        sendEvent(name: "windowShade", value: "closed")
        sendEvent(name: "contact", value: "closed")
}

def open() {
    log.debug "open()" +
        sendEvent(name: "level", value: 0)
        sendEvent(name: "windowShade", value: "open")
        sendEvent(name: "contact", value: "open")
}

def pause() {
    log.debug(CLUSTER_WINDOW_COVERING, 0x02) +
        sendEvent(name: "replay", value: "cont") +
        sendEvent(name: "windowShade", value: "partially open")
        sendEvent(name: "contact", value: "open")
}

def setLevel(value) {
    def time
    if (state.updatedDate == null) {
        time = now()
    } else {
        time = now() - state.updatedDate
    }
    state.updatedDate = now()
    log.trace("Time: ${time}")

    if (time > 1000) {
        log.debug("Setting level to: ${value}")
        log.debug(CLUSTER_WINDOW_COVERING, COMMAND_GOTO_LIFT_PERCENTAGE, zigbee.convertToHexString(100 - value, 2)) +
            sendEvent(name: "level", value: value)
    }
}


private handleBatteryEvent(volts) {
    def linkText = getLinkText(device)

    if (volts > 30 || volts < 20) {
        log.warn "${linkText} - Ignoring invalid value for voltage (${volts/10}V)"
    } else {
        def batteryMap = [30: "full", 29: "full", 28: "full", 27: "medium", 26: "low", 25: "dead"]

        def value = batteryMap[volts]
        if (value != null) {
            def minVolts = 25
            def maxVolts = 30
            def pct = (volts - minVolts) / (maxVolts - minVolts)
            def roundedPct = Math.round(pct * 100)
            def percent = Math.min(100, roundedPct)

            log.info "${linkText} - Batt: ${value} **** Volts: ${volts/10}v **** Percent: ${percent}%"
            sendEvent(name: "battery", value: percent)
            sendEvent(name: "battLife", value: value)
        }
    }
}

def refresh() {
    	log.debug "refresh()"
     
}

def installed() {
	log.trace "Executing 'installed'"
	initialize()
}

def updated() {
	log.trace "Executing 'updated'"
	initialize()
}

private initialize() {
	log.trace "Executing 'initialize'"

    sendEvent(name: "battery", value: 100)
    sendEvent(name: "battLife", value: "full")
    sendEvent(name: "level", value: 0)
    sendEvent(name: "windowShade", value: "open")
    sendEvent(name: "contact", value: "open") 

	sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
    
}



