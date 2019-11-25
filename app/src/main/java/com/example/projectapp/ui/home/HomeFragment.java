package com.example.projectapp.ui.home;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectapp.filehandler.FileReader;
import com.example.projectapp.food_stuff.Food;
import com.example.projectapp.food_stuff.FoodList;
import com.example.projectapp.R;
import com.example.projectapp.ui.info.InfoFragment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {

    public static final int MYFILE = R.raw.resultset;

    private HomeViewModel homeViewModel;
    private List ruokalista;
    private ListView lvFoods;
    private ArrayList<Food> copy;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        lvFoods = root.findViewById(R.id.lvFoods);
        setHasOptionsMenu(true);

        Button searchButton = root.findViewById(R.id.bSearch);
        searchButton.setOnClickListener(this);

        //Show food information
        lvFoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("testilog", "home " + i);

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
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.bSearch:
                Log.i("testilog", "search button toimii");
                searchFoods();
                break;
        }
    }

    //First list creation
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        searchFoods();
    }

    //Create top bar buttons
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
    }

    //Options menu item click events
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:
                Log.i("testilog", "action bar info toimii");
                return true;

            case R.id.action_search:
                Log.i("testilog", "action bar search toimii");
                return true;
        }
        return super.onOptionsItemSelected(item);
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
        //ArrayList<Food> copyList = new ArrayList<>(copy);

        //Set listView of foods
        lvFoods.setAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.food_list_layout,
                copy));
    }
}