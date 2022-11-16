package com.example.mysneakers.persistencia;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mysneakers.modelo.Sneakers;

import java.util.List;

@Dao
public interface SneakersDAO {

    @Insert
    long insert(Sneakers sneakers);

    @Delete
    void delete(Sneakers sneakers);

    @Update
    void update(Sneakers sneakers);

    @Query("SELECT * FROM sneakers WHERE id = :id")
    Sneakers queryForId(long id);

    @Query("SELECT * FROM sneakers ORDER BY marca ASC")
    List<Sneakers> queryAll();
}
