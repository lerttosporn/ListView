package com.example.myapplication

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityDetailDataBinding
import com.squareup.picasso.Picasso

class DetailData : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = this.intent
        if(intent != null){
           val description= intent.getStringExtra("description")?:""
           val publishedAt= intent.getStringExtra("publishedAt")?:""
           val mainTitle= intent.getStringExtra("mainTitle")?:""
           val urlToImage= intent.getStringExtra("urlToImage")
            Picasso.get()
                .load(urlToImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(binding.imageView2)
            binding.detailTitle.text=mainTitle
            binding.detailDes.text=description
            binding.detailPublishedAt.text=publishedAt
        }
    }
}