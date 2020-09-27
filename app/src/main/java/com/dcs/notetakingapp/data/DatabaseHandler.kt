package com.dcs.notetakingapp.data

import android.content.Context
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
}