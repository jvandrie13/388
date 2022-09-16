package com.example.stopwatch;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Locale;

public class MyViewModel extends ViewModel {
    private int milliseconds = 0;

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    private boolean running = false;

    public void setRunning() {
        this.running = !running;
    }


    private MutableLiveData<Long> elapsedTime;

    public LiveData<Long> getElapsedTime() {
        if (elapsedTime == null) {
            elapsedTime = new MutableLiveData<Long>(0L);
            updateTime();
        }
        return elapsedTime;
    }

    private void updateTime() {
//        TextView timeView = findViewById(R.id.time);
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

//                timeView.setText(timeString);
                handler.postDelayed(this, 100);
            }
        }, SystemClock.uptimeMillis());    }
}

