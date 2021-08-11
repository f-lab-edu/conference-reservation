package com.reservation.conference.exception;

public class ServerException extends RuntimeException {

    public ServerException(String msg) {
        super(msg);
    }

    public ServerException(String msg, Throwable errorCode) {
        super(msg, errorCode);
    }

}
