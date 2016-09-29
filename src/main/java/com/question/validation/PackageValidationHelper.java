package com.question.validation;

import com.aspose.cells.Workbook;
import com.aspose.cells.WorksheetCollection;
import com.question.common.ErrorCodes;
import com.question.common.ImportError;
import com.question.excel.ExcelConstants;
import com.question.excel.importdata.AnswerImportData;
import com.question.excel.importdata.BaseIndexedData;
import com.question.excel.importdata.ImportData;
import org.apache.commons.lang.Validate;

import java.util.*;
import java.util.regex.Pattern;

public abstract class PackageValidationHelper {

  private static final Pattern ALPHA_NUMERIC_REGEX = Pattern.compile("[a-zA-Z0-9]*");
  private static final String SPLIT_ANSWERS_REGEX = ",";

  public static boolean checkRequired(ImportData data, List<ImportError> errors) {
    boolean isValid = true;

    if (data.isEmpty()) {
      isValid = false;

      ImportError error = new ImportError();
      error.setMessageKey(ErrorCodes.FIELD_REQUIRED);
      error.setMessageParams(new String[]{data.getName(), data.getSource()});
      errors.add(error);
    }

    return isValid;
  }

  public static boolean checkAlphaNumeric(ImportData data, List<ImportError> errors) {
    boolean isValid = true;

    if (data.isEmpty() || !ALPHA_NUMERIC_REGEX.matcher(data.getValue()).matches()) {
      isValid = false;

      ImportError error = new ImportError();
      error.setMessageKey(ErrorCodes.FIELD_ALPHA_NUMERIC);
      error.setMessageParams(new String[]{data.getName(), data.getSource(), data.getValue()});
      errors.add(error);
    }

    return isValid;
  }

  public static boolean checkDisplayType(ImportData displayType, List<ImportError> errors) {
    boolean isValid = true;
    String val = displayType.getValue();

    if (!Arrays.asList(ExcelConstants.QUESTION_DISPLAY_TYPES).contains(val)) {
      isValid = false;

      ImportError error = new ImportError();
      error.setMessageKey(ErrorCodes.INVALID_DISPLAY_TYPE);
      error.setMessageParams(new String[]{displayType.getName(), displayType.getSource(), val});
      errors.add(error);
    }

    return isValid;
  }

  public static boolean checkAnswersExist(ImportData correctAnswers,
                                          List<AnswerImportData> answers, List<ImportError> errors) {
    boolean isValid = true;
    String[] answersData = correctAnswers.getValue().split(SPLIT_ANSWERS_REGEX);

    for (String correctAnswer : answersData) {
      Optional<AnswerImportData> answer = answers.stream()
          .filter(a -> a.getKey().getValue().equals(correctAnswer)).findFirst();
      if (!answer.isPresent()) {
        isValid = false;

        ImportError error = new ImportError();
        error.setMessageKey(ErrorCodes.INVALID_REFERENCE_ANSWER);
        error.setMessageParams(new String[]{correctAnswers.getName(), correctAnswers.getSource(),
            correctAnswer});
        errors.add(error);
      }
    }

    return isValid;
  }

    public static boolean checkUnique(List<? extends BaseIndexedData> items, List<ImportError> errors) {
    boolean isValid = true;

        Set<ImportData> checkSet = new HashSet<>();

    for (BaseIndexedData item : items) {
        if (!checkSet.add(item.getKey())) {
        isValid = false;

        ImportError error = new ImportError();
        error.setMessageKey(ErrorCodes.INVALID_DUPLICATED_VALUE);
            error.setMessageParams(new String[]{item.getKey().getName(), item.getKey().getSource(),
                    item.getKey().getValue()});
        errors.add(error);
      }
    }

    return isValid;
  }

  public static boolean verifySheetNames(Workbook workbook, List<ImportError> errors) {
    //TODO better move to properties
    Validate.notNull(workbook, "Excel workbook is required");
    Validate.notNull(errors, "Error list is required");

    int initErrorSize = errors.size();
    WorksheetCollection worksheets = workbook.getWorksheets();

    if (worksheets == null || worksheets.getCount() <= 0) {
      errors.add(new ImportError(ErrorCodes.IMPORT_EMPTY_WORKBOOK));
    } else {
      if (workbook.getWorksheets().get(ExcelConstants.INFO_SHEET) == null) {
        errors.add(ImportError
            .createError(ErrorCodes.IMPORT_MISSING_SHEET, ExcelConstants.INFO_SHEET));
      }

      if (workbook.getWorksheets().get(ExcelConstants.QUESTIONS_SHEET) == null) {
        errors.add(ImportError
            .createError(ErrorCodes.IMPORT_MISSING_SHEET, ExcelConstants.QUESTIONS_SHEET));
      }

      //TODO add other sheets
    }

    return errors.size() == initErrorSize;
  }
}
