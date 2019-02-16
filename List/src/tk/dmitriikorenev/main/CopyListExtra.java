package tk.dmitriikorenev.main;

import tk.dmitriikorenev.listclasses.ListNodeExtra;

public class CopyListExtra {
    public static void main(String[] args) {
        ListNodeExtra node1 = new ListNodeExtra(1);
        ListNodeExtra node2 = new ListNodeExtra(2);
        ListNodeExtra node3 = new ListNodeExtra(3);
        ListNodeExtra node4 = new ListNodeExtra(4);
        ListNodeExtra node5 = new ListNodeExtra(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node1.random = node5;
        node2.random = node4;
        node3.random = node3;
        node4.random = node2;
        node5.random = node1;

        for (ListNodeExtra nodeExtra = node1; nodeExtra != null; nodeExtra = nodeExtra.next) {
            if (nodeExtra.next != null) {
                System.out.println(nodeExtra.value + " " + nodeExtra.next.value + " " + nodeExtra.random.value);
            } else {
                System.out.println(nodeExtra.value + " " + nodeExtra.random.value);
            }
        }
        System.out.println();

        for (ListNodeExtra nodeExtra = node1; nodeExtra != null; nodeExtra = nodeExtra.next) {
            ListNodeExtra newNode = new ListNodeExtra(nodeExtra.value);
            newNode.next = nodeExtra.random;
            nodeExtra.random = newNode;
        }
        ListNodeExtra resultFirst = node1.random;
        for (ListNodeExtra nodeExtra = node1; nodeExtra != null; nodeExtra = nodeExtra.next) {
            ListNodeExtra newNode = nodeExtra.random;
            newNode.random = newNode.next.random;
        }
        for (ListNodeExtra nodeExtra = node1; nodeExtra != null; nodeExtra = nodeExtra.next) {
            ListNodeExtra newNode = nodeExtra.random;
            nodeExtra.random = newNode.next;
            if (nodeExtra.next != null) {
                newNode.next = nodeExtra.next.random;
            } else {
                newNode.next = null;
            }
        }

        System.out.println();
        for (ListNodeExtra nodeExtra = resultFirst; nodeExtra != null; nodeExtra = nodeExtra.next) {
            if (nodeExtra.next != null) {
                System.out.println(nodeExtra.value + " " + nodeExtra.next.value + " " + nodeExtra.random.value);
            } else {
                System.out.println(nodeExtra.value + " " + nodeExtra.random.value);
            }
        }
    }
}
