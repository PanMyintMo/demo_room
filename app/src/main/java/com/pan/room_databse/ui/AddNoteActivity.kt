package com.pan.room_databse.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.pan.room_databse.data.NoteEntity
import com.pan.room_databse.databinding.ActivityAddNoteBinding
import com.pan.room_databse.modelView.NoteViewModel

class AddNoteActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        binding.btnSave.setOnClickListener {
            val title = binding.noteTitle.text.toString()
            val description = binding.noteDescription.text.toString()

            if (title.isNotEmpty() || description.isNotEmpty()) {
                val noteDataItem = NoteEntity(0, title, description)
                noteViewModel.addNote(noteDataItem)
                Toast.makeText(
                    this@AddNoteActivity,
                    "Successful added into database",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this@AddNoteActivity, MainActivity::class.java)

               intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            } else {
                Snackbar.make(
                    it,
                    "Title and description cannot be Empty",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}