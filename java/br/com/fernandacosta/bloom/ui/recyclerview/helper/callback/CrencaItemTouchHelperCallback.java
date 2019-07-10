package br.com.fernandacosta.bloom.ui.recyclerview.helper.callback;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.List;

import br.com.fernandacosta.bloom.dao.CrencaDAO;
import br.com.fernandacosta.bloom.model.Crenca;
import br.com.fernandacosta.bloom.ui.recyclerview.adapter.CrencasAdapter;

public class CrencaItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private CrencasAdapter adapter;
    private List<Crenca> crencas;
    private View view;

    public CrencaItemTouchHelperCallback(CrencasAdapter adapter, List<Crenca> todasCrencas, View view) {
        this.adapter = adapter;
        this.crencas = todasCrencas;
        this.view = view;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
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
        removeNota(posicaoDeslizada);
    }

    private void removeNota(int posicaoDeslizada) {
        Crenca crenca = crencas.get(posicaoDeslizada);
        new CrencaDAO().remove(crenca.getId());
        adapter.remove(posicaoDeslizada);
        Snackbar.make(view, "A crença foi removida com sucesso.", Snackbar.LENGTH_LONG).show();
    }
}
