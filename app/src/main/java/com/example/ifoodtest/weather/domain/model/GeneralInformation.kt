package com.example.ifoodtest.weather.domain.model

data class GeneralInformation(
    val title: String,
    val locationType: String,
    val currentWeather: Weather
)