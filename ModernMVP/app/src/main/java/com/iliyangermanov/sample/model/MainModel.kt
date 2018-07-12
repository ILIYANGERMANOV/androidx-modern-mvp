package com.iliyangermanov.sample.model

import android.os.Handler
import com.iliyangermanov.sample.MainContract

class MainModel : MainContract.Model {
    override fun fetchData(name: String, callback: MainContract.Model.DataCallback) {
        Handler().postDelayed({
            callback.onFetched("Hello, $name!")
        }, 1000)
    }
}