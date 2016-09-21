package com.question.validation;

import com.question.ImportError;
import com.question.common.ErrorCodes;
import com.question.excel.importdata.ImportData;

import java.util.List;
import java.util.regex.Pattern;

public abstract class QuizValidationHelper {

    private static final Pattern ALPHA_NUMERIC_SPACE_REGEX = Pattern.compile("[a-zA-Z0-9\\s]*");

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

    public static boolean checkAlphaNumericSpace(ImportData data, List<ImportError> errors) {
        boolean isValid = true;

        if (data.isEmpty() || !ALPHA_NUMERIC_SPACE_REGEX.matcher(data.getValue()).matches()) {
            isValid = false;

            ImportError error = new ImportError();
            error.setMessageKey(ErrorCodes.FIELD_ALPHA_NUMERIC);
            error.setMessageParams(new String[]{data.getName(), data.getSource(), data.getValue()});
            errors.add(error);
        }

        return isValid;
    }
}
