/**
 *  Copyright 2018 SmartThings
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
	definition(name: "VIRTUAL Fan Controller", namespace: "andersonwembleyposavatz", author: "Anderson W Posavatz", ocfDeviceType: "oic.d.fan",  runLocally: true , executeCommandsLocally: true) {
		capability "Actuator"
		capability "Switch"
		capability "Sensor"
		capability "Fan Speed"
		capability "Health Check"
        
        command "low"
		command "medium"
		command "high"

	}

	simulator {
   
	}

	tiles(scale: 2) {
		multiAttributeTile(name: "fanSpeed", type: "generic", width: 6, height: 4, canChangeIcon: true) {
			tileAttribute("device.fanSpeed", key: "PRIMARY_CONTROL") {
				attributeState "0", label: "off", action: "switch.on", icon: "st.thermostat.fan-off", backgroundColor: "#ffffff"
				attributeState "1", label: "low", action: "switch.off", icon: "st.thermostat.fan-on", backgroundColor: "#00a0dc"
				attributeState "2", label: "medium", action: "switch.off", icon: "st.thermostat.fan-on", backgroundColor: "#00a0dc"
				attributeState "3", label: "high", action: "switch.off", icon: "st.thermostat.fan-on", backgroundColor: "#00a0dc"
			}

	
		}

		standardTile("0", "device.fanSpeed", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label: '${name}', action: "off", icon: "st.thermostat.fan-off"
		}
        
		standardTile("1", "device.fanSpeed", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label: '${name}', action: "low", icon: "st.thermostat.fan-on"
		}
		standardTile("2", "device.fanSpeed", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label: '${name}', action: "medium", icon: "st.thermostat.fan-on"
		}
		standardTile("3", "device.fanSpeed", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label: '${name}', action: "high", icon: "st.thermostat.fan-on"
	
		}
		main "fanSpeed"
		details "fanSpeed"
	}

}

def parse(String description) {
	log.trace "parse($description)"
}

def on() {
	sendEvent(name: "fanSpeed", value: "1")
    sendEvent(name: "switch", value: "on")


}

def off() {
    sendEvent(name: "fanSpeed", value: "0")
    sendEvent(name: "switch", value: "off")

}

def low() {

	sendEvent(name: "fanSpeed", value: "1")
    sendEvent(name: "switch", value: "on")


}

def medium() {

    sendEvent(name: "fanSpeed", value: "2")
    sendEvent(name: "switch", value: "on")


}


def high() {

    sendEvent(name: "fanSpeed", value: "3")
    sendEvent(name: "switch", value: "on")

}



def installed() {
	log.trace "Executing 'installed'"
	initialize()
}

def updated() {
	log.trace "Executing 'updated'"
	initialize()
}

def initialize() {
	sendEvent(name: "fanSpeed", value: "0")

   
    
    sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
}