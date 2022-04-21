package com.example.ifoodtest.stub

import com.example.ifoodtest.weather.data.model.GeneralInformationResponse
import com.example.ifoodtest.weather.data.model.WeatherResponse
import com.example.ifoodtest.weather.domain.model.GeneralInformation
import com.example.ifoodtest.weather.domain.model.Weather

object WeatherStub {

    fun genereteGeneralInformationResponse() = GeneralInformationResponse(
        title = "Toronto",
        locationType = "City",
        weathers = listOf(
            WeatherResponse(
                nameWeather = "sun",
                minTemp = 10.0,
                maxTemp = 15.0,
                temp = 12.0
            )
        )
    )

    fun genereteGeneralInformationData() = GeneralInformation(
        title = "Toronto",
        locationType = "City",
        currentWeather = Weather(
            nameWeather = "sun",
            minTemp = "10°",
            maxTemp = "15°",
            temp = "12°"
        )
    )
}