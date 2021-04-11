/**
 *  VIRTUAL Motion Sensor and Door
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
	definition (name: "VIRTUAL Motion Sensor and Door", namespace: "andersonwembleyposavatz", author: "Anderson W Posavatz", ocfDeviceType: "x.com.st.d.sensor.motion", runLocally: true, minHubCoreVersion: '000.021.00001', executeCommandsLocally: true, mnmn: "SmartThings",) {
 		capability "Actuator"
		capability "Door Control"
		capability "Contact Sensor"
		capability "Sensor"
		capability "Motion Sensor"
		capability "Health Check"
	}

	simulator {
		
	}

	tiles {
		standardTile("toggle", "device.door", width: 2, height: 2) {
			state("closed", label:'${name}', action:"door control.open", icon:"st.motion.motion.inactive", backgroundColor:"#00A0DC", nextState:"opening")
			state("open", label:'${name}', action:"door control.close", icon:"st.motion.motion.active", backgroundColor:"#e86d13", nextState:"closing")
			state("opening", label:'${name}', icon:"st.motion.motion.inactive", backgroundColor:"#e86d13")
			state("closing", label:'${name}', icon:"st.motion.motion.active", backgroundColor:"#00A0DC")
			
		}
		standardTile("active", "device.motion", inactiveLabel: false, decoration: "flat") {
			state "default", label:'Motion', action:"active", icon:"st.motion.motion.active"
		}
		standardTile("inactive", "device.motion", inactiveLabel: false, decoration: "flat") {
			state "default", label:'No Motion', action:"inactive", icon:"st.motion.motion.inactive"
		}

		valueTile("statesLabel", "device.states", width: 6, height: 1, decoration: "flat") {
			state "default", label: "State Events:"	
		}

		main "toggle"
		details(["toggle", "motion", "open", "close", "on", "off", "active", "inactive"])
	}

}

def parse(description) {
	
}

def open() {
	sendEvent(name: "door", value: "opening")
    runIn(3, finishOpening)
}

def close() {
    sendEvent(name: "door", value: "closing")
	runIn(3, finishClosing)
}

def on() {
	sendEvent(name: "door", value: "opening")
    runIn(3, finishOpening)
}

def off() {
    sendEvent(name: "door", value: "closing")
	runIn(3, finishClosing)
}

def active() {
	sendEvent(name: "door", value: "opening")
         runIn(3, finishOpening)
}

def inactive() {
    sendEvent(name: "door", value: "closing")
	runIn(3, finishClosing)

}

def finishOpening() {
    sendEvent(name: "door", value: "open")
    sendEvent(name: "contact", value: "open")
    sendEvent(name: "motion", value: "active")
}

def finishClosing() {
    sendEvent(name: "door", value: "closed")
    sendEvent(name: "contact", value: "closed")
    sendEvent(name: "motion", value: "inactive")
    }

def installed() {
	initialize()
}

def updated() {
	initialize()
}

def initialize() {
    sendEvent(name: "door", value: "closed")
    sendEvent(name: "contact", value: "closed")
    sendEvent(name: "motion", value: "inactive")

	sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
}