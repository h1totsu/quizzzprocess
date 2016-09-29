package com.question.validation;

import com.question.common.ImportError;
import com.question.excel.importdata.QuestionImportData;

import java.util.List;

import static com.question.validation.PackageValidationHelper.*;

public class QuestionValidator {
  public boolean validateQuestions(List<QuestionImportData> questions, List<ImportError> errors) {
    int initErrorSize = errors.size();

    if (checkUnique(questions, errors)) {
      for (QuestionImportData questionImportData : questions) {
        if (checkRequired(questionImportData.getKey(), errors)) {
          checkAlphaNumeric(questionImportData.getKey(), errors);
        }

        checkRequired(questionImportData.getText(), errors);

        if (checkRequired(questionImportData.getDisplayType(), errors)) {
          checkDisplayType(questionImportData.getDisplayType(), errors);
        }

        if (checkRequired(questionImportData.getCorrectAnswers(), errors)) {
          checkAnswersExist(questionImportData.getCorrectAnswers(),
                  questionImportData.getAnswers(), errors);
        }

        AnswerValidator ansValidator = new AnswerValidator();

        ansValidator.validateAnswers(questionImportData.getAnswers(), errors);
      }
    }

    return initErrorSize == errors.size();
  }
}
