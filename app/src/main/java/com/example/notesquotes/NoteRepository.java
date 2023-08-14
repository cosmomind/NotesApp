package com.example.notesquotes;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private Notedao NoteDao;
    LiveData<List<Note>> allnotes;

    public NoteRepository(Notedao notedao) {
        this.NoteDao = notedao;
        this.allnotes =  NoteDao.getallNotes();
    }


    public void insert(Note note) {

        NoteDao.insert(note);
    }
    public void delete(Note note) {
        NoteDao.delete(note);
    }


}