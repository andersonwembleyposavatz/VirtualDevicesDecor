/**
 *  Copyright 2017 SmartThings
 *
 *  Provides a virtual switch.
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
    definition (name: "VIRTUAL Humidifier DECOR", namespace: "smartthings", author: "SmartThings", runLocally: true, minHubCoreVersion: '000.021.00001', executeCommandsLocally: true, mnmn: "SmartThings", ocfDeviceType: "x.com.st.d.humidifier", vid: "generic-humidifier") {
        capability "Actuator"
        capability "Sensor"
        capability "Switch"
    }

    preferences {}

    tiles(scale: 2) {
        multiAttributeTile(name:"switch", type: "generic", width: 6, height: 4, canChangeIcon: true){
            tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
                attributeState "on", label:'', action:"switch.off", icon:"st.Appliances.appliances17", backgroundColor:"#00A0DC", nextState:"turningOff"
                attributeState "off", label:'', action:"switch.on", icon:"st.Appliances.appliances17", backgroundColor:"#FFFFFF", nextState:"turningOn"
                attributeState "turningOn", label:'', action:"switch.off", icon:"st.Appliances.appliances17", backgroundColor:"#00A0DC", nextState:"turningOff"
                attributeState "turningOff", label:'', action:"switch.on", icon:"st.Appliances.appliances17", backgroundColor:"#FFFFFF", nextState:"turningOn"
            }
        }

        standardTile("on", "device.switch", width: 2, height: 2, decoration: "flat") {
            state "default", label: '', action: "switch.on", icon: "st.Appliances.appliances17", backgroundColor: "#00A0DC"
        }
        standardTile("off", "device.switch", width: 2, height: 2, decoration: "flat") {
            state "default", label: '', action: "switch.off", icon: "st.Appliances.appliances17", backgroundColor: "#ffffff"
        }

        main "switch"
        details "switch", "on", "off"

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
    sendEvent(name: "switch", value: "off")
}