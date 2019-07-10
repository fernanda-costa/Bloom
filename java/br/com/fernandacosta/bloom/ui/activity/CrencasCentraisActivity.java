package br.com.fernandacosta.bloom.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.fernandacosta.bloom.R;
import br.com.fernandacosta.bloom.model.Crenca;
import br.com.fernandacosta.bloom.model.EscritaExpressiva;

import static br.com.fernandacosta.bloom.ui.constant.CrencasConstantes.APPBAR_TITULO_CRENCAS_CENTRAIS;
import static br.com.fernandacosta.bloom.ui.constant.CrencasConstantes.CHAVE_CRENCA_ADD;
import static br.com.fernandacosta.bloom.ui.constant.CrencasConstantes.CRENCA_ID;
import static br.com.fernandacosta.bloom.ui.constant.CrencasConstantes.RESPOSTAS_CRENCAS_CENTRAIS;
import static br.com.fernandacosta.bloom.ui.constant.CrencasConstantes.VALOR_INVALIDO;
import static br.com.fernandacosta.bloom.ui.constant.EscritaExpressivaConstantes.CHAVE_ESCRITA_EXPRESSIVA;
import static br.com.fernandacosta.bloom.ui.constant.EscritaExpressivaConstantes.REQUEST_CODE_ADICAO;

public class CrencasCentraisActivity extends AppCompatActivity {

    private List<CheckBox> checkBoxList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crencas_centrais);

        setTitle(APPBAR_TITULO_CRENCAS_CENTRAIS);

        configuraProximoImageButton();

        checkBoxList = inicializaCheckBox();
    }

    private List<CheckBox> inicializaCheckBox() {
        CheckBox checkBox1 = findViewById(R.id.crencas_centrais_checkbox2);
        CheckBox checkBox2 = findViewById(R.id.crencas_centrais_checkbox3);
        CheckBox checkBox3 = findViewById(R.id.crencas_centrais_checkbox1);
        CheckBox checkBox4 = findViewById(R.id.crencas_centrais_checkbox4);
        CheckBox checkBox5 = findViewById(R.id.crencas_centrais_checkbox5);
        CheckBox checkBox6 = findViewById(R.id.crencas_centrais_checkbox6);
        return Arrays.asList(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);
    }

    private ArrayList<String> getRespostas(List<CheckBox> crencasCentraisCheckBox) {
        ArrayList<String> respostas = new ArrayList<>();
        for (CheckBox checkBox: crencasCentraisCheckBox) {
            respostas.add(Boolean.toString(checkBox.isChecked()));
        }
        return respostas;
    }

    private void configuraProximoImageButton() {
        ImageButton proximoImageButton = findViewById(R.id.crencas_centrais_proximo_imageButton);
        proximoImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> respostas = getRespostas(checkBoxList);
                Intent intent = new Intent(getApplicationContext(), CrencasIntermediariasActivity.class);
                intent.putStringArrayListExtra(RESPOSTAS_CRENCAS_CENTRAIS, respostas);
                startActivityForResult(intent, CHAVE_CRENCA_ADD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                int crencaId = data.getIntExtra(CRENCA_ID, VALOR_INVALIDO);

                Intent resultado = new Intent();
                resultado.putExtra(CRENCA_ID, crencaId);
                setResult(Activity.RESULT_OK, resultado);
                finish();
            }
        }
    }
}
