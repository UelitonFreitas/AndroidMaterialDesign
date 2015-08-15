package com.ueliton.materialdesign.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ueliton.materialdesign.adapters.PageAdapter;
import com.ueliton.materialdesign.fragmentos.NavigationDrawerFragment;
import com.ueliton.materialdesign.R;
import com.ueliton.materialdesign.tabs.SlidingTabLayout;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private NavigationDrawerFragment drawerFragment;
    private ViewPager mViewPager;
    private SlidingTabLayout mSlidingTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configuraToolBar();

        configuraDrawer();

        configuraViewPager();

        configuraTabs();

    }

    private void configuraTabs() {
        inicializaTabs();
        adicionaPagerAhTabs();
    }

    private void adicionaPagerAhTabs() {
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    private void inicializaTabs() {
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
    }

    private void configuraViewPager() {
        inicializaViewPager();
        adicionaAdapterAhViewPager();
    }

    private void adicionaAdapterAhViewPager() {
        PageAdapter pageAdapter = new PageAdapter(this, getSupportFragmentManager());
        mViewPager.setAdapter(pageAdapter);
    }

    private void inicializaViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.pager);
    }

    private void configuraDrawer() {

        inicializaDrawerCustomizado();
        adicionaDrawerCustomizado();
    }

    private void adicionaDrawerCustomizado() {
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawe_layout), mToolBar);
    }

    private void inicializaDrawerCustomizado() {
        drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_navigation_drawer);
    }

    private void configuraToolBar() {

        adicionaToolbarMinhaToolBarAhActionBar();
        adicionaBotaoDeHomeAhToolBar();
    }

    private void adicionaBotaoDeHomeAhToolBar() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void adicionaToolbarMinhaToolBarAhActionBar() {

        mToolBar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.proximo){
            startActivity(new Intent(this, SubActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
