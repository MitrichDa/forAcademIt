package tk.dmitriikorenev.main;

import tk.dmitriikorenev.classes.MyTree;

public class TreeMain {
    public static void main(String[] args) {
        MyTree<Integer> tree = new MyTree<>();
        tree.add(50);
        tree.add(25);
        tree.add(75);
        tree.add(12);
        tree.add(37);
        tree.add(62);
        tree.add(87);
        tree.add(6);
        tree.add(18);
        tree.add(30);
        tree.add(43);
        tree.add(56);
        tree.add(68);
        tree.add(80);
        tree.add(93);

        System.out.println("Обход в ширину:");
        tree.traverseByBreadth(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("Обход в глубину:");
        tree.traverseByDepth(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("Обход в глубину с рекурсией:");
        tree.traverseByDepthRecursive(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("Размер: " + tree.getSize());
        System.out.println("Ищем 6: " + tree.containsElement(6));
        System.out.println("Удаляем 6: " + tree.removeElement(6));
        System.out.println("Ищем 6: " + tree.containsElement(6));
        System.out.println("Размер: " + tree.getSize());
        System.out.println("Обход в ширину:");
        tree.traverseByBreadth(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("Удаляем 12: " + tree.removeElement(12));
        System.out.println("Обход в ширину:");
        tree.traverseByBreadth(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("Удаляем 75: " + tree.removeElement(75));
        System.out.println("Обход в ширину:");
        tree.traverseByBreadth(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("Удаляем 50: " + tree.removeElement(50));
        System.out.println("Обход в ширину:");
        tree.traverseByBreadth(e -> System.out.print(e + " "));
        System.out.println();

        MyTree<Integer> tree1 = new MyTree<>();
        tree1.add(10);
        tree1.add(1);
        tree1.add(20);
        tree1.add(15);
        tree1.add(25);
        tree1.traverseByBreadth(e -> System.out.print(e + " "));
        System.out.println();

        tree1.removeElement(20);
        tree1.traverseByBreadth(e -> System.out.print(e + " "));
    }
}
