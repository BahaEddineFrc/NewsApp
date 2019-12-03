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

   /* {"id":"5c349f1d825c0c4fb978035b",
        "name":"Tudd meg, hogyan működik az Alkotmánybíróság!",
        "subtitle":"Collegium tartalom",
        "external_link":"http://mcc.hu/articles/5c349f1d825c0c4fb978035b?nomenu",
        "created":"2019-01-08 13:59:41",
        "thumbnail":"http://mcc.hu/images/5c349f07825c0c4fb978035a/medium"} */
}