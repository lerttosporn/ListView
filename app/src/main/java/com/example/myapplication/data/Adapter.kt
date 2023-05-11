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
import com.example.myapplication.databinding.ListItemBinding

class Adapter(
    private val context: Activity, private val arrayList: ArrayList<Article>
) : ArrayAdapter<Article>(context, R.layout.list_item, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val listData = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        }else {
            val imageView = view.findViewById<ImageView>(R.id.imageView)
            val title = view.findViewById<TextView>(R.id.mainTitle)
            val des = view.findViewById<TextView>(R.id.desPost)
            val publishedAt = view.findViewById<TextView>(R.id.publishedAt)

            title.text = listData?.title
            des.text = listData?.description
            publishedAt.text= listData?.publishedAt
        }

        return view!!
    }
}