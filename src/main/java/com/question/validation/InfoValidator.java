package com.question.validation;

import com.question.ImportError;
import com.question.excel.importdata.InfoImportData;

import java.util.List;

import static com.question.validation.QuizValidationHelper.checkRequired;

public class InfoValidator {
    public boolean validateInfo(InfoImportData infoData, List<ImportError> errors) {
        int initErrorSize = errors.size();

        checkRequired(infoData.getName(), errors);

        return initErrorSize == errors.size();
    }
}
