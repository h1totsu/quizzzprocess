package com.question.validation;

import com.question.common.ImportError;
import com.question.excel.importdata.QuestionImportData;

import java.util.List;

import static com.question.validation.PackageValidationHelper.*;

public class QuestionValidator {
  public boolean validateQuestions(List<QuestionImportData> questions, List<ImportError> errors) {
    int initErrorSize = errors.size();


    for (QuestionImportData questionImportData : questions) {

    }

    return initErrorSize == errors.size();
  }

  public boolean validateQuestion(QuestionImportData questionImportData, List<ImportError> errors) {
    int initErrorSize = errors.size();

    if (checkRequired(questionImportData.getKey(), errors)) {
      checkAlphaNumeric(questionImportData.getKey(), errors);
    }

    checkRequired(questionImportData.getText(), errors);

    if (checkRequired(questionImportData.getDisplayType(), errors)) {
      checkDisplayType(questionImportData.getDisplayType(), errors);
    }

    return initErrorSize == errors.size();
  }
}
