package br.com.fernandacosta.bloom.ui.recyclerview.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.fernandacosta.bloom.R;
import br.com.fernandacosta.bloom.helper.DataHelper;
import br.com.fernandacosta.bloom.model.Crenca;
import br.com.fernandacosta.bloom.ui.activity.CrencasCentraisActivity;
import br.com.fernandacosta.bloom.ui.recyclerview.OnItemClickListener;

public class CrencasAdapter extends RecyclerView.Adapter<CrencasAdapter.CrencaViewHolder> {

    private Context context;
    private List<Crenca> crencasList;
    private OnItemClickListener onItemClickListener;

    public CrencasAdapter(Context context, List<Crenca> crencasList) {
        this.context = context;
        this.crencasList = crencasList;
    }

    @NonNull
    @Override
    public CrencaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_crenca, viewGroup, false);
        return new CrencaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CrencaViewHolder viewHolder, int i) {
        viewHolder.vincula(crencasList.get(i));
    }

    @Override
    public int getItemCount() {
        return crencasList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public void adiciona(Crenca crenca) {
        crencasList.add(crenca);
        notifyItemInserted(crencasList.size());
    }

    public void altera(Crenca crenca, int posicao){
        crencasList.set(posicao, crenca);
        notifyItemChanged(posicao);
    }

    public void remove(int posicao) {
        crencasList.remove(posicao);
        notifyItemRemoved(posicao);
    }


    public class CrencaViewHolder extends RecyclerView.ViewHolder {

        private TextView dataTextView;
        private TextView situacaoTextView;
        private TextView centraisTextView;
        private Crenca crenca;

        public CrencaViewHolder(View view) {
            super(view);

            dataTextView = view.findViewById(R.id.crenca_item_data_textView);
            situacaoTextView = view.findViewById(R.id.crenca_item_situacao_textView);
            centraisTextView = view.findViewById(R.id.crenca_detalhes_centrais_textView);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });

            centraisTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }

        public void vincula(Crenca crenca) {
            this.crenca = crenca;
            preencheCampos();
        }

        private void preencheCampos() {
            dataTextView.setText(DataHelper.formataData(crenca.getDiaQueFoiRealizado()));
            situacaoTextView.setText(crenca.getCrencasIntermediarias().get(0));
        }
    }

}
