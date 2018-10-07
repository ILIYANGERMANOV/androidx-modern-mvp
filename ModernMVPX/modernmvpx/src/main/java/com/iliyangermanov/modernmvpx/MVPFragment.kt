package com.iliyangermanov.modernmvpx

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class MVPFragment<P : BasePresenter> : Fragment(), BaseView {
    override var UIActive: Boolean = false
        get() = isAdded


    protected lateinit var presenter: P

    /**
     * @return layout resource's id for the fragment's layout
     * e.g. R.layout.fragment_main
     */
    @LayoutRes
    protected abstract fun getLayout(): Int

    /**
     * Instantiate the presenter here. Called when activity is created (@see Fragment#onActivityCreated()).
     * @param appContext application's context, use it if 'Model' needs it
     * @param args arguments passed to the fragment
     * @return new instance of the presenter
     */
    protected abstract fun initPresenter(appContext: Context, args: Bundle?): P

    @CallSuper
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = initPresenter(activity!!.applicationContext, arguments)
        onSetupUI()
        onSetupListeners()
        onReady()
    }

    /**
     * Empty method. Called once in onActivityCreated() after presenter in initialized.
     * Setup programmatically UI here - RecyclerView, TextView, background colors and et.
     * e.g. textView.paintFlags = textView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
     * !NOTE: In 99% of cases is considered mistake to use presenter here!
     */
    protected open fun onSetupUI() {
    }

    /**
     * Empty method. Called once immediately after @see MVPFragment#onSetupUI().
     * Attach your UI listeners here, e.g. myButton.setOnClickListener {...}
     */
    protected open fun onSetupListeners() {
    }

    /**
     * Empty method. Called once in onCreate() after presenter, UI and listeners are setup.
     * Execute your business logic that initializes the screen here.
     * E.g. presenter.start(); presenter.loadData(); presenter.initScreen() and etc
     */
    protected open fun onReady() {
    }


    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }

    override fun showToast(msg: String, length: Int) {
        Toast.makeText(context, msg, length).show()
    }
}