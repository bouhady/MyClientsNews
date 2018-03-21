package com.citi.myclientsnews

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.citi.myclientsnews.model.Article
import com.squareup.picasso.Picasso
import timber.log.Timber

class NewsListRecycleListAdapter(val context: Context, var articlesList: List<Article>, val onArticleClick: OnArticleClick) : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = articlesList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articlesList[position]
        holder.textView?.text = article.title
        holder.content?.text = article.description
        Picasso.with(context).load(article.urlToImage).into(holder.imageView)
        holder.textView?.setOnClickListener {
            onArticleClick.articleOpen(article.url)
            Timber.d(article.url)
        }
    }

}

class NewsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var textView : TextView? = itemView?.findViewById(R.id.title)
    var imageView = itemView?.findViewById<ImageView>(R.id.article_image_view)
    var content = itemView?.findViewById<TextView>(R.id.content)

}

interface OnArticleClick {
    fun articleOpen(string: String)
}
