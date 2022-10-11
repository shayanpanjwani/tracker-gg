package com.shayan;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.shayan.Match.Result;
import com.shayan.Match.Role;

public class App  extends JFrame{

    Comparator<String> strComp = new Comparator<String>() {
        @Override
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    };
    Comparator<Integer> intComp = new Comparator<Integer>() {
        @Override
        public int compare(Integer i1, Integer i2) {
            return i1-i2;
        }
    };

    public App() {
        String fileName = "match_data_full.csv";
        MatchReader mr = new MatchReader(fileName);
        ArrayList<Match> matches = mr.read();
        String[] fields = matches.get(0).getFields();
        //     private String[] fields = {0"Champion", 1"Role", 2"Kills", 3"Deaths", 4"Assists", 5"Gold", 6"CS", 7"CS/M", 8"Duration", 9"Date", "Result"};

        DefaultTableModel tableModel = new DefaultTableModel(fields, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch(column) {
                    case 0, 8, 9: return String.class; 
                    case 2, 3, 4, 5, 6: return Integer.class;
                    case 7: return Double.class;
                    case 1: return Role.class;
                    case 10: return Result.class;
                    default: return String.class;
                }
            }
        };

        JTable table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(650, 300) );
        table.setFillsViewportHeight(true);
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        for (Match m : matches) {
            tableModel.addRow(m.getRowData());           
        }

        table.setAutoCreateRowSorter(true);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        table.setSize(800, 400);
        table.getTableHeader().setReorderingAllowed(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        
        new App();
    }
}
