package com.example.ifoodtest.domain

import app.cash.turbine.test
import com.example.ifoodtest.stub.WeatherStub.genereteGeneralInformationData
import com.example.ifoodtest.weather.domain.repository.WeatherRepository
import com.example.ifoodtest.weather.domain.usecase.GetGeneralInformationUseCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalTime
class GetGeneralInformationUseCaseTest {

    private val repository = mock<WeatherRepository>()
    private val getGeneralInformation = GetGeneralInformationUseCase(repository)


    @Test
    fun `should getGeneralInformation return GeneralInformation with success`() = runBlocking {
        // Given
        whenever(repository.fetchWeatherData()).thenReturn(flow { emit(genereteGeneralInformationData()) })

        //When
        val result = getGeneralInformation.invoke()

        //Then
        result.test {
            assertEquals(genereteGeneralInformationData(), awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `should getGeneralInformation return GeneralInformation with error`() = runBlocking {
        // Given
        val expectedError = RuntimeException("broken!")
        whenever(repository.fetchWeatherData()).thenReturn(flow { throw expectedError })

        //When
        val result = getGeneralInformation.invoke()

        //Then
        result.test {
            assertEquals("broken!", awaitError().message)
        }
    }
}