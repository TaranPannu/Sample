package com.example.roomkt

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table") // will make table in  SQL
class Note(@ColumnInfo(name="text")val text:String){
    @PrimaryKey(autoGenerate = true) var id=0 //columns for table id,text where id will be auto generated

}