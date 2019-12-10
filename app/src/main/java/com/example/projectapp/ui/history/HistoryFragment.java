package com.example.projectapp.ui.history;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.projectapp.food_stuff.Food;
import com.example.projectapp.food_stuff.FoodAtDate;
import com.example.projectapp.food_stuff.Meal;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Elmeri Katainen
 */
public class HistoryFragment extends Fragment {

    private static final String TAG = "MyLog";
    private Calendar dateSelected;
    private List<FoodAtDate> foodAtDate;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        setHasOptionsMenu(true);
        Button buttonLisaaRuoka = (Button) root.findViewById(R.id.bLisaaRuoka);
        buttonLisaaRuoka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addFood = new Intent(getActivity(), AddFoodToHistory.class);
                startActivity(addFood);
                foodAtDate.add(new FoodAtDate(dateSelected.get(Calendar.DAY_OF_MONTH), dateSelected.get(Calendar.MONTH), dateSelected.get(Calendar.YEAR)));
            }
        });

        CalendarView calendarView = root.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                dateSelected = calendar;
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

    public void addFoodHistory(Food food) {
        boolean loop = true;
        for(int i = 0; foodAtDate.size() > 0 && loop == true; i++) {
            if(foodAtDate.get(i).getDay() == dateSelected.get(Calendar.DAY_OF_MONTH)
                    && foodAtDate.get(i).getMonth() == dateSelected.get(Calendar.MONTH)
                    && foodAtDate.get(i).getYear() == dateSelected.get(Calendar.YEAR)) {
                foodAtDate.get(i).addFood(food);
                loop = false;
            }
        }
    }

    public void addMealHistory(Meal meal) {
        boolean loop = true;
        for(int i = 0; foodAtDate.size() > 0 && loop == true; i++) {
            if(foodAtDate.get(i).getDay() == dateSelected.get(Calendar.DAY_OF_MONTH)
                    && foodAtDate.get(i).getMonth() == dateSelected.get(Calendar.MONTH)
                    && foodAtDate.get(i).getYear() == dateSelected.get(Calendar.YEAR)) {
                foodAtDate.get(i).addMeal(meal);
                loop = false;
            }
        }
    }

}