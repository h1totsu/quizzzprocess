package com.question.excel.reader;

import com.aspose.cells.Cell;
import com.aspose.cells.Worksheet;
import com.question.ImportError;
import com.question.excel.ExcelConstants;
import com.question.excel.importdata.AnswerImportData;
import com.question.excel.importdata.ImportData;
import com.question.excel.importdata.QuestionImportData;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class QuestionExcelReader extends ExcelReader {
  public QuestionExcelReader(Worksheet sheet) {
    super(sheet, ExcelConstants.QUESTIONS_SHEET);
  }

  public List<QuestionImportData> getQuestions(List<ImportError> errors) {
    List<QuestionImportData> questions = new ArrayList<>();
    List<Cell> questionLocations = new ArrayList<>();
    List<List<Cell>> answerLocations = new ArrayList<>();
    Cell startCell = sheet.getCells().get(START_ROW_IDX + 1, START_COLUMN_IDX);

    while (startCell != null && StringUtils.isNotBlank(startCell.getStringValue())) {
      int rowIdx = startCell.getRow();
      int colIdx = startCell.getColumn();

      verifyColumnHeaders(errors, ExcelConstants.QUESTION_COLUMN_HEADERS, rowIdx, colIdx);
      verifyRowHeaders(errors, ExcelConstants.QUESTION_ROW_HEADERS, rowIdx + 1,
          colIdx + ExcelConstants.QUESTION_COLUMN_HEADERS.length - 1);

      int nextAnswerCol = colIdx + ExcelConstants.QUESTION_COLUMN_HEADERS.length + 2;
      Cell startAnswerCell = cells.get(rowIdx, nextAnswerCol);

      List<Cell> answerLocation = new ArrayList<>();

      while (startAnswerCell != null && StringUtils.isNotBlank(startAnswerCell.getStringValue())) {
        verifyColumnHeaders(errors, ExcelConstants.ANSWER_COLUMN_HEADERS,
            rowIdx, nextAnswerCol);
        verifyRowHeaders(errors, ExcelConstants.ANSWER_ROW_HEADERS,
            rowIdx + 1, nextAnswerCol);

        answerLocation.add(cells.get(rowIdx + 1, nextAnswerCol + 1));

        nextAnswerCol += ExcelConstants.ANSWER_COLUMN_HEADERS.length + 1;
        startAnswerCell = cells.get(rowIdx, nextAnswerCol);
      }

      questionLocations.add(cells.get(rowIdx + 1, colIdx));
      answerLocations.add(answerLocation);

      int nextQuestionRow = rowIdx + ExcelConstants.QUESTION_ROW_HEADERS.length + 3;
      startCell = sheet.getCells().get(nextQuestionRow, colIdx);
    }

    if (errors.isEmpty()) {
      questions = readData(questionLocations, answerLocations);
    }

    return questions;
  }

  private List<QuestionImportData> readData(
      List<Cell> questionLocations,
      List<List<Cell>> answerLocations) {
    List<QuestionImportData> questions = new ArrayList<>();

    for (int i = 0; i < questionLocations.size(); i++) {
      Cell questionLocation = questionLocations.get(i);
      int rowIdx = questionLocation.getRow();
      int colIdx = questionLocation.getColumn();

      ImportData key = getValue(ExcelConstants.QUESTION_COLUMN_HEADERS[0], rowIdx, colIdx);
      ImportData text = getValue(ExcelConstants.QUESTION_COLUMN_HEADERS[1], rowIdx, colIdx + 1);
      ImportData description = getValue(ExcelConstants.QUESTION_COLUMN_HEADERS[2], rowIdx, colIdx + 2);
      ImportData displayType = getValue(ExcelConstants.QUESTION_ROW_HEADERS[0], rowIdx, colIdx + 4);
      ImportData correctAnswers = getValue(ExcelConstants.QUESTION_ROW_HEADERS[1], rowIdx + 1, colIdx + 4);

      QuestionImportData questionImportData = new QuestionImportData();
      questionImportData.setKey(key);
      questionImportData.setText(text);
      questionImportData.setDescription(description);
      questionImportData.setDisplayType(displayType);
      questionImportData.setCorrectAnswers(correctAnswers);
      questionImportData.setAnswers(readAnswerData(answerLocations.get(i)));

      questions.add(questionImportData);
    }

    return questions;
  }

  private List<AnswerImportData> readAnswerData(List<Cell> answerLocations) {
    List<AnswerImportData> answers = new ArrayList<>();

    for (Cell answerLocation : answerLocations) {
      int rowIdx = answerLocation.getRow();
      int colIdx = answerLocation.getColumn();

      ImportData key = getValue(ExcelConstants.ANSWER_ROW_HEADERS[0], rowIdx, colIdx);
      ImportData text = getValue(ExcelConstants.ANSWER_ROW_HEADERS[1], rowIdx + 1, colIdx);

      AnswerImportData answerImportData = new AnswerImportData();
      answerImportData.setKey(key);
      answerImportData.setText(text);

      answers.add(answerImportData);
    }

    return answers;
  }
}
