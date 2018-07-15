package com.iliyangermanov.modernmvpx

import androidx.annotation.CallSuper

abstract class Presenter<V : BaseView>(view: V?) : BasePresenter {

    protected var view: V? = view
        get() {
            val isActive: Boolean = field?.UIActive ?: false
            if (isActive) {
                return field
            } else {
                return null
            }
        }

    @CallSuper
    override fun onDestroy() {
        view = null
    }
}
