package com.example.roomkt

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.roomkt.MainActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.NonDisposableHandle.parent
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class ApiAdapter(val items:ArrayList<String>,private val listener: MainActivity2): RecyclerView.Adapter<ApiAdapter.ViewHolder>() {

 //   private val items:ArrayList<String> = ArrayList()
    override fun getItemCount(): Int {

return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currItem=items[position]
        holder.titleView.text=""+currItem
        holder.imgView.setOnClickListener(){
Log.d("hh","ss")
            listener.onItemClicked(currItem)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {

        val view= LayoutInflater.from(parent.context).inflate(R.layout.api_item,parent,false)
        return ViewHolder(view)
    }
fun update(st:String){
    items.add(st)

    notifyDataSetChanged()
}
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val titleView: TextView =itemView.findViewById(R.id.apiView)
       val imgView: ImageView =itemView.findViewById(R.id.AddBtn)

    }
}