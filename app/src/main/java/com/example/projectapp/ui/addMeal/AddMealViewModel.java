package com.example.projectapp.ui.addMeal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddMealViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddMealViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is addMeal fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}