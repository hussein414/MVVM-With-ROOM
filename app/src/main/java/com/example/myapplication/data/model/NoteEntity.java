package com.example.myapplication.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class NoteEntity {
    private String title;
    private String description;

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int perperties;

    public NoteEntity(String title, String description, int perperties) {
        this.title = title;
        this.description = description;
        this.perperties = perperties;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerperties() {
        return perperties;
    }

    public void setPerperties(int perperties) {
        this.perperties = perperties;
    }
}
