package com.example.mysneakers.modelo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "tipos",
        indices = @Index(value = {"descricao"}, unique = true))
public class Tipo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String descricao;

    public Tipo(String descricao){
        setDescricao(descricao);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NonNull String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString(){
        return getDescricao();
    }
}
