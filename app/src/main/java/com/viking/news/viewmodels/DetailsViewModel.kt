package com.viking.news.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.webkit.*
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


class DetailsViewModel() : ViewModel() {


    val url = ObservableField<String>()
    private var compt = 0

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    var hideProgress = ObservableField<Boolean>(false)

    fun setWebviewUrl(url: String) {
        val securedUrl = url.substring(0, 4) + "s" + url.substring(4, url.length)
        this.url.set(securedUrl)
    }


    private inner class Client : WebViewClient() {
        override fun onReceivedError(
            view: WebView, request: WebResourceRequest, error: WebResourceError
        ) {
            super.onReceivedError(view, request, error)
            Log.d("DetailsViewModel", "erroor : $error")
            hideProgress.set(true)
            _error.value = true
        }

        @SuppressLint("SetJavaScriptEnabled")
        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            view.webChromeClient = WebChromeClient()
            view.settings.apply {
                allowFileAccess = true
                allowFileAccessFromFileURLs = true
                allowUniversalAccessFromFileURLs = true
                allowContentAccess = true
                javaScriptEnabled = true
                loadWithOverviewMode = true
                domStorageEnabled = true
            }


            if (compt < 1) {
                compt++
                view.reload()
            }

            hideProgress.set(true)
            _error.value = false
        }

        override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
            Log.d("DetailsViewModel", "onReceivedSslError")
            handler.proceed()
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            return false
        }
    }

    fun getWebViewClient(): WebViewClient {
        return Client()
    }


    /*fun isHideProgress(): Boolean {
        return hideProgress.get()!!
    }

    private fun setHideProgress() {
        hideProgress.set(true)
    }*/

}