package com.iliyangermanov.modernmvpx

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class MVPActivity<P : BasePresenter> : AppCompatActivity(), BaseView {

    override var UIActive: Boolean = false


    protected lateinit var presenter: P

    /**
     * @return layout resource's id for the activity's layout
     * e.g. R.layout.activity_main
     */
    @LayoutRes
    abstract fun getContentLayout(): Int

    /**
     * Instantiate the presenter here. Called immediately after content view is set
     * (@see AppCompatActivity#setContentView()).
     * @param applicationContext application's context, use it if 'Model' needs it
     * @param intent the intent that started the activity, use it for extract extras
     * and pass it to the presenter. E.g. val userId = intent.getStringExtra("user_id")
     * @return new instance of the presenter
     */
    abstract fun initPresenter(applicationContext: Context, intent: Intent): P

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBeforeSetContentView()
        setContentView(getContentLayout())
        presenter = initPresenter(applicationContext, intent)
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
     * Empty method. Called <b>before</b> activity's content view is set
     * (@see AppCompatActivity#setContentView()).
     * Presenter <b>not initialized</b><br>
     *Typically used for customizing the Window,
     * e.g. getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
     * WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
     */
    protected open fun onBeforeSetContentView() {
    }

    /**
     * Empty method. Called once in onCreate() after presenter in initialized.
     * Setup programmatically UI here - RecyclerView, TextView, background colors and et.
     * e.g. textView.paintFlags = textView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
     */
    protected open fun onSetupUI() {
    }

    /**
     * Empty method. Called once immediately after @see MVPActivity#onSetupUI().
     * Attach your UI listeners here, e.g. myButton.setOnClickListener {...}
     */
    protected open fun onSetupListeners() {
    }

    /**
     * Empty method. Called once in onStart() after presenter, UI and listeners are setup.
     * Execute your business logic that initializes the screen here.
     * E.g. presenter.start(); presenter.loadData(); presenter.initScreen() and etc
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