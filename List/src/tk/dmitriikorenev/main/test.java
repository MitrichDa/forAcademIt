package tk.dmitriikorenev.main;

import tk.dmitriikorenev.listclasses.MyLinkedList;

public class test {
    public static void main(String[] args) {
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        linkedList.add(0, "Первый");
        linkedList.add(1, "Второй");
        linkedList.add(2, "Третий");

        System.out.println(linkedList.getSize());
        System.out.println(linkedList.getFirst());
        linkedList.set(0, "Новый первый");
        linkedList.set(1, "Новый второй");

        System.out.println();
        for (int i = 0; i < linkedList.getSize(); i++) {
            System.out.println(linkedList.get(i));
        }

        linkedList.remove(2);
        System.out.println();
        for (int i = 0; i < linkedList.getSize(); i++) {
            System.out.println(linkedList.get(i));
        }
        linkedList.add(2, "Номер три");

        linkedList.remove("Новый второй");
        System.out.println();
        for (int i = 0; i < linkedList.getSize(); i++) {
            System.out.println(linkedList.get(i));
        }
        linkedList.add(1, "Номер 2");

        System.out.println();
        linkedList.removeFirst();
        for (int i = 0; i < linkedList.getSize(); i++) {
            System.out.println(linkedList.get(i));
        }
        linkedList.addToBeginning("Номер 1");

        System.out.println();
        MyLinkedList<String> copy = linkedList.copy();
        linkedList.reverse();
        for (int i = 0; i < linkedList.getSize(); i++) {
            System.out.println(linkedList.get(i));
        }

        System.out.println();
        for (int i = 0; i < copy.getSize(); i++) {
            System.out.println(copy.get(i));
        }
    }
}
