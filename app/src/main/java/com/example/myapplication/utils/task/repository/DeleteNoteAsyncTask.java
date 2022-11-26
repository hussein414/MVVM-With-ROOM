package com.example.myapplication.utils.task.repository;

import android.os.AsyncTask;

import com.example.myapplication.data.db.NoteDao;
import com.example.myapplication.data.model.NoteEntity;

public class DeleteNoteAsyncTask  extends AsyncTask<NoteEntity, Void, Void> {
    private NoteDao noteDao;

    public DeleteNoteAsyncTask(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    protected Void doInBackground(NoteEntity... noteEntities) {
        noteDao.deleteData(noteEntities[0]);
        return null;
    }
}