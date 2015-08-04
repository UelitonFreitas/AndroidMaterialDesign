package com.ueliton.materialdesign;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    public static final String NOME_ARQUIVO_PRF = "preferencias";
    public static final String CHAVE_USUARIO_APRENDEU_DRAWER = "usuario_aprendeu_drawer";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

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
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
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
