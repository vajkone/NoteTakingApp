package com.dcs.notetakingapp.data

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dcs.notetakingapp.R
import com.dcs.notetakingapp.activities.NoteCreationActivity
import com.dcs.notetakingapp.model.Note

class NoteListAdapter(private var list: MutableList<Note>, private val context: Context):
    androidx.recyclerview.widget.RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private var dbhandler=DatabaseHandler(context)

        fun bindNote(note: Note){

            val noteTitle = itemView.findViewById(R.id.noteTitle) as TextView
            val noteLabel = itemView.findViewById(R.id.noteLabels) as TextView
            val noteDate: TextView =itemView.findViewById(R.id.noteDate)
            val noteDeleteButton: TextView = itemView.findViewById(R.id.noteDeleteButton)

            noteTitle.text=note.noteTitle
            noteLabel.text=note.noteLabel
            noteDate.text=note.noteDate

            noteDeleteButton.setOnClickListener {

                val builder = AlertDialog.Builder(context)
                builder.setMessage("Are you sure you want to Delete?")
                    .setTitle("Warning!")
                    .setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog, wich ->
                        dbhandler.removeNotetextById(note.noteText_ID!!)
                        dbhandler.removeNoteById(note.noteID!!)
                        val a = adapterPosition
                        list.removeAt(a)
                        notifyItemRemoved(adapterPosition)
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener{ dialog, wich ->
                        // User cancelled the dialog
                    })
                    .show()

                builder.create()

            }

            itemView.setOnClickListener {

                val intent = Intent(context, NoteCreationActivity::class.java)
                intent.putExtra("noteTextId",note.noteText_ID)
                intent.putExtra("noteId",note.noteID)
                context.startActivity(intent)

            }

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

    fun searchByKeywords(title: String,label:String,text:String){
        list.clear()
        val dbhandler=DatabaseHandler(context)
        var listofnotesbytitle= ArrayList<Note>()
        var listofnotesbylabel= ArrayList<Note>()
        var listofnotesbytext= ArrayList<Note>()
        if (!title.isBlank()){
            listofnotesbytitle=dbhandler.searchNotesByTitle(title)
            list.plusAssign(listofnotesbytitle)
        }
        if (!label.isBlank()){
            listofnotesbylabel=dbhandler.searchNotesByLabel(label)
            list.plusAssign(listofnotesbylabel)
        }
        if (!text.isBlank()){
            listofnotesbytext=dbhandler.searchNotesByText(text)
            list.plusAssign(listofnotesbytext)
        }
    }

    fun sortByTitle(){
        list.sortBy { it.noteTitle }
    }

    fun sortByTitleReversed(){
        list.reverse()
    }

    fun sortByDate(){
        list.sortBy { it.noteDate }
    }

    fun sortByDateReversed(){
        list.reverse()
    }
}