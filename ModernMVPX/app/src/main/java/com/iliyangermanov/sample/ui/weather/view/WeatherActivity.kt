package com.iliyangermanov.sample.ui.weather.view

import android.content.Context
import android.content.Intent
import com.iliyangermanov.modernmvpx.MVPActivity
import com.iliyangermanov.sample.R
import com.iliyangermanov.sample.ui.weather.WeatherContract
import com.iliyangermanov.sample.ui.weather.model.WeatherModel
import com.iliyangermanov.sample.ui.weather.presenter.WeatherPresenter
import kotlinx.android.synthetic.main.activity_weather.*

class WeatherActivity : MVPActivity<WeatherContract.Presenter>(), WeatherContract.View {
    override fun getContentLayout() = R.layout.activity_weather

    override fun initPresenter(appContext: Context, intent: Intent): WeatherContract.Presenter {
        return WeatherPresenter(this, WeatherModel(appContext))
    }

    override fun onSetupListeners() {
        btnRefresh.setOnClickListener { presenter.displayWeather() }
    }

    override fun onReady() {
        presenter.initScreen()
    }

    override fun useCelsius(): Boolean {
        return swCelsius.isChecked
    }

    override fun getCityInput() = etCity.text.toString()

    override fun setCity(city: String) {
        etCity.setText(city)
    }

    override fun displayWeather(temp: Double, condition: String) {
        tvWeather.text = "Temperature is $temp and condition is $condition."
    }

    override fun showError() {
        tvWeather.text = getString(R.string.err_fetch_data)
    }

}