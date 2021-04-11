/**
 *  VIRTUAL Opening for Shade
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
	definition (name: "VIRTUAL Opening for Shade", namespace: "andersonwembleyposavatz", author: "Anderson W Posavatz") {
		capability "Actuator"
		capability "Door Control"
		capability "Contact Sensor"
		capability "Refresh"
		capability "Sensor"
		capability "Health Check"
        
		capability "Window Shade"

	}

	simulator {
		
	}

	tiles {
        multiAttributeTile(name:"contact", type: "generic", width: 6, height: 4){
            tileAttribute ("device.shade", key: "PRIMARY_CONTROL") {
			state("closed", label:'${name}', action:"closed", icon:"st.shades.shade-closed", backgroundColor:"#ffffff", nextState:"opening")
			state("open", label:'${name}', action:"open", icon:"st.shades.shade-open", backgroundColor:"#79b821", nextState:"closing")
			state("opening", label:'${name}',icon:"st.shades.shade-closing", backgroundColor:"#ffffff")
			state("closing", label:'${name}', icon:"st.shades.shade-opening", backgroundColor:"#79b821")
			
		}
		standardTile("open", inactiveLabel: false, decoration: "flat") {
			state "default", label:'open', action:"open", icon:"st.shades.shade-opening"
		}
		standardTile("closed", inactiveLabel: false, decoration: "flat") {
			state "default", label:'closed', action:"closed", icon:"st.shades.shade-closing"
		}

		main "contact", "shade",
		details(["contact", "shade", "open", "closed"])
	}
}

def parse(description) {

}

def open() {
	sendEvent(name: "contact", value: "opening")
    runIn(6, finishOpening)
}

def close() {
    sendEvent(name: "contact", value: "closing")
	runIn(6, finishClosing)
}

def finishOpening() {
    sendEvent(name: "shade", value: "open")
    sendEvent(name: "contact", value: "open")
}

def finishClosing() {
    sendEvent(name: "shade", value: "closed")
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
}