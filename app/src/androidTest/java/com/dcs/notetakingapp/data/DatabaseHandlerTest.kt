package com.dcs.notetakingapp.data

import android.content.Context
import android.os.Build.VERSION_CODES.LOLLIPOP
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.platform.app.InstrumentationRegistry
import com.dcs.notetakingapp.model.Note
import com.dcs.notetakingapp.test.BuildConfig
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import kotlin.concurrent.fixedRateTimer

//import org.junit.runner.RunWith
//import org.robolectric.RobolectricTestRunner
//import org.robolectric.RuntimeEnvironment
//import org.robolectric.annotation.Config

//@RunWith(RobolectricTestRunner::class)
internal class DatabaseHandlerTest {

    lateinit var instrumentationContext: Context
    lateinit var dbhandler: DatabaseHandler


    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext
        dbhandler = DatabaseHandler(instrumentationContext)
        dbhandler.clearDbAndRecreate()

    }

    @Test
    fun preCondition(){
        assertNotNull(dbhandler)
    }




    @Test
    fun createNote() {


        val dbhandler= DatabaseHandler(instrumentationContext)
        val note= Note()
        val note2=Note()
        assertDoesNotThrow { dbhandler.createNote(note) }
        assertDoesNotThrow { dbhandler.createNote(note2) }



    }

    @Test
    fun getNoteCount(){

        val note1= Note()
        note1.noteText_ID="asd1"
        note1.noteID=1
        note1.noteTitle= "fr234"
        note1.noteDate="1243"
        note1.noteLabel="dan"

        val note2=Note()
        note2.noteText_ID="asd2"
        note2.noteID=2
        note2.noteTitle= "f234r"
        note2.noteDate="122343"
        note2.noteLabel="dan"
        dbhandler.createNote(note1)
        dbhandler.createNote(note2)
        assertEquals(2,dbhandler.getNoteCount())

        val note3=Note()
        note3.noteText_ID="asd3"
        note3.noteID=3
        note3.noteTitle= "fr2"
        note3.noteDate="122343"
        note3.noteLabel="dan"

        dbhandler.createNote(note3)
        assertEquals(3,dbhandler.getNoteCount())
    }

    @Test
    fun getLastNoteId(){
        val note1= Note()
        note1.noteText_ID="asd1"
        note1.noteID=3
        note1.noteTitle= "fr234"
        note1.noteDate="1243"
        note1.noteLabel="dan"
        dbhandler.createNote(note1)

        val note2=Note()
        note2.noteText_ID="asd2"
        note2.noteID=1
        note2.noteTitle= "f234r"
        note2.noteDate="122343"
        note2.noteLabel="dan"
        dbhandler.createNote(note2)

        assertEquals(3,dbhandler.getLastNoteId())
    }

    @Test
    fun getNoteById(){
        val dbhandler= DatabaseHandler(instrumentationContext)
        val note1= Note()
        note1.noteID=1
        note1.noteTitle="Test"
        note1.noteText_ID="asd"
        dbhandler.createNote(note1)
        assertEquals("Test",dbhandler.getNoteByID(1).noteTitle)

    }

    @After
    fun tearDown(){
        dbhandler.clearDb()
    }
}