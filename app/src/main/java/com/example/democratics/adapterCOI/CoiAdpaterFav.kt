package com.example.democratics.adapterCOI

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.datasql_lite.modelclass
import com.example.democratics.FragmentArticles.ArticleFavouriteCOi
import com.example.democratics.FragmentArticles.BookmarkActivity
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

        holder.move.setOnClickListener {
            val intent = Intent(context,BookmarkActivity::class.java)

            intent.putExtra("ARTICLE_NUMBER", modelArrayList[position].artNumber)
            intent.putExtra("ARTICLE_TITLE", modelArrayList[position].artTitle)
            intent.putExtra("ARTICLE_DESCRIPTION", modelArrayList[position].artDes)
            holder.move.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return modelArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var number: TextView
        var article: TextView
        var delete: ImageView
        var move: LinearLayout

        init {
            number = itemView.findViewById(R.id.article_no_fav)
            article = itemView.findViewById(R.id.article_name_fav)
            delete = itemView.findViewById(R.id.delete_coi_fav)
            move = itemView.findViewById(R.id.click_fav)
        }
    }
}
