package com.example.mysneakers;

public class Sneakers {

    private String marca;
    private String nome;
    private String tamanho;
    private String colorway;

    public Sneakers(String marca, String nome, String tamanho,String colorway) {

        setMarca(marca);
        setNome(nome);
        setTamanho(tamanho);
        setColorway(colorway);
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

    @Override
    public String toString() {
        return
                "Marca: " + marca + "\n" +
                "Nome: " + nome + "\n" +
                "Colorway: " + colorway + "\n" +
                "Tamanho: " + tamanho;
    }
}
