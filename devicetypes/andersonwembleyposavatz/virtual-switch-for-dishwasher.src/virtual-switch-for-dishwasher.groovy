/**
 *  VIRTUAL Switch for Dishwasher
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
 */
metadata {
    definition (name: "VIRTUAL Switch for Dishwasher", namespace: "andersonwembleyposavatz", author: "Anderson Wembley Posavatz", ocfDeviceType: "oic.d.dishwasher", runLocally: true, minHubCoreVersion: '000.021.00001', executeCommandsLocally: true, mnmn: "SmartThings",) {
        capability "Actuator"
		capability "Health Check"
        capability "Dishwasher Operating State"
        capability "Switch"
        capability "Sensor"
}

    preferences {}

    tiles(scale: 2) {
        multiAttributeTile(name:"dishwasher", type: "generic", width: 6, height: 4){
            tileAttribute ("device.dishwasher", key: "PRIMARY_CONTROL") {
                attributeState "on", label:'${name}', action:"off", icon:"st.Appliances.appliances8", backgroundColor:"#00A0DC", nextState:"turningOff"
                attributeState "off", label:'${name}', action:"on", icon:"st.Appliances.appliances8", backgroundColor:"#FFFFFF", nextState:"turningOn"
                attributeState "turningOn", label:'${name}', action:"run", icon:"st.Appliances.appliances8", backgroundColor:"#00A0DC", nextState:"turningOn"
                attributeState "turningOff", label:'${name}', action:"stop", icon:"st.Appliances.appliances8", backgroundColor:"#FFFFFF", nextState:"turningOff"
            }
        }

        standardTile("Run", "device.dishwasher", width: 2, height: 2, decoration: "flat") {
            state "default", label: "run", action: "on", icon: "st.Appliances.appliances8", backgroundColor: "#00A0DC"
        }
        standardTile("Stop", "device.dishwasher", width: 2, height: 2, decoration: "flat") {
            state "default", label: "stop", action: "off", icon: "st.Appliances.appliances8", backgroundColor: "#FFFFFF"
        }

        main(["dishwasher", "switch"])
        details(["dishwasher", "On", "Off", "run", "stop" ])

    }
}

def parse(description) {
}

def on() {
    sendEvent(name: "dishwasher", value: "run")
    sendEvent(name: "switch", value: "on")
}

def off() {
    sendEvent(name: "dishwasher", value: "stop")
    sendEvent(name: "switch", value: "off")

}

def installed() {
	initialize()
}

def updated() {
	initialize()
}

def initialize() {
    sendEvent(name: "dishwasher", value: "stop")
    sendEvent(name: "switch", value: "off")

	sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme:"untracked"].encodeAsJson(), displayed: false)
}