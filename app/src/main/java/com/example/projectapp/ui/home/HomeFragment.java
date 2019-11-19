package com.example.projectapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectapp.FileReader;
import com.example.projectapp.FoodInfo;
import com.example.projectapp.FoodList;
import com.example.projectapp.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public static final String EXTRA = "com.example.projectapp.EXTRA";
    public static final int MYFILE = R.raw.resultset;

    private HomeViewModel homeViewModel;
    private List ruokalista;
    private ListView lvFoods;
    private FoodList foods;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        lvFoods = root.findViewById(R.id.lvFoods);
        listFoods();
        setHasOptionsMenu(true);

        //Search button code
        Button findButton = root.findViewById(R.id.bFindFood);
        findButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                searchFoods();
            }
        });

        lvFoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent nextActivity = new Intent(getActivity(), FoodInfo.class);
                nextActivity.putExtra(EXTRA, i);
                startActivity(nextActivity);
            }
        });
        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.top_menu_find, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    private void searchFoods(){
        //Read food_nutritions.csv file
        FileReader reader = new FileReader();
        InputStream myFile = getActivity().getResources().openRawResource(MYFILE);
        ruokalista = reader.readFile(myFile);
        foods = new FoodList(ruokalista);

        EditText editText = getActivity().findViewById(R.id.ptEditText);
        String haku = editText.getText().toString();

        List copy = new ArrayList();
        for(int i = 0; i < foods.getFoods().size(); i++){
            if(foods.getFoods().get(i).getName().toLowerCase().contains(haku.toLowerCase())){
                copy.add(foods.getFoods().get(i));
            }
        }

        FoodList copyList = new FoodList(copy);

        //ListView of foods
        lvFoods.setAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.food_list_layout,
                copyList.getInstance().getFoods()));
    }

    private void listFoods(){
        //Read food_nutritions.csv file
        FileReader reader = new FileReader();
        InputStream myFile = getActivity().getResources().openRawResource(MYFILE);
        ruokalista = reader.readFile(myFile);
        foods = new FoodList(ruokalista);

        //ListView of foods
        lvFoods.setAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.food_list_layout,
                foods.getInstance().getFoods()));
    }
}