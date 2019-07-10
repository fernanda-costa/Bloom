package br.com.fernandacosta.bloom.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import br.com.fernandacosta.bloom.R;
import br.com.fernandacosta.bloom.dao.EscritaExpressivaDAO;
import br.com.fernandacosta.bloom.model.EscritaExpressiva;

import static br.com.fernandacosta.bloom.ui.constant.EscritaExpressivaConstantes.APPBAR_TITULO_ADICIONAR;
import static br.com.fernandacosta.bloom.ui.constant.EscritaExpressivaConstantes.APPBAR_TITULO_ALTERAR;
import static br.com.fernandacosta.bloom.ui.constant.EscritaExpressivaConstantes.CHAVE_ESCRITA_EXPRESSIVA;
import static br.com.fernandacosta.bloom.ui.constant.EscritaExpressivaConstantes.CHAVE_POSICAO;
import static br.com.fernandacosta.bloom.ui.constant.EscritaExpressivaConstantes.RESULT_CODE_REMOVE;
import static br.com.fernandacosta.bloom.ui.constant.EscritaExpressivaConstantes.POSICAO_INVALIDA;

public class AddAlterarApagarEscritaEspressiva extends AppCompatActivity {

    private EscritaExpressiva escritaExpressiva;
    private int posicao = POSICAO_INVALIDA;

    private EditText tituloEditText;
    private EditText textoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alterar_apagar_escrita_espressiva);

        setTitle(APPBAR_TITULO_ADICIONAR);

        tituloEditText = findViewById(R.id.escrita_expressiva_titulo_edittext);
        textoEditText = findViewById(R.id.escrita_expressiva_texto_edittext);

        Intent intent = getIntent();

        if (intent.hasExtra(CHAVE_ESCRITA_EXPRESSIVA) && intent.hasExtra(CHAVE_POSICAO)){
            setTitle(APPBAR_TITULO_ALTERAR);
            escritaExpressiva = (EscritaExpressiva) intent.getSerializableExtra(CHAVE_ESCRITA_EXPRESSIVA);
            posicao = intent.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);
            preencheCampos(escritaExpressiva);
        }

        configuraBotaoSalvar();
        configuraBotaoApagar();

    }

    private void configuraBotaoApagar() {
        ImageButton apagarImageButton = findViewById(R.id.escrita_expressiva_apagar_imageButton);
        apagarImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (escritaExpressiva == null){
                    finish();
                } else {
                    removeEscrita(escritaExpressiva);
                    finish();
                }
            }
        });
    }

    private void preencheCampos(EscritaExpressiva escritaExpressiva) {
        tituloEditText.setText(escritaExpressiva.getTitulo());
        textoEditText.setText(escritaExpressiva.getTexto());
    }

    private void configuraBotaoSalvar() {
        ImageButton salvarImageButton = findViewById(R.id.escrita_expressiva_salvar_imageButton);
        salvarImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (escritaExpressiva == null){
                    escritaExpressiva = new EscritaExpressiva();
                    escritaExpressiva = pegaValorDosCampos();
                    escritaExpressiva.setId(new EscritaExpressivaDAO().getUlitmoId());
                    retornaEscritaExpressiva(escritaExpressiva);
                    finish();
                } else {
                    escritaExpressiva = pegaValorDosCampos();
                    retornaEscritaExpressiva(escritaExpressiva);
                    finish();
                }
            }
        });
    }

    private void retornaEscritaExpressiva(EscritaExpressiva escritaExpressiva) {
        Intent resultado = new Intent();
        resultado.putExtra(CHAVE_ESCRITA_EXPRESSIVA, escritaExpressiva);
        resultado.putExtra(CHAVE_POSICAO, posicao);
        setResult(Activity.RESULT_OK, resultado);
    }

    private void removeEscrita(EscritaExpressiva escritaExpressiva) {
        Intent resultado = new Intent();
        resultado.putExtra(CHAVE_ESCRITA_EXPRESSIVA, escritaExpressiva);
        resultado.putExtra(CHAVE_POSICAO, posicao);
        setResult(RESULT_CODE_REMOVE, resultado);
    }


    private EscritaExpressiva pegaValorDosCampos() {
        String titulo = tituloEditText.getText().toString();
        String texto = textoEditText.getText().toString();

        return getEscritaExpressiva(titulo, texto);
    }

    @NonNull
    private EscritaExpressiva getEscritaExpressiva(String titulo, String texto) {
        escritaExpressiva.setTitulo(titulo);
        escritaExpressiva.setTexto(texto);

        return escritaExpressiva;
    }

}
