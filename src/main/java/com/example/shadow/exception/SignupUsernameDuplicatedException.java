package com.example.shadow.exception;

public class SignupUsernameDuplicatedException extends Throwable {
    public SignupUsernameDuplicatedException(String message){
        super(message);
    }
}
