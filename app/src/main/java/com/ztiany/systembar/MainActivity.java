package com.ztiany.systembar;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ztiany.library.SystemBarCompat;
import com.ztiany.systembar.coordinator.CoordinatorActivity;
import com.ztiany.systembar.drawer_layout.DrawerLayoutTabActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.act_main_drawer)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;


    private ActionBarDrawerToggle mActionBarDrawerToggle;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupView();
    }


    protected void setupView() {
        setStatusBar();
        initToolBar();
        initDrawer();
        initNavigation();
    }

    private void setStatusBar() {
        //status bar color
        int color = ContextCompat.getColor(this, R.color.colorPrimaryDark);
        //status bar for api 19
        SystemBarCompat.setTranslucentStatusOnKitkat(this);
        SystemBarCompat.setStatusBarColorOnKitkat(this, color);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示actonBar左边的home按钮
            getSupportActionBar().setHomeButtonEnabled(true);//启动actonBar左边的的启用或禁用“home”按钮
        }
    }

    private void initDrawer() {
        //ActionBarDrawerToggle实现了DrawerListener
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        //        能将drawerLayout的展开和隐藏与actionbar的app 图标关联起来
        mActionBarDrawerToggle.syncState();
    }

    private void initNavigation() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void drawerLayoutWidthTab(View view) {
        startActivity(DrawerLayoutTabActivity.getLaunchIntent(this));
    }

    public void coordinatorWithTab(View view) {
        startActivity(CoordinatorActivity.getLaunchIntent(this));
    }

}
