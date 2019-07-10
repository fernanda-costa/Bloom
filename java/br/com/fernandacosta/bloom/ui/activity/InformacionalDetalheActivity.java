package br.com.fernandacosta.bloom.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.fernandacosta.bloom.R;

import static br.com.fernandacosta.bloom.ui.constant.InformacionalConstantes.CHAVE_TEXTO;
import static br.com.fernandacosta.bloom.ui.constant.InformacionalConstantes.CHAVE_TITULO;

public class InformacionalDetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacional_detalhe);

        Intent intent = getIntent();

        if (intent.hasExtra(CHAVE_TITULO) && intent.hasExtra(CHAVE_TEXTO)) {
            String titulo = intent.getStringExtra(CHAVE_TITULO);
            String texto = intent.getStringExtra(CHAVE_TEXTO);

            inicializaCampos(titulo, texto);
        }
    }

    private void inicializaCampos(String titulo, String texto) {
        TextView tituloTextView = findViewById(R.id.detalhe_informacional_titulo_textView);
        TextView textoTextView = findViewById(R.id.detalhe_informacional_texto_textView);
        tituloTextView.setText(titulo);
        textoTextView.setText(texto);
    }
}
