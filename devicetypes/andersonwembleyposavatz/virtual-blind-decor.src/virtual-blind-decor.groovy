/**
 *  Copyright 2015 SmartThings
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
	definition (name: "VIRTUAL Blind DECOR", namespace: "andersonwembleyposavatz", author: "Anderson Wembley Posavatz") {
		capability "Actuator"
		capability "switch"
		capability "Sensor"
		capability "windowShade"        

		command "up"
        command "stop"
        command "down"
	}
    preferences {
	}

	simulator {
	}
    
	tiles(scale:2) {
		multiAttributeTile(name:"blind", type: "generic", width: 6, height: 4) {
			tileAttribute ("device.status", key: "PRIMARY_CONTROL") {
                attributeState "up", label:'${name}', action:"down", icon:"st.shades.shade-open", backgroundColor:"#79b821", nextState:"down"
				attributeState "down", label:'${name}', action:"up", icon:"st.shades.shade-closed", backgroundColor:"#ffffff", nextState:"up" 			}
		}       
 		standardTile("up", "device.button", width: 2, height: 2) {
			state "default", label: "Up", backgroundColor: "#ffffff", action: "up", icon:"st.shades.shade-open"
		} 
 		standardTile("stop", "device.button", width: 2, height: 2) {
			state "default", label: "", backgroundColor: "#ffffff", action: "stop", icon:"st.shades.shade-stop"
		} 
 		standardTile("down", "device.button", width: 2, height: 2) {
			state "default", label: "Down", backgroundColor: "#ffffff", action: "down", icon:"st.shades.shade-closed"
		}
		main(["windowShade"])
		details(["blind","up","stop","down", "windowShade"])
	}
}

def parse(description) {

}

def up() {
	sendEvent(name: "", value: "up")
}

def down() {
        sendEvent(name: "", value: "down")
}

def open() {
	sendEvent(name: "", value: "up")
}

def close() {
        sendEvent(name: "", value: "down")
}

def on() {
	sendEvent(name: "", value: "up")
}

def off() {
        sendEvent(name: "", value: "down")
}


def installed() {
    initialize()

}

def updated() {
	initialize()
}

def initialize() {
    down()

    
    sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)

}