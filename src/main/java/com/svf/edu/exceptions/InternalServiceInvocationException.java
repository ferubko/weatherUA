package com.svf.edu.exceptions;

import com.svf.edu.dto.api.AbstractResponse;

/**
 * Created by stepanferubko
 */
public class InternalServiceInvocationException extends Exception {
    private String errorMessage;

    public InternalServiceInvocationException(String errorCode, String errorMessage) {
        super(String.format("code='%s' message='%s'", errorCode, errorMessage));
        this.errorMessage = errorMessage;
    }

    public InternalServiceInvocationException(AbstractResponse response) {
        this(response.getErrorCode(), response.getErrorMessage());
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
