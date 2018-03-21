package com.citi.myclientsnews.model

import android.arch.lifecycle.ViewModel

/**
 * Created by yb34982 on 21/03/2018.
 */
class ArticlesViewModel : ViewModel() {

//    val source = Source("aa")
//    val articlesList = listOf<Article>(Article(source,"Yaniv","Title Bla Bla","Whohohoho","www.google.com","https://imgc.allpostersimages.com/img/print/posters/wongstock-8-bit-pixel-art-yin-yang-symbol_a-G-12965883-9201948.jpg","1212"))
    var articlesList : List<Article> = emptyList()
}