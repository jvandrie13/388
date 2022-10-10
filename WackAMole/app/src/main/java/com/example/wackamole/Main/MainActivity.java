package com.example.wackamole.Main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wackamole.R;
import com.example.wackamole.Whack;

public class MainActivity extends AppCompatActivity {
    private TextView highScore;
    private TextView prevScore;
    private Button playButton;
    public MainActivityViewModel model;
    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        highScore = findViewById(R.id.high_score);
        prevScore = findViewById(R.id.prev_score);
        playButton = findViewById(R.id.button_play);
        model = new ViewModelProvider(this).get(MainActivityViewModel.class);
        sharedPref = getSharedPreferences("application", Context.MODE_PRIVATE);

        final Observer<Integer> scoreObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer newScore) {
                highScore.setText(sharedPref.getString("HIGH_SCORE", "0"));
                prevScore.setText(sharedPref.getString("PREV_SCORE", "0"));
            }
        };

        model.getHighScore().observe(this, scoreObserver);
        model.getPrevScore().observe(this, scoreObserver);
    }

    public void onClickPlay(View view) {
        Intent whackIntent = new Intent(MainActivity.this, Whack.class);
        MainActivity.this.startActivity(whackIntent);
    }

}