package com.pan.room_databse.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pan.room_databse.dao.NoteDao
import com.pan.room_databse.data.NoteEntity
import com.pan.room_databse.util.Constants.NOTE_TABLE


@Database(entities = [NoteEntity::class], version = 2, exportSchema = true)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `${NOTE_TABLE}` (`noteId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `note_title` TEXT NOT NULL, `note_desc` TEXT NOT NULL)")
            }
        }

        @Volatile
        private var INSTANCE: NoteDatabase? = null
        fun getDatabase(context: Context): NoteDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).addMigrations().build()
                INSTANCE = instance
                return instance
            }
        }


    }
}