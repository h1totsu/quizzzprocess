package com.question.excel.reader;

import com.aspose.cells.Cell;
import com.aspose.cells.Worksheet;
import com.question.common.ImportError;
import com.question.excel.ExcelConstants;
import com.question.excel.importdata.ImportData;
import com.question.excel.importdata.InfoImportData;

import java.util.List;

public class InfoExcelReader extends ExcelReader {

    public InfoExcelReader(Worksheet sheet) {
        super(sheet, ExcelConstants.INFO_SHEET);
    }

    public InfoImportData getInfo(List<ImportError> errors) {
        InfoImportData infoImportData = null;
        Cell startCell = sheet.getCells().get(START_ROW_IDX, START_COLUMN_IDX);

        if (startCell != null) {
            verifyRowHeaders(errors,
                    ExcelConstants.INFO_ROW_HEADERS, startCell.getRow(), startCell.getColumn());
            if (errors.isEmpty()) {
                infoImportData = readData(startCell.getRow(), startCell.getColumn());
            }
        }

        return infoImportData;
    }

    private InfoImportData readData(int rowIdx, int colIdx) {
        InfoImportData infoImportData = new InfoImportData();

        ImportData name = getValue(ExcelConstants.INFO_ROW_HEADERS[0], rowIdx, colIdx + 1);
        ImportData description = getValue(ExcelConstants.INFO_ROW_HEADERS[1], rowIdx + 1, colIdx + 1);

        infoImportData.setName(name);
        infoImportData.setDescription(description);

        return infoImportData;
    }
}
