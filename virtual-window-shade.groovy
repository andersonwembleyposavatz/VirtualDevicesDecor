/**
 *  VIRTUAL Window Shade
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
 */
metadata {
	definition (name: "VIRTUAL Window Shade", namespace: "andersonwembleyposavatz", author: "Anderson W Posavatz", runLocally: true, ocfDeviceType: "oic.d.blind", minHubCoreVersion: '000.021.00001', executeCommandsLocally: true) {
		capability "Actuator"
		capability "Sensor"
		capability "Refresh"
		capability "Switch"
		capability "Window Shade"
        capability "Health Check"
        
       
    }

	preferences {}

	tiles(scale: 2) {
		multiAttributeTile(name:"windowShade", type: "generic", width: 6, height: 4, canChangeIcon: true){
			tileAttribute ("device.windowShade", key: "PRIMARY_CONTROL") {
				attributeState "open", label:'${name}', action:"close", icon:"st.shades.shade-open", backgroundColor:"#79b821", nextState:"closing"
				attributeState "closed", label:'${name}', action:"open", icon:"st.shades.shade-closed", backgroundColor:"#ffffff", nextState:"opening", defaultState: true
				attributeState "opening", label:'${name}', action:"closed", icon:"st.shades.shade-opening", backgroundColor:"#79b821", nextState:"openign"
				attributeState "closing", label:'${name}', action:"open", icon:"st.shades.shade-closing", backgroundColor:"#ffffff", nextState:"closing"
			}
		}

		standardTile("Open", "device.windowShade", inactiveLabel: false,  width: 2, height: 2, decoration: "flat") {
			state "default", label: "Open", action:"open", icon:"st.shades.shade-open",  backgroundColor: "#ffffff"
		}
		standardTile("Closed", "device.windowShade", , inactiveLabel: false, width: 2, height: 2, decoration: "flat") {
			state "default", label: "Closed", action:"closed", icon:"st.shades.shade-closed",  backgroundColor: "#ffffff"
		}

		main "windowShade"
		details "windowShade", "open", "closed"
	}
}

def installed() {
	initialize()
}

def updated() {
	initialize()
}

def initialize() {
	sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
}

def parse(description) {

}

def open() {
	sendEvent(name: "windowShade", value: "open", isStateChange: true)
}

def closed() {
	sendEvent(name: "windowShade", value: "closed", isStateChange: true)
}

def on() {
	sendEvent(name: "windowShade", value: "open",)
}

def off() {
	sendEvent(name: "windowShade", value: "closed",)
}
