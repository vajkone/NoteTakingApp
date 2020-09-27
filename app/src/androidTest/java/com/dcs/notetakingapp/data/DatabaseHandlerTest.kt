package com.dcs.notetakingapp.data

import android.content.Context
import android.os.Build.VERSION_CODES.LOLLIPOP
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.platform.app.InstrumentationRegistry
import com.dcs.notetakingapp.model.Note
import com.dcs.notetakingapp.model.NoteText

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import java.util.*
import kotlin.concurrent.fixedRateTimer
import kotlin.collections.ArrayList as ArrayList1

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
    fun createNoteText(){
        val dbhandler= DatabaseHandler(instrumentationContext)
        val text1= NoteText()
        text1.noteText_Id="asd"
        text1.noteText_text="hali"
        dbhandler.createNoteText(text1)
        assertEquals("hali",dbhandler.getNoteTextByID("asd").noteText_text)
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

    @Test
    fun searchNotesByTitle(){
        val dbhandler= DatabaseHandler(instrumentationContext)
        val note1= Note()
        note1.noteID=1
        note1.noteTitle="Test"
        note1.noteText_ID="asd"
        dbhandler.createNote(note1)
        val note2= Note()
        note2.noteID=2
        note2.noteTitle = "asd2"
        note2.noteText_ID = "sds"
        dbhandler.createNote(note2)
        val note3 = Note()
        note3.noteID=3
        note3.noteTitle = "asd2"
        note3.noteText_ID= "c"

        dbhandler.createNote(note3)
        val list = dbhandler.searchNotesByTitle("Test")
        assertTrue(list.size==1)
        val list2 = dbhandler.searchNotesByTitle("c")
        assertTrue(list2.size==0)
        val list3 = dbhandler.searchNotesByTitle("asd2")
        assertTrue(list3.size == 2)
    }

    @Test
    fun searchNotesByLabel(){
        val dbhandler= DatabaseHandler(instrumentationContext)
        val note1= Note()
        note1.noteID=1
        note1.noteTitle="Test"
        note1.noteLabel="test"
        note1.noteText_ID = "a"
        dbhandler.createNote(note1)
        val note2= Note()
        note2.noteID=2
        note2.noteTitle = "asd2"
        note2.noteLabel = "test"
        note2.noteText_ID= "a"
        dbhandler.createNote(note2)
        val note3 = Note()
        note3.noteID=3
        note3.noteTitle = "asd2"
        note3.noteLabel= "just"
        note3.noteText_ID = "a"

        dbhandler.createNote(note3)
        val list4 = dbhandler.searchNotesByLabel("just")
        assertTrue(list4.size==1)
        val list5 = dbhandler.searchNotesByLabel("a")
        assertTrue(list5.size==0)
        val list6 = dbhandler.searchNotesByLabel("test")
        assertTrue(list6.size == 2)
    }


    @After
    fun tearDown(){
        dbhandler.clearDb()
    }
}