package com.svf.edu.dto.api;

import java.io.Serializable;

/**
 * Created by stepanferubko
 */
public class AbstractResponse implements Serializable {
    protected boolean error;
    protected String errorCode;
    protected String errorMessage;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static AbstractResponse successResponse() {
        return new AbstractResponse();
    }

    public static AbstractResponse errorResponse(String errorCode, String errorMessage) {
        return errorResponse(new AbstractResponse(), errorCode, errorMessage);
    }

    public static <T extends AbstractResponse> T errorResponse(T instance, String errorCode, String errorMessage) {
        instance.setError(true);
        instance.setErrorCode(errorCode);
        instance.setErrorMessage(errorMessage);
        return instance;
    }

    @Override
    public String toString() {
        return "AbstractResponse{" +
                "error=" + error +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
