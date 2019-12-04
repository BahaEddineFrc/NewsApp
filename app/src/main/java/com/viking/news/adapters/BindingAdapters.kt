package com.viking.news.adapters

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.viking.news.R

@BindingAdapter("android:img")
fun setImageUrl(imageView: ImageView, url: String?) {
    if (url != null)
        Picasso.get().load(url)
            //.resize(300,450)
            //.centerCrop()
            .error(R.drawable.ic_launcher_background)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    Log.d("ImageBindingAdapter", "success")
                }

                override fun onError(e: Exception?) {
                    Log.d("ImageBindingAdapter", "error ${e?.message}")
                }
            })
}