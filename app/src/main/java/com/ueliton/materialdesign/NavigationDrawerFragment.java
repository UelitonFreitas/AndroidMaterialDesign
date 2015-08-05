package com.ueliton.materialdesign;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    public static final String NOME_ARQUIVO_PRF = "preferencias";
    public static final String CHAVE_USUARIO_APRENDEU_DRAWER = "usuario_aprendeu_drawer";

    private RecyclerView mRecyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private CidadeAdapter mCidadeAdapter;

    private boolean mUsuarioAprendeuDrawer;
    private boolean mEstadoDaInstanciaSalvo;
    private View containerView;
    private Toolbar mToolbar;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUsuarioAprendeuDrawer = Boolean.valueOf(lePreferencias(getActivity(),
                CHAVE_USUARIO_APRENDEU_DRAWER, "false"));

        if (savedInstanceState != null){
            mEstadoDaInstanciaSalvo = true;
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        RecyclerView mRecyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);

        mCidadeAdapter = new CidadeAdapter(getActivity(), pegaDados());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(mCidadeAdapter);

        return layout;
    }

    public static List<Cidade> pegaDados(){
        List<Cidade> listaDeCidades = new ArrayList<Cidade>();
        String[] nomes = {"Bonito", "Corumba", "Jardim"};
        int[] icones = {R.drawable.ic_agencias, R.drawable.ic_agencias, R.drawable.ic_agencias};

        for (int i = 0; i < nomes.length; i++) {
            listaDeCidades.add(new Cidade(nomes[i], icones[i]));
        }

        return listaDeCidades;
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {

        mDrawerLayout = drawerLayout;

        containerView = getActivity().findViewById(fragmentId);

        mToolbar = toolbar;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if(!mUsuarioAprendeuDrawer){
                    mUsuarioAprendeuDrawer = true;
                    salvaPreferencias(getActivity(), CHAVE_USUARIO_APRENDEU_DRAWER,
                            mUsuarioAprendeuDrawer+"");
                }

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                if (slideOffset < 0.6) {
                    mToolbar.setAlpha(1-slideOffset);
                }
            }
        };

        if (!mUsuarioAprendeuDrawer && !mEstadoDaInstanciaSalvo){
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static void salvaPreferencias(Context contexto, String nomeDaPreferencia,
                                         String valorDaPreferencia){
        SharedPreferences sharedPreferences = contexto.getSharedPreferences(NOME_ARQUIVO_PRF,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(nomeDaPreferencia, valorDaPreferencia);
        editor.apply();
    }

    public static String lePreferencias(Context contexto, String nomeDaPreferencia,
                                        String valorPadrao){
        SharedPreferences sharedPreferences = contexto.getSharedPreferences(NOME_ARQUIVO_PRF,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(nomeDaPreferencia, valorPadrao);
    }
}
