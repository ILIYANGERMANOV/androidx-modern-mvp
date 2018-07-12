package com.iliyangermanov.sample.view

import android.content.Context
import android.graphics.Paint
import android.view.View
import com.iliyangermanov.modernmvpx.MVPActivity
import com.iliyangermanov.sample.MainContract
import com.iliyangermanov.sample.R
import com.iliyangermanov.sample.model.MainModel
import com.iliyangermanov.sample.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MVPActivity<MainContract.Presenter>(), MainContract.View {
    override fun getContentLayout() = R.layout.activity_main


    override fun initPresenter(applicationContext: Context): MainContract.Presenter {
        return MainPresenter(this, MainModel())
    }

    override fun onSetupUI() {
        tvData.paintFlags = tvData.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

    override fun onSetupListeners() {
        btnFetch.setOnClickListener {
            presenter.loadData("Iliyan")
        }
    }

    override fun onReady() {
        presenter.loadData("Iliyan")
    }

    override fun showLoading() {
        btnFetch.isEnabled = false
        pbLoading.visibility = View.VISIBLE
        tvData.visibility = View.INVISIBLE
    }

    override fun showData(data: String) {
        hideLoading()
        tvData.text = data
    }

    override fun showError() {
        hideLoading()
        tvData.text = getString(R.string.err_fetch_data)
    }

    private fun hideLoading() {
        btnFetch.isEnabled = true
        pbLoading.visibility = View.INVISIBLE
        tvData.visibility = View.VISIBLE
    }
}
