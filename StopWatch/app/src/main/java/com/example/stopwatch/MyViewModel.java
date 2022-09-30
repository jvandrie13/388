package com.example.stopwatch;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Locale;

public class MyViewModel extends ViewModel {
    public MutableLiveData<Integer> time;
    public MutableLiveData<Boolean> running;
    public MutableLiveData<String> StartStop;


    public MutableLiveData<Integer> getCurrentTime() {
        if (time == null) {
            time = new MutableLiveData<Integer>();
            time.setValue(0);
        }
        return time;
    }

    public MutableLiveData<Boolean> getIsRunning() {
        if (running == null) {
            running = new MutableLiveData<Boolean>();
            running.setValue(false);
        }
        return running;
    }

    public MutableLiveData<String> getStartStop() {
        if (StartStop == null) {
            StartStop = new MutableLiveData<String>();
            StartStop.setValue("Start");
        }
        return StartStop;
    }


}

