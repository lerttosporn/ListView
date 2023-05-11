package com.example.myapplication

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityDetailDataBinding

class DetailData : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = this.intent
        if(intent != null){

        }
    }
}