package com.iliyangermanov.sample.ui.weather.model

import android.content.Context
import android.os.Handler
import com.iliyangermanov.modernmvpx.DataCallback
import com.iliyangermanov.sample.domain.Weather
import com.iliyangermanov.sample.ui.weather.WeatherContract

class WeatherModel(val appContext: Context) : WeatherContract.Model {
    override fun fetchWeather(city: String, callback: DataCallback<Weather>) {
        //Mocked weather service
        Handler().postDelayed({
            callback.onSuccess(Weather(303.15, "Sunny"))
        }, 500)
    }
}