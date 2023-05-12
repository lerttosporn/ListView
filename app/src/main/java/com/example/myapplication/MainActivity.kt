package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.SearchView
import com.example.myapplication.data.Adapter
import com.example.myapplication.data.Article
import com.example.myapplication.data.MainData
import com.example.myapplication.data.api.ApiService
import com.example.myapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var post: ArrayList<Article>
    private lateinit var newDataList: ArrayList<Article>
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        newDataList = ArrayList()
        getNewsMove()
        mainBinding.searching.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mainBinding.searching.clearFocus()
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    newDataList.clear()
                    for (article in post) {
                        if (article.title.contains(query)) {
                            newDataList.add(article)
                        }
                    }
                    adapter = Adapter(this@MainActivity, newDataList)
                    mainBinding.listView.adapter = adapter
                }
                return true
            }
        })
    }

    private fun getNewsMove() {
        val call = ApiService.retrofitBuild().getNews()
        call.enqueue(object : Callback<MainData> {
            override fun onFailure(call: Call<MainData>, t: Throwable) {
                Log.e("API ERROR!!!!!", t.message.toString())
            }

            override fun onResponse(call: Call<MainData>, response: Response<MainData>) {
                val data = response.body()
                if (response.isSuccessful && data != null) {
                    var dataMainList = ArrayList(data.articles)
                    post = dataMainList
                    Log.i("API", data.toString())

                    mainBinding.listView.isClickable = true
                    adapter = Adapter(this@MainActivity, dataMainList)
                    mainBinding.listView.adapter = adapter

                    mainBinding.listView.onItemClickListener =
                        AdapterView.OnItemClickListener { _, _, position, _ ->
                            val intent = Intent(this@MainActivity, DetailData::class.java)
                            intent.putExtra(
                                "description",
                                dataMainList[position].description
                            )
                            intent.putExtra(
                                "publishedAt",
                                dataMainList[position].publishedAt
                            )
                            intent.putExtra(
                                "mainTitle",
                                dataMainList[position].title.toString()
                            )
                            intent.putExtra("urlToImage", dataMainList[position]?.urlToImage)
                            startActivity(intent)
                        }
                } else {
                    Log.i("API data null", data.toString())
                }
            }
        })
    }
}