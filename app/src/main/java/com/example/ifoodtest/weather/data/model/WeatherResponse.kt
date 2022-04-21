package com.example.ifoodtest.weather.data.model

import com.squareup.moshi.Json

data class WeatherResponse(
    @Json(name = "weather_state_name") val nameWeather: String,
    @Json(name = "min_temp") val minTemp: Double,
    @Json(name = "max_temp") val maxTemp: Double,
    @Json(name = "the_temp") val temp: Double
)