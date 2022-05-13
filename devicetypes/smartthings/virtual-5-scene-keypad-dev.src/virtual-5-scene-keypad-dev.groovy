/**
 *  Copyright 2018 SmartThings
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
metadata {
	definition(name: "VIRTUAL 5-Scene Keypad DEV", namespace: "smartthings", author: "SmartThings", mcdSync: true, mnmn: "SmartThings", ocfDeviceType: "x.com.st.d.remotecontroller", runLocally: true, minHubCoreVersion: '000.017.0012', executeCommandsLocally: true) {
		capability "Actuator"
		capability "Health Check"
		capability "Refresh"
		capability "Sensor"
		capability "Switch"

		
	}

	tiles(scale: 2) {
		multiAttributeTile(name: "switch", type: "lighting", width: 6, height: 4, canChangeIcon: true) {
			tileAttribute("device.switch", key: "PRIMARY_CONTROL") {
				attributeState "on", label: '${name}', action: "switch.off", icon: "st.unknown.zwave.remote-controller", backgroundColor: "#00A0DC"
				attributeState "off", label: '${name}', action: "switch.on", icon: "st.unknown.zwave.remote-controller", backgroundColor: "#ffffff"
			}
		}

		childDeviceTiles("outlets")

		standardTile("switch", "device.switch", inactiveLabel: false, decoration: "flat", width: 6, height: 2, backgroundColor: "#00a0dc") {
                        state "off", label: "", icon: "st.unknown.zwave.remote-controller", backgroundColor: "#ffffff"
			state "on", label: "", icon: "st.unknown.zwave.remote-controller", backgroundColor: "#00A0DC"		}

		main "switch"
	}
}

def installed() {
	addChildSwitches()
	
	sendEvent(name: "switch", value: "off")

	}

def updated() {
	initialize()
	
	
}

def initialize() {
	if (!childDevices) {
		addChildSwitches()
	}

	
}

def on() {
	def switchId = 1
	def state = "on"
	// this may override previous state if user changes more switches before cloud state is updated
	updateLocalSwitchState(switchId, state)
}

def off() {
	def switchId = 1
	def state = "off"
	// this may override previous state if user changes more switches before cloud state is updated
	updateLocalSwitchState(switchId, state)
}

def parse(String description) {
	def result = []
	log.debug "Parse [$description] to \"$cmd\""
	result
}

// called from child-switch's on() method
void childOn(deviceNetworkId) {
	def switchId = deviceNetworkId?.split("/")[1] as Integer
	def state = "on"
	// this may override previous state if user changes more switches before cloud state is updated
	updateLocalSwitchState(switchId, state)
}

// called from child-switch's off() method
void childOff(deviceNetworkId) {
	def switchId = deviceNetworkId?.split("/")[1] as Integer
	def state = "off"
	// this may override previous state if user changes more switches before cloud state is updated
	updateLocalSwitchState(switchId, state)
}