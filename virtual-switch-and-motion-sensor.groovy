/**
 *  VIRTUAL Switch and Motion Sensor
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
	definition (name: "VIRTUAL Switch and Motion Sensor", namespace: "andersonwembleyposavatz", author: "Anderson W Posavatz", vid: "generic-motion", runLocally: true , executeCommandsLocally: true , ocfDeviceType: "x.com.st.d.sensor.motion", 
 ) {
		capability "Actuator"
		capability "Switch"
		capability "Refresh"
		capability "Sensor"
		capability "Motion Sensor"
		capability "Health Check"
	}

	simulator {
		
	}

	tiles {
		standardTile("toggle", "device.switch", width: 3, height: 3) {
			state("off", label:'${name}', action:"on", icon:"st.motion.motion.inactive", backgroundColor:"#00A0DC", nextState:"turningOn")
			state("on", label:'${name}', action:"off", icon:"st.motion.motion.active", backgroundColor:"#cccccc", nextState:"turningOff")
			state("turningOn", label:'${name}', icon:"st.motion.motion.inactive", backgroundColor:"#cccccc")
			state("turningOff", label:'${name}', icon:"st.motion.motion.active", backgroundColor:"#00A0DC")
			
		}
		standardTile("on", "device.switch", inactiveLabel: false, decoration: "flat") {
			state "default", label:'Motion', icon:"st.motion.motion.active", backgroundColor:"#00A0DC", action:"inactive"
		}
		standardTile("off", "device.switch", inactiveLabel: false, decoration: "flat") {
			state "default", label:'No Motion', icon:"st.motion.motion.inactive", backgroundColor:"#cccccc", action:"active"
		}

		main (["toggle"])
		details(["toggle", "motion", "switch", "refresh", "on", "off", "active", "inactive"])
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

def active() {
	sendEvent(name: "switch", value: "turningOn")
    runIn(0, finishOpening)
}

def inactive() {
    sendEvent(name: "switch", value: "turningOff")
	runIn(0, finishClosing)
}

def finishOpening() {
    sendEvent(name: "switch", value: "on")
    sendEvent(name: "motion", value: "active", isStateChange: true)
}

def finishClosing() {
    sendEvent(name: "switch", value: "off")
    sendEvent(name: "motion", value: "inactive", isStateChange: true)
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
    sendEvent(name: "motion", value: "inactive")
    
    sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
}