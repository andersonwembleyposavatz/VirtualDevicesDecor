/**
 *  Simulated Alarm
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
	definition (name: "VIRTUAL Switch for Alarm", namespace: "andersonwembleyposavatz", author: "Anderson Wembley Posavatz", vid: "generic-siren", runLocally: true , executeCommandsLocally: true) {
		capability "Alarm"
		capability "Sensor"
		capability "Actuator"
		capability "Switch"
		capability "Health Check"
	}

	simulator {}

	
	tiles {
		standardTile("alarm", "device.alarm", width: 2, height: 2) {
			state "off", label:'off', action:'alarm.both', icon:"st.alarm.alarm.alarm", backgroundColor:"#ffffff"
			state "strobe", label:'strobe!', action:'alarm.off', icon:"st.alarm.alarm.alarm", backgroundColor:"#e86d13"
			state "siren", label:'siren!', action:'alarm.off', icon:"st.alarm.alarm.alarm", backgroundColor:"#e86d13"
			state "both", label:'alarm!', action:'alarm.off', icon:"st.alarm.alarm.alarm", backgroundColor:"#e86d13"
		}
		standardTile("strobe", "device.alarm", inactiveLabel: false, decoration: "flat") {
			state "default", label:'', action:"alarm.strobe", icon:"st.secondary.strobe", backgroundColor:"#cccccc"
		}
		
		standardTile("siren", "device.alarm", inactiveLabel: false, decoration: "flat") {
			state "default", label:'', action:"alarm.siren", icon:"st.secondary.siren", backgroundColor:"#cccccc"
		}       
		standardTile("off", "device.alarm", inactiveLabel: false, decoration: "flat") {
			state "default", label:'', action:"alarm.off", icon:"st.secondary.off"
		}
		main "alarm"
		details(["alarm","strobe","siren","test","on","off","switch"])
	}
}
def parse(String description) {
	log.trace "parse($description)"

}

def on() {
	sendEvent(name: "alarm", value: "strobe")
	sendEvent(name: "switch", value: "on")
	sendEvent(name: "alarm", value: "siren")
}

def siren() {
	sendEvent(name: "alarm", value: "siren")
}

def both() {
	sendEvent(name: "alarm", value: "both")
}

def off() {
	sendEvent(name: "alarm", value: "off")
	sendEvent(name: "switch", value: "off")

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

	sendEvent(name: "alarm", value: "off")
    sendEvent(name: "switch", value: "off")
    
    sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
}