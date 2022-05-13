/**
 *  Copyright 2017 SmartThings
 *
 *  Provides a Virtual DECOR for Home Theater.
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
    definition (name: "VIRTUAL DECOR for Home Theater", namespace: "andersonwembleyposavatz", author: "Anderson W Posavatz", minHubCoreVersion: '000.021.00001', mnmn: "SmartThings", ocfDeviceType: "x.com.st.d.hometheater") {
        capability "Actuator"
        capability "Sensor"
        capability "Switch"
    }

    preferences {}

    tiles(scale: 2) {
        multiAttributeTile(name:"switch", type: "hometheater", width: 6, height: 4, canChangeIcon: true){
            tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
                attributeState "on", label:'${name}', action:"switch.off", icon:"st.hometheater.hometheater.on", backgroundColor:"#00A0DC", nextState:"turningOff"
                attributeState "off", label:'${name}', action:"switch.on", icon:"st.hometheater.hometheater.off", backgroundColor:"#FFFFFF", nextState:"turningOn", defaultState: true
                attributeState "turningOn", label:'Turning On', action:"switch.off", icon:"st.hometheater.on.activated", backgroundColor:"#00A0DC", nextState:"turningOn"
                attributeState "turningOff", label:'Turning Off', action:"switch.on", icon:"st.hometheater.hometheater.off", backgroundColor:"#FFFFFF", nextState:"turningOff"
            }
        }

        standardTile("on", "device.switch", width: 2, height: 2, decoration: "flat") {
            state "default", label: "Activated", action: "switch.off", icon: "st.hometheater.hometheater.on", backgroundColor: "#00A0DC"
        }
        standardTile("off", "device.switch", width: 2, height: 2, decoration: "flat") {
            state "default", label: "Disconnected", action: "switch.on", icon: "st.hometheater.hometheater.off", backgroundColor: "#ffffff"
        }

        main(["switch"])
        details(["switch", "explicitOn", "explicitOff"])

    }
}

def parse(description) {
}

def on() {
    sendEvent(name: "switch", value: "on", isStateChange: true)
}

def off() {
    sendEvent(name: "switch", value: "off", isStateChange: true)
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

	sendEvent(name: "switch", value: "off")
    
        sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)

}