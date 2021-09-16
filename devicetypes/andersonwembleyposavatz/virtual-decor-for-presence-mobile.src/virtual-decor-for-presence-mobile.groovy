/**
 *  VIRTUAL DECOR for Motion Sensor
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
	definition (name: "VIRTUAL DECOR for Presence Mobile", namespace: "andersonwembleyposavatz", author: "Anderson W Posavatz", vid: "generic-presence", runLocally: true , executeCommandsLocally: true , ocfDeviceType: "x.com.st.d.sensor.presence", 
 ) {
		capability "Actuator"
		capability "Battery"
		capability "Presence Sensor"
		capability "Sensor"
		capability "Health Check"
		capability "Switch"
	}

	simulator {
		
	}

	tiles {
		standardTile("toggle", "device.presence", width: 3, height: 3) {
			state("off", label:'${name}', action:"on", icon:"st.presence.tile.mobile-not-present", backgroundColor:"#53a7c0", nextState:"turningOn")
			state("on", label:'${name}', action:"off", icon:"st.presence.tile.mobile-present", backgroundColor:"#ffffff", nextState:"turningOff")
			state("turningOn", label:'${name}', icon:"st.presence.tile.mobile-not-present", backgroundColor:"#ffffff")
			state("turningOff", label:'${name}', icon:"st.presence.tile.mobile-present", backgroundColor:"#53a7c0")
			
		}
		standardTile("Present", "device.presence", inactiveLabel: false, decoration: "flat") {
			state "default", label:'Present', icon:"st.presence.tile.mobile-present", backgroundColor:"#53a7c0", action:"Away"
		}
		standardTile("Away", "device.presence", inactiveLabel: false, decoration: "flat") {
			state "default", label:'No Present', icon:"st.presence.tile.mobile-not-present", backgroundColor:"#ffffff", action:"Present"
		}
        
        standardTile("battery", "device.battery", inactiveLabel: false, decoration: "flat") {
             state "battery", label: "55", unit: "%"
       
       }

		main "presence"
		details "presence", "away", "present", "battery"
	}
}

def parse(String description) {
	log.trace "parse($description)"
}

def on() {
	sendEvent(name: "switch", value: "turningOn")
    runIn(0, finishOpening)
}

def off() {
    sendEvent(name: "switch", value: "turningOff")
	runIn(0, finishClosing)
}

def away() {
	sendEvent(name: "switch", value: "turningOn")
    runIn(0, finishOpening)
}

def present() {
    sendEvent(name: "switch", value: "turningOff")
	runIn(0, finishClosing)
}

def finishOpening() {
    sendEvent(name: "switch", value: "on")
    sendEvent(name: "presence", value: "present", isStateChange: true)
}

def finishClosing() {
    sendEvent(name: "switch", value: "off")
    sendEvent(name: "presence", value: "away", isStateChange: true)
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

    sendEvent(name: "switch", value: "on")
    sendEvent(name: "presence", value: "present")
    sendEvent(name: "battery", value: device.currentValue("battery") ?: 55)

    
        sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
}