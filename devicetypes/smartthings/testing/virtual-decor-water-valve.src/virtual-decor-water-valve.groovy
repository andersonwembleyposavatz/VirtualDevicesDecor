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
	definition (name: "VIRTUAL DECOR Water Valve", namespace: "smartthings/testing", author: "SmartThings",  runLocally: true , executeCommandsLocally: true ) {
		capability "Actuator"
		capability "Valve"
		capability "Sensor"
		capability "Health Check"
		capability "Switch"
        capability "Water Sensor"

        
	}

	// tile definitions
	tiles {
		standardTile("contact", "device.contact", width: 2, height: 2, canChangeIcon: true) {
			state "closed", label: '${name}', action: "valve.open", icon: "st.alarm.water.dry", backgroundColor: "#ffffff"
			state "open", label: '${name}', action: "valve.close", icon: "st.alarm.water.wet", backgroundColor: "#00A0DC"
		}
		standardTile("open", "device.water", inactiveLabel: false, decoration: "flat") {
			state "default", label:'Wet', action:"closed", icon: "st.alarm.water.wet"
		}         
		standardTile("closed", "device.contact", inactiveLabel: false, decoration: "flat") {
			state "default", label:'Dry', action:"open", icon: "st.alarm.water.dry"
		}  

		main "contact"
		details "contact","battery", "on", "off"
	}
}

def installed() {
	log.trace "Executing 'installed'"
	initialize()

}

def updated() {
	log.trace "Executing 'updated'"
	initialize()
}

private initialize() {
	log.trace "Executing 'initialize'"

        sendEvent(name: "battery", value: device.currentValue("battery") ?: 74)
	sendEvent(name: "contact", value: "open")

	sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
}


def open() {
	sendEvent(name: "contact", value: "open")
}

def close() {
	sendEvent(name: "contact", value: "closed")

}

def off() {
        sendEvent(name: "switch", value: off)
	sendEvent(name: "contact", value: "closed")
}

def on() {
        sendEvent(name: "switch", value: on)
	sendEvent(name: "contact", value: "open")

}