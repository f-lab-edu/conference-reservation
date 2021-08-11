package com.reservation.conference.exception;

public class FailToInsertException extends ServerException {

    public FailToInsertException(String msg) {
        super(msg);
    }

    public FailToInsertException(String msg, Throwable errorCode) {
        super(msg, errorCode);
    }
}
