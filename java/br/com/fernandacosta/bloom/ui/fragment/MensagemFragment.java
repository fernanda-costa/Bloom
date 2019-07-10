package br.com.fernandacosta.bloom.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import br.com.fernandacosta.bloom.R;

public class MensagemFragment extends Fragment {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mensagem, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configuraSMSFab(view);
        configuraWhatsFab(view);

    }

    private void configuraSMSFab(View view) {
        ImageButton button = view.findViewById(R.id.smsImageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensagemContatos();
            }
        });
    }

    private void enviarMensagemContatos() {
        Fragment contatos = new ContatosFragment();

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.content_main_navigation_drawer, contatos);
        transaction.addToBackStack(contatos.getTag());
        selecionarContato(contatos);
        transaction.commit();
    }

    public void selecionarContato(Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putBoolean("OPERACAO", true);
        fragment.setArguments(bundle);
    }



    private void configuraWhatsFab(View view) {
        ImageButton button = view.findViewById(R.id.whatsImageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarMensagemWhats();
            }
        });
    }

    private void enviarMensagemWhats() {
        boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
        if (isWhatsappInstalled) {
            Uri uri = Uri.parse("smsto:" + "98*********7");
            Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hai Good Morning");
            sendIntent.setType("text/plain");
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } else {
            Toast.makeText(getContext(), "WhatsApp not Installed",
                    Toast.LENGTH_SHORT).show();
            Uri uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(goToMarket);

        }
    }

        private boolean whatsappInstalledOrNot(String uri) {
            PackageManager pm = getActivity().getPackageManager();
            boolean app_installed = false;
            try {
                pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
                app_installed = true;
            } catch (PackageManager.NameNotFoundException e) {
                app_installed = false;
            }
            return app_installed;
        }


}

