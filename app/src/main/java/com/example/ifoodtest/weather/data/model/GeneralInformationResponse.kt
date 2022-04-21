package com.example.ifoodtest.weather.data.model

import com.squareup.moshi.Json

data class GeneralInformationResponse(
    @Json(name = "title") val title: String,
    @Json(name = "location_type") val locationType: String,
    @Json(name = "consolidated_weather") val weathers: List<WeatherResponse>
)