/**
 *  andersonwembleyposavatz
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
	definition (name: "VIRTUAL Weather", namespace: "andersonwembleyposavatz", author: "Anderson Wembley Posavatz", cstHandler: true) {
		capability "Activity Sensor"
		capability "Air Quality Sensor"
		capability "Alarm"
		capability "Atmospheric Pressure Measurement"
		capability "Carbon Dioxide Measurement"
		capability "Carbon Monoxide Measurement"
		capability "Dew Point"
		capability "Energy Meter"
		capability "Equivalent Carbon Dioxide Measurement"
		capability "Geofence"
		capability "Geolocation"
		capability "Illuminance Measurement"
		capability "Image Capture"
		capability "Infrared Level"
		capability "Location Mode"
		capability "Notification"
		capability "Precipitation Measurement"
		capability "Precipitation Rate"
		capability "Relative Humidity Measurement"
		capability "Tamper Alert"
		capability "Temperature Measurement"
		capability "Ultraviolet Index"
		capability "Wind Speed"
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
	// TODO: handle 'alarm' attribute
	// TODO: handle 'atmosphericPressure' attribute
	// TODO: handle 'carbonDioxide' attribute
	// TODO: handle 'carbonMonoxideLevel' attribute
	// TODO: handle 'dewpoint' attribute
	// TODO: handle 'energy' attribute
	// TODO: handle 'equivalentCarbonDioxideMeasurement' attribute
	// TODO: handle 'name' attribute
	// TODO: handle 'geofence' attribute
	// TODO: handle 'enableState' attribute
	// TODO: handle 'latitude' attribute
	// TODO: handle 'longitude' attribute
	// TODO: handle 'method' attribute
	// TODO: handle 'accuracy' attribute
	// TODO: handle 'altitudeAccuracy' attribute
	// TODO: handle 'heading' attribute
	// TODO: handle 'speed' attribute
	// TODO: handle 'lastUpdateTime' attribute
	// TODO: handle 'illuminance' attribute
	// TODO: handle 'image' attribute
	// TODO: handle 'captureTime' attribute
	// TODO: handle 'encrypted' attribute
	// TODO: handle 'infraredLevel' attribute
	// TODO: handle 'mode' attribute
	// TODO: handle 'precipitationLevel' attribute
	// TODO: handle 'precipitationRate' attribute
	// TODO: handle 'humidity' attribute
	// TODO: handle 'tamper' attribute
	// TODO: handle 'temperature' attribute
	// TODO: handle 'ultravioletIndex' attribute
	// TODO: handle 'windspeed' attribute

}

// handle commands
def off() {
	log.debug "Executing 'off'"
	// TODO: handle 'off' command
}

def strobe() {
	log.debug "Executing 'strobe'"
	// TODO: handle 'strobe' command
}

def siren() {
	log.debug "Executing 'siren'"
	// TODO: handle 'siren' command
}

def both() {
	log.debug "Executing 'both'"
	// TODO: handle 'both' command
}

def resetEnergyMeter() {
	log.debug "Executing 'resetEnergyMeter'"
	// TODO: handle 'resetEnergyMeter' command
}

def setName() {
	log.debug "Executing 'setName'"
	// TODO: handle 'setName' command
}

def setGeofence() {
	log.debug "Executing 'setGeofence'"
	// TODO: handle 'setGeofence' command
}

def setEnableState() {
	log.debug "Executing 'setEnableState'"
	// TODO: handle 'setEnableState' command
}

def take() {
	log.debug "Executing 'take'"
	// TODO: handle 'take' command
}

def setInfraredLevel() {
	log.debug "Executing 'setInfraredLevel'"
	// TODO: handle 'setInfraredLevel' command
}

def setMode() {
	log.debug "Executing 'setMode'"
	// TODO: handle 'setMode' command
}

def deviceNotification() {
	log.debug "Executing 'deviceNotification'"
	// TODO: handle 'deviceNotification' command
}

def resetPrecipitationLevel() {
	log.debug "Executing 'resetPrecipitationLevel'"
	// TODO: handle 'resetPrecipitationLevel' command
}