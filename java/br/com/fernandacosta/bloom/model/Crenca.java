package br.com.fernandacosta.bloom.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Crenca extends RealmObject {

    @PrimaryKey
    private int id;
    private Date diaQueFoiRealizado;
    private RealmList<Boolean> crencasCentrais;
    private RealmList<String> crencasIntermediarias;

    public Crenca() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDiaQueFoiRealizado() {
        return diaQueFoiRealizado;
    }

    public void setDiaQueFoiRealizado(Date diaQueFoiRealizado) {
        this.diaQueFoiRealizado = diaQueFoiRealizado;
    }

    public RealmList<Boolean> getCrencasCentrais() {
        return crencasCentrais;
    }

    public void setCrencasCentrais(RealmList<Boolean> crencasCentrais) {
        this.crencasCentrais = crencasCentrais;
    }

    public RealmList<String> getCrencasIntermediarias() {
        return crencasIntermediarias;
    }

    public void setCrencasIntermediarias(RealmList<String> crencasIntermediarias) {
        this.crencasIntermediarias = crencasIntermediarias;
    }
}
