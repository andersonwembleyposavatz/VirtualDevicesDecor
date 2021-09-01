/**
 *
 *	Copyright 2019 SmartThings
 *
 *	Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *	in compliance with the License. You may obtain a copy of the License at:
 *
 *		http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *	on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *	for the specific language governing permissions and limitations under the License.
 */

metadata {
    definition(name: "VIRTUAL Rooms Beautiful Curtain", namespace: "Rooms Beautiful", author: "Alex Feng", ocfDeviceType: "oic.d.blind", mnmn: "SmartThings", vid: "generic-shade-2", runLocally: true , executeCommandsLocally: true) {
        capability "Actuator"
        capability "Contact Sensor"
        capability "Battery"
        capability "Refresh"
        capability "Sensor"
        capability "Health Check"
        capability "Window Shade"
        capability "Switch"


    }

    preferences {
    }

    tiles(scale: 2) {
        multiAttributeTile(name: "windowShade", type: "generic", width: 6, height: 4) {
            tileAttribute("device.windowShade", key: "PRIMARY_CONTROL") {
                attributeState "open", label: '${name}', action: "close", icon: "http://www.ezex.co.kr/img/st/window_open.png", backgroundColor: "#00A0DC", nextState: "closing"
                attributeState "closed", label: '${name}', action: "open", icon: "http://www.ezex.co.kr/img/st/window_close.png", backgroundColor: "#ffffff", nextState: "opening"
                attributeState "partially open", label: '${name}', action: "pause", icon: "http://www.ezex.co.kr/img/st/window_open.png", backgroundColor: "#d45614", nextState: "closing"
                attributeState "opening", label: '${name}', action: "close", icon: "http://www.ezex.co.kr/img/st/window_open.png", backgroundColor: "#00A0DC", nextState: "closing"
                attributeState "closing", label: '${name}', action: "open", icon: "http://www.ezex.co.kr/img/st/window_close.png", backgroundColor: "#ffffff", nextState: "opening"
            }
            tileAttribute("device.contact", key: "SECONDARY_CONTROL") {
                attributeState "closed", icon: "st.contact.contact.closed", label: ""
                attributeState "open", icon: "st.contact.contact.open", label: ""
                         
            }
      }

		standardTile("Open", "device.windowShade", width: 2, height: 2, decoration: "flat") {
			state "default", label: "${name}", action:"open", icon:"st.Home.home2"
		}
		standardTile("Close", "device.windowShade", width: 2, height: 2, decoration: "flat") {
			state "default", label: "${name}", action:"close", icon:"st.Home.home2"
		}
		standardTile("Pause", "device.windowShade", width: 2, height: 2, decoration: "flat") {
			state "default", label: "${name}", action:"pause", icon:"st.Home.home2"
	
		}

        main "windowShade", "contact"
        details "toggle", "windowShade", "contact"
    }
}

def off() {
        sendEvent(name: "windowShade", value: "closed")
        runIn(1, finishClosing)

}

def on() {
        sendEvent(name: "windowShade", value: "open")
        runIn(1, finishOpening)

}

def close() {
        sendEvent(name: "windowShade", value: "closed")
        runIn(1, finishClosing)

}

def open() {
        sendEvent(name: "windowShade", value: "open")
        runIn(1, finishOpening)

}

def pause() {
        sendEvent(name: "windowShade", value: "partially open")
        runIn(finishOpening)

}

def finishOpening() {
    sendEvent(name: "contact", value: "open")

}

def finishClosing() {
    sendEvent(name: "contact", value: "closed")

}

def installed() {
	log.trace "Executing 'installed'"
    initialize()


    sendEvent(name: "supportedWindowShadeCommands", value: JsonOutput.toJson(["open", "close", "pause"]), displayed: false)
    sendEvent(name: "battery", value: 100)
    sendEvent(name: "contact", value: (["open", "close"]))
}

def updated() {
	log.trace "Executing 'updated'"
	initialize()
}

def initialize() {
	log.trace "Executing 'initialize'"
    
    sendEvent(name: "windowShade", value: "closed")
    sendEvent(name: "contact", value: "closed")
    sendEvent(name: "switch", value: "off")
    sendEvent(name: "battery", value: 100)


    
    sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)

}
