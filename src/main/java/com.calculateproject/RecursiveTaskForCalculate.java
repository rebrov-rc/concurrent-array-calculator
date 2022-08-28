package com.calculateproject;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class RecursiveTaskForCalculate extends RecursiveTask<Long> {

    private List<Long> list;
    private final int MAX, START, END;

    public RecursiveTaskForCalculate(List<Long> list, int MAX, int START, int END){
        this.list = list;
        this.MAX = MAX;
        this.START = START;
        this.END = END;
    }

    private long calculate(){
        long counter = 0;
        for (int i = START; i < END; i++){
            counter += list.get(i);
        }
        return counter;
    }

    @Override
    protected Long compute() {


        if (this.END - this.START > MAX){
            final int MIDDLE = (this.END - this.START) / 2 + this.START;
            final var task1 = new RecursiveTaskForCalculate(list, MAX, START, MIDDLE);
            final var task2 = new RecursiveTaskForCalculate(list, MAX, MIDDLE, END);

            this.invokeAll(task1, task2);
            return task1.join() +  task2.join();
        }

        return this.calculate();
    }
}
