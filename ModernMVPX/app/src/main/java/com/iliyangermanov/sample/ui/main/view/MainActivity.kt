package com.iliyangermanov.sample.ui.main.view

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.View
import android.view.WindowManager
import com.iliyangermanov.modernmvpx.MVPActivity
import com.iliyangermanov.sample.R
import com.iliyangermanov.sample.ui.details.view.DetailsActivity
import com.iliyangermanov.sample.ui.main.MainContract
import com.iliyangermanov.sample.ui.main.model.MainModel
import com.iliyangermanov.sample.ui.main.presenter.MainPresenter
import com.iliyangermanov.sample.ui.weather.view.WeatherActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MVPActivity<MainContract.Presenter>(), MainContract.View {

    override fun getContentLayout() = R.layout.activity_main

    override fun initPresenter(appContext: Context, intent: Intent): MainContract.Presenter {
        return MainPresenter(this, MainModel())
    }


    override fun onBeforeSetContentView() {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
    }

    override fun onSetupUI() {
        tvGreeting.paintFlags = tvGreeting.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

    override fun onSetupListeners() {
        btnFetch.setOnClickListener {
            presenter.showGreeting("Iliyan")
        }
        btnDetails.setOnClickListener {
            presenter.handleDetailsClick()
        }
        btnWeather.setOnClickListener {
            presenter.handleWeatherClick()
        }
    }

    override fun onReady() {
        presenter.showGreeting("Iliyan")
    }

    override fun showLoading() {
        btnFetch.isEnabled = false
        pbLoading.visibility = View.VISIBLE
        tvGreeting.visibility = View.INVISIBLE
    }

    override fun displayGreeting(greeting: String) {
        hideLoading()
        tvGreeting.text = greeting
    }

    override fun showError() {
        hideLoading()
        tvGreeting.text = getString(R.string.err_fetch_data)
    }

    private fun hideLoading() {
        btnFetch.isEnabled = true
        pbLoading.visibility = View.INVISIBLE
        tvGreeting.visibility = View.VISIBLE
    }

    override fun openDetailsScreen() {
        val intent = DetailsActivity.getIntent(this)
        startActivity(intent)
    }

    override fun openWeatherScreen() {
        val intent = Intent(this, WeatherActivity::class.java)
        startActivity(intent)
    }
}
