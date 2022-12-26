package com.pan.room_databse.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pan.room_databse.data.NoteEntity
import com.pan.room_databse.util.Constants.NOTE_TABLE

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(noteData: NoteEntity)

    @Query("SELECT * FROM $NOTE_TABLE ORDER BY noteId")
     fun readAllNote():LiveData<MutableList<NoteEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNoteData(noteData: NoteEntity)

    @Delete
    suspend fun deleteNote(noteData: NoteEntity)

    @Query( "Delete FROM NOTE_TABLE")
    suspend fun deleteAllNote()

   /* @Query("SELECT * FROM $NOTE_TABLE WHERE noteId LIKE noteId")
    fun getNote(noteId:Int):NoteEntity*/

}