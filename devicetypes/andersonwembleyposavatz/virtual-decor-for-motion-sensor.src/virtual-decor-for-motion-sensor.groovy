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
	definition (name: "VIRTUAL DECOR for Motion Sensor", namespace: "andersonwembleyposavatz", author: "Anderson W Posavatz", vid: "generic-motion", runLocally: true , executeCommandsLocally: true , ocfDeviceType: "x.com.st.d.sensor.motion", 
 ) {
		capability "Actuator"
		capability "Battery"
		capability "Motion Sensor"
		capability "Sensor"
		capability "Health Check"
		capability "Switch"
	}

	simulator {
		
	}

	tiles {
		standardTile("toggle", "device.motion", width: 3, height: 3) {
			state("off", label:'${name}', action:"on", icon:"st.motion.motion.inactive", backgroundColor:"#00A0DC", nextState:"turningOn")
			state("on", label:'${name}', action:"off", icon:"st.motion.motion.active", backgroundColor:"#ffffff", nextState:"turningOff")
			state("turningOn", label:'${name}', icon:"st.motion.motion.inactive", backgroundColor:"#ffffff")
			state("turningOff", label:'${name}', icon:"st.motion.motion.active", backgroundColor:"#00A0DC")
			
		}
		standardTile("active", "device.motion", inactiveLabel: false, decoration: "flat") {
			state "default", label:'${name}', icon:"st.motion.motion.active", backgroundColor:"#00A0DC", action:"inactive"
		}
		standardTile("inactive", "device.motion", inactiveLabel: false, decoration: "flat") {
			state "default", label:'${name}', icon:"st.motion.motion.inactive", backgroundColor:"#ffffff", action:"active"
		}
        
        standardTile("battery", "device.battery", inactiveLabel: false, decoration: "flat") {
             state "battery", label: "74", unit: "%"
       
       }

		main "motion"
		details "motion", "active", "inactive", "battery"
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

def active() {
	sendEvent(name: "switch", value: "turningOn")
    runIn(0, finishOpening)
}

def inactive() {
    sendEvent(name: "switch", value: "turningOff")
	runIn(0, finishClosing)
}

def finishOpening() {
    sendEvent(name: "switch", value: "on")
    sendEvent(name: "motion", value: "active", isStateChange: true)
}

def finishClosing() {
    sendEvent(name: "switch", value: "off")
    sendEvent(name: "motion", value: "inactive", isStateChange: true)
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
    sendEvent(name: "motion", value: "active")
    sendEvent(name: "battery", value: device.currentValue("battery") ?: 62)

    
        sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
}