package com.example.roomkt

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    // these operations will run in background not on main thread insert and delete
    @Insert
   suspend fun insert(note: Note)// take note and insert it
    @Delete
  suspend fun delete(note: Note)// suspend means background function
    @Query("Select * from notes_table order by id  ASC")
     fun getAllNotes(): LiveData<List<Note>>//live_data lifecycle aware Data

}