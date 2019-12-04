package com.viking.news.adapters

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.viking.news.R

@BindingAdapter("android:img")
fun setImageUrl(imageView: ImageView, url: String?) {
    if (url != null) {
        val u = url.substring(0, 4) + "s" + url.substring(4, url.length)
        Picasso.get().load(u)
            .resize(600,400)
            .centerCrop()
            .error(R.drawable.ic_launcher_background)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                }

                override fun onError(e: Exception?) {
                    Log.d("ImageBindingAdapter", "error ${e?.message} onUrl $url")
                }
            })
    }
}