/**
 *  VIRTUAL Switch for FAN DECOR
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
metadata {
    definition (name: "VIRTUAL Switch for FAN DECOR", namespace: "andersonwmbleyposavatz", author: "Anderson W Posavatz", ocfDeviceType: "oic.d.fan", mnmn: "SmartThings", minHubCoreVersion: '000.021.00001', executeCommandsLocally: true, runLocally: true) {
        capability "Actuator"
        capability "Sensor"
    	capability "Health Check"
        capability "Switch"

    }

    preferences {}

    tiles(scale: 2) {
        multiAttributeTile(name:"switch", type: "generic", width: 6, height: 4, canChangeIcon: true){
            tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
			attributeState "on", label:'${name}', action:"switch.off", icon:"st.thermostat.fan-on", backgroundColor:"#00A0DC", nextState:"turningOff"
			attributeState "off", label:'${name}', action:"switch.on", icon:"st.thermostat.fan-off", backgroundColor:"#ffffff", nextState:"turningOn"
			attributeState "turningOn", label:'${name}', action:"switch.off", icon:"st.thermostat.fan-on", backgroundColor:"#00A0DC", nextState:"turningOff"
			attributeState "turningOff", label:'${name}', action:"switch.on", icon:"st.thermostat.fan-off", backgroundColor:"#ffffff", nextState:"turningOn"
            }
        }

        standardTile("explicitOn", "device.switch", width: 2, height: 2, decoration: "flat") {
            state "default", label: "On", action: "switch.on", icon: "st.thermostat.fan-off", backgroundColor: "#ffffff"
        }
        standardTile("explicitOff", "device.switch", width: 2, height: 2, decoration: "flat") {
            state "default", label: "Off", action: "switch.off", icon: "st.thermostat.fan-on", backgroundColor: "#00A0DC"
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
    on()
}