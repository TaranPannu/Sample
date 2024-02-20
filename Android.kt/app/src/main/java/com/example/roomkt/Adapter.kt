package com.example.roomkt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class Adapter( private val listener: MainActivity): RecyclerView.Adapter<NewsViewHolder>() {
    private val items:ArrayList<Note> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)
        val ViewHolder=NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked((items[ViewHolder.adapterPosition]))
        }
        return ViewHolder
    }

    override fun getItemCount(): Int {
        return items.size   }
    fun updateNews(list:List<Note>){
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()//will re-initate all three methods onCreateViewHolder,getItemCount,onBindViewHolder
    }
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currItem=items[position]
        holder.titleView.text="$position: "+currItem.text
      //  Glide.with(holder.imgView.context).load(currItem.imageUrl).into(holder.imgView)
    }

}

class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val titleView: TextView=itemView.findViewById(R.id.txtVw)
    val imgView:ImageView=itemView.findViewById(R.id.deleteBtn)
}

interface NewsItemClicked{
    fun onItemClicked(item:Note)
}