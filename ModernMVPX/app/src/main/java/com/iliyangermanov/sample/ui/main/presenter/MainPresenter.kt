package com.iliyangermanov.sample.ui.main.presenter

import com.iliyangermanov.modernmvpx.DataCallback
import com.iliyangermanov.modernmvpx.Presenter
import com.iliyangermanov.sample.ui.main.MainContract

class MainPresenter(view: MainContract.View, val model: MainContract.Model) :
        Presenter<MainContract.View>(view), MainContract.Presenter {
    private var counter = 1

    override fun showGreeting(name: String) {
        view?.showLoading()
        model.fetchGreeting("$name ${counter++}", object : DataCallback<String> {
            override fun onSuccess(data: String) {
                view?.displayGreeting(data)
            }

            override fun onError() {
                view?.showError()
            }
        })
    }

    override fun handleDetailsClick() {
        view?.openDetailsScreen()
    }

}