package com.example.ifoodtest.weather.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.ifoodtest.R
import com.example.ifoodtest.databinding.ActivityWeatherBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : AppCompatActivity(R.layout.activity_weather) {

    private val viewModel: WeatherViewModel by viewModel()

    private val binding: ActivityWeatherBinding by lazy {
        ActivityWeatherBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupActivity()
        viewModel.getGeneralInformation()
    }

    private fun setupActivity() {
        setupViewItens()
        setupState()
        setupAction()
    }

    private fun setupViewItens() {
        binding.error.btnTryAgain.setOnClickListener {
            viewModel.tryAgain()
        }
    }

    private fun setupAction() {
        viewModel.action.observe(this, { action ->
            when (action) {
                is WeatherViewAction.OnClickTryAgain -> viewModel.getGeneralInformation()
            }
        })
    }

    private fun setupState() {
        viewModel.state.observe(this, { state ->
            with(binding) {
                loading.root.isVisible = state.isLoading
                error.root.isVisible = state.isShowError
                tvTempMax.text = state.tempMax
                tvTempMin.text = state.tempMin
                tvTemp.text = state.temp
                tvTodayWeather.text = state.todayWeather
                currentCity.text = state.currentCity
            }
        })
    }
}