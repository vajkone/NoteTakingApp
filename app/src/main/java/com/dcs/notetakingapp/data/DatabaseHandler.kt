package com.dcs.notetakingapp.data

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dcs.notetakingapp.model.*

class DatabaseHandler(context: Context): SQLiteOpenHelper(context, DATABSE_NAME,null,
    DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createNoteTable = "create table $NOTE_TABLE_NAME (" +
                "$NOTE_KEY_ID INTEGER PRIMARY KEY," +
                "$NOTE_TITLE nvarchar(50) not null," +
                "$NOTE_LABEL nvarchar(50)," +
                "$NOTE_TEXT_ID nvarchar(50) not null," +
                "$NOTE_DATE LONG," +
                "constraint nt_fk_ti foreign key($NOTE_TEXT_ID) REFERENCES NoteText($NOTETEXT_TEXT_ID));"



        db?.execSQL(createNoteTable)

        val createTextTable = "create table $NOTETEXT_TABLE_NAME (" +
                "$NOTETEXT_TEXT_ID nvarchar(50) primary key," +
                "$NOTETEXT_CUE text," +
                "$NOTETEXT_TEXT text," +
                "$NOTETEXT_SUMMARY text);"

        db?.execSQL(createTextTable)


    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $NOTE_TABLE_NAME")

        onCreate(db)
    }

    fun getAllNotes() : ArrayList<Note>{

        val db: SQLiteDatabase = readableDatabase
        val list: ArrayList<Note> = ArrayList()

        //Select all notes from table
        val selectAll = "SELECT * FROM $NOTE_TABLE_NAME"

        val cursor: Cursor = db.rawQuery(selectAll, null)

        //loop through our notes
        if (cursor.moveToFirst()) {
            do {
                var note = Note()

                note.noteID = cursor.getInt(cursor.getColumnIndex(NOTE_KEY_ID))
                note.noteTitle = cursor.getString(cursor.getColumnIndex(NOTE_TITLE))
                note.noteLabel = cursor.getString(cursor.getColumnIndex(NOTE_LABEL))
                note.noteDate = cursor.getString(cursor.getColumnIndex(NOTE_DATE))
                note.noteText_ID=cursor.getString(cursor.getColumnIndex(NOTE_TEXT_ID))


                list.add(note)

            }while (cursor.moveToNext())
        }

        cursor.close()
        return list


    }

    fun updateNote(newNote: Note) {
        val db:  SQLiteDatabase = writableDatabase
        val id=newNote.noteID
        val newtitle=newNote.noteTitle
        val newnotelabel=newNote.noteLabel

        val update="UPDATE $NOTE_TABLE_NAME SET $NOTE_TITLE = '$newtitle',$NOTE_LABEL='$newnotelabel' WHERE $NOTE_KEY_ID=$id"
        db.execSQL(update)
        db.close()

    }


    fun updateNoteText(newNoteText: NoteText) {
        val db:SQLiteDatabase = writableDatabase
        val id = newNoteText.noteText_Id
        val newcue= newNoteText.noteText_cue
        val newtext = newNoteText.noteText_text
        val newsummary=newNoteText.noteText_summary

        val update = "UPDATE $NOTETEXT_TABLE_NAME SET $NOTETEXT_CUE = '$newcue',$NOTETEXT_TEXT='$newtext',$NOTETEXT_SUMMARY='$newsummary' WHERE $NOTETEXT_TEXT_ID='$id'"
        db.execSQL(update)
        db.close()

    }
}