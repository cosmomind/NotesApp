package com.example.notesquotes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


//created a table
@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int id = 0;

    @ColumnInfo(name = "text")
    public String text;
    public Note(String text){
        this.text = text;
    }

}