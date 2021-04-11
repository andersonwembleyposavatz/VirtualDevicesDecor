/**
 *  VIRTUAL Switch and Presence Sensor
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
	definition (name: "VIRTUAL Switch and Presence Sensor", namespace: "andersonwembleyposavatz", author: "Anderson W Posavatz", vid: "generic-presence", runLocally: true , executeCommandsLocally: true) {
		capability "Actuator"
		capability "Switch"
		capability "Refresh"
		capability "Sensor"
		capability "Presence Sensor"
		capability "Health Check"
	}

	simulator {
		
	}

	tiles {
		standardTile("toggle", "device.switch", width: 6, height: 4) {
			state("off", label:'${name}', action:"on", icon:"st.presence.tile.not-present", backgroundColor:"#00A0DC", nextState:"turningOn")
			state("on", label:'${name}', action:"off", icon:"st.presence.tile.present", backgroundColor:"#cccccc", nextState:"turningOff")
			state("turningOn", label:'${name}', icon:"st.presence.tile.not-present", backgroundColor:"#cccccc")
			state("turningOff", label:'${name}', icon:"st.presence.tile.present", backgroundColor:"#00A0DC")
			
		}
		standardTile("on", "device.presence", inactiveLabel: false, decoration: "flat") {
			state "default", label:"unoccupied", icon:"st.presence.tile.present", backgroundColor:"#00A0DC", action:"on"
		}
		standardTile("off", "device.presence", inactiveLabel: false, decoration: "flat") {
			state "default", label:"occupied", icon:"st.presence.tile.not-present", backgroundColor:"#cccccc", action:"off"
		}

		main (["toggle"])
		details(["toggle", "presence", "switch", "refresh", "on", "off", "occupied", "unoccupied"])
	}
}

def parse(String description) {
	log.trace "parse($description)"
}

def on() {
	sendEvent(name: "switch", value: "turningOn")
    runIn(0, finishOpening)
}

def off() {
    sendEvent(name: "switch", value: "turningOff")
	runIn(0, finishClosing)
}

def present() {
	sendEvent(name: "switch", value: "turningOn")
    runIn(0, finishOpening)
}

def notPresent() {
    sendEvent(name: "switch", value: "turningOff")
	runIn(0, finishClosing)
}

def finishOpening() {
    sendEvent(name: "switch", value: "on")
    sendEvent(name: "presence", value: "present", isStateChange: true)
}

def finishClosing() {
    sendEvent(name: "switch", value: "off")
    sendEvent(name: "presence", value: "not present", isStateChange: true)
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
	log.trace "Executing 'initialize'"

	sendEvent(name: "switch", value: "off")
    sendEvent(name: "presence", value: "not present")
    
    sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
}