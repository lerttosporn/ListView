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
    private lateinit var post: ArrayList<Article>/* olds array */
    private lateinit var newDataList: ArrayList<Article> /* new array */
    private lateinit var adapter: Adapter
    private var check=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        newDataList = ArrayList()/* set init new array */
        getNewsMove()
        mainBinding.listView.onItemClickListener =
            AdapterView.OnItemClickListener { AdapterView, view, position, l ->
                if(check){val intent = Intent(this@MainActivity, DetailData::class.java)
                    intent.putExtra(
                        "description",
                         post[position].description
                    )
                    intent.putExtra(
                        "publishedAt",
                         post[position].publishedAt
                    )
                    intent.putExtra(
                        "mainTitle",
                        post[position].title
                    )
                    intent.putExtra("urlToImage", post[position].urlToImage)
                    startActivity(intent)
                    check = false
                }else{
                    val intent = Intent(this@MainActivity, DetailData::class.java)
                    intent.putExtra(
                        "description",
                        newDataList[position].description
                    )
                    intent.putExtra(
                        "publishedAt",
                        newDataList[position].publishedAt
                    )
                    intent.putExtra(
                        "mainTitle",
                        newDataList[position].title
                    )
                    intent.putExtra("urlToImage", newDataList[position].urlToImage)
                    startActivity(intent)
                }
            }

    }

    override fun onResume() {
        super.onResume()
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
                    post = ArrayList(data.articles)
                    mainBinding.listView.isClickable = true
                    adapter = Adapter(this@MainActivity, post)
                    mainBinding.listView.adapter = adapter

                    mainBinding.searching.setOnQueryTextListener(object :
                        SearchView.OnQueryTextListener {
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
                } else {
                    Log.i("API data null", data.toString())
                }
            }
        })
    }

}
