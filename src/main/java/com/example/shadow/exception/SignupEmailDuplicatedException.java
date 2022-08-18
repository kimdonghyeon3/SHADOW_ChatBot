package com.example.shadow.exception;

public class SignupEmailDuplicatedException extends Throwable {
    public SignupEmailDuplicatedException(String message){
        super(message);
    }
}
