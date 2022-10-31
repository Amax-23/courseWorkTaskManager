package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Todos {
    protected List<String> list;
    protected int maxSizeList = 7;
    protected IncomingTask incomingTask;

    public Todos() {
        this.list = new ArrayList<>();
        this.incomingTask = new IncomingTask();
    }

    public void readIncomingMsg(String jsonFile) {
        try {
            incomingTask = new Gson().fromJson(new FileReader(jsonFile), IncomingTask.class);
            if (incomingTask.getType().equals("ADD")) {
                addTask(incomingTask.getTask());
            } else if (incomingTask.getType().equals("REMOVE")) {
                removeTask(incomingTask.getTask());
            } else if (incomingTask.getType().equals("RESTORE")) {
                restoreTask();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void restoreTask() {
        if (!TodoServer.getLog().isEmpty()) {
            TodoServer.getLog().remove(TodoServer.getLog().getLast());
            list = TodoServer.getLog().getLast();
        }
    }

    public void addTask(String task) {
        if (getList().isEmpty() || getList().size() < maxSizeList) {
            if (!getList().contains(incomingTask.getTask())) {
                getList().add(task);
                TodoServer.getLog().add(new ArrayList<>(getList()));
            }
        }
    }

    public void removeTask(String task) {
        if (getList().contains(task)) {
            getList().remove(task);
            TodoServer.getLog().add(new ArrayList<>(getList()));
        }
    }

    public String getAllTasks() {
        List<String> listOut = new ArrayList<>(getList());
        Collections.sort(listOut);
        return listOut.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();
    }

    public List<String> getList() {
        return list;
    }
}
