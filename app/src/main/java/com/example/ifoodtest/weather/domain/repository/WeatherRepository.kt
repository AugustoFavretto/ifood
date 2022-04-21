package com.example.ifoodtest.weather.domain.repository

import com.example.ifoodtest.weather.domain.model.GeneralInformation
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun fetchWeatherData(): Flow<GeneralInformation>
}