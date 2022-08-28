package com.calculateproject;

import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {
    public static  List<Long> get(int amount){

        List<Long> list = new ArrayList<>();
        for (long i = 0; i < amount; i++) {
            list.add(i + 1);
        }

        return list;
    }
}
