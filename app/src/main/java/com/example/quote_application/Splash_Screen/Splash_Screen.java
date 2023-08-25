package com.example.quote_application.Splash_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;


import com.example.quote_application.MainActivity;
import com.example.quote_application.R;

public class Splash_Screen extends AppCompatActivity {

    Handler handler = new Handler();

    private static final int SPLASH_DURATION = 3000;
    private static final int TYPE_TEXT_DELAY = 100;
    private TextView textView;
    private String fullText = "I think its better to feel goo than to look good";
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        textView = findViewById(R.id.animateTextView);


        animateTypeText();
    }

    private void animateTypeText() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentIndex <= fullText.length()) {
                    String displayedText = fullText.substring(0, currentIndex);
                    textView.setText(displayedText);
                    currentIndex++;
                    handler.postDelayed(this, TYPE_TEXT_DELAY);
                } else {
                    startActivity(new Intent(Splash_Screen.this, MainActivity.class));
                }
            }
        }, TYPE_TEXT_DELAY);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(null);
    }
}