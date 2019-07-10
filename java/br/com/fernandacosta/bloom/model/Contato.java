package br.com.fernandacosta.bloom.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Contato extends RealmObject {

    @PrimaryKey
    private int id;
    private String nome;
    private String telefone;

    public Contato() { }

    public Contato(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
