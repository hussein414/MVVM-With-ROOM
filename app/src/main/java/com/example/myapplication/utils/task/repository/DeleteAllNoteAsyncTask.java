package com.example.myapplication.utils.task.repository;

import android.os.AsyncTask;

import com.example.myapplication.data.db.NoteDao;

public class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void> {
    private NoteDao noteDao;

    public DeleteAllNoteAsyncTask(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        noteDao.deleteAllData();
        return null;
    }
}