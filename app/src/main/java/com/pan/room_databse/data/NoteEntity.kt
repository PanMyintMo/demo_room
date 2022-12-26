package com.pan.room_databse.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pan.room_databse.util.Constants.NOTE_TABLE
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = NOTE_TABLE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val noteId: Int,
    @ColumnInfo(name = "note_title")
    val noteTitle: String,
    @ColumnInfo(name = "note_desc")
    val noteDescription: String
):Parcelable