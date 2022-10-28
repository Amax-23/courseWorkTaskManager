package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Todos {
    protected List<String> list;
    protected int maxSizeList = 7;
    protected IncomingTask incomingTask;
    protected List<List> log;

    public Todos() {
        this.list = new ArrayList<>();
        this.log = new ArrayList<>();
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
            } else throw new IllegalArgumentException(
            "Список пустой или в полученном файле указаны данные не предусмотренные программой");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.add(list);
    }

    public void restoreTask() {
//        if (!list.isEmpty()) {
//            list.remove(list.size() - 1);
//        }
        System.out.println(log);
        if (!log.isEmpty()) {
            log.remove(log.size() - 1);
            list = log.get(log.size() - 1);
        }
    }

    public void addTask(String task) {
        if (list.isEmpty() || list.size() < maxSizeList) {
            if (!list.contains(incomingTask.getTask())) {
                list.add(task);
                //log.add(incomingTask);
            }
        }
    }

    public void removeTask(String task) {
        if (list.contains(incomingTask.getTask())) {
            list.remove(task);
            //log.add(incomingTask);
        }
    }

    public String getAllTasks() {
        List<String> listOut = new ArrayList<>(list);
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
}
