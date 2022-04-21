package com.example.ifoodtest.weather.di

import com.example.ifoodtest.common.retrofit.createApi
import com.example.ifoodtest.weather.data.api.WeatherApi
import com.example.ifoodtest.weather.data.mapper.GeneralInformationMapper
import com.example.ifoodtest.weather.data.remote.WeatherRemoteDataSource
import com.example.ifoodtest.weather.data.remote.WeatherRemoteDataSourceImpl
import com.example.ifoodtest.weather.data.repository.WeatherRepositoryImpl
import com.example.ifoodtest.weather.domain.repository.WeatherRepository
import com.example.ifoodtest.weather.domain.usecase.GetGeneralInformationUseCase
import com.example.ifoodtest.weather.presentation.WeatherViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val modulesWeather
    get() = listOf(
        dataModule,
        domainModule,
        presentationModule
    )

val dataModule = module {
    single { createApi<WeatherApi>(retrofit = get()) }
    factory<WeatherRemoteDataSource> {
        WeatherRemoteDataSourceImpl(api = get())
    }
}

val domainModule = module {
    factory<WeatherRepository> {
        WeatherRepositoryImpl(
            dataSourceWeatherData = get(),
            responseMapperWeatherData = GeneralInformationMapper()
        )
    }
    factory {
        GetGeneralInformationUseCase(repository = get())
    }
}

val presentationModule = module {
    viewModel {
        WeatherViewModel (getGeneralInformationUseCase = get())
    }
}


