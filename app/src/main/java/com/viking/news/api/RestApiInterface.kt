package com.viking.news.api

import com.viking.news.models.CategoryModel
import com.viking.news.models.NewsModel
import io.reactivex.Single
import retrofit2.http.*

interface RestApiInterface {

    @GET("/")
    fun getAllCategories(): Single<CategoryModel>

    @GET("/{categoryId}") //5729fc387fdea7e267fa9761
    fun getNewsForCategory(@Path("categoryId") newsCategoryId: String): Single<NewsModel>

}