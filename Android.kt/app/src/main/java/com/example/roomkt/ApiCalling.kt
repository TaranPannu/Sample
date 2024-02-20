package com.example.roomkt

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest

class ApiCalling {
    fun fetchdata(adapter: ApiAdapter,context:Context){
        var url ="https://type.fit/api/quotes"
        var json= JsonArrayRequest(Request.Method.GET,url,null,Response.Listener {
            for (i in 0 until it.length()) {
                adapter.update(it.getJSONObject(i).getString("text").toString())
            }
        }, Response.ErrorListener {
        })
        MySingleton.getInstance(context).addToRequestQueue(json)

    }
}