/**
 *  Copyright 2018, 2019 SmartThings
 *
 *  Provides a simulated window shade.
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
	definition (name: "VIRTUAL Window Shade DECOR", namespace: "smartthings/testing", author: "SmartThings",  runLocally: true , executeCommandsLocally: true , mnmn: "SmartThings", vid: "generic-window-shade") {
		capability "Actuator"
		capability "Window Shade"
		capability "Window Shade Preset"
		capability "Sensor"
		capability "Health Check"

	}

	preferences {
		section {
			input("actionDelay", "number",
				title: "Action Delay\n\nAn emulation for how long it takes the window shade to perform the requested action.",
				description: "In seconds (1-120; default if empty: 5 sec)",
				range: "1..120", displayDuringSetup: false)
		}
		section {
			input("supportedCommands", "enum",
				title: "Supported Commands\n\nThis controls the value for supportedWindowShadeCommands.",
				description: "open, close, pause", defaultValue: "2", multiple: false,
				options: [
					"1": "open, close",
					"2": "open, close, pause",
					"3": "open",
					"4": "close",
					"5": "pause",
					"6": "open, pause",
					"7": "close, pause",
					"8": "<empty list>",
					// For testing OCF/mobile client bugs
					"9": "open, closed, pause",
					"10": "open, closed, close, pause"
				]
			)
		}
	}

	tiles(scale: 2) {
		multiAttributeTile(name:"windowShade", type: "generic", width: 6, height: 4){
			tileAttribute ("device.windowShade", key: "PRIMARY_CONTROL") {
				attributeState "open", label:'${name}', action:"close", icon:"st.shades.shade-open", backgroundColor:"#79b821", nextState:"closing"
				attributeState "closed", label:'${name}', action:"open", icon:"st.shades.shade-closed", backgroundColor:"#ffffff", nextState:"opening"
				attributeState "partially open", label:'Open', action:"close", icon:"st.shades.shade-open", backgroundColor:"#79b821", nextState:"closing"
				attributeState "opening", label:'${name}', action:"pause", icon:"st.shades.shade-opening", backgroundColor:"#79b821", nextState:"partially open"
				attributeState "closing", label:'${name}', action:"pause", icon:"st.shades.shade-closing", backgroundColor:"#ffffff", nextState:"partially open"
				attributeState "unknown", label:'${name}', action:"open", icon:"st.shades.shade-closing", backgroundColor:"#ffffff", nextState:"opening"
			}
			/*tileAttribute ("device.level", key: "SLIDER_CONTROL") {
				attributeState "level", action:"setLevel"
			}*/
		}

		valueTile("blank", "device.blank", width: 2, height: 2, decoration: "flat") {
			state "default", label: ""	
		}
		valueTile("commandsLabel", "device.commands", width: 6, height: 1, decoration: "flat") {
			state "default", label: "Commands:"	
		}

		standardTile("windowShadeOpen", "device.windowShade", width: 2, height: 2, decoration: "flat") {
			state "default", label: "open", action:"open", icon:"st.shades.shade-open"
		}
		standardTile("windowShadeClose", "device.windowShade", width: 2, height: 2, decoration: "flat") {
			state "default", label: "close", action:"close", icon:"st.shades.shade-closed"
		}
		standardTile("windowShadePause", "device.windowShade", width: 2, height: 2, decoration: "flat") {
			state "default", label: "pause", action:"pause", icon:"st.Home.home2"
		}
		standardTile("windowShadePreset", "device.windowShadePreset", width: 2, height: 2, decoration: "flat") {
			state "default", label: "preset", action:"presetPosition", icon:"st.Home.home2"
		}

		valueTile("statesLabel", "device.states", width: 6, height: 1, decoration: "flat") {
			state "default", label: "State Events:"	
		}

		standardTile("windowShadePartiallyOpen", "device.windowShade", width: 2, height: 2, decoration: "flat") {
			state "default", label: "partially open", action:"partiallyOpen", icon:"st.Home.home2"
		}
		standardTile("windowShadeOpening", "device.windowShade", width: 2, height: 2, decoration: "flat") {
			state "default", label: "opening", action:"opening", icon:"st.shades.shade-opening"
		}
		standardTile("windowShadeClosing", "device.windowShade", width: 2, height: 2, decoration: "flat") {
			state "default", label: "closing", action:"closing", icon:"st.shades.shade-closing"
		}
		standardTile("windowShadeOpened", "device.windowShade", width: 2, height: 2, decoration: "flat") {
			state "default", label: "opened", action:"opened", icon:"st.shades.shade-open"
		}
		standardTile("windowShadeClosed", "device.windowShade", width: 2, height: 2, decoration: "flat") {
			state "default", label: "closed", action:"closed", icon:"st.shades.shade-closed"
		}
		standardTile("windowShadeUnknown", "device.windowShade", width: 2, height: 2, decoration: "flat") {
			state "default", label: "unknown", action:"unknown", icon:"st.Home.home2"
		}

		main "windowShade"
		details "windowShade", "open", "close", "pause",
				 "commandsLabel",
				 "windowShadeOpen", "windowShadeClose", "windowShadePause", "windowShadePreset", "blank", "blank",
				 "statesLabel",
				 "windowShadePartiallyOpen", "windowShadeOpening", "windowShadeClosing", "windowShadeOpened", "windowShadeClosed", "windowShadeUnknown"

	}
}

private getSupportedCommandsMap() {
	[
		"1": ["open", "close"],
		"2": ["open", "close", "pause"],
		"3": ["open"],
		"4": ["close"],
		"5": ["pause"],
		"6": ["open", "pause"],
		"7": ["close", "pause"],
		"8": [],
		// For testing OCF/mobile client bugs
		"9": ["open", "closed", "pause"],
		"10": ["open", "closed", "close", "pause"]
	]
}

def parse(String description) {
	log.debug "parse(): $description"
}

def open() {
	log.debug "open()"
	opening()
	runIn(shadeActionDelay, "opened")
}

def close() {
	log.debug "close()"
	closing()
	runIn(shadeActionDelay, "closed")
}

def pause() {
	log.debug "pause()"
	partiallyOpen()
}

def presetPosition() {
	log.debug "presetPosition()"
	if (device.currentValue("windowShade") == "open") {
		closePartially()
	} else if (device.currentValue("windowShade") == "closed") {
		openPartially()
	} else {
		partiallyOpen()
	}
}

// Custom test commands

def openPartially() {
	log.debug "openPartially()"
	opening()
	runIn(shadeActionDelay, "partiallyOpen")
}

def closePartially() {
	log.debug "closePartially()"
	closing()
	runIn(shadeActionDelay, "partiallyOpen")
}

def partiallyOpen() {
	log.debug "windowShade: partially open"
	sendEvent(name: "windowShade", value: "partially open", isStateChange: true)
}

def opening() {
	log.debug "windowShade: opening"
	sendEvent(name: "windowShade", value: "opening", isStateChange: true)
}

def closing() {
	log.debug "windowShade: closing"
	sendEvent(name: "windowShade", value: "closing", isStateChange: true)
}

def opened() {
	log.debug "windowShade: open"
	sendEvent(name: "windowShade", value: "open", isStateChange: true)
}

def closed() {
	log.debug "windowShade: closed"
	sendEvent(name: "windowShade", value: "closed", isStateChange: true)
}

def unknown() {
	// TODO: Add some "fuzzing" logic so that this gets hit every now and then?
	log.debug "windowShade: unknown"
	sendEvent(name: "windowShade", value: "unknown", isStateChange: true)
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

    sendEvent(name: "windowShade", value: "closed")
    
    sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
    
    }