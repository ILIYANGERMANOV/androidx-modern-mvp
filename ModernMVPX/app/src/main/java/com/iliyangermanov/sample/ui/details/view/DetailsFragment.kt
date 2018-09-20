package com.iliyangermanov.sample.ui.details.view

import android.content.Context
import android.os.Bundle
import android.view.View
import com.iliyangermanov.modernmvpx.MVPFragment
import com.iliyangermanov.sample.R
import com.iliyangermanov.sample.ui.details.DetailsContract
import com.iliyangermanov.sample.ui.details.model.DetailsModel
import com.iliyangermanov.sample.ui.details.presenter.DetailsPresenter
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : MVPFragment<DetailsContract.Presenter>(), DetailsContract.View {
    companion object {
        private const val ARG_USER_ID = "user_id_arg"

        fun newInstance(userId: String): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = Bundle().apply {
                putString(ARG_USER_ID, userId)
            }
            return fragment
        }
    }

    override fun getLayout() = R.layout.fragment_details

    override fun initPresenter(appContext: Context, args: Bundle?): DetailsContract.Presenter {
        val userId = args?.getString(ARG_USER_ID)
                ?: throw IllegalArgumentException("'$ARG_USER_ID' cannot be null")
        return DetailsPresenter(this, DetailsModel(appContext), userId)
    }

    override fun onSetupListeners() {
        btnFetch.setOnClickListener {
            presenter.loadUserDetails()
        }
    }

    override fun onReady() {
        presenter.loadUserDetails()
    }

    override fun showLoading() {
        btnFetch.isEnabled = false
        pbLoading.visibility = View.VISIBLE
        tvDetails.visibility = View.INVISIBLE
    }


    override fun displayUserDetails(details: String) {
        hideLoading()
        tvDetails.text = details
    }

    override fun showError() {
        hideLoading()
        tvDetails.text = getString(R.string.err_fetch_data)
    }

    private fun hideLoading() {
        btnFetch.isEnabled = true
        pbLoading.visibility = View.INVISIBLE
        tvDetails.visibility = View.VISIBLE
    }
}