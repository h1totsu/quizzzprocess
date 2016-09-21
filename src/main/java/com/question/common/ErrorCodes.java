package com.question.common;

public interface ErrorCodes {
    //TODO add all properties to file
    String FIELD_REQUIRED = "required.field.errorFormat";
    String FIELD_ALPHA_NUMERIC = "error.alphaNumeric";

    String IMPORT_LOAD_WORKBOOK = "import.error.loadworkbook";
    String IMPORT_EMPTY_WORKBOOK = "import.error.emptyworkbook";
    String IMPORT_MISSING_SHEET = "import.excel.missing.sheet";

    String INVALID_HEADER_VALUE = "invalid.header.value.errorFormat";
}
