package com.ueliton.materialdesign.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ueliton.materialdesign.R;
import com.ueliton.materialdesign.fragmentos.TabFragment;

/**
 * Created by ueliton on 15/08/2015.
 */
public class PageAdapter extends FragmentPagerAdapter {

    String[] titulosDasTabs;

    public PageAdapter(Context contexto, FragmentManager fm) {
        super(fm);
        titulosDasTabs = contexto.getResources().getStringArray(R.array.tabs);
    }



    @Override
    public CharSequence getPageTitle(int position) {
        return titulosDasTabs[position];
    }

    @Override
    public Fragment getItem(int posicao) {

        TabFragment meuFragmento = TabFragment.getInstance(posicao);
        return meuFragmento;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
