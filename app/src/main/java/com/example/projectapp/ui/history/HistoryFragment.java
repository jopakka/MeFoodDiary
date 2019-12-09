package com.example.projectapp.ui.history;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projectapp.R;

import java.util.Calendar;
import java.util.Date;

public class HistoryFragment extends Fragment {

    private static final String TAG = "MyLog";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        setHasOptionsMenu(true);
        Button buttonLisaaRuoka = (Button) root.findViewById(R.id.bLisaaRuoka);
        buttonLisaaRuoka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addFood = new Intent(getActivity(), AddFoodToHistory.class);
                startActivity(addFood);
                //startActivity(new Intent(PopUp.class)); popup alku
            }
        });

        CalendarView calendarView = root.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                //Log.i(TAG, "" + calendar.get(Calendar.DAY_OF_MONTH));
            }
        });
        return root;
    }

    //Create top bar buttons
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_addMeal).setVisible(false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CalendarView cv = getView().findViewById(R.id.calendarView);
        cv.setMaxDate(new Date().getTime());
    }
}