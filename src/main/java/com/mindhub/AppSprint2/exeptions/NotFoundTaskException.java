package com.mindhub.AppSprint2.exeptions;

public class NotFoundTaskException extends RuntimeException {

    public NotFoundTaskException(String message) {
        super(message);
    }

}
