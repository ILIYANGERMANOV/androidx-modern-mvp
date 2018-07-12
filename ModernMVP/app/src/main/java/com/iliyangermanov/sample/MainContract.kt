package com.iliyangermanov.sample

import com.iliyangermanov.modernmvp.BasePresenter
import com.iliyangermanov.modernmvp.BaseView

interface MainContract {
    interface Presenter : BasePresenter {
        fun loadData(name: String)
    }

    interface View : BaseView {
        fun showLoading()

        fun showData(data: String)

        fun showError()
    }

    interface Model {
        fun fetchData(name: String, callback: DataCallback)

        interface DataCallback {
            fun onFetched(data: String)

            fun onError()
        }
    }
}