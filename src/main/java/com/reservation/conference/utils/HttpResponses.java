package com.reservation.conference.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpResponses {
    // 200
    public static final ResponseEntity RESPONSE_OK = new ResponseEntity(HttpStatus.OK);
    // 201
    public static final ResponseEntity RESPONSE_CREATED = new ResponseEntity(HttpStatus.CREATED);
    // 400
    public static final ResponseEntity RESPONSE_BAD_REQUEST = new ResponseEntity(HttpStatus.BAD_REQUEST);
    // 401
    public static final ResponseEntity RESPONSE_UNAUTHORIZED = new ResponseEntity(HttpStatus.UNAUTHORIZED);
    // 404
    public static final ResponseEntity RESPONSE_NOT_FOUND = new ResponseEntity(HttpStatus.NOT_FOUND);
    // 409
    public static final ResponseEntity RESPONSE_CONFLICT = new ResponseEntity(HttpStatus.CONFLICT);
}
