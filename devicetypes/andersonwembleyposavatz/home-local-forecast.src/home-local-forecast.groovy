/**
 *  HOME LOCAL FORECAST
 *
 *  Copyright 2022 Anderson Wembley Posavatz
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
	definition (name: "HOME LOCAL FORECAST", namespace: "andersonwembleyposavatz", author: "Anderson Wembley Posavatz", cstHandler: true) {
		capability "Activity Sensor"
		capability "Air Quality Sensor"
		capability "Atmospheric Pressure Measurement"
		capability "Carbon Dioxide Measurement"
		capability "Carbon Monoxide Measurement"
		capability "Equivalent Carbon Dioxide Measurement"
		capability "Geolocation"
		capability "Illuminance Measurement"
		capability "Location Mode"
		capability "Precipitation Measurement"
		capability "Radon Measurement"
		capability "Relative Humidity Measurement"
		capability "Tamper Alert"
		capability "Temperature Alarm"
		capability "Temperature Measurement"
		capability "Ultraviolet Index"
		capability "Wind Speed"
		capability "pH Measurement"
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
	// TODO: handle 'activity' attribute
	// TODO: handle 'airQuality' attribute
	// TODO: handle 'atmosphericPressure' attribute
	// TODO: handle 'carbonDioxide' attribute
	// TODO: handle 'carbonMonoxideLevel' attribute
	// TODO: handle 'equivalentCarbonDioxideMeasurement' attribute
	// TODO: handle 'latitude' attribute
	// TODO: handle 'longitude' attribute
	// TODO: handle 'method' attribute
	// TODO: handle 'accuracy' attribute
	// TODO: handle 'altitudeAccuracy' attribute
	// TODO: handle 'heading' attribute
	// TODO: handle 'speed' attribute
	// TODO: handle 'lastUpdateTime' attribute
	// TODO: handle 'illuminance' attribute
	// TODO: handle 'mode' attribute
	// TODO: handle 'precipitationLevel' attribute
	// TODO: handle 'radonLevel' attribute
	// TODO: handle 'humidity' attribute
	// TODO: handle 'tamper' attribute
	// TODO: handle 'temperatureAlarm' attribute
	// TODO: handle 'temperature' attribute
	// TODO: handle 'ultravioletIndex' attribute
	// TODO: handle 'windspeed' attribute
	// TODO: handle 'pH' attribute

}

// handle commands
def setMode() {
	log.debug "Executing 'setMode'"
	// TODO: handle 'setMode' command
}

def resetPrecipitationLevel() {
	log.debug "Executing 'resetPrecipitationLevel'"
	// TODO: handle 'resetPrecipitationLevel' command
}