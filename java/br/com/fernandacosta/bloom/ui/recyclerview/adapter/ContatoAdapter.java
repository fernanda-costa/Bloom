package br.com.fernandacosta.bloom.ui.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.fernandacosta.bloom.R;
import br.com.fernandacosta.bloom.model.Contato;
import br.com.fernandacosta.bloom.ui.recyclerview.OnItemClickListener;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder> {

    private Context context;
    private List<Contato> contatoList;
    private OnItemClickListener onItemClickListener;
    private int itemViewMensagem;
    private List<Contato> contatosSelecionados;

    public ContatoAdapter(Context context, List<Contato> contatoList, int view) {
        this.context = context;
        this.contatoList = contatoList;
        this.itemViewMensagem = view;
        contatosSelecionados = new ArrayList<>();
    }

    @NonNull
    @Override
    public ContatoAdapter.ContatoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(itemViewMensagem, viewGroup, false);
        return new ContatoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContatoViewHolder contatoViewHolder, int i) {
        contatoViewHolder.vincula(contatoList.get(i));
    }

    @Override
    public int getItemCount() {
        return contatoList.size();
    }


    public void adiciona(Contato contato) {
        contatoList.add(contato);
        notifyItemInserted(contatoList.size());
    }

    public void altera(Contato contato, int posicao){
        contatoList.set(posicao, contato);
        notifyItemChanged(posicao);
    }

    public void remove(int posicao) {
        contatoList.remove(posicao);
        notifyItemRemoved(posicao);
    }

    public List<Contato> getContatosSelecionados() {
        return contatosSelecionados;
    }

    public class ContatoViewHolder extends RecyclerView.ViewHolder {

        private TextView nomeTextView;
        private TextView telefoneTextView;
        private CheckBox selecionado;
        private Contato contato;

        public ContatoViewHolder(@NonNull View view) {
            super(view);
            if (itemViewMensagem == R.layout.item_contato_mensagem){
                selecionado = view.findViewById(R.id.checkBox);
                selecionado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (selecionado.isChecked()){
                            contatosSelecionados.add(contato);
                        } else {
                            contatosSelecionados.remove(contato);
                        }
                    }
                });
            }

            nomeTextView = view.findViewById(R.id.contato_item_nome_textView);
            telefoneTextView = view.findViewById(R.id.contato_item_telefone_textView);
        }

        public void vincula(Contato contato){
            this.contato = contato;
            preencheCampos();
        }

        private void preencheCampos() {
            nomeTextView.setText(contato.getNome());
            telefoneTextView.setText(contato.getTelefone());
        }
    }
}
