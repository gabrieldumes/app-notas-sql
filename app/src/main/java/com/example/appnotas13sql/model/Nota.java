package com.example.appnotas13sql.model;

import java.io.Serializable;

public class Nota implements Serializable {

    private int id, status, lembrete;
    private String titulo, texto;

    public Nota() {

    }

    public Nota(int id, String titulo, String texto, int status, int lembrete) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.status = status;
        this.lembrete = lembrete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLembrete() {
        return lembrete;
    }

    public void setLembrete(int lembrete) {
        this.lembrete = lembrete;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
