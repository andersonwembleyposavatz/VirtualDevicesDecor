/**
 *  VIRTUAL Switch for Music Player
 *
 *  Copyright 2022 Anderson W Posavatz
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
    definition (name: "VIRTUAL DECOR Switch for Music Player", namespace: "andersonwembleyposavatz", author: "Anderson Wembley Posavatz", ocfDeviceType: "x.com.st.d.sensor.moisture", runLocally: true, minHubCoreVersion: '000.021.00001', executeCommandsLocally: true, mnmn: "SmartThings",) {
        capability "Actuator"
        capability "Sensor"
        capability "Switch"
        capability "Music Player"
   
    }

    tiles (scale: 2) {
        multiAttributeTile(name:"switch", type: "mediaPlayer", width: 6, height: 4, canChangeIcon: true) {
            tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
                attributeState "on", label:'${name}', action:"switch.off", icon:"st.Electronics.electronics5", backgroundColor:"#79b821"
                attributeState "off", label:'${name}', action:"switch.on", icon:"st.Electronics.electronics5", backgroundColor:"#ffffff"
            }
        }

        main "switch" 
        details "switch" 
    }
}

def parse(String description) {
    log.debug "Parsing '${description}'"
}

def on() {
    sendEvent(name: "switch", value: "on")
    log.debug "on"
   }

def off() { 
    sendEvent(name: "switch", value: "off")
    log.debug "off"
 
}