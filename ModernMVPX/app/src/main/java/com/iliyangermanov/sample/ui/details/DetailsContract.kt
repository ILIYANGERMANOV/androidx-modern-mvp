package com.iliyangermanov.sample.ui.details

import com.iliyangermanov.modernmvpx.BasePresenter
import com.iliyangermanov.modernmvpx.BaseView
import com.iliyangermanov.modernmvpx.DataCallback
import com.iliyangermanov.sample.domain.UserDetails

interface DetailsContract {
    interface Presenter : BasePresenter {
        fun loadUserDetails()
    }

    interface View : BaseView {
        fun showLoading()

        fun displayUserDetails(details: String)

        fun showError()
    }

    interface Model {
        fun fetchUserDetails(userId: String, callback: DataCallback<UserDetails>)
    }
}