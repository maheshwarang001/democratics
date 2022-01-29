package com.example.democratics.RecyclerViewNews

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.democratics.R

public class RecyclerAdapter(
    private var title: List<String>,
    private var details: List<String>,
    private var author: List<String>,
    private var image: List<String>,
    private var readmore: List<String>,
    private var date: List<String>,
    private var links: List<String>
): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

   inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

       val itemTitle: TextView = itemView.findViewById(R.id.news_title)
       val itemAuthor: TextView = itemView.findViewById(R.id.news_author)
       val itemImage: ImageView = itemView.findViewById(R.id.news_image)

       init {
           itemView.setOnClickListener{
               val position: Int = adapterPosition

               val intent: Intent = Intent(Intent.ACTION_VIEW)
               intent.data = Uri.parse(links[position])
               startActivity(itemView.context , intent , null)
           }
       }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.latest_recycler_view,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = title[position]
        holder.itemAuthor.text = author[position]

        Glide.with(holder.itemImage)
            .load(image[position])
            .into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return title.size
    }
}