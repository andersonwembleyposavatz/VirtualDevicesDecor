/**
 *  VIRTUAL Switch for MUSIC
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
    definition (name: "VIRTUAL DECOR for Audio System Switch", namespace: "andersonwembleyposavatz", author: "Anderson Wembley Posavatz", mnmn: "SmartThings", runLocally: true, ocfDeviceType: "x.com.st.d.audiosystem") {
        capability "Actuator"
        capability "Sensor"
        capability "Switch"
        

    }

    preferences {
    }

	// UI tile definitions
    tiles(scale: 2) {
        multiAttributeTile(name:"switch", type: "generic", width: 6, height: 4, canChangeIcon: false){
            tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
                attributeState "on", label:'${name}', action:"switch.off", icon:"https://client-assets.smartthings.com/oneui/home_theater-on@4x.png", backgroundColor:"#00a0dc", nextState:"turningOff"
                attributeState "off", label:'${name}', action:"switch.on", icon:"https://client-assets.smartthings.com/oneui/home_theater-off@4x.png", backgroundColor:"#ffffff", nextState:"turningOn"
                attributeState "turningOn", label:'${name}', action:"switch.off", icon:"hhttps://client-assets.smartthings.com/oneui/home_theater-on@4x.png", backgroundColor:"#00a0dc", nextState:"turningOff"
                attributeState "turningOff", label:'${name}', action:"switch.on", icon:"https://client-assets.smartthings.com/oneui/home_theater-off@4x.png", backgroundColor:"#ffffff", nextState:"turningOn"
                }
			}
        standardTile("explicitOn", "device.switch", width: 2, height: 2, decoration: "flat") {
            state "default", label: "On", action: "switch.on", icon: "https://client-assets.smartthings.com/oneui/home_theater-on@4x.png", backgroundColor: "#00a0dc"
        }
        standardTile("explicitOff", "device.switch", width: 2, height: 2, decoration: "flat") {
            state "default", label: "Off", action: "switch.off", icon: "https://client-assets.smartthings.com/oneui/home_theater-off@4x.png", backgroundColor: "#ffffff"
        }

        main "switch"
        details "switch", "explicitOn", "explicitOff", "On", "Off"
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

def explicitOn() {
    sendEvent(name: "switch", value: "on")
}

def explicitOff() {	
    sendEvent(name: "switch", value: "off")
}

def installed() {
    on()
    }