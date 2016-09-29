package com.question;


import com.question.common.ImportError;
import com.question.excel.importdata.ImportData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ImportProcess importProcess = new ImportProcess();
        List<ImportError> errors = new ArrayList<>();
        try {
            importProcess.importExcel(new FileInputStream("quizWorkbook.xlsx"), errors);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ImportData data1 = new ImportData();
        ImportData data2 = new ImportData();

        data1.setName("C1");
        data1.setValue("Value1");

        data2.setName("C1");
        data2.setValue("Value1");

        System.out.println(data1.equals(data2));
    }
}
