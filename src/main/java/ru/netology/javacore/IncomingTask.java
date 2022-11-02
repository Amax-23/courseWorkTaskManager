package ru.netology.javacore;

import java.io.Serializable;


public class IncomingTask implements Serializable {
    private String type;
    private String task;

    public String getType() {
        return type;
    }

    public String getTask() {
        return task;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
