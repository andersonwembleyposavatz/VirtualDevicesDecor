/**
 *  Copyright 2015 SmartThings
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
 *  SmartWeather Station
 *
 *  Author: SmartThings
 *
 *  Date: 2013-04-30
 */
metadata {
    definition (name: "SmartWeather Station Tile 3", namespace: "smartthings", author: "SmartThings") {
        capability "Illuminance Measurement"
        capability "Temperature Measurement"
        capability "Relative Humidity Measurement"
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
        capability "Sensor"
        capability "Refresh"
    }

    preferences {
        input "zipCode", "text", title: "Zip Code (optional)", required: false
        input "stationId", "text", title: "Personal Weather Station ID (optional)", required: false
    }

    tiles(scale: 2) {
        valueTile("temperature", "device.temperature", height: 2, width: 2) {
            state "default", label:'${currentValue}°', icon: "st.Weather.weather2", 
                   backgroundColors:[
                            // Celsius
                            [value: 0, color: "#153591"],
                            [value: 7, color: "#1e9cbb"],
                            [value: 15, color: "#90d2a7"],
                            [value: 23, color: "#44b621"],
                            [value: 28, color: "#f1d801"],
                            [value: 35, color: "#d04e00"],
                            [value: 37, color: "#bc2323"],
                            // Fahrenheit
                            [value: 40, color: "#153591"],
                            [value: 44, color: "#1e9cbb"],
                            [value: 59, color: "#90d2a7"],
                            [value: 74, color: "#44b621"],
                            [value: 84, color: "#f1d801"],
                            [value: 95, color: "#d04e00"],
                            [value: 96, color: "#bc2323"]
                    ]

              
            }
            
        
        valueTile("feelsLike", "device.feelsLike", decoration: "flat", height: 2, width: 2) {
            state "default", label:'Feels like ${currentValue}°', icon: "st.Weather.weather2"
            
        }

        standardTile("weatherIcon", "device.weatherIcon", decoration: "flat", height: 2, width: 2) {
            state "0", icon:"https://smartthings-twc-icons.s3.amazonaws.com/00.png", label: ""
            state "1", icon:"https://smartthings-twc-icons.s3.amazonaws.com/01.png", label: ""
            state "2", icon:"https://smartthings-twc-icons.s3.amazonaws.com/02.png", label: ""
            state "3", icon:"https://smartthings-twc-icons.s3.amazonaws.com/03.png", label: ""
            state "4", icon:"https://smartthings-twc-icons.s3.amazonaws.com/04.png", label: ""
            state "5", icon:"https://smartthings-twc-icons.s3.amazonaws.com/05.png", label: ""
            state "6", icon:"https://smartthings-twc-icons.s3.amazonaws.com/06.png", label: ""
            state "7", icon:"https://smartthings-twc-icons.s3.amazonaws.com/07.png", label: ""
            state "8", icon:"https://smartthings-twc-icons.s3.amazonaws.com/08.png", label: ""
            state "9", icon:"https://smartthings-twc-icons.s3.amazonaws.com/09.png", label: ""
            state "10", icon:"https://smartthings-twc-icons.s3.amazonaws.com/10.png", label: ""
            state "11", icon:"https://smartthings-twc-icons.s3.amazonaws.com/11.png", label: ""
            state "12", icon:"https://smartthings-twc-icons.s3.amazonaws.com/12.png", label: ""
            state "13", icon:"https://smartthings-twc-icons.s3.amazonaws.com/13.png", label: ""
            state "14", icon:"https://smartthings-twc-icons.s3.amazonaws.com/14.png", label: ""
            state "15", icon:"https://smartthings-twc-icons.s3.amazonaws.com/15.png", label: ""
            state "16", icon:"https://smartthings-twc-icons.s3.amazonaws.com/16.png", label: ""
            state "17", icon:"https://smartthings-twc-icons.s3.amazonaws.com/17.png", label: ""
            state "18", icon:"https://smartthings-twc-icons.s3.amazonaws.com/18.png", label: ""
            state "19", icon:"https://smartthings-twc-icons.s3.amazonaws.com/19.png", label: ""
            state "20", icon:"https://smartthings-twc-icons.s3.amazonaws.com/20.png", label: ""
            state "21", icon:"https://smartthings-twc-icons.s3.amazonaws.com/21.png", label: ""
            state "22", icon:"https://smartthings-twc-icons.s3.amazonaws.com/22.png", label: ""
            state "23", icon:"https://smartthings-twc-icons.s3.amazonaws.com/23.png", label: ""
            state "24", icon:"https://smartthings-twc-icons.s3.amazonaws.com/24.png", label: ""
            state "25", icon:"https://smartthings-twc-icons.s3.amazonaws.com/25.png", label: ""
            state "26", icon:"https://smartthings-twc-icons.s3.amazonaws.com/26.png", label: ""
            state "27", icon:"https://smartthings-twc-icons.s3.amazonaws.com/27.png", label: ""
            state "28", icon:"https://smartthings-twc-icons.s3.amazonaws.com/28.png", label: ""
            state "29", icon:"https://smartthings-twc-icons.s3.amazonaws.com/29.png", label: ""
            state "30", icon:"https://smartthings-twc-icons.s3.amazonaws.com/30.png", label: ""
            state "31", icon:"https://smartthings-twc-icons.s3.amazonaws.com/31.png", label: ""
            state "32", icon:"https://smartthings-twc-icons.s3.amazonaws.com/32.png", label: ""
            state "33", icon:"https://smartthings-twc-icons.s3.amazonaws.com/33.png", label: ""
            state "34", icon:"https://smartthings-twc-icons.s3.amazonaws.com/34.png", label: ""
            state "35", icon:"https://smartthings-twc-icons.s3.amazonaws.com/35.png", label: ""
            state "36", icon:"https://smartthings-twc-icons.s3.amazonaws.com/36.png", label: ""
            state "37", icon:"https://smartthings-twc-icons.s3.amazonaws.com/37.png", label: ""
            state "38", icon:"https://smartthings-twc-icons.s3.amazonaws.com/38.png", label: ""
            state "39", icon:"https://smartthings-twc-icons.s3.amazonaws.com/39.png", label: ""
            state "40", icon:"https://smartthings-twc-icons.s3.amazonaws.com/40.png", label: ""
            state "41", icon:"https://smartthings-twc-icons.s3.amazonaws.com/41.png", label: ""
            state "42", icon:"https://smartthings-twc-icons.s3.amazonaws.com/42.png", label: ""
            state "43", icon:"https://smartthings-twc-icons.s3.amazonaws.com/43.png", label: ""
            state "44", icon:"https://smartthings-twc-icons.s3.amazonaws.com/44.png", label: ""
            state "45", icon:"https://smartthings-twc-icons.s3.amazonaws.com/45.png", label: ""
            state "46", icon:"https://smartthings-twc-icons.s3.amazonaws.com/46.png", label: ""
            state "47", icon:"https://smartthings-twc-icons.s3.amazonaws.com/47.png", label: ""
            state "na", icon:"st.alarm.temperature.normal", label: ""
        }

        valueTile("humidity", "device.humidity", decoration: "flat", height: 2, width: 2) {
            state "default", label:'${currentValue}% humidity', icon: "st.Weather.weather12"
        }

        valueTile("wind", "device.windVector", decoration: "flat", height: 2, width: 2) {
            state "default", label:'Wind\n${currentValue}', icon: "st.Outdoor.outdoor20"
        }

        valueTile("weather", "device.weather", decoration: "flat", height: 2, width: 2) {
            state "default", label:'${currentValue}', icon: "st.Weather.weather11"
        }

        valueTile("city", "device.city", decoration: "flat", height: 2, width: 2) {
            state "default", label:'${currentValue}', icon: "st.Home.home15"
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

        standardTile("refresh", "device.refresh", decoration: "flat", height: 2, width: 2) {
            state "default", label: "", action: "refresh", icon:"st.secondary.refresh"
        }

        valueTile("rise", "device.localSunrise", decoration: "flat", height: 2, width: 2) {
            state "default", label:'Sunrise ${currentValue}'
        }

        valueTile("set", "device.localSunset", decoration: "flat", height: 2, width: 2) {
            state "default", label:'Sunset ${currentValue}'
        }

        valueTile("light", "device.illuminance", decoration: "flat", height: 2, width: 2) {
            state "default", label:'${currentValue} lux'
        }

        valueTile("today", "device.forecastToday", decoration: "flat", height: 2, width: 3) {
            state "default", label:'Today:\n${currentValue}'
        }

        valueTile("tonight", "device.forecastTonight", decoration: "flat", height: 2, width: 3) {
            state "default", label:'Tonight:\n${currentValue}'
        }

        valueTile("tomorrow", "device.forecastTomorrow", decoration: "flat", height: 2, width: 3) {
            state "default", label:'Tomorrow:\n${currentValue}'
        }

        valueTile("lastUpdate", "device.lastUpdate", decoration: "flat", height: 2, width: 3) {
            state "default", label:'Last update:\n${currentValue}'
        }

        main(["temperature", "weatherIcon","feelsLike"])
        details(["temperature", "feelsLike", "weatherIcon", "humidity", "wind",
                 "weather", "city", "percentPrecip", "ultravioletIndex", "light",
                 "rise", "set",
                 "refresh",
                 "today", "tonight", "tomorrow", "lastUpdate",
                 "alert"])}
}

// parse events into attributes
def parse(String description) {
    log.debug "Parsing '${description}'"
}

def installed() {
    schedulePoll()
    poll()
}

def schedulePoll() {
    unschedule()
    runEvery3Hours("poll")
}

def updated() {
    schedulePoll()
    poll()
}

def uninstalled() {
    unschedule()
}

// handle commands
def poll() {
    log.debug "WUSTATION: Executing 'poll', location: ${location.name}"
    if (stationId) {
        pollUsingPwsId(stationId.toUpperCase())
    } else {
        if (zipCode && zipCode.toUpperCase().startsWith('PWS:')) {
            log.debug zipCode.substring(4)
            pollUsingPwsId(zipCode.substring(4).toUpperCase())
        } else {
            pollUsingZipCode(zipCode?.toUpperCase())
        }
    }
}

def pollUsingZipCode(String zipCode) {
    // Last update time stamp
    def timeZone = location.timeZone ?: timeZone(timeOfDay)
    def timeStamp = new Date().format("yyyy MMM dd EEE h:mm:ss a", location.timeZone)
    send(name: "lastUpdate", value: timeStamp)

    // Current conditions
    def tempUnits = getTemperatureScale()
    def windUnits = tempUnits == "C" ? "KPH" : "MPH"
    def obs = getTwcConditions(zipCode)
    if (obs) {
        // TODO def weatherIcon = obs.icon_url.split("/")[-1].split("\\.")[0]

        send(name: "temperature", value: obs.temperature, unit: tempUnits)
        send(name: "feelsLike", value: obs.temperatureFeelsLike, unit: tempUnits)

        send(name: "humidity", value: obs.relativeHumidity, unit: "%")
        send(name: "weather", value: obs.wxPhraseLong)
        send(name: "weatherIcon", value: obs.iconCode, displayed: false)

        send(name: "wind", value: obs.windSpeed, unit: windUnits)
        send(name: "windspeed", value: new BigDecimal(convertWindSpeed(obs.windSpeed, tempUnits == "F" ? "imperial" : "metric", "metric") / 3.6).setScale(2, BigDecimal.ROUND_HALF_UP), unit: "m/s")
        send(name: "windVector", value: "${obs.windDirectionCardinal} ${obs.windSpeed} ${windUnits}")

        log.trace "Getting location info"
        def loc = getTwcLocation(zipCode)?.location
        def cityValue = createCityName(loc) ?: zipCode // I don't think we'll ever hit a point where we can't build a city name... But just in case...
        if (cityValue != device.currentValue("city")) {
            send(name: "city", value: cityValue, isStateChange: true)
        }

        send(name: "ultravioletIndex", value: obs.uvIndex)
        send(name: "uvDescription", value: obs.uvDescription)

        def dtf = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")

        def sunriseDate = dtf.parse(obs.sunriseTimeLocal)
        log.debug "'${obs.sunriseTimeLocal}'"

        def sunsetDate = dtf.parse(obs.sunsetTimeLocal)

        def tf = new java.text.SimpleDateFormat("h:mm a")
        tf.setTimeZone(TimeZone.getTimeZone(loc?.ianaTimeZone))

        def localSunrise = "${tf.format(sunriseDate)}"
        def localSunset = "${tf.format(sunsetDate)}"
        send(name: "localSunrise", value: localSunrise, descriptionText: "Sunrise today is at $localSunrise")
        send(name: "localSunset", value: localSunset, descriptionText: "Sunset today at is $localSunset")

        send(name: "illuminance", value: estimateLux(obs, sunriseDate, sunsetDate))

        // Forecast
        def f = getTwcForecast(zipCode)
        if (f) {
            def icon = f.daypart[0].iconCode[0] != null ? f.daypart[0].iconCode[0] : f.daypart[0].iconCode[1]
            def precip = f.daypart[0].precipChance[0] != null ? f.daypart[0].precipChance[0] : f.daypart[0].precipChance[1]
            def narrative = f.daypart[0].narrative

            send(name: "percentPrecip", value: precip, unit: "%")
            send(name: "forecastIcon", value: icon, displayed: false)
            send(name: "forecastToday", value: narrative[0] ?: "n/a")
            send(name: "forecastTonight", value: narrative[1] ?: "n/a")
            send(name: "forecastTomorrow", value: narrative[2] ?: "n/a")
        } else {
            log.warn "Forecast not found"
            send(name: "percentPrecip", value: 0, unit: "%", descriptionText: "Chance of precipitation could not be found")
            send(name: "forecastIcon", value: "", displayed: false)
            send(name: "forecastToday", value: "n/a", descriptionText: "Today's forecast could not be found")
            send(name: "forecastTonight", value: "n/a", descriptionText: "Tonight's forecast could not be found")
            send(name: "forecastTomorrow", value: "n/a", descriptionText: "Tomorrow's forecast could not be found")
        }

        // Alerts
        def alerts = getTwcAlerts("${loc?.latitude},${loc?.longitude}")
        if (alerts) {
            alerts.each {alert ->
                def msg = alert.headlineText
                if (alert.effectiveTimeLocal && !msg.contains(" from ")) {
                    msg += " from ${parseAlertTime(alert.effectiveTimeLocal).format("E hh:mm a", TimeZone.getTimeZone(alert.effectiveTimeLocalTimeZone))}"
                }
                if (alert.expireTimeLocal && !msg.contains(" until ")) {
                    msg += " until ${parseAlertTime(alert.expireTimeLocal).format("E hh:mm a", TimeZone.getTimeZone(alert.expireTimeLocalTimeZone))}"
                }
                send(name: "alert", value: msg, descriptionText: msg)
            }
        } else {
            send(name: "alert", value: "No current alerts", descriptionText: msg)
        }
    } else {
        log.warn "No response from TWC API"
    }

    return null
}

def pollUsingPwsId(String stationId) {
    // Last update time stamp
    def timeZone = location.timeZone ?: timeZone(timeOfDay)
    def timeStamp = new Date().format("yyyy MMM dd EEE h:mm:ss a", location.timeZone)
    sendEvent(name: "lastUpdate", value: timeStamp)

    // Current conditions
    def tempUnits = getTemperatureScale()
    def windUnits = tempUnits == "C" ? "KPH" : "MPH"
    def obsWrapper = getTwcPwsConditions(stationId)
    if (obsWrapper && obsWrapper.observations && obsWrapper.observations.size()) {
        def obs = obsWrapper.observations[0]
        def dataScale = obs.imperial ? 'imperial' : 'metric'

        send(name: "temperature", value: convertTemperature(obs[dataScale].temp, dataScale, tempUnits), unit: tempUnits)
        send(name: "feelsLike", value: convertTemperature(obs[dataScale].windChill, dataScale, tempUnits), unit: tempUnits)

        send(name: "humidity", value: obs.humidity, unit: "%")

        def windSpeed = convertWindSpeed(obs[dataScale].windSpeed, dataScale, tempUnits)
        send(name: "wind", value: windSpeed, unit: windUnits)
        send(name: "windspeed", value: new BigDecimal(convertWindSpeed(obs[dataScale].windSpeed, dataScale, "metric") / 3.6).setScale(2, BigDecimal.ROUND_HALF_UP), unit: "m/s")
        send(name: "windVector", value: "${obs.winddir}° ${windSpeed} ${windUnits}")

        def loc = getTwcLocation("${obs.lat},${obs.lon}")?.location
        def cityValue = createCityName(loc) ?: "${obs.neighborhood}, ${obs.country}"
        if (cityValue != device.currentValue("city")) {
            send(name: "city", value: cityValue, isStateChange: true)
        }

        send(name: "ultravioletIndex", value: obs.uv)

        def cond = getTwcConditions("${obs.lat},${obs.lon}")
        if (cond) {
            send(name: "weather", value: cond.wxPhraseLong)
            send(name: "weatherIcon", value: cond.iconCode, displayed: false)
            send(name: "uvDescription", value: cond.uvDescription)

            def dtf = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            def sunriseDate = dtf.parse(cond.sunriseTimeLocal)
            log.debug "'${cond.sunriseTimeLocal}'"

            def sunsetDate = dtf.parse(cond.sunsetTimeLocal)
            def tf = new java.text.SimpleDateFormat("h:mm a")
            tf.setTimeZone(TimeZone.getTimeZone(loc?.ianaTimeZone))

            def localSunrise = "${tf.format(sunriseDate)}"
            def localSunset = "${tf.format(sunsetDate)}"
            send(name: "localSunrise", value: localSunrise, descriptionText: "Sunrise today is at $localSunrise")
            send(name: "localSunset", value: localSunset, descriptionText: "Sunset today at is $localSunset")

            send(name: "illuminance", value: estimateLux(cond, sunriseDate, sunsetDate))
        } else {
            log.warn "Conditions not found"
            send(name: "weather", value: "n/a", descriptionText: "Weather summary could not be found")
            send(name: "weatherIcon", value: "", displayed: false)
            send(name: "uvDescription", value: "n/a")

            send(name: "localSunrise", value: "n/a", descriptionText: "Sunrise time could not be found")
            send(name: "localSunset", value: "n/a", descriptionText: "Sunset time could not be found")
            send(name: "illuminance", value: 0, descriptionText: "Illuminance could not be found")
        }

        // Forecast
        def f = getTwcForecast("${obs.lat},${obs.lon}")
        if (f) {
            def icon = f.daypart[0].iconCode[0] != null ? f.daypart[0].iconCode[0] : f.daypart[0].iconCode[1]
            def precip = f.daypart[0].precipChance[0] != null ? f.daypart[0].precipChance[0] : f.daypart[0].precipChance[1]
            def narrative = f.daypart[0].narrative

            send(name: "percentPrecip", value: precip, unit: "%")
            send(name: "forecastIcon", value: icon, displayed: false)
            send(name: "forecastToday", value: narrative[0] ?: "n/a")
            send(name: "forecastTonight", value: narrative[1] ?: "n/a")
            send(name: "forecastTomorrow", value: narrative[2] ?: "n/a")
        } else {
            log.warn "Forecast not found"
            send(name: "percentPrecip", value: 0, unit: "%", descriptionText: "Chance of precipitation could not be found")
            send(name: "forecastIcon", value: "", displayed: false)
            send(name: "forecastToday", value: "n/a", descriptionText: "Today's forecast could not be found")
            send(name: "forecastTonight", value: "n/a", descriptionText: "Tonight's forecast could not be found")
            send(name: "forecastTomorrow", value: "n/a", descriptionText: "Tomorrow's forecast could not be found")
        }

        // Alerts
        def alerts = getTwcAlerts("${obs.lat},${obs.lon}")
        if (alerts) {
            alerts.each {alert ->
                def msg = alert.headlineText
                if (alert.effectiveTimeLocal && !msg.contains(" from ")) {
                    msg += " from ${parseAlertTime(alert.effectiveTimeLocal).format("E hh:mm a", TimeZone.getTimeZone(alert.effectiveTimeLocalTimeZone))}"
                }
                if (alert.expireTimeLocal && !msg.contains(" until ")) {
                    msg += " until ${parseAlertTime(alert.expireTimeLocal).format("E hh:mm a", TimeZone.getTimeZone(alert.expireTimeLocalTimeZone))}"
                }
                send(name: "alert", value: msg, descriptionText: msg)
            }
        } else {
            send(name: "alert", value: "No current alerts", descriptionText: msg)
        }
    } else {
        log.warn "No response from TWC API"
    }

    return null
}

def parseAlertTime(s) {
    def dtf = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
    def s2 = s.replaceAll(/([0-9][0-9]):([0-9][0-9])$/,'$1$2')
    dtf.parse(s2)
}

def refresh() {
    poll()
}

def configure() {
    poll()
}

private pad(String s, size = 25) {
    def n = (size - s.size()) / 2
    if (n > 0) {
        def sb = ""
        n.times {sb += " "}
        sb += s
        n.times {sb += " "}
        return sb
    }
    else {
        return s
    }
}


private get(feature) {
    getWeatherFeature(feature, zipCode)
}

private localDate(timeZone) {
    def df = new java.text.SimpleDateFormat("yyyy-MM-dd")
    df.setTimeZone(TimeZone.getTimeZone(timeZone))
    df.format(new Date())
}

private send(Map map) {
    //log.trace "WUSTATION: event: $map"
    sendEvent(map)
}

private estimateLux(obs, sunriseDate, sunsetDate) {
    def lux = 0
    if (obs.dayOrNight == 'N') {
        lux = 10
    } else {
        //day
        switch(obs.iconCode) {
            case 4:
                lux = 200
                break
            case 5..26:
                lux = 1000
                break
            case 27..28:
                lux = 2500
                break
            case 29..30:
                lux = 7500
                break
            default:
                //sunny, clear
                lux = 10000
        }

        //adjust for dusk/dawn
        def now = new Date().time
        def afterSunrise = now - sunriseDate.time
        def beforeSunset = sunsetDate.time - now
        def oneHour = 1000 * 60 * 60

        if (afterSunrise < oneHour) {
            //dawn
            lux = (long)(lux * (afterSunrise/oneHour))
        } else if (beforeSunset < oneHour) {
            //dusk
            lux = (long)(lux * (beforeSunset/oneHour))
        }
    }
    lux
}

private fixScale(scale) {
    switch (scale.toLowerCase()) {
        case "c":
        case "metric":
            return "metric"
        default:
            return "imperial"
    }
}

private convertTemperature(value, fromScale, toScale) {
    def fs = fixScale(fromScale)
    def ts = fixScale(toScale)
    if (fs == ts) {
        return value
    }
    if (ts == 'imperial') {
        return value * 9.0 / 5.0 + 32.0
    }
    return (value - 32.0) * 5.0 / 9.0
}

private convertWindSpeed(value, fromScale, toScale) {
    def fs = fixScale(fromScale)
    def ts = fixScale(toScale)
    if (fs == ts) {
        return value
    }
    if (ts == 'imperial') {
        return value / 1.609
    }
    return value * 1.609
}

private createCityName(location) {
    def cityName = null

    if (location) {
        cityName = location.city + ", "

        if (location.adminDistrictCode) {
            cityName += location.adminDistrictCode
            cityName += " "
            cityName += location.countryCode ?: location.country
        } else {
            cityName += location.country
        }
    }

    cityName
}