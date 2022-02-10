package edu.ucsd.cse110.lab6_starter_debugging;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


import java.io.IOException;
import java.util.List;

import edu.ucsd.cse110.lab6_starter_debugging.model.db.AppDatabase;
import edu.ucsd.cse110.lab6_starter_debugging.model.db.Note;
import edu.ucsd.cse110.lab6_starter_debugging.model.db.NotesDao;
import edu.ucsd.cse110.lab6_starter_debugging.model.db.Person;
import edu.ucsd.cse110.lab6_starter_debugging.model.db.PersonDao;

@RunWith(AndroidJUnit4.class)
public class DBUnitTest {
    private PersonDao personsDao;
    private NotesDao notesDao;
    private AppDatabase empty_db;

    @Before
    public void initDB() {
        Context context = ApplicationProvider.getApplicationContext();
        AppDatabase.useTestSingleton(context);
        empty_db = AppDatabase.singleton(context);
        personsDao = empty_db.personsDao();
        notesDao = empty_db.notesDao();
    }

    @After
    public void closeDB() throws IOException {
        empty_db.close();
    }

    @Test
    public void testInsertPersonsAndNotes() {
        Person p1 = new Person("Alex");
        Person p2 = new Person("Kevin");
        personsDao.insert(p1);
        personsDao.insert(p2);

        Note n1 = new Note(1, "note 1!");
        Note n2 = new Note(2, "note 2!");
        notesDao.insert(n1);
        notesDao.insert(n2);

        assertEquals(2, personsDao.count());
        assertEquals(2, notesDao.count());
    }

}
