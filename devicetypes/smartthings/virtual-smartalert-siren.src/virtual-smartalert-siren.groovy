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
 *  SmartAlert Siren
 *
 *  Author: SmartThings
 *  Date: 2013-03-05
 */
metadata {
	definition (name: "VIRTUAL SmartAlert Siren", namespace: "smartthings", author: "SmartThings", ocfDeviceType: "x.com.st.d.siren", runLocally: true, minHubCoreVersion: '000.017.0012', executeCommandsLocally: true) {
		capability "Actuator"
		capability "Switch"
		capability "Sensor"
		capability "Alarm"
		capability "Tamper Alert"
		capability "Health Check"

		command "both"
		command "strobe"
		command "siren"
		command "on"
		command "off"


	}

	simulator {

	}

	tiles(scale: 2) {
		multiAttributeTile(name:"alarm", type: "generic", width: 6, height: 4){
			tileAttribute ("device.alarm", key: "PRIMARY_CONTROL") {
				attributeState "off", label:'off', action:'alarm.strobe', icon:"st.alarm.alarm.alarm", backgroundColor:"#ffffff"
				attributeState "strobe", label:'alarm!', action:'alarm.off', icon:"st.alarm.alarm.alarm", backgroundColor:"#e86d13"
				attributeState "siren", label:'alarm!', action:'alarm.off', icon:"st.alarm.alarm.alarm", backgroundColor:"#e86d13"
				attributeState "both", label:'alarm!', action:'alarm.off', icon:"st.alarm.alarm.alarm", backgroundColor:"#e86d13"
			}
		}
		standardTile("strobe", "device.alarm", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
			state "off", label:'', action:"alarm.strobe", icon:"st.secondary.strobe", backgroundColor:"#cccccc"
			state "siren", label:'', action:"alarm.strobe", icon:"st.secondary.strobe", backgroundColor:"#cccccc"
			state "strobe", label:'', action:'alarm.strobe', icon:"st.secondary.strobe", backgroundColor:"#e86d13"
			state "both", label:'', action:'alarm.strobe', icon:"st.secondary.strobe", backgroundColor:"#e86d13"
		}
		standardTile("siren", "device.alarm", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
			state "off", label:'', action:"alarm.siren", icon:"st.secondary.siren", backgroundColor:"#cccccc"
			state "strobe", label:'', action:"alarm.siren", icon:"st.secondary.siren", backgroundColor:"#cccccc"
			state "siren", label:'', action:'alarm.siren', icon:"st.secondary.siren", backgroundColor:"#e86d13"
			state "both", label:'', action:'alarm.siren', icon:"st.secondary.siren", backgroundColor:"#e86d13"
		}
	        
		valueTile("tamper", "device.tamper", inactiveLabel: false, width: 2, height: 2, decoration: "flat") {
			state "detected", label:'tampered'
			state "clear", label:'tamper clear'
		}
        
		main "tamper"
		details(["alarm","strobe","siren","both","off","on", "tamper"])
	}
}

def parse(String description) {
	log.debug "parse($description)"
	
}

def on() {
	sendEvent(name: "switch", value: "on")
    sendEvent(name: "tamper", value: "detected")
    sendEvent(name: "alarm", value: "siren")


}

def off() {
	sendEvent(name: "switch", value: "off")
    sendEvent(name: "tamper", value: "clear")
        sendEvent(name: "alarm", value: "off")



}

def test() {
	sendEvent(name: "switch", value: "on")
    sendEvent(name: "tamper", value: "detected")
    sendEvent(name: "alarm", value: "both")

	
}

def strobe() {
	sendEvent(name: "switch", value: "on")
    sendEvent(name: "tamper", value: "detected")
    sendEvent(name: "tamper", value: "strobe")

}

def siren() {
	sendEvent(name: "switch", value: "on")
    sendEvent(name: "tamper", value: "detected")
    sendEvent(name: "tamper", value: "siren")
	
}

def both() {
	sendEvent(name: "", value: "on")
    sendEvent(name: "tamper", value: "detected")
    sendEvent(name: "tamper", value: "both")

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
	sendEvent(name: "switch", value: "off")
    sendEvent(name: "alarm", value: "off")
    sendEvent(name: "tamper", value: "clear")

    
    sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
}