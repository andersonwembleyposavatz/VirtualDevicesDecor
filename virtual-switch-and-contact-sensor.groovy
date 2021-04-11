/**
 *  VIRTUAL Switch and Contact Sensor
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
	definition (name: "VIRTUAL Switch and Contact Sensor", namespace: "andersonwembleyposavatz", author: "Anderson W Posavatz", vid: "generic-contact", runLocally: true , executeCommandsLocally: true , ocfDeviceType: "x.com.st.d.sensor.contact", 
 ) {
		capability "Actuator"
		capability "Switch"
		capability "Refresh"
		capability "Sensor"
		capability "Contact Sensor"
		capability "Health Check"
	}

	simulator {
		
	}

	tiles {
		standardTile("toggle", "device.switch", width: 3, height: 3) {
			state("off", label:'${name}', action:"on", icon:"st.contact.contact.closed", backgroundColor:"#00A0DC", nextState:"turningOn")
			state("on", label:'${name}', action:"off", icon:"st.contact.contact.open", backgroundColor:"#cccccc", nextState:"turningOff")
			state("turningOn", label:'${name}', icon:"st.contact.contact.closed", backgroundColor:"#cccccc")
			state("turningOff", label:'${name}', icon:"st.contact.contact.open", backgroundColor:"#00A0DC")
			
		}
		standardTile("on", "device.contact", inactiveLabel: false, decoration: "flat") {
			state "default", label:'opened', icon:"st.contact.contact.open", backgroundColor:"#00A0DC"
		}
		standardTile("off", "device.contact", inactiveLabel: false, decoration: "flat") {
			state "default", label:'closed', icon:"st.contact.contact.closed", backgroundColor:"#cccccc"
		}

		main (["contact", "toggle"])
		details(["toggle", "contact", "switch", "refresh", "on", "off", "opened", "closed"])
	}
}

def parse(String description) {
	log.trace "parse($description)"
}

def on() {
	sendEvent(name: "switch", value: "turningOn")
    runIn(2, finishOpening)
}

def off() {
    sendEvent(name: "switch", value: "turningOff")
	runIn(2, finishClosing)
}

def open() {
	sendEvent(name: "switch", value: "turningOn")
    runIn(2, finishOpening)
}

def close() {
    sendEvent(name: "switch", value: "turningOff")
	runIn(2, finishClosing)
}

def finishOpening() {
    sendEvent(name: "switch", value: "on")
    sendEvent(name: "contact", value: "opened")
}

def finishClosing() {
    sendEvent(name: "switch", value: "off")
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

def initialize() {
	sendEvent(name: "switch", value: "off")
    sendEvent(name: "contact", value: "closed")
    
    sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
}