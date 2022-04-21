package com.example.ifoodtest.weather.data.remote

import com.example.ifoodtest.weather.data.api.WeatherApi
import com.example.ifoodtest.weather.data.model.GeneralInformationResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
const val cityId = "4118"

internal class WeatherRemoteDataSourceImpl(
    private val api: WeatherApi
) : WeatherRemoteDataSource {
    override fun fetchWeatherData(): Flow<GeneralInformationResponse> {
        return flow {
            emit(api.fetchWeatherData(cityId).await())
        }
    }

}