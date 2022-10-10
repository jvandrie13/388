package com.example.wackamole.Main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * View model for main activity
 */
public class MainActivityViewModel extends ViewModel {
    public MutableLiveData<Integer> highScore;
    public MutableLiveData<Integer> prevScore;
    public MutableLiveData<Integer> currScore;
    public MutableLiveData<Integer> visibleMole;

    /**
     * gets the high score
     * @return high score int
     */
    public MutableLiveData<Integer> getHighScore() {
        if (highScore == null) {
            highScore = new MutableLiveData<Integer>(0);
            highScore.setValue(0);
        }
        return highScore;
    }
    /**
     * gets the previous score
     * @return previous score int
     */
    public MutableLiveData<Integer> getPrevScore() {
        if (prevScore == null) {
            prevScore = new MutableLiveData<Integer>(0);
            prevScore.setValue(0);
        }
        return prevScore;
    }
    /**
     * gets the current score
     * @return current score int
     */
    public MutableLiveData<Integer> getCurrScore() {
        if (currScore == null) {
            currScore = new MutableLiveData<Integer>(0);
            currScore.setValue(0);
        }
        return currScore;
    }
    /**
     * gets if the mole is visible 
     * @return value of visible mole
     */
    public MutableLiveData<Integer> getVisibleMole() {
        if (visibleMole == null) {
            visibleMole = new MutableLiveData<Integer>(0);
            currScore.setValue(0);
        }
        return visibleMole;
    }

}
