package com.ztiany.systembar.coordinator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.jakewharton.behavior.drawer.DrawerBehavior;
import com.ztiany.library.SystemBarCompat;
import com.ztiany.systembar.MainActivity;
import com.ztiany.systembar.R;
import com.ztiany.systembar.coordinator.tab.RecyclerViewFragment;
import com.ztiany.systembar.coordinator.tab.Tab2Fragment;
import com.ztiany.systembar.coordinator.tab.Tab3Fragment;
import com.ztiany.systembar.coordinator.tab.Tab4Fragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * <br/>    功能描述：
 * <br/>    Email     : ztiany3@gmail.com
 *
 * @author Ztiany
 * @see
 * @since 1.0
 */

public class CoordinatorActivity extends AppCompatActivity {


    @Bind(R.id.act_main_bottom_bar)
    AHBottomNavigation mAHBottomNavigation;

    @Bind(R.id.act_main_drawer)
    View mDrawerView;

    private DrawerBehavior mBehavior;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        ButterKnife.bind(this);
        setStatusBar();
        initBottomBar();
        getWindow().getDecorView().post(
                new Runnable() {
                    @Override
                    public void run() {
                        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) mDrawerView.getLayoutParams();
                        mBehavior = (DrawerBehavior) lp.getBehavior();

                    }
                }
        );
    }

    private void setStatusBar() {
        //status bar color
        int color = ContextCompat.getColor(this, R.color.colorPrimaryDark);
        //status bar for api 19
        SystemBarCompat.setTranslucentStatusOnKitkat(this);
        SystemBarCompat.setStatusBarColorOnKitkat(this, color);

        SystemBarCompat.setTranslucentStatusAfterKitkat(this);

    }


    private void initBottomBar() {

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Tab1", R.drawable.sel_main_home, R.color.colorAccent);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Tab1", R.drawable.sel_main_design, R.color.colorAccent);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Tab1", R.drawable.sel_main_show, R.color.colorAccent);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Tab1", R.drawable.sel_main_me, R.color.colorAccent);

        mAHBottomNavigation.addItem(item1);
        mAHBottomNavigation.addItem(item2);
        mAHBottomNavigation.addItem(item3);
        mAHBottomNavigation.addItem(item4);
        mAHBottomNavigation.setForceTitlesDisplay(true);
        mAHBottomNavigation.setOnTabSelectedListener(null);


        mAHBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                if (!wasSelected) {
                    switchFragment(position);
                }
            }
        });
    }

    private void switchFragment(int position) {
        Class clazz;
        if (position == 0) {
            clazz = RecyclerViewFragment.class;
        } else if (position == 1) {
            clazz = Tab2Fragment.class;
        } else if (position == 2) {
            clazz = Tab3Fragment.class;
        } else {
            clazz = Tab4Fragment.class;
        }


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_id, Fragment.instantiate(this, clazz.getName()))
                .commit();
    }


    public static Intent getLaunchIntent(MainActivity mainActivity) {
        return new Intent(mainActivity, CoordinatorActivity.class);
    }

}
