/**
 *  Copyright 2017 Tomas Axerot
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
	definition (name: "VIRTUAL PIR Motion Sensor", namespace: "andersonwembleyposavatz", author: "Anderson Wembley Posavatz", vid: "generic-motion", ocfDeviceType: "x.com.st.d.sensor.motion") {
		capability "Motion Sensor"
		capability "Sensor"
		capability "Actuator"
		capability "Health Check"
		
        capability "Switch"
        
		command "active"
		command "inactive"
	}

	simulator {
		status "active": "motion:active"
		status "inactive": "motion:inactive"
		}

	tiles {
		standardTile("motion", "device.motion", width: 2, height: 2) {
			state("inactive", label:'no motion', icon:"st.motion.motion.inactive", backgroundColor:"#cccccc", action: "active")
			state("active", label:'motion', icon:"st.motion.motion.active", backgroundColor:"#00A0DC", action: "inactive")
		}
		main "motion"
		details "motion"
	}
}

def parse(String description) {
	log.trace "parse: $description"
	def pair = description.split(":")
	createEvent(name: pair[0].trim(), value: pair[1].trim())
}

def active() {
	log.trace "active"
	sendEvent(name: "motion", value: "active")
}

def inactive() {
	log.trace "inactive"
    sendEvent(name: "motion", value: "inactive")

}

def on() {
	log.trace "on"
	sendEvent(name: "motion", value: "active")
	sendEvent(name: "switch", value: "on")

}

def off() {
	log.trace "off"
    sendEvent(name: "switch", value: "off")
    sendEvent(name: "motion", value: "inactive")
}

def installed() {
	initialize()
}

def updated() {
	initialize()
}

private initialize() {

	sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
}