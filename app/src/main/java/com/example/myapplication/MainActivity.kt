package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    private lateinit var dataMainList:ArrayList<Article>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        getNews()
        mainBinding.listView.isClickable = true
        mainBinding.listView.adapter = Adapter(this, dataMainList)
        mainBinding.listView.setOnClickListener{
//            dataMainList.
        }
    }

    private fun getNews() {
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
                } else {
                    Log.i("API data null", data.toString())
                }
            }
        })

    }
}