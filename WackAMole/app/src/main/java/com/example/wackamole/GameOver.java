package com.example.wackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class GameOver extends AppCompatActivity {
    private TextView score;
    private TextView highScore;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        highScore = findViewById(R.id.high_score_go);
        score = findViewById(R.id.score_go);

        sharedPref = getSharedPreferences("application", Context.MODE_PRIVATE);
        highScore.setText(sharedPref.getString("HIGH_SCORE", "0"));
        score.setText(sharedPref.getString("PREV_SCORE", "O"));



    };
    public void onClickPlayAgain(View view) {
        Intent whackIntent = new Intent(GameOver.this, Whack.class);
        GameOver.this.startActivity(whackIntent);
    }
}