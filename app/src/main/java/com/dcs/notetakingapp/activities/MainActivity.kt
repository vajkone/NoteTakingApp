package com.dcs.notetakingapp.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.dcs.notetakingapp.R
import com.dcs.notetakingapp.data.DatabaseHandler
import com.dcs.notetakingapp.data.NoteListAdapter
import com.dcs.notetakingapp.model.Note
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.search_card.view.*

class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var noteListAdapter: NoteListAdapter? = null
    private var dbHandler: DatabaseHandler? = null
    private var noteList: MutableList<Note>? = null
    private var noteListItems: ArrayList<Note>? = null
    private var view: View? = null
    private var dialog: AlertDialog? = null
    private var sorted: Boolean = false
    private var sortedByDate: Boolean = false

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

        search.setOnClickListener {

            view = View.inflate(this, R.layout.search_card, null)
            val builder = AlertDialog.Builder(this)
            builder.setView(view)

            dialog = builder.create()
            dialog!!.show()
            dialog!!.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        dateBtn.setOnClickListener{
            sortedByDate = if (sortedByDate) {
                noteListAdapter!!.sortByDate()
                noteListAdapter!!.notifyDataSetChanged()
                true
            } else {
                noteListAdapter!!.sortByDateReversed()
                noteListAdapter!!.notifyDataSetChanged()
                false
            }
        }

        alphaBtn.setOnClickListener {
            if (!sorted) {
                noteListAdapter!!.sortByTitle()
                sorted = true
                noteListAdapter!!.notifyDataSetChanged()
            } else {
                noteListAdapter!!.sortByTitleReversed()
                noteListAdapter!!.notifyDataSetChanged()
                sorted = false
            }
        }

        defaultList.setOnClickListener {
            noteListAdapter!!.getDefaultList()
            noteListAdapter!!.notifyDataSetChanged()
        }

    }

    fun onSearchClicked(v: View){
        val titlesearch=view!!.titleSearch.text.toString()

        val labelsearch=view!!.labelSearch.text.toString()
        val textsearch=view!!.textSearch.text.toString()
        Log.d("searching by tag:",titlesearch)

        noteListAdapter!!.searchByKeywords(titlesearch,labelsearch,textsearch)
        noteListAdapter!!.notifyDataSetChanged()
        dialog!!.dismiss()


    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode== Activity.RESULT_OK){
            val id:Int = data!!.getIntExtra("recentNoteID",0)
            noteListItems!!.add(dbHandler!!.getNoteByID(id))
            noteListAdapter!!.notifyDataSetChanged()
        }


    }


}