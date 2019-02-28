package tk.dmitriikorenev.main;

import tk.dmitriikorenev.hashtable.MyHashTable;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        MyHashTable<Integer> myHashTable = new MyHashTable<>(list);

        System.out.println(myHashTable);
        System.out.println(myHashTable.add(123));
        System.out.println(myHashTable);
        myHashTable.retainAll(list);
        System.out.println(" " + myHashTable);
        myHashTable.addAll(list);
        myHashTable.retainAll(list);
        System.out.println("  " + myHashTable);
        myHashTable.add(null);
        list = Arrays.asList(null, 1, 2, 3, 4);
        System.out.println("list" + list);
        System.out.println("hash table" + myHashTable);
        //list = new LinkedList<>();
        myHashTable.retainAll(list);
        //myHashTable.removeAll(list);
        System.out.println(myHashTable);
    }
}
