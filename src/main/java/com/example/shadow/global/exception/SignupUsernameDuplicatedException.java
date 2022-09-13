package com.example.shadow.global.exception;

public class SignupUsernameDuplicatedException extends Throwable {
    public SignupUsernameDuplicatedException(String message){
        super(message);
    }
}
