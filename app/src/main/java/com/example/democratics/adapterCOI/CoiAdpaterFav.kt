package com.example.democratics.adapterCOI

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.datasql_lite.modelclass
import com.example.democratics.FragmentArticles.ArticleFavouriteCOi
import com.example.democratics.R


class CoiAdpaterFav(var context: Context, var modelArrayList: ArrayList<modelclass>) :
    RecyclerView.Adapter<CoiAdpaterFav.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.art_recyleradapter_coi, null, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = modelArrayList.get(position)

        holder.number.text = item.artNumber
        holder.article.text = item.artTitle



        holder.delete.setOnClickListener {
            if (context is ArticleFavouriteCOi) {
                (context as ArticleFavouriteCOi).deleteRecord(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return modelArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var number: TextView
        var article: TextView
        var delete: ImageView

        init {
            number = itemView.findViewById(R.id.article_no_fav)
            article = itemView.findViewById(R.id.article_name_fav)
            delete = itemView.findViewById(R.id.delete_coi_fav)
        }
    }
}
