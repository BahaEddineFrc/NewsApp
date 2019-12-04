package com.viking.news.api

import com.viking.news.models.CategoryModel
import com.viking.news.models.NewsModel
import io.reactivex.Single
import retrofit2.http.*

interface RestApiInterface {

    @GET("article_types/")
    fun getAllCategories(): Single<CategoryModel>

    @GET("article_types/{categoryId}")
    fun getNewsForCategory(@Path("categoryId") newsCategoryId: String ): Single<ArrayList<NewsModel>>

}