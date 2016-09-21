package com.question;


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
    }
}
