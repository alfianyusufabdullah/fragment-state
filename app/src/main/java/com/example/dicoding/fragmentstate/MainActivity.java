package com.example.dicoding.fragmentstate;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private String title = "Decrement";

    private static final String KEY_FRAGMENT = "fragment";
    private static final String KEY_TITLE = "title";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerMain);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.mainNavigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menu_decrement:
                        fragment = setFragment(new DecrementFragment());
                        title = "Decrement";
                        break;
                    case R.id.menu_increment:
                        fragment = setFragment(new IncrementFragment());
                        title = "Increment";
                        break;
                }

                assert getSupportActionBar() != null;
                getSupportActionBar().setTitle(title);

                drawerLayout.closeDrawer(Gravity.START);
                return true;
            }
        });

        if (savedInstanceState != null) {
            fragment = setFragment(getSupportFragmentManager().getFragment(savedInstanceState, KEY_FRAGMENT));
            title = savedInstanceState.getString(KEY_TITLE);
        } else {
            fragment = setFragment(new DecrementFragment());
        }

        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(title);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_TITLE, title);
        getSupportFragmentManager().putFragment(outState, KEY_FRAGMENT, fragment);
        super.onSaveInstanceState(outState);
    }

    private Fragment setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerCounting, fragment, fragment.getTag())
                .commit();

        return fragment;
    }
}
