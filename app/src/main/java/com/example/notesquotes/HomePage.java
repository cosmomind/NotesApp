package com.example.notesquotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements Inotesrv{

    NoteViewModel viewnotemodel;
    ImageView add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


        ImageView add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i = new Intent(HomePage.this,AddNote.class);
               startActivity(i);
            }
        });
        RecyclerView rvnotes = findViewById(R.id.recyclerview1);
        rvnotes.setLayoutManager(new LinearLayoutManager(this));

        NotesAdaptor adapter =  new NotesAdaptor(this, this);
        rvnotes.setAdapter((RecyclerView.Adapter) adapter);


        viewnotemodel =  new ViewModelProvider(this , (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(NoteViewModel.class);
//the notes activity would be observing thus

        viewnotemodel.allNotes.observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> list) {
                if (list != null) {
                    ArrayList<Note> arrayList = new ArrayList<>(list);
                    adapter.updatelist(arrayList);
                }
            }
        });



    }

    @Override
    public void onItemClicked(Note note) {
//creating delete in viewmodel
        viewnotemodel.deleteNote(note);
        Toast.makeText(this,  " Deleted", Toast.LENGTH_LONG).show();

    }







    }

