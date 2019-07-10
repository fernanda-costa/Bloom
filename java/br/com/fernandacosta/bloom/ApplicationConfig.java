package br.com.fernandacosta.bloom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.fernandacosta.bloom.ui.activity.NavigationDrawerActivity;
import br.com.fernandacosta.bloom.ui.activity.SplashScreenActivity;
import io.realm.Realm;
import io.realm.RealmConfiguration;

import static br.com.fernandacosta.bloom.ui.constant.SplashScreenConstantes.CHAVE_JA_ABRIU_APP;

public class ApplicationConfig extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inicializaRealm();

        SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
        if (preferences.contains(CHAVE_JA_ABRIU_APP)) {
            vaiParaTelaPrincipal();
            finish();
        } else {
            mostraSplashScreen();
            adicionarPreferenceJaAbriu(preferences);
            finish();
        }
    }

    private void inicializaRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private void adicionarPreferenceJaAbriu(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(CHAVE_JA_ABRIU_APP, true);
        editor.apply();
    }

    private void mostraSplashScreen() {
        Intent intent = new Intent(this, SplashScreenActivity.class);
        startActivity(intent);
    }

    private void vaiParaTelaPrincipal() {
        Intent intent = new Intent(this, NavigationDrawerActivity.class);
        startActivity(intent);
    }

}
