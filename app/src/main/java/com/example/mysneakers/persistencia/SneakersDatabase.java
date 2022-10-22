package com.example.mysneakers.persistencia;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.mysneakers.modelo.Sneakers;

@Database(entities = {Sneakers.class}, version = 1, exportSchema = false)
public abstract class SneakersDatabase extends RoomDatabase {

    public abstract SneakersDAO sneakersDAO();

    private static SneakersDatabase instance;

    public static SneakersDatabase getDatabase(final Context context) {

        if (instance == null) {

            synchronized (SneakersDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context,
                            SneakersDatabase.class,
                            "sneakers.db").allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }
}
