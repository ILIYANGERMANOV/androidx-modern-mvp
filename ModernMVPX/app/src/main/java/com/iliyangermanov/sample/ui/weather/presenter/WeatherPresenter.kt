package com.iliyangermanov.sample.ui.weather.presenter

import com.iliyangermanov.modernmvpx.DataCallback
import com.iliyangermanov.modernmvpx.Presenter
import com.iliyangermanov.sample.domain.Weather
import com.iliyangermanov.sample.ui.weather.WeatherContract

class WeatherPresenter(view: WeatherContract.View, val model: WeatherContract.Model)
    : Presenter<WeatherContract.View>(view), WeatherContract.Presenter {
    override fun initScreen() {
        view?.setCity("Sofia")
        displayWeather()
    }

    override fun displayWeather() {
        val city = view?.getCityInput() ?: return
        model.fetchWeather(city, object : DataCallback<Weather> {
            override fun onSuccess(data: Weather) {
                val useCelsius = view?.useCelsius() ?: return
                var temp = data.tempKelvin
                if (useCelsius) {
                    //transform temperature from Kelvin to Celsius
                    temp -= 273.15
                }
                view?.displayWeather(temp, data.condition)
            }

            override fun onError() {
                view?.showError()
            }
        })
    }
}