package com.question.excel.importdata;

public class AnswerImportData implements BaseIndexedData {
  private ImportData key = new ImportData();
  private ImportData text = new ImportData();

  public AnswerImportData() {
  }

  @Override
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
}
