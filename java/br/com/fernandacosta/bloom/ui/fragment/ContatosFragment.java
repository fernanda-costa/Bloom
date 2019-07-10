package br.com.fernandacosta.bloom.ui.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.List;

import br.com.fernandacosta.bloom.R;
import br.com.fernandacosta.bloom.dao.ContatoDAO;
import br.com.fernandacosta.bloom.model.Contato;
import br.com.fernandacosta.bloom.ui.recyclerview.adapter.ContatoAdapter;
import br.com.fernandacosta.bloom.ui.recyclerview.helper.callback.ContatoTouchHlperCallbar;

import static android.app.Activity.RESULT_OK;
import static br.com.fernandacosta.bloom.ui.constant.ContatosConstantes.APPBAR_TITULO;
import static br.com.fernandacosta.bloom.ui.constant.ContatosConstantes.RESULTADO_SELECAO_CONTATOS;

public class ContatosFragment extends Fragment {

    private List<Contato> contatos;
    private ContatoAdapter adapter;
    private boolean selecionarContato;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments() != null){
            selecionarContato = getArguments().getBoolean("OPERACAO");
        }

        return inflater.inflate(R.layout.fragment_contatos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contatos = new ContatoDAO().getContatos();
        FloatingActionButton adicionarFab = view.findViewById(R.id.contatos_adiciona_fab);

        if(selecionarContato){
            configuraAdapter(R.layout.item_contato_mensagem);
            configuraRecyclerView(view);
            adicionarFab.setImageResource(R.drawable.ic_done);
            configuraContatoAddFab(adicionarFab);
        } else {
            getActivity().setTitle(APPBAR_TITULO);
            configuraContatoAddFab(adicionarFab);
            configuraAdapter(R.layout.item_contato);
            RecyclerView recyclerView = configuraRecyclerView(view);
            configuraItemTouchHelper(recyclerView, contatos);
        }

    }

    private RecyclerView configuraRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.contatos_recyclerView);
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    private void configuraItemTouchHelper(RecyclerView recyclerView, List<Contato> contatos) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ContatoTouchHlperCallbar(adapter, contatos, getView()));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void configuraAdapter(int view) {
        adapter = new ContatoAdapter(getContext(), contatos, view);
    }

    private void configuraContatoAddFab(FloatingActionButton adicionarFab) {
        adicionarFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selecionarContato){
                    List<Contato> contatosSelecionados = adapter.getContatosSelecionados();
                    try{
                        for (Contato contato: contatosSelecionados) {
                            SmsManager smgr = SmsManager.getDefault();
                            smgr.sendTextMessage(contato.getTelefone(),null,"texto",null,null);
                            Intent sInt = new Intent(Intent.ACTION_VIEW);
                            sInt.putExtra("address", new String[]{contato.getTelefone()});
                            sInt.putExtra("sms_body", "text");
                            sInt.setType("vnd.android-dir/mms-sms");
                        }
                        Toast.makeText(getContext(), contatosSelecionados.size() + " mensagens foram enviadas corretamente.", Toast.LENGTH_SHORT).show();
                        voltarEmergencia();
                    }
                    catch (Exception e){
                        Toast.makeText(getContext(), "Falha no envio da mensagem.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Intent in = new Intent (Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                    startActivityForResult(in, RESULTADO_SELECAO_CONTATOS);
                }
            }
        });
    }

    private void voltarEmergencia() {
        Fragment emergenciaFragment = new EmergenciaFragment();

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.content_main_navigation_drawer, emergenciaFragment);
        transaction.addToBackStack(emergenciaFragment.getTag());
        transaction.commit();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULTADO_SELECAO_CONTATOS:
                    contactPicked (data);
                    break;
            }
        } else {
            Toast.makeText (getContext(), "Falha na seleção do contato", Toast.LENGTH_SHORT).show();
        }
    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;

        try {
            String telefone, nome;
            Uri uri = data.getData ();
            cursor = getActivity().getContentResolver ().query (uri, null, null,null,null);
            cursor.moveToFirst ();

            int phoneIndex = cursor.getColumnIndex (ContactsContract.CommonDataKinds.Phone.NUMBER);
            int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

            telefone = cursor.getString (phoneIndex);
            nome = cursor.getString(nameIndex);
            adicionarContato(new Contato(nome, telefone));

        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    private void adicionarContato(Contato contato) {
        new ContatoDAO().adicionar(contato);
        adapter.adiciona(contato);
        Snackbar.make(getView(), "Contato adicionado com sucesso.", Snackbar.LENGTH_LONG).show();
    }

}
