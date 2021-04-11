/**
 *  VIRTUAL Door and Motion Sensor
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
	definition (name: "VIRTUAL Door and Contact Sensor Switch", namespace: "andersonwembleyposavatz", author: "Anderson W Posavatz", vid: "generic-contact-2", runLocally: true , executeCommandsLocally: true , ocfDeviceType: "x.com.st.d.sensor.contact", 
 ) {
		capability "Actuator"
		capability "Door Control"
		capability "Refresh"
		capability "Sensor"
		capability "Switch"
		capability "Contact Sensor"
		capability "Health Check"
	}

	simulator {
		
	}

	tiles {
		standardTile("toggle", "device.door", width: 2, height: 2) {
			state("closed", label:'${name}', action:"door control.open", icon:"st.contact.contact.closed", backgroundColor:"#00A0DC", nextState:"opening")
			state("open", label:'${name}', action:"door control.close", icon:"st.contact.contact.open", backgroundColor:"#e86d13", nextState:"closing")
			state("opening", label:'${name}', icon:"st.contact.contact.closed", backgroundColor:"#e86d13")
			state("closing", label:'${name}', icon:"st.contact.contact.open", backgroundColor:"#00A0DC")
			
		}
		standardTile("open", "device.door", inactiveLabel: false, decoration: "flat") {
			state "default", label:'open', action:"open", icon:"st.contact.contact.open"
		}
		standardTile("close", "device.door", inactiveLabel: false, decoration: "flat") {
			state "default", label:'close', action:"close", icon:"st.contact.contact.closed"
		}
	
		main "toggle"
		details(["toggle", "open", "closed", "on", "off"])
	}
}

def parse(String description) {
	log.trace "parse($description)"
}

def open() {
	sendEvent(name: "door", value: "opening")
    runIn(2, finishOpening)
}

def close() {
    sendEvent(name: "door", value: "closing")
	runIn(2, finishClosing)
}

def on() {
	sendEvent(name: "door", value: "opening")
    runIn(2, finishOpening)
}

def off() {
    sendEvent(name: "door", value: "closing")
	runIn(2, finishClosing)
}

def finishOpening() {
    sendEvent(name: "door", value: "open")
    sendEvent(name: "contact", value: "open")
    sendEvent(name: "switch", value: "on")

}

def finishClosing() {
    sendEvent(name: "door", value: "closed")
    sendEvent(name: "contact", value: "closed")
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
	log.trace "Executing 'initialize'"

    sendEvent(name: "door", value: "closed")
    sendEvent(name: "contact", value: "closed")
    sendEvent(name: "switch", value: "off")
    
    sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
}