package com.example.ifoodtest.weather.presentation

import com.example.ifoodtest.common.viewmodel.UIAction

sealed class WeatherViewAction: UIAction {
    object OnClickTryAgain: WeatherViewAction()
}