/**
 *  Copyright 2017 SmartThings
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *	  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *  An enhanced virtual lock that allows for testing failure modes
 *  Author: SmartThings
 *  Date: 2017-08-07
 *
 */
metadata {
	definition (name: "VIRTUAL DECOR Generic Lock", namespace: "smartthings", author: "SmartThings", minHubCoreVersion: '000.017.0012', executeCommandsLocally: true, ) {
		capability "Actuator"
        capability "Sensor"
        capability "Health Check"
        capability "Switch"
        capability "Lock"
        capability "Battery"
        capability "Refresh"

	}

	simulator {
		status "locked": "lock:locked"
		status "unlocked": "lock:unlocked"

		reply "lock": "lock:locked"
		reply "unlock": "lock:unlocked"
	}

	tiles {
		standardTile("lock", "device.lock", width: 2, height: 2) {
			state "unlocked", label:'unlocked', action:"lock.lock", icon:"st.locks.lock.unlocked", backgroundColor:"#ffffff"
			state "locked", label:'locked', action:"lock.unlock", icon:"st.locks.lock.locked", backgroundColor:"#00A0DC"
		}
		standardTile("lock", "device.lock", inactiveLabel: false, decoration: "flat") {
			state "default", label:'lock', action:"lock.lock", icon:"st.locks.lock.locked"
		}
		standardTile("unlock", "device.lock", inactiveLabel: false, decoration: "flat") {
			state "default", label:'unlock', action:"lock.unlock", icon:"st.locks.lock.unlocked"
		}

		main "lock"
		details(["toggle", "lock", "unlock", "refresh"])
	}
}

def parse(String description) {
	def pair = description.split(":")
	createEvent(name: pair[0].trim(), value: pair[1].trim())
}

def lock() {
	"lock"
}

def unlock() {
	"unlock"

}

def off() {
        sendEvent(name: "lock", value: "unlock")

}

def on() {
        sendEvent(name: "lock", value: "lock")
       
}

def installed() {
	initialize()
}

def updated() {
	initialize()
}

private initialize() {

    sendEvent(name: "lock", value: "unlock")
    
    sendEvent(name: "battery", value: device.currentValue("battery") ?: 74)

	sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
    }