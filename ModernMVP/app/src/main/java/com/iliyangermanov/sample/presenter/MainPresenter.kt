package com.iliyangermanov.sample.presenter

import com.iliyangermanov.modernmvpx.Presenter
import com.iliyangermanov.sample.MainContract

class MainPresenter(view: MainContract.View, val model: MainContract.Model) :
        Presenter<MainContract.View>(view), MainContract.Presenter {
    private var counter = 1

    override fun loadData(name: String) {
        view?.showLoading()
        model.fetchData("$name ${counter++}", object : MainContract.Model.DataCallback {
            override fun onFetched(data: String) {
                view?.showData(data)
            }

            override fun onError() {
                view?.showError()
            }

        })
    }

}