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
    definition (name: "VIRTUAL Control for Window Shade", namespace: "andersonwembleyposavatz", author: "Anderson Wembley Posavatz", ocfDeviceType: "oic.d.blind", runLocally: true, minHubCoreVersion: '000.021.00001', executeCommandsLocally: true, mnmn: "SmartThings",) {
        capability "Actuator"
        capability "Sensor"
        capability "Switch"
        //capability "Window Shade"
    }

    preferences {}

    tiles(scale: 2) {
        multiAttributeTile(name:"windowShade", type: "generic", width: 6, height: 4, canChangeIcon: true){
            tileAttribute ("device.windowShade", key: "PRIMARY_CONTROL") {
                attributeState "open", label:'${name}', action:"close", icon:"st.shades.shade-open", backgroundColor:"#79b821", nextState:"closing"
                attributeState "closed", label:'${name}', action:"open", icon:"st.shades.shade-closed", backgroundColor:"#ffffff", nextState:"opening", defaultState: true
                attributeState "opening", label:'${name}', action:"close", icon:"st.shades.shade-opening", backgroundColor:"#79b821", nextState:"closing"
                attributeState "closing", label:'${name}', action:"open", icon:"st.shades.shade-closing", backgroundColor:"#ffffff", nextState:"opening"
            }
		}

		standardTile("windowShadeOpen", "device.windowShade", width: 2, height: 2, decoration: "flat") {
			state "default", label: "open", action:"open", icon:"st.Home.home2"
		}
		standardTile("windowShadeClose", "device.windowShade", width: 2, height: 2, decoration: "flat") {
			state "default", label: "close", action:"close", icon:"st.Home.home2"
		}

        main(["windowShade"])
        details(["switch", "windowShade", "home", "On", "Off",  "Open", "Close"])

    }
}

def parse(description) {
}

def on() {
    sendEvent(value: "open")
}

def off() {
    sendEvent( value: "close")
}

def open() {
    sendEvent(value: "open", isStateChange: true)
}

def close() {
    sendEvent(value: "close", isStateChange: true)
    
}

def installed() {
    open()
}