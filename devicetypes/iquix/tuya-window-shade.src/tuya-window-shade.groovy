/**
 *	Tuya Window Shade (v.0.5.2.0)
 *	Copyright 2020-2021 Jaewon Park (iquix)
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

import groovy.json.JsonOutput

metadata {
	definition(name: "Tuya Window Shade", namespace: "iquix", author: "iquix", ocfDeviceType: "oic.d.blind", vid: "generic-shade") {
		capability "Actuator"
		capability "Configuration"
		capability "Window Shade"
		capability "Window Shade Preset"
		capability "Switch Level"

		command "pause"

		fingerprint profileId: "0104", manufacturer: "_TZE200_cowvfni3", model: "TS0601", deviceJoinName: "Tuya Window Treatment" // Zemismart Zigbee Curtain *
		fingerprint profileId: "0104", manufacturer: "_TZE200_xaabybja", model: "TS0601", deviceJoinName: "Tuya Window Treatment" // Zemismart Zigbee Curtain (Not fully tested)
		fingerprint profileId: "0104", manufacturer: "_TZE200_wmcdj3aq", model: "TS0601", deviceJoinName: "Tuya Window Treatment" // Zemismart Blind *
		fingerprint profileId: "0104", manufacturer: "_TZE200_fzo2pocs", model: "TS0601", deviceJoinName: "Tuya Window Treatment" // Zemismart Blind (Not tested)
		fingerprint profileId: "0104", manufacturer: "_TZE200_5sbebbzs", model: "TS0601", deviceJoinName: "Tuya Window Treatment" // Zemismart Blind with Battery *
		fingerprint profileId: "0104", manufacturer: "_TZE200_nogaemzt", model: "TS0601", deviceJoinName: "Tuya Window Treatment" // YS-MT750 *
		fingerprint profileId: "0104", manufacturer: "_TZE200_5zbp6j0u", model: "TS0601", deviceJoinName: "Tuya Window Treatment" // YS-MT750 *
		fingerprint profileId: "0104", manufacturer: "_TZE200_fdtjuw7u", model: "TS0601", deviceJoinName: "Tuya Window Treatment" // YS-MT750 *
		fingerprint profileId: "0104", manufacturer: "_TZE200_bqcqqjpb", model: "TS0601", deviceJoinName: "Tuya Window Treatment" // YS-MT750L *
		fingerprint profileId: "0104", manufacturer: "_TZE200_zpzndjez", model: "TS0601", deviceJoinName: "Tuya Window Treatment" // DS82 *
		fingerprint profileId: "0104", manufacturer: "_TZE200_nueqqe6k", model: "TS0601", deviceJoinName: "Tuya Window Treatment" // Smart Motorized Chain Roller *
		fingerprint profileId: "0104", manufacturer: "_TYST11_cowvfni3", model: "owvfni3", deviceJoinName: "Tuya Window Treatment" // Zemismart Zigbee Curtain *
		fingerprint profileId: "0104", manufacturer: "_TYST11_xaabybja", model: "aabybja", deviceJoinName: "Tuya Window Treatment" // Zemismart Zigbee Curtain (Not fully tested)
		fingerprint profileId: "0104", manufacturer: "_TYST11_wmcdj3aq", model: "mcdj3aq", deviceJoinName: "Tuya Window Treatment" // Zemismart Zigbee Blind *
		fingerprint profileId: "0104", manufacturer: "_TYST11_fzo2pocs", model: "zo2pocs", deviceJoinName: "Tuya Window Treatment" // Zemismart Zigbee Blind (Not tested)
		fingerprint profileId: "0104", manufacturer: "_TYST11_5sbebbzs", model: "sbebbzs", deviceJoinName: "Tuya Window Treatment" // Zemismart Blind with Battery
		fingerprint profileId: "0104", manufacturer: "_TYST11_nogaemzt", model: "ogaemzt", deviceJoinName: "Tuya Window Treatment" // YS-MT750
		fingerprint profileId: "0104", manufacturer: "_TYST11_5zbp6j0u", model: "zbp6j0u", deviceJoinName: "Tuya Window Treatment" // YS-MT750
		fingerprint profileId: "0104", manufacturer: "_TYST11_fdtjuw7u", model: "dtjuw7u", deviceJoinName: "Tuya Window Treatment" // YS-MT750
		fingerprint profileId: "0104", manufacturer: "_TYST11_bqcqqjpb", model: "qcqqjpb", deviceJoinName: "Tuya Window Treatment" // YS-MT750L
		fingerprint profileId: "0104", manufacturer: "_TYST11_zpzndjez", model: "pzndjez", deviceJoinName: "Tuya Window Treatment" // DS82
		fingerprint profileId: "0104", manufacturer: "_TYST11_nueqqe6k", model: "ueqqe6k", deviceJoinName: "Tuya Window Treatment" // Smart Motorized Chain Roller
	}

	preferences {
		input "preset", "number", title: "Preset position", description: "Set the window shade preset position", defaultValue: 50, range: "0..100", required: false, displayDuringSetup: false
		input "reverse", "enum", title: "Direction", description: "Set direction of curtain motor by open/close app commands. For example, if you send 'open' command from app, but the curtain motor is closing, then set this option to 'Reverse'.", options: ["Forward", "Reverse"], defaultValue: "Forward", required: false, displayDuringSetup: false
		input "fixpercent", "enum", title: "Fix percent", description: "Set 'Fix percent' option unless open is 100% and close is 0%. In Smartthings, 'Open' should be 100% in level and 'Close' should be 0% in level. If it is reversed, then set this option to 'Fix percent'.", options: ["Default", "Fix percent"], defaultValue: "Default", required: false, displayDuringSetup: false
	}

	tiles(scale: 2) {
		multiAttributeTile(name:"windowShade", type: "generic", width: 6, height: 4) {
			tileAttribute("device.windowShade", key: "PRIMARY_CONTROL") {
				attributeState "open", label: 'Open', action: "close", icon: "https://objectstorage.ap-seoul-1.oraclecloud.com/n/cnqa4dxui0ha/b/ST/o/window_open.png", backgroundColor: "#00A0DC", nextState: "closing"
				attributeState "closed", label: 'Closed', action: "open", icon: "https://objectstorage.ap-seoul-1.oraclecloud.com/n/cnqa4dxui0ha/b/ST/o/window_close.png", backgroundColor: "#ffffff", nextState: "opening"
				attributeState "partially open", label: 'Partially open', action: "close", icon: "https://objectstorage.ap-seoul-1.oraclecloud.com/n/cnqa4dxui0ha/b/ST/o/window_open.png", backgroundColor: "#d45614", nextState: "closing"
				attributeState "opening", label: 'Opening', action: "pause", icon: "https://objectstorage.ap-seoul-1.oraclecloud.com/n/cnqa4dxui0ha/b/ST/o/window_open.png", backgroundColor: "#00A0DC", nextState: "partially open"
				attributeState "closing", label: 'Closing', action: "pause", icon: "https://objectstorage.ap-seoul-1.oraclecloud.com/n/cnqa4dxui0ha/b/ST/o/window_close.png", backgroundColor: "#ffffff", nextState: "partially open"
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
		details(["windowShade", "contPause", "presetPosition", "shadeLevel", "levelSliderControl", "refresh"])
	}
}

private getCLUSTER_TUYA() { 0xEF00 }
private getSETDATA() { 0x00 }
private getINIT_DEVICE() { 0x03 }

// tuya DP type
private getDP_TYPE_BOOL() { "01" }
private getDP_TYPE_VALUE() { "02" }
private getDP_TYPE_ENUM() { "04" }


// Parse incoming device messages to generate events
def parse(String description) {
	if (description?.startsWith('catchall:') || description?.startsWith('read attr -')) {
		Map descMap = zigbee.parseDescriptionAsMap(description)
		log.debug descMap
		if (descMap?.clusterInt==CLUSTER_TUYA) {
			if ( descMap?.command == "01" || descMap?.command == "02" ) {
				def dp = zigbee.convertHexToInt(descMap?.data[2])
				def fncmd = zigbee.convertHexToInt(descMap?.data[6..-1].join(''))
				log.debug "dp=${dp} fncmd=${fncmd}"
				switch (dp) {
					case 0x07: // 0x07: Work state -- Started moving (triggered by RF remote or pulling the curtain)
						if (isZemiCurtain()) {
							if (device.currentValue("level") == 0) {
								log.debug "moving from position 0 : must be opening"
								levelEventMoving(100)
							} else if (device.currentValue("level") == 100) {
								log.debug "moving from position 100 : must be closing"
								levelEventMoving(0)
							}
						} else if (supportDp1State()) {
							return
						} else {
							if (directionVal(fncmd) == 0) {
								log.debug "opening"
								levelEventMoving(100)
							} else if (directionVal(fncmd) == 1) {
								log.debug "closing"
								levelEventMoving(0)
							}
						}
						break
					case 0x01: // 0x01: Control -- Opening/closing/stopped
						def statVal = cmdVal(fncmd)
						if (statVal == 1) {
							log.debug "stopped"
							state.moving = false
							return
						}
						if (statVal == 0 || statVal == 2) {
							state.moving = levelEventMoving((statVal==0) ? 100 : 0)
						}
						break
					case 0x02: // 0x02: Percent control -- Started moving to position (triggered from Zigbee)
						if (!isDp2PositionDevices()) {
							def pos = levelVal(fncmd)
							log.debug "moving to position: "+pos
							levelEventMoving(pos)
							break
						}
						// isDp2PositionDevices() devices send current position packet with dp 2, so it will be processed with the following code.
					case 0x03: // 0x03: Percent state -- Arrived at position
						def pos = levelVal(fncmd)
						log.debug "position: "+pos
						levelEventArrived(pos)
						break
					case 0x05: // 0x05: Direction state
						log.debug "direction state of the motor is "+ (fncmd ? "reverse" : "forward")
						break
					case 0x67: // 0x67: Completion of limit setttings (YS-MT750 only)
						if (state?.direction_post) {
							state.autolimit = null
							log.debug "*** automatic limit settings complete. moving to 50% position... ***"
							moveTo50()
						}
						break
				}
			}
		}
	}
}

private levelEventMoving(currentLevel) {
	def lastLevel = device.currentValue("level")
	log.debug "levelEventMoving - currentLevel: ${currentLevel} lastLevel: ${lastLevel}"
	if (lastLevel == "undefined" || currentLevel == lastLevel) { //Ignore invalid reports
		log.debug "Ignore invalid reports"
	} else {
		if (lastLevel < currentLevel) {
			sendEvent(name:"windowShade", value: "opening", displayed: true)
			return true
		} else if (lastLevel > currentLevel) {
			sendEvent(name:"windowShade", value: "closing", displayed: true)
			return true
		}
	}
	return false
}

private levelEventArrived(level) {
	def windowShadeVal
	if (level == 0) {
		windowShadeVal = "closed"
	} else if (level == 100) {
		windowShadeVal = "open"
	} else if (level > 0 && level < 100) {
		windowShadeVal = "partially open"
	} else {
		log.debug "Position value error (${level}) : Please remove the device from Smartthings, and setup limit of the curtain before pairing."
		level = 50
        windowShadeVal = "unknown"
	}
	sendEvent(name: "level", value: (level), displayed: true)
	if (!(supportDp1State() && state.moving)) {
		sendEvent(name: "windowShade", value: (windowShadeVal), displayed: true)
	}
}

def close() {
	log.info "close()"
	def currentLevel = device.currentValue("level")
	if (currentLevel == 0) {
		sendEvent(name: "windowShade", value: "closed", displayed: true)
	}
	sendTuyaCommand("01", DP_TYPE_ENUM, zigbee.convertToHexString(cmdVal(2)))
}

def open() {
	log.info "open()"
	def currentLevel = device.currentValue("level")
	if (currentLevel == 100) {
		sendEvent(name: "windowShade", value: "open", displayed: true)
	}
	sendTuyaCommand("01", DP_TYPE_ENUM, zigbee.convertToHexString(cmdVal(0)))
}

def pause() {
	log.info "pause()"
	sendEvent(name: "windowShade", value: device.currentValue("windowShade"), displayed: false)
	sendTuyaCommand("01", DP_TYPE_ENUM, "01")
}

def setLevel(data, rate = null) {
	log.info "setLevel(${data})"
	def currentLevel = device.currentValue("level")
	if (currentLevel == data) {
		sendEvent(name: "level", value: currentLevel, displayed: true)
	}
	runIn(10, "setEvent", [overwrite:true])
	sendTuyaCommand("02", DP_TYPE_VALUE, zigbee.convertToHexString(levelVal(data), 8))
}


def presetPosition() {
	setLevel(preset ?: 50)
}

def installed() {
	log.info "installed()"
	state.preferences = null
	state.default_fix_percent = null
	state.autolimit = null
	state.run_autolimit = true
	sendEvent(name: "supportedWindowShadeCommands", value: JsonOutput.toJson(["open", "close", "pause"]), displayed: false)
	sendEvent(name: "windowShade", value: "unknown", displayed: false)
	sendEvent(name: "level", value: 50, displayed: false)
	return
} 

def updated() {
	log.info "updated()"
	calcDefaultFixpercent()
	if (state?.preferences != null) {
		def prev_pref = state.preferences?.tokenize("|")
		if ((prev_pref[0] == "Reverse") ^ (reverse == "Reverse")) {
			log.debug "reverse mode changed : ${prev_pref[0]} -> ${reverse}. inverting state and level"
			levelEventArrived(100 - device.currentValue("level"))
			setDirection()
		}
		if ((prev_pref[1] == "Fix percent") ^ (fixpercent == "Fix percent")) {
			log.debug "fix percent changed : ${prev_pref[1]} -> ${fixpercent}. inverting state and level"
			levelEventArrived(100 - device.currentValue("level"))
		}
	} else {
		setDirection()
	}
	state.preferences = "|${reverse}|${fixpercent}|${fixcommand}|"
	return
}

def setEvent() {
	sendEvent(name: "level", value: device.currentValue("level"), displayed: false)
}

private setDirection() {
	log.info "setDirection()"
	def cmds = sendTuyaCommand("05", DP_TYPE_ENUM, (reverse == "Reverse") ? "01" : "00")
	cmds.each{ sendHubCommand(new physicalgraph.device.HubAction(it)) }	
	runIn(5, directionPostProcess)
}

def directionPostProcess() {
	log.info "directionPostProcess()"
	def cmds
	if (isAutoLimitSupported && state.run_autolimit) {
		log.debug "*** this device is capable of automatic limit settings. starting automatic limit settings... ***"
		state.autolimit = true
		state.run_autolimit = false
		cmds = sendTuyaCommand("06", DP_TYPE_BOOL, "01")
	} else {
		cmds = sendTuyaCommand("02", DP_TYPE_VALUE, zigbee.convertToHexString(50, 8))
	}
	cmds.each{ sendHubCommand(new physicalgraph.device.HubAction(it)) }	
}

def moveTo50() {
	log.info "moveTo50()"
	def cmds = sendTuyaCommand("02", DP_TYPE_VALUE, zigbee.convertToHexString(50, 8))
	cmds.each{ sendHubCommand(new physicalgraph.device.HubAction(it)) }	
}

private sendTuyaCommand(dp, dp_type, fncmd) {
	zigbee.command(CLUSTER_TUYA, SETDATA, PACKET_ID + dp + dp_type + zigbee.convertToHexString(fncmd.length()/2, 4) + fncmd )
}

private getPACKET_ID() {
	state.packetID = ((state.packetID ?: 0) + 1 ) % 65536
	zigbee.convertToHexString(state.packetID, 4)
}

private levelVal(n) {
	if (state.default_fix_percent == null) {
		calcDefaultFixpercent()
	}
	def pct = n & 0xFF
	//extremly awkward percent packet in "ogaemzt" device. special thanks to 경기PA팬텀
	if (state.default_fix_percent == "ogaemzt") {
		return (int)(((fixpercent == "Fix percent") ^ (n == pct)) ? 100 - pct : pct)
	} else {
		return (int)(((fixpercent == "Fix percent") ^ state.default_fix_percent) ? 100 - pct : pct)	
	}
}

private cmdVal(c) {
	//return (fixcommand == "Fix command") ? 2 - c : c
	return c
}

private directionVal(c) {
	//return ( (isZemiBlind() && (reverse != "Reverse")) ^ (fixcommand == "Fix command") ) ? 1 - c : c
	return (isZemiBlind() && (reverse != "Reverse")) ? 1 - c : c
}

private calcDefaultFixpercent() {
	def fixpercent_devices = ["owvfni3", "zbp6j0u", "pzndjez", "qcqqjpb", "ueqqe6k", "sbebbzs", "aabybja"]
	def dev = fixpercent_devices.find { productId == it }
	state.default_fix_percent = isOgaemzt() ? "ogaemzt" : (dev != null)
	log.debug "default fixpercent for this device is set to ${state.default_fix_percent}"
}

private getProductId() {
	return device.getDataValue("manufacturer")[-7..-1]
}

private isZemiCurtain() {
	return (productId == "owvfni3")
}

private isZemiBlind() {
	return (productId == "mcdj3aq" || productId == "zo2pocs")
}

private isOgaemzt() {
	return (productId == "ogaemzt")
}

private getIsAutoLimitSupported() {
	return (productId == "dtjuw7u")
}

private isDp2PositionDevices() {
	return (productId == "ueqqe6k" || productId == "sbebbzs")
}

private supportDp1State() {
	return (productId == "qcqqjpb" || productId == "aabybja")
}