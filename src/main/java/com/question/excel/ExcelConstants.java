package com.question.excel;

public interface ExcelConstants {
    String INFO_SHEET = "Info";
    String QUESTIONS_SHEET = "Questions";
    String SETTINGS_SHEET = "Settings";

    String[] INFO_ROW_HEADERS = {"Name", "Description"};

    String[] QUESTION_COLUMN_HEADERS = {"Key", "Text", "Description", "Attributes"};
    String[] QUESTION_ROW_HEADERS = {"Display Type", "Correct Answers"};
    String[] ANSWER_ROW_HEADERS = {"Key", "Text"};
    String[] ANSWER_COLUMN_HEADERS = {"Answer Attributes"};
}
