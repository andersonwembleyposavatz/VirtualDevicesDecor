/**
 *  VIRTUAL Clone Temperature and Humidity
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
	definition (name: "VIRTUAL Clone Temperature and Humidity", namespace: "andersonwembleyposavatz", author: "Anderson Wembley Posavatz", cstHandler: true) {
		capability "Air Quality Sensor"
		capability "Execute"
		capability "Relative Humidity Measurement"
		capability "Temperature Measurement"
		capability "Thermostat Cooling Setpoint"
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
	// TODO: handle 'airQuality' attribute
	// TODO: handle 'data' attribute
	// TODO: handle 'humidity' attribute
	// TODO: handle 'temperature' attribute
	// TODO: handle 'coolingSetpoint' attribute

}

// handle commands
def execute() {
	log.debug "Executing 'execute'"
	// TODO: handle 'execute' command
}

def setCoolingSetpoint() {
	log.debug "Executing 'setCoolingSetpoint'"
	// TODO: handle 'setCoolingSetpoint' command
}