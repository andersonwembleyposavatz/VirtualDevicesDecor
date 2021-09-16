/**
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
	definition (name: "VIRTUAL DECOR Water Sensor", namespace: "smartthings/testing", author: "SmartThings") {
		capability "Actuator"
		capability "Water Sensor"
		capability "Battery"
		capability "Sensor"
		capability "Health Check"
		capability "Switch"

        
	}

	simulator {
		status "wet": "water:wet"
		status "dry": "water:dry"
	}

	tiles {
        multiAttributeTile(name:"water", type: "generic", width: 6, height: 4, canChangeIcon: true){
            tileAttribute ("device.water", key: "PRIMARY_CONTROL") {
                attributeState "dry", label:'${name}', action:"dry", icon:"st.alarm.water.wet", backgroundColor:"#00A0DC"
                attributeState "wet", label:'${name}', action:"wet", icon:"st.alarm.water.dry", backgroundColor:"#FFFFFF", defaultState: true
                }
		}
        
		standardTile("wet", "device.water", inactiveLabel: false, decoration: "flat") {
			state "default", label:'Wet', action:"wet", icon: "st.alarm.water.wet"
		}         
		standardTile("dry", "device.water", inactiveLabel: false, decoration: "flat") {
			state "default", label:'Dry', action:"dry", icon: "st.alarm.water.dry"
		}  
        
		main "water"
		details(["water","wet","dry"])
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
   	    sendEvent(name: "water", value: "wet")


	sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
}

def parse(String description) {
	def pair = description.split(":")
	createEvent(name: pair[0].trim(), value: pair[1].trim())
}

def off() {
	    sendEvent(name: "water", value: "dry")
	    sendEvent(name: "switch", value: "off")
}

def on() {
	    sendEvent(name: "water", value: "wet")
	    sendEvent(name: "switch", value: "on")

}

def wet() {
	log.trace "wet()"
	sendEvent(name: "water", value: "wet")

}

def dry() {
	log.trace "dry()"
	sendEvent(name: "water", value: "dry")
}