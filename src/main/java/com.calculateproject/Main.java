package com.calculateproject;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;

public class Main {

    private static long executionTime(Consumer<List<Long>> consumer, List<Long> arg){

        long start = System.currentTimeMillis();
        consumer.accept(arg);
        long finish = System.currentTimeMillis() - start;

        return finish;
    }

    private static void calculate(List<Long> list){
        long counter = 0;
        for (long l : list){
            counter += l;
        }
        System.out.println(counter);
    }


    public static void main(String[] args) throws InterruptedException {

        List<Long> list = ObjectFactory.get(15_000_000);
        final int MAX = 2_000_000, start = 0, end = list.size();

        long result = executionTime(x -> calculate(x), list);
        System.out.println("single thread: " + result);


        ForkJoinPool pool = new ForkJoinPool();

        long result2 = executionTime(x -> {
            long res = pool.invoke(new RecursiveTaskForCalculate(x, MAX, start, end));
            System.out.println(res);
        }, list);

        System.out.println("multi thread: " + result2);
        pool.shutdown();
    }
}
