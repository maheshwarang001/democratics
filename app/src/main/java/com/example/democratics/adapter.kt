package com.example.democratics

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view.view.*

class adapter(var list: List<info>) : RecyclerView.Adapter<adapter.viewHolder>() {

    class viewHolder(view: View) : RecyclerView.ViewHolder(view){

        var image = view.image_view
        var menu = view.menu_ig
        var animalname = view.animal_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.view , parent , false)
        return viewHolder(itemview)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.menu.setOnClickListener {
//            val intent = Intent(holder.menu.context , page2::class.java)
//
//            intent.putExtra("data" , list[position])
//            intent.putExtra("Animalname" , list[position].head)
//            intent.putExtra("locationAnimal" , list[position].location)
//            intent.putExtra("animalImage" , list[position].image)
//            intent.putExtra("weigntAnimal" , list[position].weight)
//            intent.putExtra("heigntAnimal" , list[position].height)
//            holder.menu.context.startActivity(intent)


        }


        holder.animalname.text = list[position].head
//        holder.location.text = list[position].location
//        holder.height.text = list[position].height
//        holder.weight.text = list[position].weight

        holder.image.setImageResource(list[position].image!!)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
