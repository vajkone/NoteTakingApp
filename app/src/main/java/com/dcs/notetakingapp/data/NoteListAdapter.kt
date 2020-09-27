package com.dcs.notetakingapp.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dcs.notetakingapp.R
import com.dcs.notetakingapp.model.Note

class NoteListAdapter(private var list: MutableList<Note>, private val context: Context):
    androidx.recyclerview.widget.RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindNote(note: Note){

            val noteTitle = itemView.findViewById(R.id.noteTitle) as TextView
            val noteLabel = itemView.findViewById(R.id.noteLabels) as TextView
            val noteDate: TextView =itemView.findViewById(R.id.noteDate)
            val noteDeleteButton: TextView = itemView.findViewById(R.id.noteDeleteButton)

            noteTitle.text=note.noteTitle
            noteLabel.text=note.noteLabel
            noteDate.text=note.noteDate

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.note_card,parent,false)
        return ViewHolder(view)    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindNote(list[position])
    }
}