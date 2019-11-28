package com.example.projectapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectapp.R;
import com.example.projectapp.filehandler.FileReader;
import com.example.projectapp.food_stuff.Food;
import com.example.projectapp.food_stuff.FoodList;
import com.example.projectapp.ui.MainActivity;
import com.example.projectapp.ui.info.InfoFragment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class HomeFragment extends Fragment implements View.OnClickListener {

    public static final int MYFILE = R.raw.resultset;
    private List ruokalista;
    private ListView lvFoods;
    private ArrayList<Food> copy;
    private boolean showSearch;
    private EditText et;
    private String searchWords;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        lvFoods = root.findViewById(R.id.lvFoods);
        setHasOptionsMenu(true);
        showSearch = false;
        et = getActivity().findViewById(R.id.ptFind);

        Button searchButton = root.findViewById(R.id.bSearch);
        searchButton.setOnClickListener(this);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        //Show food information
        lvFoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("testilog", "home " + i);

                searchWords = et.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putInt("item", copy.get(i).getId());
                Fragment infoFragment = new InfoFragment();
                infoFragment.setArguments(bundle);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, infoFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return root;
    }

    //Button onClick events
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bSearch:
                Log.i("testilog", "search button toimii");
                if (et.getText().toString().isEmpty()) {
                    if (copy.size() < FoodList.getInstance().getFoods().size()) {
                        searchFoods();
                    }
                    try {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {
                        Log.d("Error", "keyboard not visible");
                    }
                } else {
                    searchFoods();
                    try {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {
                        Log.d("Error", "keyboard not visible");
                    }
                }
                break;
        }
    }

    //After view is created
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        et = getActivity().findViewById(R.id.ptFind);
        et.setText(searchWords);
        searchFoods();
        if(((MainActivity)getActivity()).getSearchVisible()) {
            getView().findViewById(R.id.ptFind).setVisibility(View.VISIBLE);
            getView().findViewById(R.id.bSearch).setVisibility(View.VISIBLE);
        } else{
            getView().findViewById(R.id.ptFind).setVisibility(View.GONE);
            getView().findViewById(R.id.bSearch).setVisibility(View.GONE);
        }
    }

    //Create top bar buttons
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    // Show all foods or find specific one
    private void searchFoods() {
        //Read food file
        FileReader reader = new FileReader();
        InputStream myFile = getActivity().getResources().openRawResource(MYFILE);
        ruokalista = reader.readFile(myFile);
        FoodList.getInstance().addFoods(ruokalista);

        //Food you want to find
        EditText findText = getActivity().findViewById(R.id.ptFind);
        String haku = findText.getText().toString();
        String[] hakusanat = haku.split(" ");

        //Find specific foods in list
        copy = new ArrayList();
        for (int i = 0; i < FoodList.getInstance().getFoods().size(); i++) {
            int arvo = 0;
            for (int j = 0; j < hakusanat.length; j++) {
                if (FoodList.getInstance().getFoods().get(i).getName().toLowerCase().contains(hakusanat[j].toLowerCase())) {
                    arvo++;
                }
            }
            if (arvo == hakusanat.length) {
                copy.add(FoodList.getInstance().getFoods().get(i));
            }
        }

        //Set listView of foods
        lvFoods.setAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.food_list_layout,
                copy));
    }
}