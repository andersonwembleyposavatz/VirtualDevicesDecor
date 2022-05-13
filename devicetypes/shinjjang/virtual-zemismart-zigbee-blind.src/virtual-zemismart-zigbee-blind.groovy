/**
 *  Tuya Window Shade (v.0.1.0)
 *	Copyright 2020 iquix
 *
 *	Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *	in compliance with the License. You may obtain a copy of the License at:
 *
 *		http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *	on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *	for the specific language governing permissions and limitations under the License.
 This DTH is coded based on iquix's tuya-window-shade DTH.
 https://github.com/iquix/Smartthings/blob/master/devicetypes/iquix/tuya-window-shade.src/tuya-window-shade.groovy
 */

metadata {
	definition(name: "VIRTUAL ZemiSmart Zigbee Blind", namespace: "ShinJjang", author: "ShinJjang-iquix", ocfDeviceType: "oic.d.blind", vid: "generic-shade", runLocally: true , executeCommandsLocally: true ) {
		capability "Actuator"
		capability "Configuration"
		capability "Window Shade"
		capability "Window Shade Preset"
		capability "Switch Level"
		capability "Sensor"

		command "pause"
		command "levelOpenClose"
        
        attribute "Direction", "enum", ["Reverse","Forward"]
        attribute "OCcommand", "enum", ["Replace","Original"]
        attribute "stapp", "enum", ["Reverse","Forward"]
        attribute "remote", "enum", ["Reverse","Forward"]

		
	}

	preferences {
		input "preset", "number", title: "Preset position", description: "Set the window shade preset position", defaultValue: 50, range: "0..100", required: false, displayDuringSetup: false
        input name: "Direction", type: "enum", title: "Direction Set", options:["01": "Reverse", "00": "Forward"], required: true, displayDuringSetup: true
        input name: "OCcommand", type: "enum", title: "Replace Open and Close commands", options:["2": "Replace", "0": "Original"], required: true, displayDuringSetup: true
        input name: "stapp", type: "enum", title: "app opening,closing Change", options:["2": "Reverse", "0": "Forward"], required: true, displayDuringSetup: true
        input name: "remote", type: "enum", title: "RC opening,closing Change", options:["1": "Reverse", "0": "Forward"], required: true, displayDuringSetup: true
	}

	tiles(scale: 2) {
		multiAttributeTile(name:"windowShade", type: "generic", width: 6, height: 4) {
			tileAttribute("device.windowShade", key: "PRIMARY_CONTROL") {
				attributeState "open", label: 'Open', action: "close", icon: "http://www.ezex.co.kr/img/st/window_open.png", backgroundColor: "#00A0DC", nextState: "closing"
				attributeState "closed", label: 'Closed', action: "open", icon: "http://www.ezex.co.kr/img/st/window_close.png", backgroundColor: "#ffffff", nextState: "opening"
				attributeState "partially open", label: 'Partially open', action: "close", icon: "http://www.ezex.co.kr/img/st/window_open.png", backgroundColor: "#d45614", nextState: "closing"
				attributeState "opening", label: 'Opening', action: "close", icon: "http://www.ezex.co.kr/img/st/window_open.png", backgroundColor: "#00A0DC", nextState: "closing"
				attributeState "closing", label: 'Closing', action: "open", icon: "http://www.ezex.co.kr/img/st/window_close.png", backgroundColor: "#ffffff", nextState: "opening"
			}
		}
		standardTile("contPause", "device.switch", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
			state "pause", label:"", icon:'st.sonos.pause-btn', action:'pause', backgroundColor:"#cccccc"
		}
		standardTile("presetPosition", "device.presetPosition", width: 2, height: 2, decoration: "flat") {
			state "default", label: "Preset", action:"presetPosition", icon:"st.Home.home2"
		}
		standardTile("refresh", "device.refresh", inactiveLabel: false, decoration: "flat", width: 2, height: 1) {
			state "default", label:"", action:"refresh.refresh", icon:"st.secondary.refresh"
		}
		valueTile("shadeLevel", "device.level", width: 4, height: 1) {
			state "level", label: 'Shade is ${currentValue}% up', defaultState: true
		}
		controlTile("levelSliderControl", "device.level", "slider", width:2, height: 1, inactiveLabel: false) {
			state "level", action:"switch level.setLevel"
		}

		main "windowShade"
		details(["windowShade", "contPause", "presetPosition", "shadeLevel", "levelSliderControl"])
	}
}


// Parse incoming device messages to generate events
def parse(String description) {

}

def close() {
	log.info "close()"
    def cm = (OCcommand ?:"0") as int
    log.debug "cm=${cm}"
    def val = Math.abs(2 - cm)
	sendTuyaCommand("0104", "00", "010" + val)
}

def open() {
	log.info "open()"
    def cm = (OCcommand ?:"0") as int
    def val = Math.abs(0 - cm)
	sendTuyaCommand("0104", "00", "010" + val)
}

def pause() {
	log.info "pause()"
	sendTuyaCommand("0104", "00", "0101")
}

def setLevel(data, rate = null) {
	log.info "setLevel("+data+")"
    def currentLevel = device.currentValue("level")
    if (currentLevel == data) {
    	sendEvent(name: "level", value: currentLevel, displayed: true)
        return
    }
	sendTuyaCommand("0202", "00", "04000000"+zigbee.convertToHexString(data, 2))
}

def refresh() {
	zigbee.readAttribute(CLUSTER_TUYA, 0x00, )
}

def presetPosition() {
    setLevel(preset ?: 50)
}

def installed() {
	sendEvent(name: "supportedWindowShadeCommands", value: JsonOutput.toJson(["open", "close", "pause"]), displayed: false)
}

def updated() {
	log.debug "val(${Direction}),valC(${OCcommand}),valR(${remote})"
	DirectionSet(Direction ?:"00")
}	

def DirectionSet(Dval) {
	log.info "Dset(${Dval})"
   sendHubCommand(zigbee.command(CLUSTER_TUYA, SETDATA, "00" + zigbee.convertToHexString(rand(256), 2) + "05040001" + Dval))
}

def configure() {
	log.info "configure()"
}
