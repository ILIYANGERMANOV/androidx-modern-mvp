package com.iliyangermanov.sample.ui.details.model

import android.content.Context
import android.content.pm.PackageManager
import android.os.Handler
import com.iliyangermanov.modernmvpx.DataCallback
import com.iliyangermanov.sample.domain.UserDetails
import com.iliyangermanov.sample.ui.details.DetailsContract

class DetailsModel(val appContext: Context) : DetailsContract.Model {
    override fun fetchUserDetails(userId: String, callback: DataCallback<UserDetails>) {
        try {
            val pInfo = appContext.packageManager.getPackageInfo(appContext.packageName, 0)
            Handler().postDelayed({
                callback.onSuccess(UserDetails(userId.capitalize(), pInfo.versionName))
            }, 1000)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace();
            callback.onError()
        }
    }

}