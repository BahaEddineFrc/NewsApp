package com.viking.news.models

import java.io.Serializable

class NewsModel(
    val id: String,
    val name: String,
    val subtitle: String,
    val external_link: String,
    val created: String,
    val thumbnail: String
) : Serializable {

    override fun toString(): String {
        return "NewsModel(id='$id', name='$name', subtitle='$subtitle', external_link='$external_link', created='$created', thumbnail='$thumbnail')"
    }
}