package com.question.excel.reader;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Worksheet;
import com.question.ImportError;
import com.question.common.ErrorCodes;
import com.question.excel.importdata.ImportData;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import java.util.List;

public class ExcelReader {
    protected final int START_ROW_IDX = 1;
    protected final int START_COLUMN_IDX = 1;

    protected Worksheet sheet;
    protected Cells cells;
    protected String sheetName;

    public ExcelReader() {
    }

    public ExcelReader(Worksheet sheet, String sheetName) {
        //TODO to properties
        Validate.notNull(sheet, "Sheet is required");
        Validate.notNull(sheetName, "Sheet name is required");

        this.sheet = sheet;
        this.cells = sheet.getCells();
        this.sheetName = sheetName;
    }

    public boolean verifyRowHeaders(List<ImportError> errors, String[] headers, int rowIdx, int colIdx) {
        int initialSize = errors.size();

        for (int i = 0; i < headers.length; i++) {
            Cell cell = cells.get(rowIdx + i, colIdx);

            if (!StringUtils.equalsIgnoreCase(headers[i], cell.getStringValue())) {
                errors.add(ImportError.createError(ErrorCodes.INVALID_HEADER_VALUE, headers[i],
                        sheet.getName() + ":" + cell.getName()));
            }
        }

        return (initialSize == errors.size());
    }

    public boolean verifyColumnHeaders(List<ImportError> errors, String[] headers, int rowIdx, int colIdx) {
        int initialSize = errors.size();

        for (int i = 0; i < headers.length; ++i) {
            Cell cell = cells.get(rowIdx, colIdx + i);

            if (!StringUtils.equalsIgnoreCase(headers[i], cell.getStringValue())) {
                errors.add(ImportError.createError(ErrorCodes.INVALID_HEADER_VALUE, headers[i],
                    sheet.getName() + ":" + cell.getName()));
            }
        }

        return (initialSize == errors.size());
    }

    public ImportData getValue(String fieldName, int rowIdx, int colIdx) {
        Cell cell = cells.get(rowIdx, colIdx);

        return new ImportData(fieldName, StringUtils.trimToNull(cell.getStringValue()), sheetName + ":"
                + cell.getName());
    }
}
