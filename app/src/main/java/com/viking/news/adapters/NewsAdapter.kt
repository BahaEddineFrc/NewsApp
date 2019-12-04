package com.viking.news.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.viking.news.R
import com.viking.news.databinding.NewsItemBinding
import com.viking.news.models.NewsModel


class NewsAdapter(private var callback :(NewsModel)->Unit) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    private var newsModels: List<NewsModel>? = null


    fun setNews(newsModels: List<NewsModel>?) {
        this.newsModels = newsModels

        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val newsModelItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.news_item,
            parent,
            false
        ) as NewsItemBinding
        return ViewHolder(newsModelItemBinding)
    }

    override fun getItemCount(): Int {
        return if (newsModels == null) 0 else newsModels!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsModel = newsModels!![position]
        holder.bind(newsModel)
    }

    inner class ViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind( newsModel: NewsModel) {

            binding.cardLayout.setOnClickListener {
                callback.invoke(newsModel)
            }
            binding.newsItem  = newsModel
            binding.executePendingBindings()

        }
    }






}
