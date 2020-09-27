package com.dcs.notetakingapp.activities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dcs.notetakingapp.R
import com.dcs.notetakingapp.data.DatabaseHandler
import com.dcs.notetakingapp.data.NoteListAdapter
import com.dcs.notetakingapp.model.Note
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var noteListAdapter: NoteListAdapter? = null
    private var dbHandler: DatabaseHandler? = null
    private var noteList: MutableList<Note>? = null
    private var noteListItems: ArrayList<Note>? = null

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutManager=LinearLayoutManager(this)
        dbHandler= DatabaseHandler(this)
        noteList= ArrayList()
        noteListItems= ArrayList()
        noteListAdapter= NoteListAdapter(noteListItems!!,this)

        NotesRecView.layoutManager=layoutManager
        NotesRecView.adapter=noteListAdapter

        noteList= dbHandler!!.getAllNotes()

        if (noteList!!.isNotEmpty()){

            for (n: Note in noteList!!.iterator()){
                val note=Note()
                //note.noteText=n.noteText
                note.noteID=n.noteID
                note.noteTitle=n.noteTitle
                note.noteDate=n.noteDate
                note.noteLabel=n.noteLabel
                note.noteText_ID=n.noteText_ID

                noteListItems!!.add(note)
            }

            noteListAdapter!!.notifyDataSetChanged()
        }

        addNewNote.setOnClickListener {
            val intent = Intent(this,NoteCreationActivity::class.java)
            startActivityForResult(intent,1)
        }

    }


}