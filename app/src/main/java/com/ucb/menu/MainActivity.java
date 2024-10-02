package com.ucb.menu;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set title and subtitle
        getSupportActionBar().setTitle("World Peace");
        getSupportActionBar().setSubtitle("Choose Kindness,Choose Peace.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.option1) {
            // Replace with AboutUsFragment
            AboutUsFragment aboutUsFragment = new AboutUsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, aboutUsFragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        } else if (id == R.id.option2) {
            // Show DialogFragment
            MyDialogFragment dialogFragment = new MyDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "MyDialogFragment");
            return true;
        } else if (id == R.id.submenu_settings) {
            // Replace with SettingsFragment
            SettingsFragment settingsFragment = new SettingsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, settingsFragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        } else if (id == R.id.submenu_help) {
            // Show a Toast message for Help
            Toast.makeText(this, "Help Section", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.exit_app) {
            // Exit the application
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
