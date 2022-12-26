package com.pan.room_databse.modelView

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pan.room_databse.data.NoteEntity
import com.pan.room_databse.database.NoteDatabase
import com.pan.room_databse.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val readAllNote: LiveData<MutableList<NoteEntity>>
    private val repository: NoteRepository

    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        readAllNote = repository.readAllNote
    }


    fun addNote(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(noteEntity)
        }
    }


    fun updateNoteItem(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(noteEntity)
        }
    }

    fun deleteNoteItem(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(noteEntity)
        }
    }

    fun deleteAllNoteItem(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllNote()
        }
    }

}