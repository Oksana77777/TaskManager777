package org.example;

public class Restore {
    private String task;
    private String operation;
    public Restore(String task, String operation) {
        this.task = task;
        this.operation = operation;
    }

    public String getTask() {
        return task;
    }

    public String getOperation() {
        return operation;
    }
}