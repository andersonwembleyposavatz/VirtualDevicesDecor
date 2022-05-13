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
    definition (name: "VIRTUAL DECOR for MUSIC Speakers", namespace: "andersonwembleyposavatz", author: "Anderson Wembley Posavatz", runLocally: true, ocfDeviceType: "oic.d.networkaudio" ) {
        capability "Actuator"
        capability "Sensor"
        capability "Switch"
        
    	}

	preferences {

	}

	// UI tile definitions
    tiles(scale: 2) {
        multiAttributeTile(name:"switch", type: "generic", width: 6, height: 4, canChangeIcon: true){
            tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
                attributeState "on", label:'${name}', action:"switch.off", icon:"https://client-assets.smartthings.com/oneui/x.com.st.d.networkaudio-on@4x.png", backgroundColor:"#00a0dc", nextState:"turningOff"
                attributeState "off", label:'${name}', action:"switch.on", icon:"https://client-assets.smartthings.com/oneui/x.com.st.d.networkaudio-off@4x.png", backgroundColor:"#ffffff", nextState:"turningOn"
                attributeState "turningOn", label:'${name}', action:"switch.off", icon:"https://client-assets.smartthings.com/oneui/x.com.st.d.networkaudio-on@4x.png", backgroundColor:"#00a0dc", nextState:"turningOff"
                attributeState "turningOff", label:'${name}', action:"switch.on", icon:"https://client-assets.smartthings.com/oneui/x.com.st.d.networkaudio-off@4x.png", backgroundColor:"#ffffff", nextState:"turningOn"
                }
			}
        standardTile("on", "device.switch", width: 2, height: 2, decoration: "flat") {
            state "default", label:"On", action: "switch.on", icon: "https://client-assets.smartthings.com/oneui/x.com.st.d.networkaudio-on@2x.png", backgroundColor: "#00a0dc"
        }
        standardTile("off", "device.switch", width: 2, height: 2, decoration: "flat") {
            state "default", label:"Off", action: "switch.off", icon: "https://client-assets.smartthings.com/oneui/x.com.st.d.networkaudio-off@2x.png", backgroundColor: "#ffffff"
        }

        main "switch"
        details "switch", "explicitOn", "explicitOff"
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
