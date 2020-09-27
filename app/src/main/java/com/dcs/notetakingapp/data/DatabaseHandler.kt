package com.dcs.notetakingapp.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
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

    fun createNote(note: Note){
        val db: SQLiteDatabase = writableDatabase

        val values = ContentValues()

        values.put(NOTE_TITLE, note.noteTitle)
        values.put(NOTE_DATE, note.noteDate)
        values.put(NOTE_LABEL, note.noteLabel)
        values.put(NOTE_TEXT_ID, note.noteText_ID)
        values.put(NOTE_KEY_ID, note.noteID)

        db.insert(NOTE_TABLE_NAME, null, values)

        Log.d("DATA INSERTED", "SUCCESS")
        db.close()
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
}