package com.example.ifoodtest.weather.data.api

import com.example.ifoodtest.weather.data.model.GeneralInformationResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface WeatherApi {

    @GET("api/location/{cityId}")
    fun fetchWeatherData( @Path("cityId") cityId: String): Deferred<GeneralInformationResponse>

}