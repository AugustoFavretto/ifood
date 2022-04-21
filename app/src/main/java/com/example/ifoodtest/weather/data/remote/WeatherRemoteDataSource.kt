package com.example.ifoodtest.weather.data.remote

import com.example.ifoodtest.weather.data.model.GeneralInformationResponse
import kotlinx.coroutines.flow.Flow

internal interface WeatherRemoteDataSource {
    fun fetchWeatherData(): Flow<GeneralInformationResponse>
}