package ru.netology.javacore;

import com.google.gson.Gson;

import java.util.*;


public class Todos {
    private List<String> list;
    private int maxSizeList;
    protected IncomingTask incomingTask;

    public Todos() {
        this.list = new ArrayList<>();
        this.incomingTask = new IncomingTask();
        this.maxSizeList = 7;
    }

    public void readIncomingMsg(String task) {
        incomingTask = new Gson().fromJson(task, IncomingTask.class);
        if (incomingTask.getType().equals("ADD")) {
            addTask(incomingTask.getTask());
        } else if (incomingTask.getType().equals("REMOVE")) {
            removeTask(incomingTask.getTask());
        } else if (incomingTask.getType().equals("RESTORE")) {
            restoreTask();
        }
    }

    public void restoreTask() {
        if (!TodoServer.getLog().isEmpty()) {
            TodoServer.getLog().remove(TodoServer.getLog().getLast());
            list = TodoServer.getLog().getLast();
        } else {
            throw new NoSuchElementException("Нечего восстанавливать!");
        }
    }

    public void addTask(String task) {
        if (getList().isEmpty() || getList().size() < maxSizeList) {
            if (!getList().contains(task)) {
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

    public void setMaxSizeList(int maxSizeList) {
        this.maxSizeList = maxSizeList;
    }

    public int getMaxSizeList() {
        return maxSizeList;
    }
}
