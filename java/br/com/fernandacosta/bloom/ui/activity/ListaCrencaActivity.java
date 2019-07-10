package br.com.fernandacosta.bloom.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import br.com.fernandacosta.bloom.R;
import br.com.fernandacosta.bloom.dao.CrencaDAO;
import br.com.fernandacosta.bloom.model.Crenca;
import br.com.fernandacosta.bloom.ui.recyclerview.OnItemClickListener;
import br.com.fernandacosta.bloom.ui.recyclerview.adapter.DetalhesCrencaAdapter;

import static br.com.fernandacosta.bloom.ui.constant.CrencasConstantes.APPBAR_TITULO_CRENCAS_INTERMEDIARIAS;
import static br.com.fernandacosta.bloom.ui.constant.CrencasConstantes.CHAVE_CRENCA_ALTERAR_REMOVER;
import static br.com.fernandacosta.bloom.ui.constant.CrencasConstantes.CRENCA_ID;
import static br.com.fernandacosta.bloom.ui.constant.CrencasConstantes.NUMERO_PERGUNTA;
import static br.com.fernandacosta.bloom.ui.constant.CrencasConstantes.POSICAO;
import static br.com.fernandacosta.bloom.ui.constant.CrencasConstantes.VALOR_INVALIDO;

public class ListaCrencaActivity extends AppCompatActivity {

    private Crenca crenca;
    private DetalhesCrencaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_crenca);

        crenca = getCrenca();

        setTitle(APPBAR_TITULO_CRENCAS_INTERMEDIARIAS);

        adapter = configuraAdapter(crenca);
        configuraRecyclerView(adapter);
    }

    private void configuraRecyclerView(DetalhesCrencaAdapter adapter) {
        RecyclerView respostasRecyclerview = findViewById(R.id.detalhes_crenca_recyclerview);
        respostasRecyclerview.setAdapter(adapter);
    }

    @NonNull
    private DetalhesCrencaAdapter configuraAdapter(Crenca crenca) {
        DetalhesCrencaAdapter adapter = new DetalhesCrencaAdapter(getApplicationContext(), crenca.getCrencasIntermediarias());
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int posicao) {
                vaiParaAlterarCrencaIntermediaria(posicao);
            }
        });

        return adapter;
    }

    private Crenca getCrenca() {
        Intent intent = getIntent();
        int crenca_id = intent.getIntExtra(CRENCA_ID, VALOR_INVALIDO);

        return new CrencaDAO().getCrenca(crenca_id);
    }

    private void vaiParaAlterarCrencaIntermediaria(int posicao) {
        Intent intent = new Intent(this, CrencasIntermediariasActivity.class);
        intent.putExtra(NUMERO_PERGUNTA, posicao);
        intent.putExtra(CRENCA_ID, crenca.getId());
        startActivityForResult(intent, CHAVE_CRENCA_ALTERAR_REMOVER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHAVE_CRENCA_ALTERAR_REMOVER) {
            if (resultCode == Activity.RESULT_OK && data.hasExtra(NUMERO_PERGUNTA)){
                int posicao = data.getIntExtra(NUMERO_PERGUNTA, VALOR_INVALIDO);
                adapter.altera(posicao);

                Intent resultado = new Intent();
                resultado.putExtra(CRENCA_ID, crenca.getId());
                resultado.putExtra(POSICAO, posicao);
                setResult(Activity.RESULT_OK, resultado);
            }
        }
    }
}
