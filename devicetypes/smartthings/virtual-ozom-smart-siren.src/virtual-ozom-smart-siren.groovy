/**
 *  Ozom Smart Siren
 *
 *  Copyright 2018 Samsung SRBR
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
 */metadata {
	definition(name: "VIRTUAL Ozom Smart Siren", namespace: "smartthings", author: "SmartThings", mnmn: "SmartThings", vid: "generic-siren-2", ocfDeviceType: "x.com.st.d.siren",  runLocally: true, minHubCoreVersion: '000.017.0012', executeCommandsLocally: true) {
		capability "Actuator"
		capability "Alarm"
		capability "Health Check"
		capability "Sensor"
		capability "Tamper Alert"
		capability "Switch"

	}
	simulator {

    
    }

	tiles(scale: 2) {
		multiAttributeTile(name:"tamper", type: "generic", width: 6, height: 4, canChangeIcon: true){
			tileAttribute ("device.tamper", key: "PRIMARY_CONTROL") {
				attributeState "clear", label:'tamper clear', action:'alarm.siren', icon:"st.security.alarm.clear", backgroundColor:"#ffffff"
				attributeState "detected", label:'tampered', action:'alarm.off', icon:"st.alarm.alarm.alarm", backgroundColor:"#e86d13"
            }
            }
         standardTile("alarm", "device.alarm", width: 2, height: 2) {
			state "off", label:'tamper clear', action:'alarm.siren', icon:"st.security.alarm.clear", backgroundColor:"#ffffff"
			state "siren", label:'tampered', action:'alarm.off', icon:"st.alarm.alarm.alarm", backgroundColor:"#e86d13"
		}
	        
		standardTile("detected", "device.tamper", inactiveLabel: false, width: 2, height: 2, decoration: "flat") {
			state "default", label:'tampered', icon:"st.alarm.alarm.alarm", backgroundColor:"#e86d13"
        }
			
		standardTile("clear", "device.tamper", inactiveLabel: false, width: 2, height: 2, decoration: "flat") {
            state "default", label:'tamper clear', icon:"st.security.alarm.clear", backgroundColor:"#ffffff"
		}
	        
		valueTile("tamper", "device.tamper", inactiveLabel: false, width: 2, height: 2, decoration: "flat") {
			state "detected", label:'tampered', icon:"st.alarm.alarm.alarm", backgroundColor:"#e86d13"
			state "clear", label:'tamper clear', icon:"st.security.alarm.clear", backgroundColor:"#ffffff"
		}

		standardTile("refresh", "device.tamper", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
			state "default", label: '', action: "refresh.refresh", icon: "st.secondary.refresh"
		
        }
        
		main "tamper"
		details(["tamper", "alarm", "refresh"])
	}
}

def turnOffAlarmTile() {
	log.info "turn off alarm tile ${}"
	sendEvent(name: "alarm", value: "off")
	sendEvent(name: "switch", value: "off")
	sendEvent(name: "tamper", value: "clear")
}

def turnOnAlarmTile() {
	log.info "turn on alarm tile ${}"
	sendEvent(name: "switch", value: "on")
	sendEvent(name: "alarm", value: "siren")
	sendEvent(name: "tamper", value: "detected")

}

def installed() {
	off()
   	sendEvent(name: "tamper", value: "clear")

}

def parse(String description) {
	log.info "Parsing '${description}'"

}

def both() {
	log.info "both()"
	sendEvent(name: "switch", value: "on")
	sendEvent(name: "alarm", value: "both")
	sendEvent(name: "tamper", value: "detected")}

def siren() {
	log.info "siren()"
	sendEvent(name: "switch", value: "on")
	sendEvent(name: "alarm", value: "siren")
	sendEvent(name: "tamper", value: "detected")

}
def strobe() {
	log.info "strobe()"
	sendEvent(name: "switch", value: "on")
	sendEvent(name: "alarm", value: "strobe")
	sendEvent(name: "tamper", value: "detected")}

def on() {
	log.info "on()"
	sendEvent(name: "alarm", value: "siren")
	sendEvent(name: "switch", value: "on")
	sendEvent(name: "tamper", value: "detected")


}

def off() {
	log.info "off()"
	sendEvent(name: "alarm", value: "off")
	sendEvent(name: "switch", value: "off")
	sendEvent(name: "tamper", value: "clear")

}
