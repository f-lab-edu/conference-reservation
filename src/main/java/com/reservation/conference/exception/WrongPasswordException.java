package com.reservation.conference.exception;

public class WrongPasswordException extends RuntimeException {

    public WrongPasswordException(String msg) {
        super(msg);
    }

}
