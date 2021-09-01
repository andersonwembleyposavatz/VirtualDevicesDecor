/**
 *  VIRTUAL Energy Monitor for Switch
 *
 *  Copyright 2021 Anderson Wembley Posavatz
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
	definition (name: "VIRTUAL Energy Monitor for Switch", namespace: "andersonwembleyposavatz", author: "Anderson Wembley Posavatz", cstHandler: true) {
		capability "Actuator"
		capability "Energy Meter"
		capability "Switch"
		capability "Execute"
		capability "Notification"
		capability "Power Meter"
		capability "Power Source"
		capability "Three Axis"
		capability "Voltage Measurement"
	}


	simulator {
		// TODO: define status and reply messages here
	}

	tiles {
		// TODO: define your main and details tiles here
	}
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
	// TODO: handle 'energy' attribute
	// TODO: handle 'switch' attribute
	// TODO: handle 'data' attribute
	// TODO: handle 'power' attribute
	// TODO: handle 'powerSource' attribute
	// TODO: handle 'threeAxis' attribute
	// TODO: handle 'voltage' attribute

}

// handle commands
def resetEnergyMeter() {
	log.debug "Executing 'resetEnergyMeter'"
	// TODO: handle 'resetEnergyMeter' command
}

def execute() {
	log.debug "Executing 'execute'"
	// TODO: handle 'execute' command
}

def deviceNotification() {
	log.debug "Executing 'deviceNotification'"
	// TODO: handle 'deviceNotification' command
}

def on() {
	log.debug "Executing 'on'"
	// TODO: handle 'deviceNotification' command
}

def off() {
	log.debug "Executing 'off'"
	// TODO: handle 'deviceNotification' command
}