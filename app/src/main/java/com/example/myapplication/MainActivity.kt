package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        getNewsMove()

//        mainBinding.listView.setOnClickListener{
//            dataMainList.
//        }
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
                    Log.i("API", data.toString())
                    mainBinding.listView.isClickable = true
                    mainBinding.listView.adapter = Adapter(this@MainActivity, dataMainList)
                mainBinding.listView.onItemClickListener= AdapterView.OnItemClickListener {
                        _, _, position, _ ->
                val intent=Intent(this@MainActivity,DetailData::class.java)
                    intent.putExtra("description",dataMainList[position].description.toString())
                    intent.putExtra("publishedAt",dataMainList[position].publishedAt.toString())
                    intent.putExtra("mainTitle",dataMainList[position].title.toString())
                    intent.putExtra("urlToImage",dataMainList[position]?.urlToImage.toString())
                    startActivity(intent)
                }
                } else {
                    Log.i("API data null", data.toString())
                }
            }
        })

    }
}