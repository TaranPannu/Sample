package com.example.roomkt

import android.content.Context
import android.text.method.TextKeyListener.clear
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.lang.Exception

class NotesRVAdapter(private val context: Context,private val listener: INotesRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {
 val allNotes=ArrayList<Note>()
 //   private val allNotes:ArrayList<Note> = ArrayList()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.txtVw)
        val deleteButton = itemView.findViewById<ImageView>(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
val viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent))
      viewHolder.deleteButton.setOnClickListener { listener.onItemClicked(allNotes[viewHolder.adapterPosition]) }
    return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote=allNotes[position]
        holder.textView.text=currentNote.text
  }

    override fun getItemCount(): Int {
        return allNotes.size      }

    interface INotesRVAdapter {
        fun onItemClicked(note:Note)
    }
    fun updateNews(newList: List<Note>){

        try{
            allNotes.clear()
     //    allNotes.addAll(newList)

              allNotes.add(Note("hello"))

         notifyDataSetChanged()//will re-initate all three methods onCreateViewHolder,getItemCount,onBindViewHolder}
    }catch (er:Exception){
            Log.d("122s",""+er.toString())


        }    }
}