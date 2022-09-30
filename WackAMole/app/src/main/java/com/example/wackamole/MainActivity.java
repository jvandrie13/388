package com.example.wackamole;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView highScore;
    private TextView prevScore;
    private MainActivityViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        highScore = findViewById(R.id.high_score);
        prevScore = findViewById(R.id.prev_score);
        model = new ViewModelProvider(this).get(MainActivityViewModel.class);

        final Observer<Integer> scoreObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer newScore) {
                highScore.setText(model.getHighScore().getValue().toString());
                prevScore.setText(model.getPrevScore().getValue().toString());
            }
        };

        model.getHighScore().observe(this, scoreObserver);
        model.getPrevScore().observe(this, scoreObserver);

    }

    public void onClickPlay(View view)
    {

    }
}