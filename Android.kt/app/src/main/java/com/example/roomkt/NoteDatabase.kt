package com.example.roomkt

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object{
       @Volatile
        private var INSTANCE: NoteDatabase?=null

      fun getDatabase(context: Context): NoteDatabase{
          //if instance is not null, then return it
          //if null, then create database
          return INSTANCE ?: synchronized(this){
              val instance= Room.databaseBuilder(
                  context.applicationContext,
                  NoteDatabase::class.java,
                  "note_databse"
              ).build()
              INSTANCE=instance
              //return instance
              instance
          }
      }
    }

}