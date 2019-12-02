package com.example.projectapp.ui.home;

import android.content.Intent;
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
import com.example.projectapp.ui.home.foodinfo.FoodInfoActivity;
import com.example.projectapp.ui.home.foodinfo.InfoFragment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class HomeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static final String EXTRA = "com.example.projectapp.ui.home.EXTRA";
    private static final String TAG = "MyLog";
    private static final int MYFILE = R.raw.resultset;
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

        Button searchButton = root.findViewById(R.id.bSearch);
        searchButton.setOnClickListener(this);
        lvFoods.setOnItemClickListener(this);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        return root;
    }

    //Button onClick events
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bSearch:
                Log.i(TAG, "search button toimii");
                try {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    Log.d(TAG, "keyboard not visible");
                }
                getActivity().findViewById(R.id.ptFind).clearFocus();

                if (et.getText().toString().isEmpty()) {
                    if (copy.size() < FoodList.getInstance().getFoods().size()) {
                        searchFoods();
                    }
                } else {
                    searchFoods();
                }
                break;
        }
    }

    //ArrayList item click listener
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Log.i(TAG,  "Item ID: " + copy.get(i).getId());
        searchWords = et.getText().toString();

        Intent infoIntent = new Intent(getActivity(), FoodInfoActivity.class);
        infoIntent.putExtra(EXTRA, copy.get(i).getId());
        startActivity(infoIntent);

        /*
        Bundle bundle = new Bundle();
        bundle.putInt("item", copy.get(i).getId());
        Fragment infoFragment = new InfoFragment();
        infoFragment.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment, infoFragment);
        ft.addToBackStack(null);
        ft.commit();
         */
    }

    //After view is created
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        et = getActivity().findViewById(R.id.ptFind);
        et.setText(searchWords);
        searchFoods();
        if(((MainActivity)getActivity()).getSearchVisible()) {
            et.setVisibility(View.VISIBLE);
            getView().findViewById(R.id.bSearch).setVisibility(View.VISIBLE);
        } else{
            et.setVisibility(View.GONE);
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
        // If FoodList is empty, create new list
        if(FoodList.getInstance().getFoods().size() == 0){
            FileReader reader = new FileReader();
            InputStream myFile = getActivity().getResources().openRawResource(MYFILE);
            ruokalista = reader.readFile(myFile);
            FoodList.getInstance().addFoods(ruokalista);
        }

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