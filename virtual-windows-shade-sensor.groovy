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
	definition (name: "VIRTUAL Windows Shade Sensor", namespace: "andersonwembleyposavatz", author: "Anderson W Posavatz", runLocally: true , executeCommandsLocally: true , ocfDeviceType: "oic.d.blind", mnmn: "SmartThings", vid: "generic-shade-2") {
		capability "Actuator"
		capability "Contact Sensor"
		capability "Window Shade"
		capability "Sensor"
		capability "Health Check"
        
        
        command "open"
		command "closed"
	}

	simulator {
		
	}

	tiles {
		standardTile("generic", "device.windowShade", width: 2, height: 2) {
			state("closed", label:'${name}', action:"open", icon:"st.shades.shade-closed", backgroundColor:"#00A0DC", nextState:"opening")
			state("open", label:'${name}', action:"close", icon:"st.shades.shade-open", backgroundColor:"#79b821", nextState:"closing")
			state("opening", label:'${name}', icon:"st.shades.shade-open", backgroundColor:"#79b821")
			state("closing", label:'${name}', icon:"st.shades.shade-closed", backgroundColor:"#00A0DC")
			
		}
		standardTile("open", "device.windowShade", inactiveLabel: false, decoration: "flat") {
			state "default", label:'open', action:"open", icon:"st.shades.shade-opening"
		}
		standardTile("closed", "device.windowShade", inactiveLabel: false, decoration: "flat") {
			state "default", label:'close', action:"close", icon:"st.shades.shade-closing"
		}

		main "windowShade"
		details(["windowShade",  "open", "close", "closed"])
	}
}

def parse(String description) {
	log.trace "parse($description)"
}

def open() {
	sendEvent(name: "windowShade", value: "opening")
    runIn(3, finishOpening)
}

def close() {
    sendEvent(name: "windowShade", value: "closing")
	runIn(3, finishClosing)
}

def finishOpening() {
    sendEvent(name: "windowShade", value: "open")
    sendEvent(name: "contact", value: "open")
}

def finishClosing() {
    sendEvent(name: "windowShade", value: "closed")
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

private initialize() {
	log.trace "Executing 'initialize'"

	sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
}