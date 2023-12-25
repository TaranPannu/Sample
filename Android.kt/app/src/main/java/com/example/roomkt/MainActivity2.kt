package com.example.roomkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception


class MainActivity2 : AppCompatActivity()
                {
    override fun onCreate(savedInstanceState: Bundle?)
            {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var x=ApiCalling()
        var v:ArrayList<String> = ArrayList()
        var rcy2=findViewById<RecyclerView>(R.id.rcy2)
        rcy2.layoutManager=LinearLayoutManager(this)
        var adapter=ApiAdapter(v,this)
        rcy2.adapter=adapter
        x.fetchdata(adapter,this)
             }

                    fun onItemClicked(currItem: String) {
                        var intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("key",currItem)
startActivity(intent)
                    }
                }