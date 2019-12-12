package com.example.projectapp.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projectapp.R;
import com.example.projectapp.food_stuff.Food;
import com.example.projectapp.food_stuff.FoodAtDate;
import com.example.projectapp.food_stuff.FoodHistory;
import com.example.projectapp.food_stuff.Meal;
import com.example.projectapp.food_stuff.MealsList;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Elmeri Katainen
 */
public class HistoryFragment extends Fragment {

    private static final String TAG = "MyLog";
    public static final String EXTRA = "com.example.projectapp.ui.history.EXTRA";
    private Calendar dateSelected;
    private ListView foodHistoryAtDate;
    private ArrayAdapter adapter;
    private List<Food> foodListDay;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        setHasOptionsMenu(true);
        foodListDay = new ArrayList<>();
        dateSelected = Calendar.getInstance();
        Button buttonLisaaRuoka = (Button) root.findViewById(R.id.bLisaaRuoka);
        buttonLisaaRuoka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addFoodToHistory = new Intent(getActivity(), AddFoodToHistory.class);
                addFoodToHistory.putExtra(EXTRA, dateSelected.get(Calendar.DAY_OF_MONTH) + "_" + dateSelected.get(Calendar.MONTH) + "_" + dateSelected.get(Calendar.YEAR));
                startActivity(addFoodToHistory);
                //foodAtDate.add(new FoodAtDate(dateSelected.get(Calendar.DAY_OF_MONTH), dateSelected.get(Calendar.MONTH), dateSelected.get(Calendar.YEAR)));
            }
        });

        CalendarView calendarView = root.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                dateSelected.set(year, month, dayOfMonth);
                Log.i(TAG, Integer.toString(dateSelected.get(Calendar.DAY_OF_MONTH)) + Integer.toString(dateSelected.get(Calendar.MONTH)));
                updateUi();
            }
        });
        return root;
    }

    @Override
    public void onStart() {
        foodHistoryAtDate = getActivity().findViewById(R.id.lvRuokaHistoria);
        super.onStart();
    }

    @Override
    public void onResume() {
        updateUi();
        super.onResume();
    }

    /**
     * Create top bar buttons
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_addMeal).setVisible(false);
    }

    /**
     * Set maximum modifiable date, populate ListView
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CalendarView cv = getView().findViewById(R.id.calendarView);
        cv.setMaxDate(new Date().getTime());

        adapter = new ArrayAdapter<>(getActivity(),
                R.layout.food_list_layout_history,
                foodListDay);
    }

    /**
     * Set values to TextViews, calls createDayList
     */
    private void updateUi(){
        createDayList();

        DecimalFormat df = new DecimalFormat("#.#");
        ((TextView)getActivity().findViewById(R.id.tvHomeEnergia))
                .setText(String.format(getResources().getString(R.string.text_energy), Integer.toString(energyCounter()),
                        "" + Math.round(energyCounter() / 4.1868)));
        ((TextView)getActivity().findViewById(R.id.tvHomehiilihydraatti)).setText(String.format(getResources()
                .getString(R.string.text_gram), df.format(counter("hiilihydraatti"))));
        ((TextView)getActivity().findViewById(R.id.tvHomeKuitu)).setText(String.format(getResources()
                .getString(R.string.text_gram), df.format(counter("kuitu"))));

        ((TextView)getActivity().findViewById(R.id.tvHomeProteiini)).setText(String.format(getResources()
                .getString(R.string.text_gram), df.format(counter("proteiini"))));

        ((TextView)getActivity().findViewById(R.id.tvHomeRasva)).setText(String.format(getResources()
                .getString(R.string.text_gram), df.format(counter("rasva"))));

        ((TextView)getActivity().findViewById(R.id.tvHomeSuola)).setText(String.format(getResources()
                .getString(R.string.text_mGram), df.format(counter("suola"))));

        foodHistoryAtDate.setAdapter(adapter);
    }

    private void createDayList() {
        foodListDay.clear();
        for(int i = 0; FoodHistory.getInstance().getFoodHistory().size() > i; i++) {
            if(FoodHistory.getInstance().getFoodHistory().get(i).isMeal()
                    && dateSelected.get(Calendar.DAY_OF_MONTH) == FoodHistory.getInstance().getFoodHistory().get(i).getDay()
                    && dateSelected.get(Calendar.MONTH) == FoodHistory.getInstance().getFoodHistory().get(i).getMonth()
                    && dateSelected.get(Calendar.YEAR) == FoodHistory.getInstance().getFoodHistory().get(i).getYear())
                foodListDay.addAll(FoodHistory.getInstance().getFoodHistory().get(i).getMeal().getMeal());
            else if(dateSelected.get(Calendar.DAY_OF_MONTH) == FoodHistory.getInstance().getFoodHistory().get(i).getDay()
                    && dateSelected.get(Calendar.MONTH) == FoodHistory.getInstance().getFoodHistory().get(i).getMonth()
                    && dateSelected.get(Calendar.YEAR) == FoodHistory.getInstance().getFoodHistory().get(i).getYear())
                foodListDay.add(FoodHistory.getInstance().getFoodHistory().get(i).getFood());
        }
    }

    private double counter(String value){
        double summa = 0;
        if(foodListDay == null || foodListDay.size() == 0) {
            return 0;
        }

        switch (value){
            case "hiilihydraatti":
                for(int i = 0; i < foodListDay.size(); i++){
                    double luku = Double.parseDouble(foodListDay.get(i).getHiilihyraatti().replaceAll("[^\\d.]", ""));
                    summa += luku;
                }
                return summa / foodListDay.size();

            case "kuitu":
                for(int i = 0; i < foodListDay.size(); i++){
                    double luku = Double.parseDouble(foodListDay.get(i).getKuitu().replaceAll("[^\\d.]", ""));
                    summa += luku;
                }
                return summa / foodListDay.size();

            case "proteiini":
                for(int i = 0; i < foodListDay.size(); i++){
                    double luku = Double.parseDouble(foodListDay.get(i).getProteiini().replaceAll("[^\\d.]", ""));
                    summa += luku;
                }
                return summa / foodListDay.size();

            case "rasva":
                for(int i = 0; i < foodListDay.size(); i++){
                    double luku = Double.parseDouble(foodListDay.get(i).getRasva().replaceAll("[^\\d.]", ""));
                    summa += luku;
                }
                return summa / foodListDay.size();

            case "suola":
                for(int i = 0; i < foodListDay.size(); i++){
                    double luku = Double.parseDouble(foodListDay.get(i).getSuola().replaceAll("[^\\d.]", ""));
                    summa += luku;
                }
                return summa / foodListDay.size();
        }
        return 0;
    }

    private int energyCounter(){

        if(foodListDay == null || foodListDay.size() == 0) {
            return 0;
        }
        int summa = 0;
        for(int i = 0; i < foodListDay.size(); i++){
            int luku = Integer.parseInt(foodListDay.get(i).getEnergia().replaceAll("[^\\d.]", ""));
            summa += luku;
        }
        return summa / foodListDay.size();
    }
}