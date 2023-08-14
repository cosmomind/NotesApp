package com.example.notesquotes;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;



@Database(entities = {Note.class},exportSchema = false,
        version = 1)

public abstract class NoteDatabase extends RoomDatabase {
    public abstract  Notedao getNotedao();

    private static volatile  NoteDatabase INSTANCE;
    public static NoteDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (NoteDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            NoteDatabase.class,
                            "note_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}