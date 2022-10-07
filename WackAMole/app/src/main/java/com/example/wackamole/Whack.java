package com.example.wackamole;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wackamole.Main.MainActivity;
import com.example.wackamole.Main.MainActivityViewModel;
import com.example.wackamole.GameOver;

import java.util.Random;

public class Whack extends AppCompatActivity {
    private TextView highScore;
    private TextView currScore;
    private TextView livesLeft;
    private MainActivityViewModel model;
    Handler handler;
    Runnable runnable;
    ImageView[] moles;
    Integer lives;
    long delayMillis = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whack);
        highScore = findViewById(R.id.whack_high_score);
        currScore = findViewById(R.id.whack_score);
        livesLeft = findViewById(R.id.whack_lives);
        moles = new ImageView[9];

        for (int i = 0; i < 9; i++) {
            int res = getResources().getIdentifier("mole_"+i, "id", getPackageName()); //This line is necessary to "convert" a string (e.g. "i1", "i2" etc.) to a resource ID
            moles[i] = (ImageView) findViewById(res);
            moles[i].setVisibility(View.INVISIBLE);
        }

        lives = 4;
        livesLeft.setText(lives.toString());

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                lives = lives - 1;
                int prevMole = model.getVisibleMole().getValue();
                moles[prevMole].setVisibility(View.INVISIBLE);
                int random = new Random().nextInt(9);
                model.getVisibleMole().setValue(random);
                moles[random].setVisibility(View.VISIBLE);
                handler.postDelayed(this, delayMillis);
                delayMillis = delayMillis - 10;
                livesLeft.setText(lives.toString());
                if (lives == 0){
                    onGameOver();
                }
            }
        };

        model = new ViewModelProvider(this).get(MainActivityViewModel.class);
        final Observer<Integer> scoreObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer newScore) {
                highScore.setText(model.getHighScore().getValue().toString());
                currScore.setText(model.getCurrScore().getValue().toString());
            }
        };

        model.getHighScore().observe(this, scoreObserver);
        model.getCurrScore().observe(this, scoreObserver);

        handler.postAtTime(runnable, SystemClock.uptimeMillis());
    }

    public void onClickMole(View view){
        lives = lives + 1;
        model.getCurrScore().setValue(model.getCurrScore().getValue()+10);
        moles[model.getVisibleMole().getValue()].setVisibility(View.INVISIBLE);
    }

    public void onGameOver(){
        if (model.getCurrScore().getValue() > model.getHighScore().getValue()){
            model.getHighScore().setValue(model.getCurrScore().getValue());
        }
        handler.removeCallbacks(runnable);
        model.getPrevScore().setValue(model.getCurrScore().getValue());

        SharedPreferences sharedPref = getSharedPreferences("application", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("HIGH_SCORE", model.getHighScore().getValue().toString());
        editor.putString("PREV_SCORE", model.getPrevScore().getValue().toString());
        editor.apply();
        Intent gameOvaIntent = new Intent(Whack.this, GameOver.class);
        startActivity(gameOvaIntent);
    }

    public void onClickBack(View view){
        handler.removeCallbacks(runnable);
        Intent intent = new Intent(Whack.this, MainActivity.class);
        String hScore = model.getHighScore().getValue().toString();
        String pScore = model.getPrevScore().getValue().toString();
        intent.putExtra(hScore, model.getHighScore().getValue());
        intent.putExtra(pScore, model.getPrevScore().getValue());

        Whack.this.startActivity(intent);
    }
}