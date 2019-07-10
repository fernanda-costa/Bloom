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
import br.com.fernandacosta.bloom.ui.recyclerview.OnItemClickListener;

public class DetalhesCrencaAdapter extends RecyclerView.Adapter<DetalhesCrencaAdapter.DetalhesCrencaViewHolder> {

    private Context context;
    private List<String> respostasCrencas;
    private String[] perguntasCrencas;
    private OnItemClickListener onItemClickListener;

    public DetalhesCrencaAdapter(Context context, List<String> respostasCrencas) {
        this.context = context;
        this.respostasCrencas = respostasCrencas;
        this.perguntasCrencas = this.context.getResources().getStringArray(R.array.crencas_intermediarias);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void altera(int posicao){
        notifyItemChanged(posicao);
    }

    @NonNull
    @Override
    public DetalhesCrencaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutCriado = LayoutInflater.from(context).inflate(R.layout.item_crenca_detalhe, viewGroup, false);
        return new DetalhesCrencaViewHolder(layoutCriado);
    }

    @Override
    public void onBindViewHolder(@NonNull DetalhesCrencaViewHolder viewHolder, int i) {
        viewHolder.vincula(respostasCrencas.get(i), perguntasCrencas[i]);
    }

    @Override
    public int getItemCount() {
        return respostasCrencas.size();
    }

    class DetalhesCrencaViewHolder extends RecyclerView.ViewHolder {

        TextView perguntaTextView;
        TextView respostaTextView;

        public DetalhesCrencaViewHolder(@NonNull View itemView) {
            super(itemView);
            perguntaTextView = itemView.findViewById(R.id.item_detalhe_crenca_pergunta_textView);
            respostaTextView = itemView.findViewById(R.id.item_detalhe_crenca_resposta_textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });

        }

        public void vincula(String resposta, String pergunta) {
            respostaTextView.setText(resposta);
            perguntaTextView.setText(pergunta);
        }
    }

}
