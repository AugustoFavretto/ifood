package com.example.ifoodtest.weather.presentation

import androidx.lifecycle.viewModelScope
import com.example.ifoodtest.common.ViewModel
import com.example.ifoodtest.weather.domain.usecase.GetGeneralInformationUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getGeneralInformationUseCase: GetGeneralInformationUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel<WeatherViewState, WeatherViewAction>(WeatherViewState()) {

    fun getGeneralInformation() {
        viewModelScope.launch {
            getGeneralInformationUseCase.invoke()
                .flowOn(dispatcher)
                .onStart { setState { state -> state.setLoadingState() } }
                .onCompletion { setState { state -> state.setLoadingHideState() } }
                .catch { setState { state -> state.setErrorPage() } }
                .collect { setState { state -> state.setSuccessState(it) } }
        }
    }

    fun tryAgain() = sendAction { WeatherViewAction.OnClickTryAgain }

}