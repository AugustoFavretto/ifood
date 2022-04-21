package com.example.ifoodtest.di

import com.example.ifoodtest.common.retrofit.provideOkHttpClient
import com.example.ifoodtest.common.retrofit.provideRetrofit
import org.koin.dsl.module.module

val remoteModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(okHttpClient = get()) }
}