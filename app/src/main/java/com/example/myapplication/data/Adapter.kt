package com.example.myapplication.data

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.R
import com.squareup.picasso.Picasso

class Adapter(
    private val context: Activity,
    private var arrayList: ArrayList<Article>
) : ArrayAdapter<Article>(context, R.layout.list_item, arrayList), Filterable {

    private var filteredArrayList: ArrayList<Article> = arrayList

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val listData = filteredArrayList[position]
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }
        val imageView = view!!.findViewById<ImageView>(R.id.imageView)
        val title = view.findViewById<TextView>(R.id.mainTitle)
        val des = view.findViewById<TextView>(R.id.desPost)
        val publishedAt = view.findViewById<TextView>(R.id.publishedAt)
        Picasso.get()
            .load(listData.urlToImage)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .into(imageView)
        title.text = listData.title
        des.text = listData.description
        publishedAt.text = listData.publishedAt

        return view
    }
}
