package com.example.ifoodtest.weather.data.mapper

import com.example.ifoodtest.common.mapper.Mapper
import com.example.ifoodtest.weather.data.model.GeneralInformationResponse
import com.example.ifoodtest.weather.domain.model.GeneralInformation
import com.example.ifoodtest.weather.domain.model.Weather

class GeneralInformationMapper : Mapper<GeneralInformationResponse, GeneralInformation> {
    override fun map(source: GeneralInformationResponse): GeneralInformation {
        return GeneralInformation(
            title = source.title,
            locationType = source.locationType,
            currentWeather =  Weather(
                nameWeather = source.weathers.first().nameWeather,
                minTemp = "${source.weathers.first().minTemp.toInt()}°",
                maxTemp = "${source.weathers.first().maxTemp.toInt()}°",
                temp = "${source.weathers.first().temp.toInt()}°"
            )
        )
    }
}