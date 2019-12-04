package com.viking.news.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.viking.news.R
import com.viking.news.adapters.NewsAdapter
import com.viking.news.databinding.ActivityMainBinding
import com.viking.news.viewmodels.NewsViewModel

class NewsMain : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding
    private lateinit var  model : NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val swipeRefreshLayout=findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout)

        swipeRefreshLayout.setProgressViewOffset(true, START_SWIPE_REFRESH, resources.getDimension(R.dimen.swipe_refresh_offset).toInt())


        binding = ActivityMainBinding.inflate(layoutInflater)
        model = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        binding.viewmodel=model

        model.error.observe(this, androidx.lifecycle.Observer { isError->
            if(isError)
                Snackbar.make(binding.root,"a server error has occurred", Snackbar.LENGTH_LONG).show()
        })

        model.isRefreshing.observe(this, Observer {it->
            swipeRefreshLayout.isRefreshing=it
        })


        swipeRefreshLayout.setOnRefreshListener {
            model.getNewsForCategory(CATEGORY_ID)
        }

        setUpRecyclerView()
    }



    private fun setUpRecyclerView() {

        val recyclerView : RecyclerView = findViewById(R.id.rv)
        //val emptyPlaceholder : RelativeLayout = findViewById(R.id.empty_placeholder)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = NewsAdapter(null) { n->
            intent = Intent(this, NewsDetails::class.java)
            intent.putExtra("newsUrl",n.external_link)
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        binding.viewmodel?.getNewsForCategory(CATEGORY_ID)

        model.news.observe(this, Observer { news ->
            adapter.setNews(news)
        })
    }

    companion object {
        private const val START_SWIPE_REFRESH = 50
        private const val CATEGORY_ID = "5729fc387fdea7e267fa9761"
    }

}
