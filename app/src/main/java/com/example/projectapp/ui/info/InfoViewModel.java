package com.example.projectapp.ui.info;

import androidx.lifecycle.ViewModel;

public class InfoViewModel extends ViewModel {
    private int number;

    public InfoViewModel() {
        number = 0;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
