package com.andyron.bcdlj.c14;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVTest {
    public static void main(String[] args) throws IOException {
        CSVFormat format = CSVFormat.DEFAULT;
        FileReader reader = new FileReader("data/student.csv");
        try {
            for (CSVRecord record : format.parse(reader)) {
                int fieldNum = record.size();
                for (int i = 0; i < fieldNum; i++) {
                    System.out.println(record.get(i) + " ");
                }
                System.out.println();
            }
        } finally {
            reader.close();
        }


    }
}
