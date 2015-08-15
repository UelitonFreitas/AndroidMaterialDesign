package com.ueliton.materialdesign.fragmentos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ueliton.materialdesign.R;

/**
 * Created by ueliton on 15/08/2015.
 */
public class TabFragment extends Fragment {

    private TextView texto;
    private View layout;

    public static TabFragment getInstance(int posicao){
        TabFragment meuFragmento = new TabFragment();

        Bundle args = new Bundle();
        args.putInt("posicao", posicao);
        meuFragmento.setArguments(args);
        return meuFragmento;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        inicializaOhLayout(inflater, container);

        adicionaAhPosicaoDoFragmento();

        return layout;
    }

    private void inicializaOhLayout(LayoutInflater inflater, ViewGroup container) {
        this.layout = inflater.inflate(R.layout.fragment_tab_fragment, container, false);
    }

    private void adicionaAhPosicaoDoFragmento() {
        inicializaTextView();
        adicionaOhNumeroDoFragmento();
    }

    private void adicionaOhNumeroDoFragmento() {
        Bundle bundle = getArguments();
        if(bundle != null){
            adicionaPosicaoDoFragmentoNoTextoDoLayout(bundle.getInt("posicao"));
        }
    }

    private void adicionaPosicaoDoFragmentoNoTextoDoLayout(int posicao) {
        texto.setText("A posição escolhida é: "+posicao);
    }


    private void inicializaTextView() {
        texto = (TextView) layout.findViewById(R.id.tv_texto_do_fragmento);
    }
}
