package com.pan.room_databse.repository

import androidx.lifecycle.LiveData
import com.pan.room_databse.dao.NoteDao
import com.pan.room_databse.data.NoteEntity

class NoteRepository(private val noteDao: NoteDao) {

    val readAllNote: LiveData<MutableList<NoteEntity>> = noteDao.readAllNote()

    suspend fun addNote(noteEntity: NoteEntity) {
        noteDao.addNote(noteEntity)
    }

    suspend fun updateNote(note: NoteEntity) {
        noteDao.updateNoteData(note)
    }

    suspend fun deleteNote(note: NoteEntity) {
        noteDao.deleteNote(note)
    }

    suspend fun deleteAllNote(){
        noteDao.deleteAllNote()
    }
}