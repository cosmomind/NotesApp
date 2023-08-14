package com.example.notesquotes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Notedao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void  insert(Note note) ;

    @Delete
    public void  delete(Note note) ;

    @Query("Select * from notes order by id ASC")
    public LiveData<List<Note>> getallNotes() ;

}
