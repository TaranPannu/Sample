package com.example.roomkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Request.Method.GET
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.w3c.dom.Text

class API_Calling : AppCompatActivity() {
    lateinit var TxtVw: Button
    lateinit var txt:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_calling)
        TxtVw = findViewById<Button>(R.id.TxtVw)
        val url: String = "https://jsonplaceholder.typicode.com/todos/1"
        val queue = Volley.newRequestQueue(this)
        txt=findViewById<TextView>(R.id.txt)
        fetchdata(url,queue)


    }

    fun fetchdata(url:String,queue: RequestQueue) {
        val url: String = "https://jsonplaceholder.typicode.com/todos/1"
        val queue = Volley.newRequestQueue(this)

        val json = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { a ->
            Toast.makeText(this, a.toString() + "", Toast.LENGTH_SHORT)
            txt.text=a.toString()
        }, Response.ErrorListener { err ->
            Toast.makeText(this, "error = " + err, Toast.LENGTH_SHORT)
        })
        queue.add(json)

    }

    fun shareMeme(view: View){
        Toast.makeText(this, "a.toString()", Toast.LENGTH_SHORT)


    }
}