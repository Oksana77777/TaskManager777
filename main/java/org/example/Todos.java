package org.example;

import java.util.*;

public class Todos {
    public Set<String> taskTreeSet;
    protected List<Restore> restoreList;

    public Todos() {
        taskTreeSet = new TreeSet<>();
        restoreList = new ArrayList<>();
    }

    public void addTask(String task) {
        if (taskTreeSet.size() > 6) return;
        taskTreeSet.add(task);
        restoreList.add(new Restore(task, "ADD"));
    }

    public void removeTask(String task) {
        taskTreeSet.remove(task);
        restoreList.add(new Restore(task, "REMOVE"));
    }

    public void restore() {
        String lastOperation = restoreList.get(restoreList.size() - 1).getOperation();
        switch (lastOperation) {
            case "ADD":
                taskTreeSet.remove(restoreList.get(restoreList.size() - 1).getTask());
                restoreList.remove(restoreList.size() - 1);
                break;
            case "REMOVE":
                taskTreeSet.add(restoreList.get(restoreList.size() - 1).getTask());
                restoreList.add(new Restore(restoreList.get(restoreList.size() - 1).getTask(),
                        restoreList.get(restoreList.size() - 1).getOperation()));
                break;
        }
    }

    public String getAllTasks() {
        Optional<String> optionalS = taskTreeSet.stream()
                .reduce((x, y) -> x + " " + y);
        return optionalS.get();
    }

}