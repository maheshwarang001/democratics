package com.example.democratics.adapterCOI

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.democratics.COI.model.model
import com.example.democratics.FragmentArticles.ArticleMain
import com.example.democratics.R

class adapter(var context: Context, var modelArrayList: ArrayList<model>) :
    RecyclerView.Adapter<adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.coi_recycle, null, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var position = position
        position = holder.adapterPosition
        holder.number.text = modelArrayList[position].article
        holder.article.text = modelArrayList[position].title

        holder.next.setOnClickListener {

            val intent = Intent(context, ArticleMain::class.java)

            intent.putExtra("ARTICLE_NUMBER", modelArrayList[position].article)
            intent.putExtra("ARTICLE_TITLE", modelArrayList[position].title)
            intent.putExtra("ARTICLE_DESCRIPTION", modelArrayList[position].description)
            holder.next.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return modelArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var number: TextView
        var article: TextView
        var next: ImageView

        init {
            number = itemView.findViewById(R.id.article_no)
            article = itemView.findViewById(R.id.article_name)
            next = itemView.findViewById(R.id.next_coi)
        }
    }
}