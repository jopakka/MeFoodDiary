package com.example.projectapp.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectapp.R;

public class HistoryFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        setHasOptionsMenu(true);
        Button buttonLisaaRuoka = (Button) root.findViewById(R.id.bLisaaRuoka);
        buttonLisaaRuoka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addFood = new Intent(getActivity(), AddFoodToHistory.class);
                startActivity(addFood);
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
}