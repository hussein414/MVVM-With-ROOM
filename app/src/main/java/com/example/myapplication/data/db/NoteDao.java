package com.example.myapplication.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.data.model.NoteEntity;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insertData(NoteEntity noteEntity);

    @Update
    void updateData(NoteEntity noteEntity);

    @Delete
    void deleteData(NoteEntity noteEntity);

    @Query("DELETE FROM note_table")
    void deleteAllData();

    @Query("SELECT*FROM note_table ORDER BY perperties DESC")
    LiveData<List<NoteEntity>> getAllData();
}
