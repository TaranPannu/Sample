package com.example.roomkt

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import java.lang.Exception

class NoteViewModel(application: Application): AndroidViewModel(application){
    val allNotes: LiveData<List<Note>>
   val repository: NoteRepository

    init {
        val dao =NoteDatabase.getDatabase(application).getNoteDao()
       repository=NoteRepository(dao)
        allNotes=repository.allNotes

    }
    // suspend function can only be called from corountines and another suspend function
     fun deletenote(note: Note)=viewModelScope.launch {
        (Dispatchers.IO) {
            repository.delete(note)

        }
    }
      fun insertNote(note: Note)=viewModelScope.launch{(Dispatchers.IO) {
          repository.insert(note)
        }

}


}