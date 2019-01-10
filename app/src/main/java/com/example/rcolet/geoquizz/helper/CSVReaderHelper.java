package com.example.rcolet.geoquizz.helper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReaderHelper {
    public static ArrayList<String[]> load(String path) {
        String csvFile = path;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        ArrayList<String[]> result = new ArrayList();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                result.add(line.split(cvsSplitBy));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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