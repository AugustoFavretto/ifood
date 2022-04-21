package com.example.ifoodtest.weather.domain.usecase

import com.example.ifoodtest.weather.domain.model.GeneralInformation
import com.example.ifoodtest.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetGeneralInformationUseCase(
    private val repository: WeatherRepository
) {
    operator fun invoke(): Flow<GeneralInformation> {
        return repository.fetchWeatherData()
    }
}