package br.com.fernandacosta.bloom.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.fernandacosta.bloom.R;
import br.com.fernandacosta.bloom.dao.CrencaDAO;
import br.com.fernandacosta.bloom.model.Crenca;
import br.com.fernandacosta.bloom.ui.activity.CrencasCentraisActivity;
import br.com.fernandacosta.bloom.ui.activity.ListaCrencaActivity;
import br.com.fernandacosta.bloom.ui.recyclerview.OnItemClickListener;
import br.com.fernandacosta.bloom.ui.recyclerview.adapter.CrencasAdapter;
import br.com.fernandacosta.bloom.ui.recyclerview.helper.callback.CrencaItemTouchHelperCallback;

import static br.com.fernandacosta.bloom.ui.constant.CrencasConstantes.APPBAR_TITULO;
import static br.com.fernandacosta.bloom.ui.constant.CrencasConstantes.CHAVE_CRENCA_ADD;
import static br.com.fernandacosta.bloom.ui.constant.CrencasConstantes.CHAVE_CRENCA_ALTERAR_REMOVER;
import static br.com.fernandacosta.bloom.ui.constant.CrencasConstantes.CRENCA_ID;
import static br.com.fernandacosta.bloom.ui.constant.CrencasConstantes.POSICAO;
import static br.com.fernandacosta.bloom.ui.constant.CrencasConstantes.VALOR_INVALIDO;


public class CrencasFragment extends Fragment {

    private List<Crenca> todasCrencas;
    private CrencasAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contatos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(APPBAR_TITULO);

        todasCrencas = new CrencaDAO().getTodasCrencas();

        configuraCrencasAdicionarFab(view);
        configuraAdapter();
        configuraRecyclerview(view);
    }

    private void configuraRecyclerview(@NonNull View view) {
        RecyclerView crencasRecyclerView = view.findViewById(R.id.crencas_recyclerView);
        crencasRecyclerView.setAdapter(adapter);
        configuraItemTouchHelper(crencasRecyclerView, todasCrencas);
    }
    private void configuraItemTouchHelper(RecyclerView recyclerView,  List<Crenca> todasCrencas) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new CrencaItemTouchHelperCallback(adapter, todasCrencas, getView()));
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private void configuraAdapter() {
        adapter = new CrencasAdapter(getContext(), todasCrencas);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int posicao) {
                Crenca crenca = todasCrencas.get(posicao);
                vaiParaDetalhesCrenca(crenca, posicao);
            }
        });
    }

    private void configuraCrencasAdicionarFab(@NonNull View view) {
        FloatingActionButton adicionarFab = view.findViewById(R.id.crencas_adicionar_fab);
        adicionarFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CrencasCentraisActivity.class);
                startActivityForResult(intent, CHAVE_CRENCA_ADD);
            }
        });
    }

    private void vaiParaDetalhesCrenca(Crenca crenca, int posicao) {
        Intent intent = new Intent(getContext(), ListaCrencaActivity.class);
        intent.putExtra(CRENCA_ID, crenca.getId());
        intent.putExtra(POSICAO, posicao);
        startActivityForResult(intent, CHAVE_CRENCA_ALTERAR_REMOVER);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHAVE_CRENCA_ADD) {
            if (resultCode == Activity.RESULT_OK && data.hasExtra(CRENCA_ID)) {
                int crenca_id = data.getIntExtra(CRENCA_ID, VALOR_INVALIDO);
                Crenca crenca = new CrencaDAO().getCrenca(crenca_id);
                Snackbar.make(getView(), "A crença foi adicionada com sucesso.", Snackbar.LENGTH_LONG).show();
                adapter.adiciona(crenca);
            }
        } else if (requestCode == CHAVE_CRENCA_ALTERAR_REMOVER){
            if (resultCode == Activity.RESULT_OK && data.hasExtra(CRENCA_ID) && data.hasExtra(POSICAO)) {
                int crenca_id = data.getIntExtra(CRENCA_ID, VALOR_INVALIDO);
                int posicao = data.getIntExtra(POSICAO, VALOR_INVALIDO);
                Crenca crenca = new CrencaDAO().getCrenca(crenca_id);
                Snackbar.make(getView(), "A crença foi alterada com sucesso.", Snackbar.LENGTH_LONG).show();
                adapter.altera(crenca, posicao);
            }
        }
    }
}
