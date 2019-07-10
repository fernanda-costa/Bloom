package br.com.fernandacosta.bloom.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class EscritaExpressiva extends RealmObject implements Serializable {

    @PrimaryKey
    private int id;
    private String texto;
    private String titulo;

    public EscritaExpressiva() { }

    public EscritaExpressiva(int id, String texto, String titulo) {
        this.id = id;
        this.texto = texto;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}

