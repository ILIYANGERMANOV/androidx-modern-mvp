package com.iliyangermanov.modernmvpx

import android.content.Context
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class MVPActivity<P : BasePresenter> : AppCompatActivity(), BaseView {

    override var UIActive: Boolean = false


    protected lateinit var presenter: P

    @LayoutRes
    abstract fun getContentLayout(): Int

    abstract fun initPresenter(applicationContext: Context): P

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBeforeSetContentView()
        setContentView(getContentLayout())
        if (!::presenter.isInitialized) {
            presenter = initPresenter(applicationContext)
        }
        onSetupUI()
        onSetupListeners()
        UIActive = true
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        onReady()
    }

    /**
     * Empty stub
     */
    protected open fun onBeforeSetContentView() {
    }

    /**
     * Empty stub
     */
    protected open fun onSetupUI() {
    }

    /**
     * Empty stub
     */
    protected open fun onSetupListeners() {
    }

    /**
     * Empty stub
     */
    protected open fun onReady() {
    }


    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        UIActive = false
        presenter.onDestroy()
    }
}