package com.example.ifoodtest.data

import app.cash.turbine.test
import com.example.ifoodtest.stub.WeatherStub.genereteGeneralInformationData
import com.example.ifoodtest.stub.WeatherStub.genereteGeneralInformationResponse
import com.example.ifoodtest.weather.data.mapper.GeneralInformationMapper
import com.example.ifoodtest.weather.data.remote.WeatherRemoteDataSource
import com.example.ifoodtest.weather.data.repository.WeatherRepositoryImpl
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.time.ExperimentalTime
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@ExperimentalTime
class WeatherRepositoryTest {

    private val weatherRemoteDataSource = mock<WeatherRemoteDataSource>()
    private val responseMapper = GeneralInformationMapper()

    private val weatherRepository = WeatherRepositoryImpl(
        dataSourceWeatherData = weatherRemoteDataSource,
        responseMapperWeatherData = responseMapper
    )

    @Test
    fun `fetchWeatherData should return GeneralInformation with success`() = runBlocking {

        //Given
        whenever(weatherRemoteDataSource.fetchWeatherData()).thenReturn( flow {
            emit(genereteGeneralInformationResponse())
        })

        //When
        val result = weatherRepository.fetchWeatherData()

        //Then
        result.test {
            assertEquals(genereteGeneralInformationData(), awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `fetchWeatherData should return GeneralInformation with error`() = runBlocking {

        //Given
        val expectedError = RuntimeException("broken!")
        whenever(weatherRemoteDataSource.fetchWeatherData()).thenReturn(flow {
            throw expectedError
        })

        //When
        val result = weatherRepository.fetchWeatherData()

        //Then
        result.test {
            assertEquals("broken!", awaitError().message)
        }
    }
}