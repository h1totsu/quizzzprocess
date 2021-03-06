package com.question;

import com.aspose.cells.Workbook;
import com.question.common.ErrorCodes;
import com.question.excel.ExcelConstants;
import com.question.excel.importdata.InfoImportData;
import com.question.excel.importdata.QuizPackageData;
import com.question.excel.reader.InfoExcelReader;
import com.question.validation.BasePackageValidator;
import com.question.validation.InfoValidator;
import org.apache.commons.lang.Validate;

import java.io.InputStream;
import java.util.List;

public class ImportProcess {


    public QuizPackageData importExcel(InputStream is, List<ImportError> errors) {
        //TODO properties
        Validate.notNull(is, "Input Excel stream is required");

        QuizPackageData packageInfo = null;

        try {
            Workbook workbook = new Workbook(is);

            if (BasePackageValidator.verifySheetNames(workbook, errors)) {
                InfoExcelReader infoExcelReader = new InfoExcelReader(workbook.getWorksheets()
                        .get(ExcelConstants.INFO_SHEET), ExcelConstants.INFO_SHEET);
                InfoImportData infoImportData = infoExcelReader.getInfo(errors);

                if (errors.isEmpty()) {
                    if (validateData(infoImportData, errors)) {
                        packageInfo = createPackage(infoImportData);
                    }
                }
            }

        } catch (Exception e) {
            errors.add(new ImportError(ErrorCodes.IMPORT_LOAD_WORKBOOK));
        }

        return packageInfo;
    }

    private QuizPackageData createPackage(
            InfoImportData infoImportData) {
        //TODO Need converter to convert to domain data(db usage)
        QuizPackageData packageData = new QuizPackageData();

        packageData.setInfoImportData(infoImportData);

        return packageData;
    }

    private boolean validateData(
            InfoImportData infoImportData,
            List<ImportError> errors) {
        int initErrorSize = errors.size();

        InfoValidator infoValidator = new InfoValidator();
        infoValidator.validateInfo(infoImportData, errors);

        return initErrorSize == errors.size();
    }
}
