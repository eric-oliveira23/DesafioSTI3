package com.eric.sti3desafio.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.eric.sti3desafio.R;
import com.eric.sti3desafio.fragment.PedidosFragment;
import com.eric.sti3desafio.fragment.PesquisarFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private boolean doubleBackPressed;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    PedidosFragment pedidosFragment = new PedidosFragment();
    PesquisarFragment pesquisarFragment = new PesquisarFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer);
        ImageView btnMenu = findViewById(R.id.btnMenu);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        showFragment(pedidosFragment);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                switch (id){
                    case R.id.itemPesquisar:
                            showFragment(pesquisarFragment);
                        break;

                    case R.id.itemProdutos:
                            showFragment(pedidosFragment);
                        break;
                }
                return true;
            }
        });
    }

    void showFragment(Fragment fragmentToShow) {
            // transições
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

            // oculta todos os fragments
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            transaction.hide(fragment);
        }

        if (fragmentToShow.isAdded()) {
            // mostra o fragment se ele ja foi adicionado
            transaction.show(fragmentToShow);
        } else {
            transaction.add(R.id.frame, fragmentToShow);
        }
        drawer.closeDrawer(GravityCompat.START);

        transaction.commit();
    }

    @Override
    public void onBackPressed() {

        if (doubleBackPressed){
            super.onBackPressed();
            this.finishAffinity();
        }
        this.doubleBackPressed = true;
        Snackbar snackbar = Snackbar.make(drawer, "Pressione novamente para sair", Snackbar.LENGTH_LONG);
        snackbar.show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackPressed = false;
            }
        },2000);
    }

}