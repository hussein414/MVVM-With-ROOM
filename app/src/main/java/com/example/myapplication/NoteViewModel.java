package com.example.myapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository noteRepository;
    private LiveData<List<NoteEntity>> allNote;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        allNote = noteRepository.getAllNote();
    }

    public void insert(NoteEntity noteEntity) {
        noteRepository.insertNote(noteEntity);
    }

    public void update(NoteEntity noteEntity) {
        noteRepository.updateNote(noteEntity);
    }

    public void delete(NoteEntity noteEntity) {
        noteRepository.deleteNote(noteEntity);
    }

    public void deleteAll() {
        noteRepository.deleteAllNote();
    }

    public LiveData<List<NoteEntity>> getAllNote() {
        return allNote;
    }
}
