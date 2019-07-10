package br.com.fernandacosta.bloom.ui.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import br.com.fernandacosta.bloom.R;


import static br.com.fernandacosta.bloom.ui.constant.CrencasConstantes.APPBAR_TITULO_CRENCAS_INTERMEDIARIAS;


public class CrencasIntermediariasActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crencas_intermediarias);

        setTitle(APPBAR_TITULO_CRENCAS_INTERMEDIARIAS);


    }




}
