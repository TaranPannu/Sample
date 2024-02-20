package com.example.roomkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class MainActivity : AppCompatActivity(), NotesRVAdapter.INotesRVAdapter {

    lateinit var viewModel: NoteViewModel
    lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val x = intent.getStringExtra("key")



        var intent = Intent(this, MainActivity2::class.java)
        var tt = findViewById<TextView>(R.id.tt)
tt.setOnClickListener(){
    startActivity(intent)
}

      var recyclerView: RecyclerView = findViewById(R.id.rcy)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = Adapter(this)
        recyclerView.adapter = adapter
        val newsArray = ArrayList<Note>()
        adapter.updateNews(newsArray)


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)  //ViewModelProvider -> to attach viewmodel with activity life_cycle ,,
                                         // owner is lifecycle owner
        try {
            viewModel.allNotes.observe(this, Observer {
                it?.let {// to check list is not null

            adapter.updateNews(it)

                    // adapter.updateNews(it)
                }
            }) //allnotes live data,, owner that have life cycle--only observe data when lifecycle of Activity is Active
            //oncreate it start ,,on destroy band ho janda
        } catch (er: Exception) {
            Toast.makeText(this, er.toString(), Toast.LENGTH_SHORT)
        }
        if (!x.isNullOrEmpty()) {
            Toast.makeText(this,"this "+x.toString(),Toast.LENGTH_SHORT).show()
               viewModel.insertNote(Note(x))
        }

    }

    override fun onItemClicked(note: Note) {
        viewModel.deletenote(note)
    }

    fun submitData(view: View) {
        val input = findViewById<EditText>(R.id.input)
        val noteText = input.text.toString()
        if (noteText.isNotEmpty()) {
            viewModel.insertNote(Note(noteText))
        }
    }




}


