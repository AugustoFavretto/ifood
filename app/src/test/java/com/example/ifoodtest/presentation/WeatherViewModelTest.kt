package com.example.ifoodtest.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.ifoodtest.stub.WeatherStub.genereteGeneralInformationData
import com.example.ifoodtest.weather.domain.usecase.GetGeneralInformationUseCase
import com.example.ifoodtest.weather.presentation.WeatherViewAction
import com.example.ifoodtest.weather.presentation.WeatherViewModel
import com.example.ifoodtest.weather.presentation.WeatherViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import com.example.ifoodtest.common.CoroutinesTestRule

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: WeatherViewModel

    private var observerAction: Observer<WeatherViewAction> = mock()

    private var observerState: Observer<WeatherViewState> = mock()

    private var getGeneralInformationUseCase: GetGeneralInformationUseCase = mock()


    @Test
    fun `fetchWeatherData should return getGeneralInformation with success`()  {
        //Given
        val initialState = WeatherViewState().setLoadingState()
        val successState = initialState.setSuccessState(genereteGeneralInformationData())
        val finalState = successState.setLoadingHideState()
        setupSuccesInit()

        //When
        viewModel.getGeneralInformation()

        //Then
        inOrder(observerState) {
            verify(observerState).onChanged(initialState)
            verify(observerState).onChanged(successState)
            verify(observerState).onChanged(finalState)
            verifyNoMoreInteractions()
        }
    }

    @Test
    fun `fetchWeatherData should return getGeneralInformation with error`()  {
        //Given
        val initialState = WeatherViewState().setLoadingState()
        val finalState = initialState.setLoadingHideState()
        val errorState = finalState.setErrorPage()
        setupErrorInit()

        //When
        viewModel.getGeneralInformation()

        //Then
        inOrder(observerState) {
            verify(observerState).onChanged(initialState)
            verify(observerState).onChanged(finalState)
            verify(observerState).onChanged(errorState)
            verifyNoMoreInteractions()
        }
    }

    @Before
    fun setUp() {
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = WeatherViewModel(
            getGeneralInformationUseCase,
            coroutinesTestRule.dispatcher
        )
        viewModel.action.observeForever(observerAction)
        viewModel.state.observeForever(observerState)
    }

    private fun setupSuccesInit() {
        whenever(getGeneralInformationUseCase.invoke()).thenReturn(
            flow {
                emit(genereteGeneralInformationData())
            }
        )
    }

    private fun setupErrorInit() {
        whenever(getGeneralInformationUseCase.invoke()).thenReturn(
            flow {
                throw WeatherException()
            }
        )
    }

}

class WeatherException: Exception()