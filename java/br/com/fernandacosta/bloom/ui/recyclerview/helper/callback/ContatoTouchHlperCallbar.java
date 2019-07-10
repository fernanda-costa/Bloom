package br.com.fernandacosta.bloom.ui.recyclerview.helper.callback;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.List;

import br.com.fernandacosta.bloom.dao.ContatoDAO;
import br.com.fernandacosta.bloom.model.Contato;
import br.com.fernandacosta.bloom.ui.recyclerview.adapter.ContatoAdapter;

public class ContatoTouchHlperCallbar extends ItemTouchHelper.Callback {

    private ContatoAdapter adapter;
    private List<Contato> contatos;
    private View view;

    public ContatoTouchHlperCallbar(ContatoAdapter adapter, List<Contato> contatos, View view) {
        this.adapter = adapter;
        this.contatos = contatos;
        this.view = view;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int marcacoesDeDeslize = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        int marcacoesDeArraste = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT | ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        return makeMovementFlags(marcacoesDeArraste, marcacoesDeDeslize);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int posicaoDeslizada = viewHolder.getAdapterPosition();
        removeContato(posicaoDeslizada);
    }

    private void removeContato(int posicaoDeslizada) {
        Contato contato = contatos.get(posicaoDeslizada);
        new ContatoDAO().remove(contato.getId());
        adapter.remove(posicaoDeslizada);
        Snackbar.make(view, "O contato foi removida com sucesso.", Snackbar.LENGTH_LONG).show();
    }
}
