package com.iliyangermanov.modernmvpx

interface DataCallback<T> {
    fun onSuccess(data: T)

    fun onError()
}