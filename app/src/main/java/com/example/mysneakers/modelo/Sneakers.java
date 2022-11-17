package com.example.mysneakers.modelo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "sneakers",
        foreignKeys = @ForeignKey(entity = Tipo.class,
                parentColumns = "id",
                childColumns  = "tipoId"))

public class Sneakers {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public static final int NOVO = 1;
    public static final int POUCO = 2;
    public static final int MUITO = 3;

    private String marca;
    private String nome;
    private String tamanho;
    private String colorway;
    private String precoOg;
    private String precoRev;
    private int estadosnk;
    private boolean possuisnk;
    private String tipotamanho;

    @ColumnInfo(index = true)
    private int tipoId;



    public Sneakers(String marca, String nome, String tipotamanho, String tamanho,String colorway, String precoOg, String precoRev, int estadosnk, boolean possuisnk) {

        setMarca(marca);
        setNome(nome);
        setTamanho(tamanho);
        setColorway(colorway);
        setPrecoOg(precoOg);
        setPrecoRev(precoRev);
        setTipotamanho(tipotamanho);
        setEstadosnk(estadosnk);
        setPossuisnk(possuisnk);
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getColorway() {
        return colorway;
    }

    public void setColorway(String colorway) {
        this.colorway = colorway;
    }

    public String getPrecoOg() {
        return precoOg;
    }

    public void setPrecoOg(String precoOg) {
        this.precoOg = precoOg;
    }

    public String getPrecoRev() {
        return precoRev;
    }

    public void setPrecoRev(String precoRev) {
        this.precoRev = precoRev;
    }

    public int getEstadosnk() {
        return estadosnk;
    }

    public void setEstadosnk(int estadosnk) {
        this.estadosnk = estadosnk;
    }

    public boolean isPossuisnk() {
        return possuisnk;
    }

    public void setPossuisnk(boolean possuisnk) {
        this.possuisnk = possuisnk;
    }

    public String getTipotamanho() {
        return tipotamanho;
    }

    public void setTipotamanho(String tipotamanho) {
        this.tipotamanho = tipotamanho;
    }

    @Override
    public String toString() {
        return
                getMarca() + "\n" +
                getNome() + "\n" +
                getColorway() + "\n" +
                getTipotamanho() + "\n" +
                getTamanho() + "\n" +
                getPrecoOg() + "\n" +
                getPrecoRev() + "\n" +
                getEstadosnk() + "\n" +
                isPossuisnk();
    }


}
