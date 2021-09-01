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
		capability "Refresh"
		capability "Sensor"
		capability "Fan Speed"
		capability "Health Check"

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
			tileAttribute("device.fanSpeed", key: "VALUE_CONTROL") {
				attributeState "VALUE_UP", action: "raiseFanSpeed"
				attributeState "VALUE_DOWN", action: "lowerFanSpeed"
			}
		}

		standardTile("switch", "device.switch", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label: '', action: "refresh.refresh", icon: "st.secondary.refresh"
		}
		main "fanSpeed"
		details "fanSpeed", "on", "off"
	}

}

def parse(String description) {
	log.trace "parse($description)"
}

def on() {
	sendEvent(name: "switch", value: "on")

}

def off() {
    sendEvent(name: "switch", value: "off")

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
	sendEvent(name: "switch", value: "off")
    sendEvent(name: "contact", value: "closed")
    
    sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
}