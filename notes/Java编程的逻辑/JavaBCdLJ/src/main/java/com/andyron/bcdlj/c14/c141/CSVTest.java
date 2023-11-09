package com.andyron.bcdlj.c14.c141;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVTest {
    public static void main(String[] args) throws IOException {

        writeCSV();
    }

    static void readCSV() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT;
        FileReader reader = new FileReader("data/student.csv");
        try {
            for (CSVRecord record : format.parse(reader)) {
                int fieldNum = record.size();
                for (int i = 0; i < fieldNum; i++) {
                    System.out.print(record.get(i) + " ");
                }
                System.out.println();
            }
        } finally {
            reader.close();
        }
    }

    static void writeCSV() throws IOException {
        CSVPrinter out = new CSVPrinter(new FileWriter("data/student2.csv"), CSVFormat.DEFAULT);
        out.printRecord("张三", 19, "看书，玩游戏，吃面");
        out.printRecord("王五", 17, "科幻；篮球");
        out.close();
    }
}
