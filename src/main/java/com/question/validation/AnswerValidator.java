package com.question.validation;

import com.question.common.ImportError;
import com.question.excel.importdata.AnswerImportData;

import java.util.List;

import static com.question.validation.PackageValidationHelper.checkAlphaNumeric;
import static com.question.validation.PackageValidationHelper.checkRequired;

public class AnswerValidator {
  public boolean validateAnswers(List<AnswerImportData> answers, List<ImportError> errors) {
    int initErrorSize = errors.size();

    for (AnswerImportData answer : answers) {
      if (checkRequired(answer.getKey(), errors)) {
        checkAlphaNumeric(answer.getKey(), errors);
      }

      checkRequired(answer.getText(), errors);
    }

    return initErrorSize == errors.size();
  }
}
