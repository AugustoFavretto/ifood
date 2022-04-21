package com.example.ifoodtest.weather.data.repository

import com.example.ifoodtest.common.mapper.Mapper
import com.example.ifoodtest.weather.data.model.GeneralInformationResponse
import com.example.ifoodtest.weather.data.remote.WeatherRemoteDataSource
import com.example.ifoodtest.weather.domain.model.GeneralInformation
import com.example.ifoodtest.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class WeatherRepositoryImpl(
    private val dataSourceWeatherData: WeatherRemoteDataSource,
    private val responseMapperWeatherData: Mapper<GeneralInformationResponse, GeneralInformation>
): WeatherRepository {

    override fun fetchWeatherData(): Flow<GeneralInformation> {
        return dataSourceWeatherData.fetchWeatherData()
            .map { responseMapperWeatherData.map(it) }
    }
}