package com.example.myapplication.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.myapplication.data.db.NoteDao;
import com.example.myapplication.data.db.NoteDatabase;
import com.example.myapplication.data.model.NoteEntity;
import com.example.myapplication.utils.task.repository.DeleteAllNoteAsyncTask;
import com.example.myapplication.utils.task.repository.DeleteNoteAsyncTask;
import com.example.myapplication.utils.task.repository.InsertNoteAsyncTask;
import com.example.myapplication.utils.task.repository.UpdateNoteAsyncTask;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<NoteEntity>> allNote;

    public NoteRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        allNote = noteDao.getAllData();
    }

    public void insertNote(NoteEntity noteEntity) {
        new InsertNoteAsyncTask(noteDao).execute(noteEntity);
    }

    public void updateNote(NoteEntity noteEntity) {
        new UpdateNoteAsyncTask(noteDao).execute(noteEntity);

    }

    public void deleteNote(NoteEntity noteEntity) {
        new DeleteNoteAsyncTask(noteDao).execute(noteEntity);
    }

    public void deleteAllNote() {
        new DeleteAllNoteAsyncTask(noteDao).execute();
    }

    public LiveData<List<NoteEntity>> getAllNote() {
        return allNote;
    }


}
