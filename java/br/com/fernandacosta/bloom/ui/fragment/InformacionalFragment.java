package br.com.fernandacosta.bloom.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import br.com.fernandacosta.bloom.R;
import br.com.fernandacosta.bloom.ui.activity.InformacionalDetalheActivity;

import static br.com.fernandacosta.bloom.ui.constant.InformacionalConstantes.APPBAR_TITULO_INFORMACIONAL;
import static br.com.fernandacosta.bloom.ui.constant.InformacionalConstantes.CHAVE_TEXTO;
import static br.com.fernandacosta.bloom.ui.constant.InformacionalConstantes.CHAVE_TITULO;

public class InformacionalFragment extends Fragment {

    private String titulo;
    private String texto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_informacional, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(APPBAR_TITULO_INFORMACIONAL);

        configuraBotaoDepressao(view);
        configuraBotaoTcc(view);
        configuraBotaoDiagnostico(view);
        configuraBotaoSintomas(view);
        configuraBotaoBloom(view);
        configuraBotaoReferencias(view);
    }

    private void configuraBotaoReferencias(View view) {
        ImageButton referenciasImageButton = view.findViewById(R.id.informacional_referencia_imageButtom);
        referenciasImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inicializaTextoETitulo(R.string.informacional_detalhe_titulo_referencias, R.string.informacional_detalhe_texto_referencias);
                mostraDetalhesInformacional(titulo, texto);
            }
        });
    }

    private void configuraBotaoBloom(View view) {
        ImageButton bloomImageButton = view.findViewById(R.id.informacional_bloom_imageButtom);
        bloomImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inicializaTextoETitulo(R.string.informacional_detalhe_titulo_bloom, R.string.informacional_detalhe_texto_bloom);
                mostraDetalhesInformacional(titulo, texto);
            }
        });
    }

    private void configuraBotaoSintomas(View view) {
        ImageButton sintomasImageButton = view.findViewById(R.id.informacional_sintomas_imageButtom);
        sintomasImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inicializaTextoETitulo(R.string.informacional_detalhe_titulo_sintomas, R.string.informacional_detalhe_texto_sintomas);
                mostraDetalhesInformacional(titulo, texto);
            }
        });
    }

    private void configuraBotaoDiagnostico(View view) {
        ImageButton diagnosticoImageButton = view.findViewById(R.id.informacional_diagnostico_imageButtom);
        diagnosticoImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inicializaTextoETitulo(R.string.informacional_detalhe_titulo_diagnostico, R.string.informacional_detalhe_texto_diagnostico);
                mostraDetalhesInformacional(titulo, texto);
            }
        });
    }

    private void configuraBotaoTcc(View view) {
        ImageButton tccImageButton = view.findViewById(R.id.informacional_tcc_imageButtom);
        tccImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inicializaTextoETitulo(R.string.informacional_detalhe_titulo_tcc, R.string.informacional_detalhe_texto_tcc);
                mostraDetalhesInformacional(titulo, texto);
            }
        });
    }

    private void configuraBotaoDepressao(View view) {
        ImageButton depressaoImageButton = view.findViewById(R.id.informacional_depressao_imageButtom);
        depressaoImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inicializaTextoETitulo(R.string.informacional_detalhe_titulo_depressao, R.string.informacional_detalhe_texto_depressao);
                mostraDetalhesInformacional(titulo, texto);
            }
        });
    }

    private void inicializaTextoETitulo(int idTitulo, int idTexto) {
        titulo = getResources().getString(idTitulo);
        texto = getResources().getString(idTexto);
    }


    private void mostraDetalhesInformacional(String titulo, String texto) {
        Intent intent = new Intent(getContext(), InformacionalDetalheActivity.class);
        intent.putExtra(CHAVE_TITULO, titulo);
        intent.putExtra(CHAVE_TEXTO, texto);
        startActivity(intent);
    }

}
