package com.example.shadow.global.exception;

public class SignupEmailDuplicatedException extends Throwable {
    public SignupEmailDuplicatedException(String message){
        super(message);
    }
}
