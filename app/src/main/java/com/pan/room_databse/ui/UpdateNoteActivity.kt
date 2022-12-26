package com.pan.room_databse.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.pan.room_databse.R
import com.pan.room_databse.data.NoteEntity
import com.pan.room_databse.databinding.ActivityUpdateNoteBinding
import com.pan.room_databse.modelView.NoteViewModel
import com.pan.room_databse.util.Constants

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel
    private var noteItems: NoteEntity? = null
    private lateinit var binding: ActivityUpdateNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        if (intent.hasExtra(Constants.UPDATE_KEY)) {
            intent.getParcelableExtra<NoteEntity>(Constants.UPDATE_KEY)?.let {
                noteItems = it
                binding.noteUpdateTitle.append(noteItems!!.noteTitle)
                binding.noteUpdateDescription.append(noteItems!!.noteDescription)
                binding.btnUpdateSave.setOnClickListener {
                    updateNoteItems()
                }
            }

        } else {
            Toast.makeText(this, "fail append data", Toast.LENGTH_SHORT).show()
        }
    }


    private fun updateNoteItems() {
        val updateTitle = binding.noteUpdateTitle.text.toString()
        val updateDesc = binding.noteUpdateDescription.text.toString()
        if (updateTitle.isNotEmpty() && updateDesc.isNotEmpty()) {
            val updateNoteModel = NoteEntity(noteItems!!.noteId, updateTitle, updateDesc)
            noteViewModel.updateNoteItem(updateNoteModel)
            val intent = Intent(this@UpdateNoteActivity, MainActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_item -> {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("Delete Note Item")
                alertDialogBuilder.setMessage("Do you want to delete this note item")
                alertDialogBuilder.setPositiveButton("Yes") { dialog, which ->
                    noteViewModel.deleteNoteItem(noteItems!!)
                    val intent=Intent(this,MainActivity::class.java)
                    intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
                alertDialogBuilder.setNegativeButton("No") { _, _ ->
                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                }
                alertDialogBuilder.create().show()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

}