package com.tim.tinder;


public class TinderException extends Exception {

    public TinderException(String message) {
        super(Thread.currentThread().getStackTrace().toString() + message);
    }
}
