/**
 *  VIRTUAL Windows Shade for Decor
 *
 *  Copyright 2021 Anderson W Posavatz
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
	definition (name: "VIRTUAL Windows Shade for Decor", namespace: "andersonwembleyposavatz", author: "Anderson W Posavatz", runLocally: true , executeCommandsLocally: true , ocfDeviceType: "oic.d.blind", mnmn: "SmartThings", vid: "generic-shade-2") {
		capability "Actuator"
		capability "Window Shade"
		capability "Sensor"
		capability "Battery"
		capability "Health Check"
        
        command "open"
        command "close"
        command "up"
        command "down"
        
           
	}

	simulator {
		
	}

    tiles {
        multiAttributeTile(name:"windowsShade", type: "generic", width: 6, height: 4){
            tileAttribute ("device.windowsShade", key: "PRIMARY_CONTROL") {
                attributeState "open", label:'${name}', action:"close", icon:"st.shades.shade-open", backgroundColor:"#79b821", nextState:"closing"
				attributeState "closed", label:'${name}', action:"open", icon:"st.shades.shade-closed", backgroundColor:"#ffffff", nextState:"opening"
				attributeState "partially open", label:'${name}', action:"close", icon:"st.shades.shade-open", backgroundColor:"#79b821", nextState:"closing"
				attributeState "opening", label:'${name}', action:"pause", icon:"st.shades.shade-opening", backgroundColor:"#79b821"
				attributeState "closing", label:'${name}', action:"pause", icon:"st.shades.shade-closing", backgroundColor:"#ffffff"
    }
            tileAttribute ("device.battery", key: "SECONDARY_CONTROL") {
                attributeState "battery", label: "74", unit: "%"
            }
        }

		standardTile("open", "device.windowShade", width: 2, height: 2, decoration: "flat") {
			state "default", label: '${name}', action:"open", icon:"st.shades.shade-closed"
		}
		standardTile("closed", "device.windowShade", width: 2, height: 2, decoration: "flat") {
			state "default", label: '${name}', action:"closed", icon:"st.shades.shade-open"
		
	    }

        
		main "windowShade"
		details "windowShade", "battery", "up", "down"
                }
} 

def parse(String description) {
	log.trace "parse($description)"
}

def open() {
	sendEvent(name: "windowShade", value: "opening")
        runIn(3, finishOpening)
}

def close() {
    sendEvent(name: "windowShade", value: "closing")
	runIn(3, finishClosing)
}

def up() {
	sendEvent(name: "windowShade", value: "opening")
        runIn(3, finishOpening)
}

def down() {
     sendEvent(name: "windowShade", value: "closing")
	runIn(3, finishClosing)
}

def finishOpening() {
	sendEvent(name: "windowShade", value: "open")

}

def finishClosing() {
	sendEvent(name: "windowShade", value: "closed")


}

def setBatteryLevel(Number lvl) {
    log.trace "setBatteryLevel(level)"
    sendEvent(name: "battery", value: lvl)

}

def installed() {
	log.trace "Executing 'installed'"
	initialize()
}

def updated() {
	log.trace "Executing 'updated'"
	initialize()
    setBatteryLevel(74)

}


private initialize() {
    setBatteryLevel(74)

    sendEvent(name: "windowShade", value: "close")
    
    sendEvent(name: "battery", value: device.currentValue("battery") ?: 74)

	sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
  
}