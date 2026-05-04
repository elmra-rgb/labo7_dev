package com.example.lab7_dev.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab7_dev.R;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView appLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appLogo = findViewById(R.id.appLogo);

        // Animation de rotation
        appLogo.animate()
                .rotation(360f)
                .setDuration(2000)
                .start();

        // Animation de réduction
        appLogo.animate()
                .scaleX(0.5f)
                .scaleY(0.5f)
                .setDuration(3000)
                .start();

        // Animation de translation
        appLogo.animate()
                .translationY(1000f)
                .setDuration(2000)
                .start();

        // Animation de disparition
        appLogo.animate()
                .alpha(0f)
                .setDuration(6000)
                .start();

        // Redirection après délai
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, MainListActivity.class);
            startActivity(intent);
            finish();
        }, 5000);
    }
}