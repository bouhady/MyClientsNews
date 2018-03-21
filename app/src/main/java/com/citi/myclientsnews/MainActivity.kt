package com.citi.myclientsnews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import com.citi.myclientsnews.app.App
import com.citi.myclientsnews.model.NewDataClass
import com.citi.myclientsnews.network.MyClientsNewsApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var myClientsNewsApiService: MyClientsNewsApiService

    @BindView(R.id.news_list_recycleview)
    lateinit var newsRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App)
            .getComponent()
            .inject(this)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        ButterKnife.bind(this)

        newsRecycler.layoutManager = LinearLayoutManager(this)

        val newsListRecycleListAdapter = NewsListRecycleListAdapter(
            this,
            emptyList(),
            object : OnArticleClick {
                override fun articleOpen(string: String) {
                    itemOpen(string)
                }
            }
        )
        newsRecycler.adapter = newsListRecycleListAdapter

        myClientsNewsApiService.getNews()
            .doOnSubscribe { Timber.d("Subscribe!") }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { Timber.e("ERRRROR : $it") }
            .onErrorReturn { NewDataClass("Error", 0, emptyList()) }
            .subscribe {
                Timber.d(it.toString())
                newsListRecycleListAdapter.articlesList = it.articles
                newsListRecycleListAdapter.notifyDataSetChanged()
            }

//        fab.setOnClickListener { view ->
//
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun itemOpen(url: String){
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}
