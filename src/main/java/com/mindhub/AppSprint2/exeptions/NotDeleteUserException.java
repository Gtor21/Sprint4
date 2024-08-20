package com.mindhub.AppSprint2.exeptions;

public class NotDeleteUserException extends RuntimeException{
    public NotDeleteUserException(String message) {
        super(message);
    }
}
