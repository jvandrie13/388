package com.example.wackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * This class represents the game over page
 */
public class GameOver extends AppCompatActivity {
    private TextView score;
    private TextView highScore;
    SharedPreferences sharedPref;

    /**
     * creates the game over view
     * @param savedInstanceState
     */
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

    /**
     * Switches to the game play screen (wack) when the play button is clicked
     * @param view
     */
    public void onClickPlayAgain(View view) {
        Intent whackIntent = new Intent(GameOver.this, Whack.class);
        GameOver.this.startActivity(whackIntent);
    }
}