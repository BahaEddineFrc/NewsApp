package com.viking.news.viewmodels

import androidx.lifecycle.ViewModel
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import android.webkit.WebViewClient
import android.webkit.WebView
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class DetailsViewModel() : ViewModel() {

    val url = ObservableField<String>()

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    fun setWebviewUrl(url: String){
        this.url.set(url)
    }

    private var hideProgress=ObservableField<Boolean>(false)

    private inner class Client : WebViewClient() {
        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError
        ) {
            super.onReceivedError(view, request, error)
            setHideProgress()
            _error.value = true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            setHideProgress()
            _error.value = false
        }
    }

    fun getWebViewClient(): WebViewClient {
        return Client()
    }


    @Bindable
    fun isHideProgress(): Boolean {
        return hideProgress.get()!!
    }

    private fun setHideProgress() {
        hideProgress.set(true)
    }

}