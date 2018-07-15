package com.iliyangermanov.sample.model

import android.os.Handler
import com.iliyangermanov.modernmvpx.DataCallback
import com.iliyangermanov.sample.MainContract

class MainModel : MainContract.Model {
    override fun fetchGreeting(name: String, callback: DataCallback<String>) {
        Handler().postDelayed({
            callback.onSuccess("Hello, $name!")
        }, 1000)
    }
}