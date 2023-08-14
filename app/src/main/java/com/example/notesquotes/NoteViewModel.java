package com.example.notesquotes;

import android.app.Application;
import android.os.AsyncTask;


import androidx.lifecycle.AndroidViewModel;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.AsyncListUtil;


import java.util.List;

public class NoteViewModel extends AndroidViewModel {



    //use nodedatabase to get dao

    //this gets data from repository
    //in database file the abstract method  getNotedao() returns dao object to interact with the database


    LiveData<List<Note>> allNotes;
    private NoteRepository repo;
    private Notedao dao;

    public NoteViewModel(Application application) {
        super(application);

        dao = NoteDatabase.getDatabase(application).getNotedao();
        repo = new NoteRepository(dao);
        allNotes = repo.allnotes;
    }


    public void deleteNote(Note note) {
        new DeleteNoteAsyncTask(repo).execute(note);

    }

    public void insertNote(Note note) {
        new InsertNoteAsyncTask(repo).execute(note);
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteRepository repository;

        private InsertNoteAsyncTask(NoteRepository repository) {
            this.repository = repository;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            repository.insert(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteRepository repository;

        private DeleteNoteAsyncTask(NoteRepository repository) {
            this.repository = repository;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            repository.delete(notes[0]);
            return null;
        }
    }
}