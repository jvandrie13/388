package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int milliseconds = 0;
    private boolean running = false;
    private MutableLiveData<Long> elapsedTime = new MutableLiveData<Long>(0L);
    TextView StartStop;
    MyViewModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StartStop = findViewById(R.id.StartStop);
        model = new ViewModelProvider(this).get(MyViewModel.class);
        runTimer();
    }

    public void onClickStart(View view)
    {
        if (running){
            StartStop.setText(R.string.start);
        } else {
            StartStop.setText(R.string.stop);
        }
        running = !running;
    }

    public void onClickReset(View view)
    {
        milliseconds = 0;
    }

    private void runTimer(){

        TextView timeView = findViewById(R.id.time);
        Handler handler = new Handler();
        handler.postAtTime(new Runnable() {
            @Override
            public void run() {
                if (running) {
                    milliseconds = milliseconds + 10;
                }
                elapsedTime.setValue((long) milliseconds);
                long milli = elapsedTime.getValue() % 100;
                long secs = (elapsedTime.getValue() / 100) %60;
                long minutes = (elapsedTime.getValue() / 60000) %60;
                long hours = elapsedTime.getValue() / 3600000;

;
                String timeString = String.format(Locale.getDefault(), "%d:%02d:%02d.%01d", hours, minutes, secs, milli);

                timeView.setText(timeString);
                handler.postDelayed(this, 100);
            }
        },SystemClock.uptimeMillis());
    }
}