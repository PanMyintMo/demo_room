package com.pan.room_databse.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pan.room_databse.R
import com.pan.room_databse.adapter.NoteAdapter
import com.pan.room_databse.data.NoteEntity
import com.pan.room_databse.databinding.ActivityMainBinding
import com.pan.room_databse.modelView.NoteViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var adapter: NoteAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteViewModel: NoteViewModel
    private var itemList = mutableListOf<NoteEntity>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        binding.btnAddNote.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddNoteActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        noteViewModel = ViewModelProvider(this@MainActivity)[NoteViewModel::class.java]
        noteViewModel.readAllNote.observe(this) { noteList ->
            if (noteList.isNotEmpty()) {
                itemList = noteList
                adapter = NoteAdapter(this, itemList)
                binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                binding.recyclerView.adapter = adapter
                binding.recyclerView.visibility = View.VISIBLE
                binding.textNote.visibility = View.GONE
            } else {
                itemList.clear()
                binding.recyclerView.visibility = View.GONE
                binding.textNote.visibility = View.VISIBLE
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { menuInflater.inflate(R.menu.delete, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_item -> {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("Delete Note Item")
                alertDialogBuilder.setMessage("Do you want to delete this note item")
                alertDialogBuilder.setPositiveButton("Yes") { dialog, which ->
                    noteViewModel.deleteAllNoteItem()
                    binding.textNote.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                alertDialogBuilder.setNegativeButton("No") { _, _ ->
                }
                alertDialogBuilder.create().show()
                true
            }
            else -> {
                return false
            }
        }
    }
}