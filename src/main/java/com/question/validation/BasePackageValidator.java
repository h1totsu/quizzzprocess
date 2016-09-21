package com.question.validation;

import com.aspose.cells.Workbook;
import com.aspose.cells.WorksheetCollection;
import com.question.ImportError;
import com.question.common.ErrorCodes;
import com.question.excel.ExcelConstants;
import org.apache.commons.lang.Validate;

import java.util.List;

public class BasePackageValidator {
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
