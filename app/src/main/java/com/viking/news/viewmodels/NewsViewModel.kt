package com.viking.news.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viking.news.api.RestApiClient
import com.viking.news.models.NewsModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NewsViewModel : ViewModel() {

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    private val _news = MutableLiveData<ArrayList<NewsModel>>()
    val news: LiveData<ArrayList<NewsModel>>
        get() = _news


    fun getNewsForCategory(categoryId: String) {

        _isRefreshing.value = true

        RestApiClient.retrofit.getNewsForCategory(categoryId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ArrayList<NewsModel>> {
                var disposable: Disposable? = null
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onSuccess(response: ArrayList<NewsModel>) {
                    disposable?.dispose()
                    _news.postValue(response)
                    _isRefreshing.value = false
                    _error.value = false
                }

                override fun onError(e: Throwable) {
                    _isRefreshing.value = false
                    Log.d("NewsViewModel", "onError | Throwable received = " + e.message)
                    _error.value = true
                }
            })
    }


}