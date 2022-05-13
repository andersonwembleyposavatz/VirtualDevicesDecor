/**
 *  The Weather Company Web
 *
 *  Copyright 2019 Philippe Portes
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
	definition (name: "The Weather Company Web", namespace: "philippeportesppo", author: "Philippe Portes") {
		capability "refresh"
        capability "polling"
        capability "sensor"
        capability "capability.temperatureMeasurement"
        capability "capability.relativeHumidityMeasurement"
        capability "Illuminance Measurement"
        capability "Ultraviolet Index"
        capability "Wind Speed"
        capability "stsmartweather.windSpeed"
        capability "stsmartweather.windDirection"
        capability "stsmartweather.apparentTemperature"
        capability "stsmartweather.astronomicalData"
        capability "stsmartweather.precipitation"
        capability "stsmartweather.ultravioletDescription"
        capability "stsmartweather.weatherAlert"
        capability "stsmartweather.weatherForecast"
        capability "stsmartweather.weatherSummary"
	}

	tiles(scale: 2) {

	standardTile("city", "device.city",  width: 6, height: 3,  canChangeIcon: true ) {
            state "default", label: '${currentValue}%'}   
             
    standardTile("temperature", "device.temperature", width: 2, height: 2, canChangeIcon: false) {
            state "default", label: '${currentValue}º',unit:'${currentValue}', icon: "st.Weather.weather2", backgroundColor:"#999999"}  
        
	standardTile("humidity", "device.humidity", width: 2, height: 2, canChangeIcon: false) {
            state "default", label: '${currentValue}%', icon: "st.Weather.weather12", backgroundColor:"#999999"      }
            

            
    standardTile("refresh", "device.refresh", decoration: "flat", width: 2, height: 2) {
 		state "default", action:"refresh", icon:"st.secondary.refresh"
 		} 
	
    standardTile("weather", "device.weather", width: 6, height: 2) {
 		state "default", label:'${currentValue}'
 		
         }

        valueTile("feelsLike", "device.feelsLike", decoration: "flat", height: 1, width: 2) {
            state "default", label:'Feels like ${currentValue}°'
        }
            
    standardTile("refresh", "device.refresh", decoration: "flat", width: 2, height: 2) {
 		state "default", action:"refresh", icon:"st.secondary.refresh"
 		} 
	
    standardTile("weather", "device.weather", width: 6, height: 2) {
 		state "default", label:'${currentValue}'
 		
        } 

        valueTile("humidity", "device.humidity", decoration: "flat", height: 1, width: 2) {
            state "default", label:'${currentValue}% humidity'
        }

        valueTile("wind", "device.windVector", decoration: "flat", height: 1, width: 2) {
            state "default", label:'Wind\n${currentValue}'
        }

        valueTile("weather", "device.weather", decoration: "flat", height: 1, width: 2) {
            state "default", label:'${currentValue}'
        }

        valueTile("city", "device.city", decoration: "flat", height: 1, width: 2) {
            state "default", label:'${currentValue}'
        }

        valueTile("percentPrecip", "device.percentPrecip", decoration: "flat", height: 1, width: 2) {
            state "default", label:'${currentValue}% precip'
        }

        valueTile("ultravioletIndex", "device.uvDescription", decoration: "flat", height: 1, width: 2) {
            state "default", label:'UV ${currentValue}'
        }

        valueTile("alert", "device.alert", decoration: "flat", height: 2, width: 6) {
            state "default", label:'${currentValue}'
        }

        standardTile("refresh", "device.refresh", decoration: "flat", height: 1, width: 2) {
            state "default", label: "", action: "refresh", icon:"st.secondary.refresh"
        }

        valueTile("rise", "device.localSunrise", decoration: "flat", height: 1, width: 2) {
            state "default", label:'Sunrise ${currentValue}'
        }

        valueTile("set", "device.localSunset", decoration: "flat", height: 1, width: 2) {
            state "default", label:'Sunset ${currentValue}'
        }

        valueTile("light", "device.illuminance", decoration: "flat", height: 1, width: 2) {
            state "default", label:'${currentValue} lux'
        }

        valueTile("today", "device.forecastToday", decoration: "flat", height: 1, width: 3) {
            state "default", label:'Today:\n${currentValue}'
        }

        valueTile("tonight", "device.forecastTonight", decoration: "flat", height: 1, width: 3) {
            state "default", label:'Tonight:\n${currentValue}'
        }

        valueTile("tomorrow", "device.forecastTomorrow", decoration: "flat", height: 1, width: 3) {
            state "default", label:'Tomorrow:\n${currentValue}'
        }

        valueTile("lastUpdate", "device.lastUpdate", decoration: "flat", height: 1, width: 3) {
            state "default", label:'Last update:\n${currentValue}'
        }

        main("temperature", "feelsLike", "city")
        details(["temperature", "feelsLike", "weatherIcon", "humidity", "wind",
                 "weather", "city", "percentPrecip", "ultravioletIndex", "light",
                 "rise", "set",
                 "refresh",
                 "today", "tonight", "tomorrow", "lastUpdate",
                 "alert"])
                 
                 }
}

def installed() {
    

}

def updated() {

	log.debug "Executing 'updated'"
   
	refresh()
}

def poll(){
log.debug "Executing 'poll'"
    refresh()
}

String convertTemperature( float temperatureCelcius, unit)
{
	float value = temperatureCelcius 
    // No clue yet how to know if units are F or C in TWC interface so do nothing
    //if (unit =="F")
    //{
    //   value = temperatureCelcius * 1.8 + 32.0
    // }
    return value.toString().format(java.util.Locale.US,"%.1f", value)
}

// parse events into attributes
def parse(String description) {
	log.debug "Executing 'parse'"
    
   	state.snowalert=false
    state.stormalert=false
    state.rainmalert=false
	state.lowtempalert=false
	state.hightempalert=false
    state.lowhumidityalert=false
    state.highhumidityalert=false    
    refresh()    
    runEvery10Minutes(forcepoll)
    
}

def forcepoll()
{
	refresh()
}

// handle commands
def refresh() {
	log.debug "Executing 'refresh'"
    
    def mymap = getTwcConditions()
    
    log.debug mymap

	/*log.debug "state.snowalert=${state.snowalert}"
    log.debug "state.stormalert=${state.stormalert}"
    log.debug "state.rainmalert=${state.rainmalert}"
    log.debug "state.lowtempalert=${state.lowtempalert}"
    log.debug "state.hightempalert=${state.hightempalert}"
    log.debug "state.lowhumidityalert=${state.lowhumidityalert}"
    log.debug "state.highhumidityalert=${state.highhumidityalert}"*/


    log.debug "response feelslike_c: ${mymap['temperatureFeelsLike']}"
    log.debug "response dewpoint_c: ${mymap['temperatureDewPoint']}"
    log.debug "response relative_humidity: ${mymap['relativeHumidity']}"
    log.debug "response temp_c: ${mymap['temperature']}"
    log.debug "response weather: ${mymap['wxPhraseMedium']}"

    //log.debug "Generating events for UX refresh"
    def temperatureScale = getTemperatureScale()

    // UnderGround Weather references
    sendEvent(name: "TWCFeelsLikelevel", value: mymap['temperatureFeelsLike'], unit: temperatureScale)
    sendEvent(name: "TWCdewpointlevel", value: mymap['temperatureDewPoint'], unit: temperatureScale)
    sendEvent(name: "humidity", value:  mymap['relativeHumidity'])
    sendEvent(name: "temperature", value: mymap['temperature'], unit: temperatureScale)
    sendEvent(name: "TWC_Icon_UrlIcon", value: mymap['iconCode'])
    sendEvent(name: "TWC_main", value: mymap['iconCode'])
    sendEvent(name: "weather", value: mymap['wxPhraseMedium'], displayed:true, isStateChange: true)


    if (getDataValue("TWCsnowalert")=="True" && mymap['wxPhraseMedium'].contains("Snow"))
    {
        // if ( state.snowalert == false) {
            sendEvent(name:"Alert", value: "TWC Snow Alert!", displayed:true)
        //    state.snowalert=true  }
    }
    else
        state.snowalert=false

    if (getDataValue("TWCrainalert")=="True" && (mymap['wxPhraseMedium'].contains("Rain") || mymap['wxPhraseMedium'].contains("Shower")))
    {
        // if ( state.rainalert == false) {
            sendEvent(name:"Alert", value: "TWC Rain Alert!", displayed:true)
        //    state.rainalert=true  }
    }
    else
        state.rainalert=false

    if (getDataValue("TWCstormalert")=="True" && mymapmymap['wxPhraseMedium'].contains("Storm"))
    {
        // if ( state.stormalert == false) {
            sendEvent(name:"Alert", value: "TWC Storm Alert!", displayed:true)
        //    state.stormalert=true  }
    }
    else
        state.stormalert=false

    if (getDataValue("TWClowtempalert")!="null") {
        if (getDataValue("TWClowtempalert").toFloat() >= mymap['temperature'].toFloat())
        {

            //if ( state.lowtempalert == false) {
                sendEvent(name:"Alert", value: "TWC Low Temperature Alert!", displayed:true)
            //    state.lowtempalert=true }
        }
        else
            state.lowtempalert=false
    }

    if (getDataValue("TWChightempalert")!="null") {
        if (getDataValue("TWChightempalert").toFloat() <= mymap['temperature'].toFloat())
        {

            //if ( state.hightempalert == false) {
                sendEvent(name:"Alert", value: "TWC High Temperature Alert!", displayed:true)
            //    state.hightempalert=true }
        }
        else
            state.hightempalert=false
    }

    if (getDataValue("TWClowhumidityalert")!="null") {
        if (getDataValue("TWClowhumidityalert").toFloat() >= mymap['relativeHumidity'].toFloat())
        {

            //if ( state.lowhumidityalert == false) {

                sendEvent(name:"Alert", value: "TWC Low Humidity Alert!", displayed:true)
            //    state.lowhumidityalert=true }
        }
        else
        {
            state.lowhumidityalert=false

        }
    }

    if (getDataValue("TWChighhumidityalert")!="null") {

        if (getDataValue("TWChighhumidityalert").toFloat() <= mymap['relativeHumidity'].toFloat())
        {
            //if ( state.highhumidityalert == false) {
                sendEvent(name:"Alert", value: "TWC High Humidity Alert!", displayed:true)
            //    state.highhumidityalert=true }
        }
        else
            state.highhumidityalert=false
    }

}    