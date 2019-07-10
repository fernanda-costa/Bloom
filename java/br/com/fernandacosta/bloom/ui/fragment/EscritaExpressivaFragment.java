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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.fernandacosta.bloom.R;
import br.com.fernandacosta.bloom.dao.EscritaExpressivaDAO;
import br.com.fernandacosta.bloom.model.EscritaExpressiva;
import br.com.fernandacosta.bloom.ui.activity.AddAlterarApagarEscritaEspressiva;
import br.com.fernandacosta.bloom.ui.recyclerview.OnItemClickListener;
import br.com.fernandacosta.bloom.ui.recyclerview.adapter.EscritaExpressivaAdapter;

import static br.com.fernandacosta.bloom.ui.constant.EscritaExpressivaConstantes.APPBAR_TITULO;
import static br.com.fernandacosta.bloom.ui.constant.EscritaExpressivaConstantes.CHAVE_ESCRITA_EXPRESSIVA;
import static br.com.fernandacosta.bloom.ui.constant.EscritaExpressivaConstantes.CHAVE_POSICAO;
import static br.com.fernandacosta.bloom.ui.constant.EscritaExpressivaConstantes.RESULT_CODE_REMOVE;
import static br.com.fernandacosta.bloom.ui.constant.EscritaExpressivaConstantes.POSICAO_INVALIDA;
import static br.com.fernandacosta.bloom.ui.constant.EscritaExpressivaConstantes.REQUEST_CODE_ADICAO;
import static br.com.fernandacosta.bloom.ui.constant.EscritaExpressivaConstantes.REQUEST_CODE_ALTERAR;

public class EscritaExpressivaFragment extends Fragment {

    private EscritaExpressivaAdapter adapter;
    private final EscritaExpressivaDAO escritaExpressivaDAO = new EscritaExpressivaDAO();
    private List<EscritaExpressiva> escritasExpressivasList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_escrita_expressiva, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(APPBAR_TITULO);

        escritasExpressivasList = new EscritaExpressivaDAO().getTodasEscritasExpressivas();

        configuraRecyclerView(escritasExpressivasList);
        configuraFloatingActionButtonAdicionar(view);
    }

    private void configuraFloatingActionButtonAdicionar(@NonNull View view) {
        FloatingActionButton adicionaFab = view.findViewById(R.id.escrita_expressiva_add_fab);
        adicionaFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaiParaAdicionarEscritaExpressivaAdicionar();
            }
        });
    }

    private void configuraRecyclerView(List<EscritaExpressiva> escritasExpressivasList) {
        RecyclerView recyclerViewEscritas = getView().findViewById(R.id.escrita_expressiva_recyclerView);
        configuraAdapter(escritasExpressivasList, recyclerViewEscritas);
    }

    private void configuraAdapter(final List<EscritaExpressiva> escritasExpressivasList, RecyclerView recyclerViewEscritas) {
        adapter = new EscritaExpressivaAdapter(escritasExpressivasList, getContext());
        recyclerViewEscritas.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int posicao) {
                EscritaExpressiva expressiva = escritasExpressivasList.get(posicao);
                vaiParaAdicionarEscritaExpressivaAlterar(expressiva, posicao);
            }
        });
    }

    private void vaiParaAdicionarEscritaExpressivaAdicionar() {
        Intent intent = new Intent(getContext(), AddAlterarApagarEscritaEspressiva.class);
        startActivityForResult(intent, REQUEST_CODE_ADICAO);
    }

    private void vaiParaAdicionarEscritaExpressivaAlterar(EscritaExpressiva escritaExpressiva, int posicao) {
        Intent intent = new Intent(getContext(), AddAlterarApagarEscritaEspressiva.class);
        intent.putExtra(CHAVE_ESCRITA_EXPRESSIVA, escritaExpressiva);
        intent.putExtra(CHAVE_POSICAO, posicao);
        startActivityForResult(intent, REQUEST_CODE_ALTERAR);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADICAO && temEscritaExpressiva(data)) {
            if (resultCode == Activity.RESULT_OK) {
                EscritaExpressiva expressivaRecebida = (EscritaExpressiva) data.getSerializableExtra(CHAVE_ESCRITA_EXPRESSIVA);
                adiciona(expressivaRecebida);
            }
        } else if (requestCode == REQUEST_CODE_ALTERAR && temEscritaExpressiva(data)) {
            if (resultCode == Activity.RESULT_OK) {
                EscritaExpressiva expressivaRecebida = (EscritaExpressiva) data.getSerializableExtra(CHAVE_ESCRITA_EXPRESSIVA);
                int posicao = data.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);
                altera(expressivaRecebida, posicao);
            } else if (resultCode == RESULT_CODE_REMOVE) {
                EscritaExpressiva expressivaRecebida = (EscritaExpressiva) data.getSerializableExtra(CHAVE_ESCRITA_EXPRESSIVA);
                int posicao = data.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);
                remove(expressivaRecebida, posicao);
            }
        }
    }

    private boolean temEscritaExpressiva(Intent data) {
        return data != null && data.hasExtra(CHAVE_ESCRITA_EXPRESSIVA);
    }

    private void altera(EscritaExpressiva escritaExpressiva, int posicao) {
        escritaExpressivaDAO.alterar(escritaExpressiva);
        Snackbar.make(getView(), "A escrita expressiva foi alterada com sucesso.", Snackbar.LENGTH_LONG).show();
        adapter.altera(escritaExpressiva, posicao);
    }

    private void adiciona(EscritaExpressiva escritaExpressiva) {
        escritaExpressivaDAO.adicionar(escritaExpressiva);
        Snackbar.make(getView(), "A escrita expressiva foi salva com sucesso.", Snackbar.LENGTH_LONG).show();
        adapter.adiciona(escritaExpressiva);
    }

    private void remove(EscritaExpressiva escritaExpressiva, int posicao) {
        escritaExpressivaDAO.remove(escritaExpressiva.getId());
        adapter.remove(posicao);
        Snackbar.make(getView(), "A escrita expressiva foi removida com sucesso.", Snackbar.LENGTH_LONG).show();
    }

}
