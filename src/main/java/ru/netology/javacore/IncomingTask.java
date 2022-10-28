package ru.netology.javacore;

import java.io.Serializable;


public class IncomingTask implements Serializable {
    protected String type;
    protected String task;

//    public IncomingTask() {
//        this.type = type;
//        this.task = task;
//
//    }

    public String getType() {
        return type;
    }

    public String getTask() {
        return task;
    }

}
