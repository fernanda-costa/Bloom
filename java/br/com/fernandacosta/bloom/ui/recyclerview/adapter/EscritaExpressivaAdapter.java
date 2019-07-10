package br.com.fernandacosta.bloom.ui.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.fernandacosta.bloom.R;
import br.com.fernandacosta.bloom.model.EscritaExpressiva;
import br.com.fernandacosta.bloom.ui.recyclerview.OnItemClickListener;

public class EscritaExpressivaAdapter extends RecyclerView.Adapter<EscritaExpressivaAdapter.EscritaExpressivaViewHolder>  {

    private final List<EscritaExpressiva> escritaExpressivas;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public EscritaExpressivaAdapter(List<EscritaExpressiva> escritaExpressivas, Context context) {
        this.escritaExpressivas = escritaExpressivas;
        this.context = context;
    }

    @NonNull
    @Override
    public EscritaExpressivaAdapter.EscritaExpressivaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_escrita_expressiva, viewGroup, false);
        return new EscritaExpressivaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EscritaExpressivaViewHolder escritaExpressivaViewHolder, int i) {
        EscritaExpressiva escritaExpressiva = escritaExpressivas.get(i);
        escritaExpressivaViewHolder.vincula(escritaExpressiva);
    }

    @Override
    public int getItemCount() {
        return escritaExpressivas.size();
    }

    public void adiciona(EscritaExpressiva escritaExpressiva) {
        escritaExpressivas.add(escritaExpressiva);
        notifyItemInserted(escritaExpressivas.size());
    }

    public void altera(EscritaExpressiva escritaExpressiva, int posicao){
        escritaExpressivas.set(posicao, escritaExpressiva);
        notifyItemChanged(posicao);
    }

    public void remove(int posicao) {
        escritaExpressivas.remove(posicao);
        notifyItemRemoved(posicao);
    }


    public class EscritaExpressivaViewHolder extends RecyclerView.ViewHolder {

        private final TextView tituloTextView;
        private final TextView textoTextView;

        public EscritaExpressivaViewHolder(@NonNull View itemView) {
            super(itemView);

            tituloTextView = itemView.findViewById(R.id.item_escrita_expressiva_titulo);
            textoTextView = itemView.findViewById(R.id.item_escrita_expressiva_texto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }

        public void vincula(EscritaExpressiva escritaExpressiva) {
            preencheCampos(escritaExpressiva);
        }

        private void preencheCampos(EscritaExpressiva escritaExpressiva) {
            tituloTextView.setText(escritaExpressiva.getTitulo());
            textoTextView.setText(escritaExpressiva.getTexto());
        }
    }
}
