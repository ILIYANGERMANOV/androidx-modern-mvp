package com.iliyangermanov.sample.ui.main

import com.iliyangermanov.modernmvpx.BasePresenter
import com.iliyangermanov.modernmvpx.BaseView
import com.iliyangermanov.modernmvpx.DataCallback

interface MainContract {
    interface Presenter : BasePresenter {
        fun showGreeting(name: String)

        fun handleDetailsClick()
    }

    interface View : BaseView {
        fun showLoading()

        fun displayGreeting(greeting: String)

        fun showError()

        fun openDetailsScreen()
    }

    interface Model {
        fun fetchGreeting(name: String, callback: DataCallback<String>)
    }
}