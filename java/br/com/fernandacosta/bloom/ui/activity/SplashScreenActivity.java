package br.com.fernandacosta.bloom.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.fernandacosta.bloom.R;

import static br.com.fernandacosta.bloom.ui.constant.SplashScreenConstantes.TEMPO_DELAY;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mostraSplashScreen();
    }

    private void mostraSplashScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                vaiParaTelaPrincipal();
            }
        }, TEMPO_DELAY);
    }

    private void vaiParaTelaPrincipal() {
        Intent intent = new Intent(this, NavigationDrawerActivity.class);
        startActivity(intent);
    }
}
