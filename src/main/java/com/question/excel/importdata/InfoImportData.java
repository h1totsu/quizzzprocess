package com.question.excel.importdata;

public class InfoImportData {
    private ImportData name = new ImportData();
    private ImportData description = new ImportData();

    public InfoImportData() {
    }

    public ImportData getName() {
        return name;
    }

    public void setName(ImportData name) {
        this.name = name;
    }

    public ImportData getDescription() {
        return description;
    }

    public void setDescription(ImportData description) {
        this.description = description;
    }
}
