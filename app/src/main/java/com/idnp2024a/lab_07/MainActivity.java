package com.idnp2024a.lab_07;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cargar el fragment adecuado al inicio
        if (savedInstanceState == null) {
            Fragment initialFragment;
            if (isDataAvailable()) {
                initialFragment = new ViewFragment();
            } else {
                initialFragment = new RegisterFragment();
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, initialFragment)
                    .commit();
        }
    }
    private boolean isDataAvailable() {
        FileInputStream fis = null;
        try {
            fis = openFileInput("painting.txt");
            if (fis.available() > 0) {
                return true;
            }
        } catch (IOException e) {
            return false;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}