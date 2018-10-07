package com.iliyangermanov.modernmvpx

import android.widget.Toast


interface BaseView {
    var UIActive: Boolean

    fun showToast(msg: String, length: Int = Toast.LENGTH_SHORT)
}