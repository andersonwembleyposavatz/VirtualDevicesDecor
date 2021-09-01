/**
 *  VIRTUAL Sensor para Cenas
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
	definition (name: "VIRTUAL Sensor para Cenas", namespace: "andersonwembleyposavatz", author: "Anderson Wembley Posavatz", cstHandler: true) {
		capability "Button"
		capability "Execute"
		capability "Sensor"
		capability "Health Check"
        capability "Configuration"
        capability "Refresh"
		capability "Log Trigger"
		capability "Notification"
		capability "Remote Control Status"
		capability "Switch"
		capability "Test Capability"
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
	// TODO: handle 'button' attribute
	// TODO: handle 'numberOfButtons' attribute
	// TODO: handle 'supportedButtonValues' attribute
	// TODO: handle 'data' attribute
	// TODO: handle 'logState' attribute
	// TODO: handle 'logRequestState' attribute
	// TODO: handle 'logInfo' attribute
	// TODO: handle 'remoteControlEnabled' attribute
	// TODO: handle 'switch' attribute

}

// handle commands
def execute() {
	log.debug "Executing 'execute'"
	// TODO: handle 'execute' command
}

def triggerLog() {
	log.debug "Executing 'triggerLog'"
	// TODO: handle 'triggerLog' command
}

def triggerLogWithLogInfo() {
	log.debug "Executing 'triggerLogWithLogInfo'"
	// TODO: handle 'triggerLogWithLogInfo' command
}

def triggerLogWithUrl() {
	log.debug "Executing 'triggerLogWithUrl'"
	// TODO: handle 'triggerLogWithUrl' command
}

def deviceNotification() {
	log.debug "Executing 'deviceNotification'"
	// TODO: handle 'deviceNotification' command
}

def on() {
	log.debug "Executing 'on'"
	// TODO: handle 'on' command
}

def off() {
	log.debug "Executing 'off'"
	// TODO: handle 'off' command
}