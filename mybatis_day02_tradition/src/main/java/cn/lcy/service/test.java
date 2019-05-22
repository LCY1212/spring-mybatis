package cn.lcy.service;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(4);
        list.add(3);
        list.add(5);

        for (int i = 0; i < list.size(); i++) {
            list.add(i);
            System.out.println(list.get(i));
            list.remove(i);
        }

        System.out.println(list);
    }
}
