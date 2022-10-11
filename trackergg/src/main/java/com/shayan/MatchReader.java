package com.shayan;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;


import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;


public class MatchReader 
{
    private String fileName;
    public MatchReader(String fileName){
        this.fileName = fileName;
    }
    public ArrayList<Match> read() {
        try {
            ArrayList<Match> matchList = new ArrayList<Match>();
            InputStreamReader isr = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(fileName), "UTF-8");
            CSVReaderHeaderAware cr = new CSVReaderHeaderAware(isr);
            Map<String, String> nextLine;
            while ((nextLine = cr.readMap()) != null) {
                matchList.add(new Match(nextLine));
            };
            cr.close();
            return matchList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        System.out.println("FAILED");
        return null;

    }
}
