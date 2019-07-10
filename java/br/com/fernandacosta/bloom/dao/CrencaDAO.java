package br.com.fernandacosta.bloom.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.fernandacosta.bloom.model.Crenca;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class CrencaDAO {

    private Realm realm = Realm.getDefaultInstance();


    public void adicionar(Crenca crenca){
        realm.beginTransaction();
        realm.copyToRealm(crenca);
        realm.commitTransaction();
    }

    public int getUlitmoId(){
        Number ultimoId = realm.where(Crenca.class).max("id");
        if (ultimoId != null)
            return ultimoId.intValue() + 1;
        else
            return 0;
    }

    public List<Crenca> getTodasCrencas(){
        RealmResults<Crenca> todasCrencas = realm.where(Crenca.class).findAll();
        return realm.copyFromRealm(todasCrencas);
    }

    public Crenca getCrenca(int id){
        return realm.where(Crenca.class).equalTo("id", id).findFirst();
    }


    public void alteraListaCrencasIntermediarias(int posicao, String resposta, Crenca crenca){
        realm.beginTransaction();
        crenca.getCrencasCentrais().remove(posicao);
        crenca.getCrencasIntermediarias().add(posicao, resposta);
        realm.copyToRealmOrUpdate(crenca);
        realm.commitTransaction();
    }

    public void remove (int id){
        realm.beginTransaction();
        realm.where(Crenca.class).equalTo("id", id).findFirst().deleteFromRealm();
        realm.commitTransaction();
    }


}
