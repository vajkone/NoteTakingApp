package com.dcs.notetakingapp.model



val DATABASE_VERSION: Int = 2
val DATABSE_NAME: String = "notes.db"

val NOTE_TABLE_NAME: String = "note"

val NOTE_KEY_ID: String = "note_id"
val NOTE_TITLE: String = "note_name"
val NOTE_DATE: String = "note_date"
val NOTE_TEXT_ID: String = "note_text_id"
val NOTE_LABEL: String = "note_label"

val NOTETEXT_TABLE_NAME: String = "noteText"
val NOTETEXT_TEXT_ID: String = "noteText_text_id"
val NOTETEXT_CUE: String = "noteText_cue"
val NOTETEXT_TEXT: String = "noteText_text"
val NOTETEXT_SUMMARY: String = "noteText_summary"