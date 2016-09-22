package com.question.excel.importdata;

import java.util.ArrayList;
import java.util.List;

public class QuestionImportData {
  private ImportData key = new ImportData();
  private ImportData text = new ImportData();
  private ImportData description = new ImportData();
  private ImportData displayType = new ImportData();
  private ImportData correctAnswers = new ImportData();
  private List<AnswerImportData> answers = new ArrayList<>();

  public QuestionImportData() {
  }

  public ImportData getKey() {
    return key;
  }

  public void setKey(ImportData key) {
    this.key = key;
  }

  public ImportData getText() {
    return text;
  }

  public void setText(ImportData text) {
    this.text = text;
  }

  public ImportData getDescription() {
    return description;
  }

  public void setDescription(ImportData description) {
    this.description = description;
  }

  public ImportData getDisplayType() {
    return displayType;
  }

  public void setDisplayType(ImportData displayType) {
    this.displayType = displayType;
  }

  public ImportData getCorrectAnswers() {
    return correctAnswers;
  }

  public void setCorrectAnswers(ImportData correctAnswers) {
    this.correctAnswers = correctAnswers;
  }

  public List<AnswerImportData> getAnswers() {
    return answers;
  }

  public void setAnswers(List<AnswerImportData> answers) {
    this.answers = answers;
  }
}
