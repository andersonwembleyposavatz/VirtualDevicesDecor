/**
 *  Z-Wave Garage Door Opener
 *
 *  Copyright 2014 SmartThings
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
	definition (name: "VIRTUAL Simulated Window Shade Opener", namespace: "smartthings/testing", author: "SmartThings", runLocally: true , executeCommandsLocally: true, ocfDeviceType: "oic.d.blind") {
		capability "Actuator"
		capability "Window Shade"
		capability "Sensor"
		capability "Health Check"
        
        command "open"
        command "close"
	}

	simulator {
		
	}

	tiles {
		standardTile("generic", "device.windowShade", width: 2, height: 2) {
			state("closed", label:'${name}', action:"open", icon:"st.shades.shade-closed", backgroundColor:"#00A0DC", nextState:"opening")
			state("open", label:'${name}', action:"close", icon:"st.shades.shade-open", backgroundColor:"#79b821", nextState:"closing")
			state("opening", label:'${name}', icon:"st.shades.shade-opening", backgroundColor:"#79b821")
			state("closing", label:'${name}', icon:"st.shades.shade-closing", backgroundColor:"#00A0DC")
			
		}
		standardTile("open", "device.windowShade", inactiveLabel: false, decoration: "flat") {
			state "open", label:'${name}', action:"close", icon:"st.shades.shade-closing"
		}
		standardTile("close", "device.windowShade", inactiveLabel: false, decoration: "flat") {
			state "close", label:'${name}', action:"open", icon:"st.shades.shade-opening"
		}

		main "windowShade"
		details(["windowShade"])
	}
}

def parse(String description) {
	log.trace "parse($description)"
}

def open() {
	sendEvent(name: "windowShade", value: "opening")
    runIn(1, finishOpening)
}

def close() {
    sendEvent(name: "windowShade", value: "closing")
	runIn(1, finishClosing)
}

def finishOpening() {
    sendEvent(name: "windowShade", value: "open")
}

def finishClosing() {
    sendEvent(name: "windowShade", value: "close")
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
    
    sendEvent(name: "windowShade", value: "open")

	sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
}