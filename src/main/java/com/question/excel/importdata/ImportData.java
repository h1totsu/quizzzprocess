package com.question.excel.importdata;

import org.apache.commons.lang.StringUtils;

public class ImportData {
    private String name;

    private String value;

    private String source;

    public ImportData() {
    }

    public ImportData(String name, String value, String source) {
        this.name = name;
        this.value = value;
        this.source = source;
    }

    public boolean isEmpty() {
        return StringUtils.isEmpty(value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
