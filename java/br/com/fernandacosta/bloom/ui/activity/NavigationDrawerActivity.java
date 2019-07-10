package br.com.fernandacosta.bloom.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import br.com.fernandacosta.bloom.R;
import br.com.fernandacosta.bloom.ui.fragment.ContatosFragment;
import br.com.fernandacosta.bloom.ui.fragment.CrencasFragment;
import br.com.fernandacosta.bloom.ui.fragment.DiarioCompletoFragment;
import br.com.fernandacosta.bloom.ui.fragment.DiarioDePensamentosFragment;
import br.com.fernandacosta.bloom.ui.fragment.EmergenciaFragment;
import br.com.fernandacosta.bloom.ui.fragment.EscritaExpressivaFragment;
import br.com.fernandacosta.bloom.ui.fragment.InformacionalFragment;
import br.com.fernandacosta.bloom.ui.fragment.MainFragment;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main_navigation_drawer, new MainFragment());
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (fragment != null) {
                finish();
                this.startActivity(getIntent());
                this.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
            } else {
                super.onBackPressed();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        mostraTelaSelecionada(id);

        return true;
    }

    private void mostraTelaSelecionada(int id) {
        fragment = null;

        if (id == R.id.nav_contatos) {
            fragment = new ContatosFragment();
        } else if (id == R.id.nav_crenca) {
            fragment = new CrencasFragment();
        } else if (id == R.id.nav_diarioCompleto) {
            fragment = new DiarioCompletoFragment();
        } else if (id == R.id.nav_diarioPensamentos) {
            fragment = new DiarioDePensamentosFragment();
        } else if (id == R.id.nav_escritaExpressiva) {
            fragment = new EscritaExpressivaFragment();
        } else if (id == R.id.nav_emergencia) {
            fragment = new EmergenciaFragment();
        } else if (id == R.id.nav_informacional) {
            fragment = new InformacionalFragment();
        }

        if (fragment != null) {
            trocaFragment();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void trocaFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.content_main_navigation_drawer, fragment, fragment.getTag());
        transaction.commit();

        getSupportActionBar().show();
    }

    public void irParaCrenca(View view) {
        mostraTelaSelecionada(R.id.nav_crenca);
    }

    public void irParaInformacional(View view) {
        mostraTelaSelecionada(R.id.nav_informacional);
    }

    public void irParaEmergencia(View view) {
        mostraTelaSelecionada(R.id.nav_emergencia);
    }

    public void irParaEscritaExpressiva(View view) {
        mostraTelaSelecionada(R.id.nav_escritaExpressiva);
    }

    public void irParaDiarioDePensamentos(View view) {
        mostraTelaSelecionada(R.id.nav_diarioPensamentos);
    }

    public void irParaDiarioCompleto(View view) {
        mostraTelaSelecionada(R.id.nav_diarioCompleto);
    }

}
