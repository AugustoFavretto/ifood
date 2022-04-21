package com.example.ifoodtest.weather.presentation

import com.example.ifoodtest.common.viewmodel.UIState
import com.example.ifoodtest.weather.domain.model.GeneralInformation

data class WeatherViewState(
    val isLoading: Boolean = false,
    val isShowError: Boolean = false,
    val temp: String = "",
    val tempMax: String = "",
    val tempMin: String = "",
    val todayWeather: String = "",
    val currentCity: String = ""
): UIState {

    fun setLoadingState() = copy(
        isLoading = true,
        isShowError = false
    )

    fun setLoadingHideState() = copy(
        isLoading = false,
        isShowError = false
    )

    fun setErrorPage() = copy(
        isShowError = true
    )

    fun setSuccessState(generalInformation: GeneralInformation) = copy(
        temp = generalInformation.currentWeather.temp,
        tempMax = generalInformation.currentWeather.maxTemp,
        tempMin = generalInformation.currentWeather.minTemp,
        todayWeather = generalInformation.currentWeather.nameWeather,
        currentCity = generalInformation.title
    )

}