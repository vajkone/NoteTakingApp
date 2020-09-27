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
    fun createNote() {


        val dbhandler= DatabaseHandler(instrumentationContext)
        val note= Note()
        assertDoesNotThrow { dbhandler.createNote(note) }



    }

    @Test
    fun getNoteCount(){

        val note1= Note()
        dbhandler.createNote(note1)
        val note2=Note()
        dbhandler.createNote(note2)
        val note3=Note()
        dbhandler.createNote(note3)
        assertEquals(3,dbhandler.getNoteCount())
    }

    @Test
    fun getNoteById(){
        val dbhandler= DatabaseHandler(instrumentationContext)
        val note1= Note()
        note1.noteID=1
        note1.noteTitle="Test"
        dbhandler.createNote(note1)
        assertEquals("Test",dbhandler.getNoteByID(1).noteTitle)

    }

    @After
    fun tearDown(){
        dbhandler.clearDb()
    }
}