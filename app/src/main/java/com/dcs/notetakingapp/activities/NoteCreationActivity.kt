package com.dcs.notetakingapp.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.dcs.notetakingapp.R
import com.dcs.notetakingapp.data.DatabaseHandler
import com.dcs.notetakingapp.model.Note
import com.dcs.notetakingapp.model.NoteText
import kotlinx.android.synthetic.main.activity_note_creation.*

class NoteCreationActivity : AppCompatActivity() {

    private var dbHandler: DatabaseHandler? = null
    private var noteTextId:String? = null
    private var noteID:Int? = null
    private var exists:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_creation)

        dbHandler = DatabaseHandler(this)
        exists=false


        if (intent.hasExtra("noteTextId")){


            exists=true
            val data:Bundle? = intent.extras
            noteTextId = data?.getString("noteTextId")
            Log.d("notetextID:",noteTextId!!)
            noteID = data?.getInt("noteId")
            Log.d("noteID:",noteID.toString())
            var note: Note =dbHandler!!.getNoteByID(noteID!!)
            var noteText: NoteText =dbHandler!!.getNoteTextByID(noteTextId)

            noteTitle.setText(note.noteTitle, TextView.BufferType.EDITABLE)
            noteLabels.setText(note.noteLabel, TextView.BufferType.EDITABLE)
            noteTextCue.setText(noteText.noteText_cue, TextView.BufferType.EDITABLE)
            noteTextText.setText(noteText.noteText_text, TextView.BufferType.EDITABLE)
            noteTextSummary.setText(noteText.noteText_summary, TextView.BufferType.EDITABLE)

        }

        saveNote.setOnClickListener {
//
            if (exists){
                updateNote()
            }else {

            }

        }
    }

    private fun updateNote() {
        val newNote=Note()

        newNote.noteID=noteID
        newNote.noteText_ID=noteTextId
        newNote.noteLabel=noteLabels.text.toString()
        newNote.noteTitle=noteTitle.text.toString()

        dbHandler!!.updateNote(newNote)

        val newNoteText=NoteText()
        newNoteText.noteText_Id=noteTextId
        newNoteText.noteText_text=noteTextText.text.toString()
        newNoteText.noteText_cue=noteTextCue.text.toString()
        newNoteText.noteText_summary=noteTextSummary.text.toString()
        dbHandler!!.updateNoteText(newNoteText)
        val returnIntent = Intent()
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}