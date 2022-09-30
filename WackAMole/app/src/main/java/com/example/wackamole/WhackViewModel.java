package com.example.wackamole;

import androidx.lifecycle.MutableLiveData;

public class WhackViewModel {
    public MutableLiveData<Integer> currentScore;
    public MutableLiveData<Integer> lives;

    public MutableLiveData<Integer> getCurrentScore() {
        if (currentScore == null) {
            currentScore = new MutableLiveData<Integer>(0);
            currentScore.setValue(0);
        }
        return currentScore;
    }

    public MutableLiveData<Integer> getLives() {
        if (lives == null) {
            lives = new MutableLiveData<Integer>(3);
            lives.setValue(3);
        }
        return lives;
    }
}
