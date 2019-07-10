package br.com.fernandacosta.bloom.ui.fragment;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import br.com.fernandacosta.bloom.R;
import br.com.fernandacosta.bloom.model.Contato;

public class EmergenciaFragment extends Fragment {

    public static final String APPBAR_TITULO = "Emergencia";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_emergencia, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(APPBAR_TITULO);

        configuraBotaoContatos(view);
        configuraBotaoMensagem(view);
        configuraBotaoDiscagemRapida(view);

    }

    private void configuraBotaoDiscagemRapida(View view) {
        ImageButton discagemRapidaImageButton = view.findViewById(R.id.emergencia_discagem_rapida_imageButton);
        discagemRapidaImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment ligacao = new LigacaoFragment();

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                transaction.replace(R.id.content_main_navigation_drawer, ligacao);
                transaction.addToBackStack(ligacao.getTag());
                transaction.commit();
            }
        });
    }

    private void configuraBotaoMensagem(View view) {
        ImageButton mensagemImageButton = view.findViewById(R.id.emergencia_mensagem_imageButton);
        mensagemImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment mensagem = new MensagemFragment();

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                transaction.replace(R.id.content_main_navigation_drawer, mensagem);
                transaction.addToBackStack(mensagem.getTag());
                transaction.commit();
            }
        });
    }

    private void configuraBotaoContatos(View view) {
        ImageButton contatosImageButton = view.findViewById(R.id.emergencia_contatos_imageButton);
        contatosImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment contatos = new ContatosFragment();

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                transaction.replace(R.id.content_main_navigation_drawer, contatos);
                transaction.addToBackStack(contatos.getTag());
                transaction.commit();
            }
        });
    }
}
