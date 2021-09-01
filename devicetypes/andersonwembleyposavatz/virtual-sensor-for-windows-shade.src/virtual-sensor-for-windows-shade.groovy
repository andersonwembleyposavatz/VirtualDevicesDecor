/**
 *  Copyright 2021 SmartThings
 *
 *  VIRTUAL Sensor for Windows Shade
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
metadata {
	definition (name: "VIRTUAL Sensor for Windows Shade", namespace: "andersonwembleyposavatz", author: "Anderson W Posavatz", runLocally: true , executeCommandsLocally: true , ocfDeviceType: "oic.d.blind", mnmn: "SmartThings", vid: "generic-shade-2") {
		capability "Actuator"
        capability "Configuration"
		capability "Window Shade"
		capability "Stateless Curtain Power Button"
		capability "Contact Sensor"
		capability "Sensor"
		capability "Health Check"		
        //capability "Switch"
	
	}

	preferences {
		section {
			input("actionDelay", "number",
				title: "Action Delay\n\nAn emulation for how long it takes the window shade to perform the requested action.",
				description: "In seconds (1-120; default if empty: 5 sec)",
				range: "1..120", displayDuringSetup: false)
		}
		
	}

	tiles(scale: 2) {
		multiAttributeTile(name:"windowShade", type: "generic", width: 6, height: 4){
			tileAttribute ("device.windowShade", key: "PRIMARY_CONTROL") {
				attributeState "open", label:'${name}', action:"close", icon:"st.shades.shade-open", backgroundColor:"#79b821", nextState:"closing"
				attributeState "closed", label:'${name}', action:"open", icon:"st.shades.shade-closed", backgroundColor:"#ffffff", nextState:"opening"
				attributeState "partially open", label:'Open', action:"close", icon:"st.shades.shade-open", backgroundColor:"#79b821", nextState:"closing"
				attributeState "opening", label:'${name}', action:"pause", icon:"st.shades.shade-opening", backgroundColor:"#79b821", nextState:"partially open"
				attributeState "closing", label:'${name}', action:"pause", icon:"st.shades.shade-closing", backgroundColor:"#ffffff", nextState:"partially open"

            }
        }
        				
		standardTile("windowShadeOpen", "device.windowShade", width: 2, height: 2, decoration: "flat") {
			state "default", label: "open", action:"open", icon:"st.shades.shade-open"
		}
		standardTile("windowShadeClose", "device.windowShade", width: 2, height: 2, decoration: "flat") {
			state "default", label: "close", action:"close", icon:"st.shades.shade-closed"
		}
		standardTile("windowShadePause", "device.windowShade", width: 2, height: 2, decoration: "flat") {
			state "default", label: "pause", action:"pause", icon:"st.shades.shade-opening"
		}
		standardTile("windowShadePreset", "device.windowShadePreset", width: 2, height: 2, decoration: "flat") {
			state "default", label: "preset", action:"presetPosition", icon:"st.Home.home2"
		}

		valueTile("statesLabel", "device.states", width: 6, height: 1, decoration: "flat") {
			state "default", label: "State Events:"	
		}

		main "toggle", "windowShade"
		details "windowShade", "contact", "windowShadeOpen", "windowShadeClose", "windowShadePause", "windowShadePreset", "open", "close", "pause", "opened", "closed", "on", "off"
				
	}
}

def parse(String description) {
	log.debug "parse(): $description"
    
}

def open() {
	sendEvent(name: "windowShade", value: "opening")
	sendEvent(name: "contact", value: "open")
    runIn(12, finishOpening)
}

def close() {
    sendEvent(name: "windowShade", value: "closing")
    sendEvent(name: "contact", value: "closed")
	runIn(12, finishClosing)
}

def on() {
	sendEvent(name: "windowShade", value: "opening")
	sendEvent(name: "contact", value: "open")
    runIn(shadeActionDelay, finishOpening)
}

def off() {
    sendEvent(name: "windowShade", value: "closing")
    sendEvent(name: "contact", value: "closed")
	runIn(shadeActionDelay, finishClosing)
}

def pause() {
	sendEvent(name: "windowShade", value: "pause")
	sendEvent(name: "contact", value: "open")

}

def setButton(value){
    log.info "setButton ${value}"
    if (value == "pause") {
        pause()
    }
}

def finishOpening() {
    sendEvent(name: "windowShade", value: "open")
    sendEvent(name: "contact", value: "open")
}

def finishClosing() {
    sendEvent(name: "windowShade", value: "closed")
    sendEvent(name: "contact", value: "closed")
}

def openPartially() {
	opening()
	runIn(shadeActionDelay, "partiallyOpen")
}

def closePartially() {
	closing()
	runIn(shadeActionDelay, "partiallyOpen")
}

def partiallyOpen() {
	sendEvent(name: "windowShade", value: "partially open")
}

def opening() {
	sendEvent(name: "windowShade", value: "opening")
}

def closing() {
	sendEvent(name: "windowShade", value: "closing")
}

def opened() {
	sendEvent(name: "windowShade", value: "open")
	sendEvent(name: "contact", value: "open")
}

def closed() {
	sendEvent(name: "windowShade", value: "closed")
	sendEvent(name: "contact", value: "closed")
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
	sendEvent(name: "windowShade", value: "open")
	sendEvent(name: "contact", value: "open")
    
	sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
}