package com.example.myapplication.utils.task.db;

import android.os.AsyncTask;

import com.example.myapplication.data.db.NoteDao;
import com.example.myapplication.data.db.NoteDatabase;
import com.example.myapplication.data.model.NoteEntity;

public class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
    private NoteDao noteDao;

    public PopulateDbAsyncTask(NoteDatabase noteDatabase) {
        this.noteDao = noteDatabase.noteDao();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        noteDao.insertData(new NoteEntity("title1", "descrption1", 1));
        noteDao.insertData(new NoteEntity("title2", "descrption2", 2));
        noteDao.insertData(new NoteEntity("title3", "descrption3", 3));
        return null;
    }
}
