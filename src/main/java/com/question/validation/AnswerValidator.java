package com.question.validation;

import com.question.common.ImportError;
import com.question.excel.importdata.AnswerImportData;

import java.util.List;

public class AnswerValidator {
  public boolean validateAnswers(List<AnswerImportData> answers, List<ImportError> errors) {
    int initErrorSize = errors.size();

    return initErrorSize == errors.size();
  }
}
