package com.iliyangermanov.sample.ui.weather

import com.iliyangermanov.modernmvpx.BasePresenter
import com.iliyangermanov.modernmvpx.BaseView
import com.iliyangermanov.modernmvpx.DataCallback
import com.iliyangermanov.sample.domain.Weather

interface WeatherContract {
    interface Presenter : BasePresenter {
        fun initScreen()

        fun displayWeather()
    }

    interface View : BaseView {
        fun useCelsius(): Boolean

        fun getCityInput(): String

        fun setCity(city: String)

        fun displayWeather(temp: Double, condition: String)

        fun showError()
    }

    interface Model {
        fun fetchWeather(city: String, callback: DataCallback<Weather>)
    }
}