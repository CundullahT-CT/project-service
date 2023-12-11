package com.cydeo.exception;

public class RelatedTasksCanNotBeDeleted extends RuntimeException {

    public RelatedTasksCanNotBeDeleted(String message) {
        super(message);
    }

}
