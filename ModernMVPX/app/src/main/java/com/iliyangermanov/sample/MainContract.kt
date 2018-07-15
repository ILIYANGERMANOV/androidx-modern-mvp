package com.iliyangermanov.sample

import com.iliyangermanov.modernmvpx.BasePresenter
import com.iliyangermanov.modernmvpx.BaseView
import com.iliyangermanov.modernmvpx.DataCallback

interface MainContract {
    interface Presenter : BasePresenter {
        fun showGreeting(name: String)
    }

    interface View : BaseView {
        fun showLoading()

        fun showGreeting(greeting: String)

        fun showError()
    }

    interface Model {
        fun fetchGreeting(name: String, callback: DataCallback<String>)
    }
}