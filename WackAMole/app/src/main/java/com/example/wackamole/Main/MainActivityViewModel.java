package com.example.wackamole.Main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    public MutableLiveData<Integer> highScore;
    public MutableLiveData<Integer> prevScore;

    public MutableLiveData<Integer> getHighScore() {
        if (highScore == null) {
            highScore = new MutableLiveData<Integer>(0);
            highScore.setValue(0);
        }
        return highScore;
    }

    public MutableLiveData<Integer> getPrevScore() {
        if (prevScore == null) {
            prevScore = new MutableLiveData<Integer>(0);
            prevScore.setValue(0);
        }
        return prevScore;
    }
}
