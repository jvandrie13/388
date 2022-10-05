package com.example.wackamole.Main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    public MutableLiveData<Integer> highScore;
    public MutableLiveData<Integer> prevScore;
    public MutableLiveData<Integer> currScore;
    public MutableLiveData<Integer> visibleMole;

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

    public MutableLiveData<Integer> getCurrScore() {
        if (currScore == null) {
            currScore = new MutableLiveData<Integer>(0);
            currScore.setValue(0);
        }
        return currScore;
    }

    public MutableLiveData<Integer> getVisibleMole() {
        if (visibleMole == null) {
            visibleMole = new MutableLiveData<Integer>(0);
            currScore.setValue(0);
        }
        return visibleMole;
    }

}
