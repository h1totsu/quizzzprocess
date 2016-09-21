package com.question;

import java.util.Arrays;

public class ImportError {
    private String msgKey;
    private String[] msgParams;

    public ImportError() {
    }

    public ImportError(String msgKey) {
        this.msgKey = msgKey;
        this.msgParams = new String[0];
    }

    public static ImportError createError(String msgKey, String... params) {
        ImportError error = new ImportError();

        error.setMessageKey(msgKey);
        error.setMessageParams(params);

        return error;
    }

    public String getMessageKey() {
        return msgKey;
    }

    public void setMessageKey(String msgKey) {
        this.msgKey = msgKey;
    }

    public String[] getMessageParams() {
        return msgParams;
    }

    public void setMessageParams(String[] msgParams) {
        if (msgParams == null) {
            this.msgParams = new String[0];
        } else {
            this.msgParams = Arrays.copyOf(msgParams, msgParams.length);
        }
    }

    @Override
    public String toString() {
        return "ImportError [msgKey=" + msgKey + ", msgParams=" + Arrays.toString(msgParams) + "]";
    }
}