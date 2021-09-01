/**
 *  Copyright 2021 SmartThings
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
	definition(name: "Virtual Fan Controller", namespace: "sharptools-io", author: "josh", ocfDeviceType: "oic.d.fan") {
		capability "Switch Level"
		capability "Switch"
		capability "Fan Speed"
		capability "Health Check"
		capability "Actuator"
		capability "Refresh"
		capability "Sensor"

		command "low"
		command "medium"
		command "high"
		command "raiseFanSpeed"
		command "lowerFanSpeed"
	}


 tiles(scale: 2) {
        multiAttributeTile(name: "fanSpeed", type: "generic", width: 6, height: 4, canChangeIcon: true) {
            tileAttribute("device.fanSpeed", key: "PRIMARY_CONTROL") {
                attributeState "0", label: "off", action: "switch.on", icon: "st.thermostat.fan-off", backgroundColor: "#ffffff"
                attributeState "1", label: "low", action: "switch.off", icon: "st.thermostat.fan-on", backgroundColor: "#00a0dc"
                attributeState "2", label: "medium", action: "switch.off", icon: "st.thermostat.fan-on", backgroundColor: "#00a0dc"
                attributeState "3", label: "high", action: "switch.off", icon: "st.thermostat.fan-on", backgroundColor: "#00a0dc"
            }
            tileAttribute("device.fanSpeed", key: "VALUE_CONTROL") {
                attributeState "VALUE_UP", action: "raiseFanSpeed"
                attributeState "VALUE_DOWN", action: "lowerFanSpeed"
			}
		}

        standardTile("refresh", "device.switch", width: 2, height: 2, inactiveLabel: false, decoration: "flat") {
            state "default", label: '', action: "refresh.refresh", icon: "st.secondary.refresh"
		}
        
		main "fanSpeed"
		details(["fanSpeed", "refresh"])
	}

}

def installed() {
	response(refresh())
}

def parse(String description) {
    //no parsing - just simulating
}



def generateFanSpeedEvent(level) {
	def rawLevel = level as int
	def result = []

	if (0 <= rawLevel && rawLevel <= 100) {
		def value = (rawLevel ? "on" : "off")
		sendEvent(name: "switch", value: value)
		sendEvent(name: "level", value: rawLevel == 99 ? 100 : rawLevel)

		def fanLevel = 0

		// The GE, Honeywell, and Leviton treat 33 as medium, so account for that
		if (1 <= rawLevel && rawLevel <= 32) {
			fanLevel = 1
		} else if (33 <= rawLevel && rawLevel <= 66) {
			fanLevel = 2
		} else if (67 <= rawLevel && rawLevel <= 100) {
			fanLevel = 3
		}
		sendEvent(name: "fanSpeed", value: fanLevel)
	}
}

def on() {
	generateFanSpeedEvent(100) //should we change to a last known internal level and fanSpeed -- or only change if not already on?
    //switch=off, level=100, fanSpeed=3
}

def off() {
	generateFanSpeedEvent(0); //switch=off, level=0, fanSpeed=0
}

def setLevel(value, rate = null) {
	def cmds = []

	def level = value as Integer
	level = level == 255 ? level : Math.max(Math.min(level, 99), 0)
	log.debug "setLevel >> value: $level"

	generateFanSpeedEvent(level)
}

def setFanSpeed(speed) {
	if (speed as Integer == 0) {
		off()
	} else if (speed as Integer == 1) {
		low()
	} else if (speed as Integer == 2) {
		medium()
	} else if (speed as Integer == 3) {
		high()
	}
}

def raiseFanSpeed() {
	setFanSpeed(Math.min((device.currentValue("fanSpeed") as Integer) + 1, 3))
}

def lowerFanSpeed() {
	setFanSpeed(Math.max((device.currentValue("fanSpeed") as Integer) - 1, 0))
}

def low() {
	setLevel(32)
}

def medium() {
	setLevel(66)
}

def high() {
	setLevel(99)
}

def refresh() {
	//nothing for now
}

def ping() {
	refresh()
}