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

    fun clearDbAndRecreate() {
        clearDb()
        onCreate(writableDatabase)
    }

    fun clearDb() {
        writableDatabase.execSQL("DROP TABLE IF EXISTS $NOTE_TABLE_NAME")
        writableDatabase.execSQL("DROP TABLE IF EXISTS $NOTETEXT_TABLE_NAME")
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
    
    fun createNoteText(noteText:NoteText){
        val db: SQLiteDatabase = writableDatabase

        val values = ContentValues()

        values.put(NOTETEXT_TEXT_ID, noteText.noteText_Id)
        values.put(NOTETEXT_CUE, noteText.noteText_cue)
        values.put(NOTETEXT_TEXT, noteText.noteText_text)
        values.put(NOTETEXT_SUMMARY, noteText.noteText_summary)

        db.insert(NOTETEXT_TABLE_NAME, null, values)

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

    fun getNoteCount(): Int{

        val db:  SQLiteDatabase = readableDatabase

        val countQuery = "SELECT * FROM $NOTE_TABLE_NAME"
        val cursor: Cursor = db.rawQuery(countQuery, null)
        val returnit = cursor.count

        cursor.close()
        return returnit

    }

    fun getLastNoteId(): Int{

        val db:  SQLiteDatabase = readableDatabase

        val countQuery = "SELECT $NOTE_KEY_ID FROM $NOTE_TABLE_NAME order by $NOTE_KEY_ID desc"
        val cursor: Cursor = db.rawQuery(countQuery, null)

        cursor.moveToFirst()
        val returnit = cursor.getInt(cursor.getColumnIndex(NOTE_KEY_ID))
        cursor.close()
        return returnit

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

    fun getNoteByID(id: Int): Note {
        val db:  SQLiteDatabase = readableDatabase

        val countQuery = "SELECT * FROM $NOTE_TABLE_NAME where $NOTE_KEY_ID=$id"
        val cursor: Cursor = db.rawQuery(countQuery,null)
        val note = Note()
        if (cursor.moveToFirst()) {



            note.noteID = cursor.getInt(cursor.getColumnIndex(NOTE_KEY_ID))
            note.noteTitle = cursor.getString(cursor.getColumnIndex(NOTE_TITLE))
            note.noteLabel = cursor.getString(cursor.getColumnIndex(NOTE_LABEL))
            note.noteDate = cursor.getString(cursor.getColumnIndex(NOTE_DATE))
            note.noteText_ID=cursor.getString(cursor.getColumnIndex(NOTE_TEXT_ID))


        }



        cursor.close()
        return note
    }

    fun getNoteTextByID(noteTextId: String?): NoteText {

        val db:  SQLiteDatabase = readableDatabase

        val countQuery = "SELECT * FROM $NOTETEXT_TABLE_NAME where $NOTETEXT_TEXT_ID='$noteTextId'"
        val cursor: Cursor = db.rawQuery(countQuery,null)
        val noteText = NoteText()
        if (cursor.moveToFirst()) {



            noteText.noteText_Id = cursor.getString(cursor.getColumnIndex(NOTETEXT_TEXT_ID))
            noteText.noteText_text = cursor.getString(cursor.getColumnIndex(NOTETEXT_TEXT))
            noteText.noteText_cue = cursor.getString(cursor.getColumnIndex(NOTETEXT_CUE))
            noteText.noteText_summary = cursor.getString(cursor.getColumnIndex(NOTETEXT_SUMMARY))


        }



        cursor.close()
        return noteText

    }

    fun searchNotesByTitle(title: String):ArrayList<Note> {

        val db:  SQLiteDatabase = readableDatabase

        val query = "SELECT * FROM $NOTE_TABLE_NAME where $NOTE_TITLE LIKE '%$title%'"
        val cursor: Cursor = db.rawQuery(query,null)
        val list=ArrayList<Note>()

        if (cursor.moveToFirst()) {
            do {
                val note = Note()
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

    fun removeAllNotes(){
        val db:  SQLiteDatabase = writableDatabase
        val delete="delete from $NOTE_TABLE_NAME"
        db.execSQL(delete)
        db.close()
    }

    fun searchNotesByLabel(label: String):ArrayList<Note> {

        val db:  SQLiteDatabase = readableDatabase

        val query = "SELECT * FROM $NOTE_TABLE_NAME where $NOTE_LABEL LIKE '%$label%'"
        val cursor: Cursor = db.rawQuery(query,null)
        val list=ArrayList<Note>()

        if (cursor.moveToFirst()) {
            do {
                val note = Note()
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

    fun searchNotesByText(text: String):ArrayList<Note> {

        val db:  SQLiteDatabase = readableDatabase

        val query = "SELECT * FROM $NOTE_TABLE_NAME nt inner join $NOTETEXT_TABLE_NAME ntt on " +
                "nt.$NOTE_TEXT_ID = ntt.$NOTETEXT_TEXT_ID where $NOTETEXT_TEXT LIKE '%$text%'"
        val cursor: Cursor = db.rawQuery(query,null)
        val list=ArrayList<Note>()

        if (cursor.moveToFirst()) {
            do {
                val note = Note()
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

    fun removeNoteById(id: Int) {
        val db:  SQLiteDatabase = writableDatabase

        db.execSQL("DELETE FROM $NOTE_TABLE_NAME where $NOTE_KEY_ID=$id")
        db.close()
    }

    fun removeNotetextById(id: String) {
        val db:  SQLiteDatabase = writableDatabase
        db.execSQL("DELETE FROM $NOTETEXT_TABLE_NAME where $NOTETEXT_TEXT_ID='$id'")
        db.close()
    }


}