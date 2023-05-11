package com.example.myapplication.data

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.myapplication.R

class Adapter(
    private val context: Activity, private val arrayList: ArrayList<Article>
) : ArrayAdapter<Article>(context, R.layout.list_item, arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.list_item, null)

//        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val title = view.findViewById<TextView>(R.id.mainTitle)
        val des = view.findViewById<TextView>(R.id.desPost)
        val publishedAt = view.findViewById<TextView>(R.id.publishedAt)

//        Glide.with(this)
//            .load("https://example.com/image.jpg")
//            .into(imageView)
//        imageView.setImageResource(arrayList[position].)
        title.text = arrayList[position].title
        des.text = arrayList[position].description
        publishedAt.text= arrayList[position].publishedAt
        return view
    }
}