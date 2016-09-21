package com.question.excel.reader;

import com.aspose.cells.Cell;
import com.aspose.cells.Worksheet;
import com.question.ImportError;
import com.question.excel.ExcelConstants;
import com.question.excel.importdata.ImportData;
import com.question.excel.importdata.InfoImportData;

import java.util.List;

public class InfoExcelReader extends ExcelReader {
    private final int START_ROW_IDX = 1;
    private final int START_COLUMN_IDX = 1;

    public InfoExcelReader(Worksheet sheet, String sheetName) {
        super(sheet, sheetName);
    }

    public InfoImportData getInfo(List<ImportError> errors) {
        InfoImportData infoData = null;
        Cell startCell = sheet.getCells().get(START_ROW_IDX, START_COLUMN_IDX);

        if (startCell != null) {
            verifyRowHeaders(errors,
                    ExcelConstants.INFO_ROW_HEADERS, startCell.getRow(), startCell.getColumn());
            if (errors.isEmpty()) {
                infoData = readData(startCell.getRow(), startCell.getColumn());
            }
        }

        return infoData;
    }

    private InfoImportData readData(int rowIdx, int colIdx) {
        InfoImportData infoData = new InfoImportData();

        ImportData name = getValue(ExcelConstants.INFO_ROW_HEADERS[0], rowIdx, colIdx + 1);
        ImportData description = getValue(ExcelConstants.INFO_ROW_HEADERS[1], rowIdx + 1, colIdx + 1);

        infoData.setName(name);
        infoData.setDescription(description);

        return infoData;
    }
}
