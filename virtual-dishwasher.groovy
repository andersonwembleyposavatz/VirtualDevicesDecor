/**
 *  VIRTUAL Dishwasher
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
    definition (name: "VIRTUAL Dishwasher", namespace: "andersonwembleyposavatz", author: "Anderson Wembley Posavatz", ocfDeviceType: "oic.d.dishwasher", runLocally: true, minHubCoreVersion: '000.021.00001', executeCommandsLocally: true, mnmn: "SmartThings",) {
		capability "Dishwasher Mode"
		capability "Dishwasher Operating State"
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
	// TODO: handle 'dishwasherMode' attribute
	// TODO: handle 'machineState' attribute
	// TODO: handle 'supportedMachineStates' attribute
	// TODO: handle 'dishwasherJobState' attribute
	// TODO: handle 'completionTime' attribute

}

// handle commands
def setDishwasherMode() {
	log.debug "Executing 'setDishwasherMode'"
	// TODO: handle 'setDishwasherMode' command
}

def setMachineState() {
	log.debug "Executing 'setMachineState'"
	// TODO: handle 'setMachineState' command
}

def installed() {
    on()
}