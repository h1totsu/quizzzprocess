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

    public Cell findFirstNonEmpty(int rowIdxStart, int colIdxStart) {
        Cell firstCell = null;
        int rowIdx = rowIdxStart;
        int colIdx = colIdxStart;

        while (rowIdx <= cells.getMaxRow() && (firstCell == null)) {
            colIdx = colIdxStart;

            while (colIdx <= cells.getMaxColumn() && (firstCell == null)) {
                Cell cell = cells.get(rowIdx, colIdx);

                if (cell != null) {
                    String cellVal = cell.getStringValue();

                    if (StringUtils.isNotBlank(cellVal)) {
                        firstCell = cell;
                    } else {
                        colIdx++;
                    }
                }
            }

            rowIdx++;
        }

        return firstCell;
    }

    public boolean verifyRowHeaders(List<ImportError> errors, String[] headers, int rowIdx, int colIdx) {
        int initialSize = errors.size();

        for (int i = 0; i < headers.length; ++i) {
            Cell cell = cells.get(rowIdx + i, colIdx);

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
