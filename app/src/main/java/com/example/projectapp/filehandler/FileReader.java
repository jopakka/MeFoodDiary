package com.example.projectapp.filehandler;

import android.util.Log;

import com.example.projectapp.food_stuff.Food;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that reads file
 */
public class FileReader {
    private static final String TAG = "MyLog";

    /**
     * Creates list from items it has read
     * @param fileName File that user wants to read
     * @return returns list that contains files information
     */
    public List readFile(InputStream fileName){
        try{
            List results = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileName));
            String line;
            int id = 0;
            reader.readLine(); //Skips first line
            while((line = reader.readLine()) != null){
                String[] lineSplit = line.split(";");
                String name = lineSplit[1];
                String energia = lineSplit[2];
                String rasva = lineSplit[3];
                String hiilihydraatti = lineSplit[4];
                String kuitu = lineSplit[17];
                String proteiini = lineSplit[5];
                String suola = lineSplit[39];

                Log.i(TAG, "");
                results.add(new Food(id, name, energia, rasva, hiilihydraatti, kuitu, proteiini, suola));
                id++;
            }
            Log.i(TAG, "Tiedostonluku toimii!!!!");
            return results;
        } catch (IOException ex) {
            Log.i(TAG, "Tiedoston " + fileName + " lukeminen epäonnistui!\n" + "Virhe: " + ex);
            return new ArrayList<>();
        }
    }
}
