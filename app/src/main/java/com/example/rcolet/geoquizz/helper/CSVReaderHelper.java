package com.example.rcolet.geoquizz.helper;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class CSVReaderHelper {
    public static ArrayList<String[]> load(InputStreamReader isr) {
        //String csvFile = path;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        ArrayList<String[]> result = new ArrayList();

        try {

            br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {

                // use comma as separator
                result.add(line.split(cvsSplitBy));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "error CSVReaderHelper: " + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}