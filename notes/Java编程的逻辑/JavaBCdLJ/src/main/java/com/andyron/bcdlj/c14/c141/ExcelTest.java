package com.andyron.bcdlj.c14.c141;

import com.andyron.bcdlj.c14.Student;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelTest {
    public static void main(String[] args) throws Exception {
//        List<Student> students = new ArrayList<>();
//        students.add(new Student("andy", 11, "68"));
//        students.add(new Student("lucy", 15, "A"));
//        students.add(new Student("jack", 18, "S++"));
//        saveAsExcel(students);

        for (Student student : readAsExcel()) {
            System.out.println(student.toString());
        }

    }

    public static void saveAsExcel(List<Student> list) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        for (int i = 0; i < list.size(); i++) {
            Student student = list.get(i);
            HSSFRow row = sheet.createRow(i);
            row.createCell(0).setCellValue(student.getName());
            row.createCell(1).setCellValue(student.getAge());
            row.createCell(2).setCellValue(student.getScore());
        }
        FileOutputStream out = new FileOutputStream("data/student.xls");
        wb.write(out);
        out.close();
        wb.close();
    }

    public static List<Student> readAsExcel() throws Exception   {
        Workbook wb = WorkbookFactory.create(new File("data/student.xls"));
        List<Student> list = new ArrayList<Student>();
        for(Sheet sheet : wb){
            for(Row row : sheet){
                String name = row.getCell(0).getStringCellValue();
                int age = (int)row.getCell(1).getNumericCellValue();
                String score = row.getCell(2).getStringCellValue();
                list.add(new Student(name, age, score));
            }
        }
        wb.close();
        return list;
    }
}
