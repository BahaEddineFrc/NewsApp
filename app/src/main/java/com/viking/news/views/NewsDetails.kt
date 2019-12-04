package com.viking.news.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.viking.news.R
import com.viking.news.databinding.ActivityNewsDetailsBinding
import com.viking.news.viewmodels.DetailsViewModel
import androidx.appcompat.widget.Toolbar



class NewsDetails : AppCompatActivity() {

    private lateinit var binding: ActivityNewsDetailsBinding
    private lateinit var model: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, com.viking.news.R.layout.activity_news_details)
        model = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        binding.viewModel = model

        val toolbar = findViewById<Toolbar>(com.viking.news.R.id.toolbar_top)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_action_back)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        intent.getStringExtra("newsUrl")?.let {newsUrl->
            model.setWebviewUrl(newsUrl)
        }

        model.error.observe(this, androidx.lifecycle.Observer { isError->
            if(isError) {
                Snackbar.make(binding.root, "an error has occurred", Snackbar.LENGTH_LONG).show()
            }
        })
    }
}
