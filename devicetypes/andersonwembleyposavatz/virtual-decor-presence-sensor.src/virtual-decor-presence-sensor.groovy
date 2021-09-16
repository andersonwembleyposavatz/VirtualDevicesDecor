/**
 *  VIRTUAL DECOR Presence Sensor
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
//Release History
//			Initial Release


metadata {
        definition (name: "VIRTUAL DECOR Presence Sensor", namespace: "andersonwembleyposavatz", author: "Anderson Wembley Posavatz") {
        capability "Switch"
        capability "Refresh"
        capability "Presence Sensor"
	    capability "Sensor"
        
	command "arrived"
	command "departed"
    }

	// simulator metadata
	simulator {
	}

	// UI tile definitions
	tiles {
		standardTile("button", "device.switch", width: 2, height: 2, canChangeIcon: false,  canChangeBackground: true) {
			state "off", label: 'Away', action: "switch.on", icon: "st.Kids.kid10", backgroundColor: "#ffffff", nextState: "on"
			state "on", label: 'Present', action: "switch.off", icon: "st.Kids.kid10", backgroundColor: "#53a7c0", nextState: "off"
		}
		standardTile("refresh", "device.switch", inactiveLabel: false, decoration: "flat") {
			state "default", label:'', action:"refresh.refresh", icon:"st.secondary.refresh"
		}
        standardTile("presence", "device.presence", width: 1, height: 1, canChangeBackground: true) {
			state("present", labelIcon:"st.presence.tile.mobile-present", backgroundColor:"#53a7c0")
			state("not present", labelIcon:"st.presence.tile.mobile-not-present", backgroundColor:"#ffffff")
		}
		main (["button", "presence"])
		details(["button", "presence", "refresh"])
	}
}

def parse(String description) {
	def pair = description.split(":")
	createEvent(name: pair[0].trim(), value: pair[1].trim())
}

def arrived() {
    on()
}


def departed() {
    off()
}

def on() {
    sendEvent(name: "switch", value: "on")
    sendEvent(name: "presence", value: "present")

}

def off() {
    sendEvent(name: "switch", value: "off")
    sendEvent(name: "presence", value: "not present")

}