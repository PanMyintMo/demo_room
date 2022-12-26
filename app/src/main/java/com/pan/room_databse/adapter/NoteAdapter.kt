package com.pan.room_databse.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pan.room_databse.R
import com.pan.room_databse.data.NoteEntity
import com.pan.room_databse.databinding.NoteRowBinding
import com.pan.room_databse.ui.UpdateNoteActivity
import com.pan.room_databse.util.Constants.UPDATE_KEY

class NoteAdapter(private val context: Context, private val itemList: MutableList<NoteEntity>) :

    RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {

    class MyViewHolder(noteView: View) : RecyclerView.ViewHolder(noteView) {
        val binding = NoteRowBinding.bind(noteView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentNote = itemList[position]
        holder.binding.noteRowTitle.text = currentNote.noteTitle
        holder.binding.noteRowDescription.text = currentNote.noteDescription


        holder.binding.root.setOnClickListener {
            val intent = Intent(context, UpdateNoteActivity::class.java)
            intent.putExtra(UPDATE_KEY,currentNote)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}