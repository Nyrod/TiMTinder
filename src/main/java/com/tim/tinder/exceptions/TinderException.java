package com.tim.tinder.exceptions;


public class TinderException extends Exception {

    public TinderException(String message) {
        super(Thread.currentThread().getStackTrace().toString() + message);
    }
}
