package br.com.fernandacosta.bloom.dao;

import java.util.List;

import br.com.fernandacosta.bloom.model.EscritaExpressiva;
import io.realm.Realm;
import io.realm.RealmResults;

public class EscritaExpressivaDAO {

    private Realm realm = Realm.getDefaultInstance();

    public void adicionar(EscritaExpressiva escritaExpressiva){
        realm.beginTransaction();
        realm.copyToRealm(escritaExpressiva);
        realm.commitTransaction();
    }

    public int getUlitmoId(){
        Number ultimoId = realm.where(EscritaExpressiva.class).max("id");
        if (ultimoId != null)
            return ultimoId.intValue() + 1;
        else
            return 0;
    }

    public List<EscritaExpressiva> getTodasEscritasExpressivas(){
        RealmResults<EscritaExpressiva> todasEscritas = realm.where(EscritaExpressiva.class).findAll();
        return realm.copyFromRealm(todasEscritas);
    }

    public void alterar(EscritaExpressiva escritaExpressiva){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(escritaExpressiva);
        realm.commitTransaction();
    }

    public void remove(int id){
        realm.beginTransaction();
        EscritaExpressiva escritaExpressiva = realm.where(EscritaExpressiva.class).equalTo("id", id).findFirst();
        escritaExpressiva.deleteFromRealm();
        realm.commitTransaction();
    }


}
