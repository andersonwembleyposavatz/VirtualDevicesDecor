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
    definition (name: "VIRTUAL DECOR for Audio Home Theater", namespace: "andersonwembleyposavatz", author: "Anderson W Posavatz", minHubCoreVersion: '000.021.00001', mnmn: "SmartThings", ocfDeviceType: "oic.d.audiosystem") {
        capability "Actuator"
        capability "Sensor"
        capability "Switch"
		capability "Health Check"

    }

    preferences {}

    tiles(scale: 2) {
        multiAttributeTile(name:"switch", type: "switch", width: 6, height: 4, canChangeIcon: true){
            tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
                attributeState "on", label:'${name}', action:"switch.off", icon:"https://client-assets.smartthings.com/oneui/home_theater-on@4x.png", backgroundColor:"#00A0DC", nextState:"turningOff"
                attributeState "off", label:'${name}', action:"switch.on", icon:"https://client-assets.smartthings.com/oneui/home_theater-off@4x.png", backgroundColor:"#FFFFFF", nextState:"turningOn"
                attributeState "turningOn", label:'Turning On', action:"switch.off", icon:"https://client-assets.smartthings.com/oneui/home_theater-on@4x.png", backgroundColor:"#00A0DC", nextState:"turningOn"
                attributeState "turningOff", label:'Turning Off', action:"switch.on", icon:"https://client-assets.smartthings.com/oneui/home_theater-off@4x.png", backgroundColor:"#FFFFFF", nextState:"turningOff"
            }
        }

        standardTile("on", "device.switch", width: 2, height: 2, decoration: "flat") {
            state "default", label: '${name}', action: "switch.on", icon: "https://client-assets.smartthings.com/oneui/home_theater-on@2x.png", backgroundColor: "#00A0DC"
        }
        standardTile("off", "device.switch", width: 2, height: 2, decoration: "flat") {
            state "default", label: '${name}', action: "switch.off", icon: "https://client-assets.smartthings.com/oneui/home_theater-off@2x.png", backgroundColor: "#ffffff"
        }
        

        main "switch"
        details(["switch", "on", "off", ])

    }
}

def parse(description) {
}

def on() {
    sendEvent(name: "switch", value: "on")
}

def off() {
    sendEvent(name: "switch", value: "off")
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

	sendEvent(name: "switch", value: "on")
    
    sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
    }

