package com.example.projectapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader {
    private static final FileReader ourInstance = new FileReader();

    public static FileReader getInstance() {
        return ourInstance;
    }

    public List readFile(InputStream fileName){
        try{
            List results = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileName));
            String line;
            reader.readLine(); //Skips first line
            while((line = reader.readLine()) != null){
                String[] lineSplit = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                String name = lineSplit[1];
                name = name.replace("\"", "");

                Log.i("FileReader", "");
                results.add(new Food(name));
            }
            Log.i("FileReader", "Toimii!!!!");
            return results;
        } catch (IOException ex) {
            Log.i("FileReader", "Tiedoston " + fileName + " lukeminen epäonnistui!\n" + "Virhe: " + ex);
            return new ArrayList<>();
        }
    }
}
