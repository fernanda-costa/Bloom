package br.com.fernandacosta.bloom.dao;

import java.util.List;

import br.com.fernandacosta.bloom.model.Contato;
import io.realm.Realm;
import io.realm.RealmResults;

public class ContatoDAO {

    private Realm realm = Realm.getDefaultInstance();


    public void adicionar(Contato contato){
        contato.setId(getUlitmoId());
        realm.beginTransaction();
        realm.copyToRealm(contato);
        realm.commitTransaction();
    }

    public int getUlitmoId(){
        Number ultimoId = realm.where(Contato.class).max("id");
        if (ultimoId != null)
            return ultimoId.intValue() + 1;
        else
            return 0;
    }

    public List<Contato> getContatos(){
        RealmResults<Contato> contatos = realm.where(Contato.class).findAll();
        return realm.copyFromRealm(contatos);
    }

    public void remove (int id){
        realm.beginTransaction();
        realm.where(Contato.class).equalTo("id", id).findFirst().deleteFromRealm();
        realm.commitTransaction();
    }
}
