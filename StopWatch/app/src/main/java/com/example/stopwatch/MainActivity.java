package com.example.stopwatch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int milliseconds = 0;
//    private boolean running = false;
    TextView StartStop;
    public TextView timeView;
    private MyViewModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StartStop = findViewById(R.id.StartStop);
        timeView = findViewById(R.id.time);
        model = new ViewModelProvider(this).get(MyViewModel.class);

        final Observer<Integer> timeObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer newTime) {
                long milli = model.getCurrentTime().getValue() % 100;
                long secs = (model.getCurrentTime().getValue() / 100) %60;
                long minutes = (model.getCurrentTime().getValue() / 6000) %60;
                long hours = model.getCurrentTime().getValue() / 360000;


                String timeString = String.format(Locale.getDefault(), "%d:%02d:%02d.%01d", hours, minutes, secs, milli);
                timeView.setText(timeString);
            }
        };

        final Observer<String> StartStopObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newString) {
                StartStop.setText(model.getStartStop().getValue());
            }
        };

        model.getCurrentTime().observe(this, timeObserver);
        model.getStartStop().observe(this, StartStopObserver);
        milliseconds = model.getCurrentTime().getValue();
        StartStop.setText(model.getStartStop().getValue());
        runTimer();
    }

    public void onClickStart(View view)
    {
        if (model.getIsRunning().getValue() == false){
            model.getStartStop().setValue("Stop");
        } else {
            model.getStartStop().setValue("Start");
        }
        model.getIsRunning().setValue(!model.getIsRunning().getValue());
    }

    public void onClickReset(View view)
    {
        milliseconds = 0;
        model.getCurrentTime().setValue(0);

    }

    private void runTimer(){

        Handler handler = new Handler();
        handler.postAtTime(new Runnable() {
            @Override
            public void run() {
                if (model.getIsRunning().getValue()) {
                    milliseconds = milliseconds + 10;
                }
                model.getCurrentTime().setValue(milliseconds);

                handler.postDelayed(this, 100);
            }
        },SystemClock.uptimeMillis());
    }
}