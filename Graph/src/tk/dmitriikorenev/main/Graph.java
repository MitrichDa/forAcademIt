package tk.dmitriikorenev.main;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.IntConsumer;

public class Graph {
    public static void main(String[] args) {
        int[][] connectedGraph = {
                {0, 1, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 1},
                {0, 0, 1, 0, 0, 1, 0},
        };
        int[][] disconnectedGraph = {
                {0, 1, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0},
        };

        traverseByBreadth(connectedGraph, e -> System.out.println(e + 1 + " is visited"));
        System.out.println();
        traverseByBreadth(disconnectedGraph, e -> System.out.println(e + 1 + " is visited"));
        System.out.println();

        traverseByDepth(connectedGraph, e -> System.out.println(e + 1 + " is visited"));
        System.out.println();
        traverseByDepth(disconnectedGraph, e -> System.out.println(e + 1 + " is visited"));
        System.out.println();
    }

    public static void traverseByBreadth(int[][] graph, IntConsumer consumer) {
        boolean[] visited = new boolean[graph.length];

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    int index = queue.remove();
                    if (!visited[index]) {
                        visited[index] = true;
                        consumer.accept(index);
                        for (int j = 0; j < graph.length; j++) {
                            if (graph[index][j] == 1) {
                                queue.add(j);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void traverseByDepth(int[][] graph, IntConsumer consumer) {
        boolean[] visited = new boolean[graph.length];

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                deque.addLast(i);
                while (!deque.isEmpty()) {
                    int index = deque.removeLast();
                    if (!visited[index]) {
                        consumer.accept(index);
                        visited[index] = true;
                        for (int j = graph.length - 1; j >= 0; j--) {
                            if (graph[index][j] == 1) {
                                deque.addLast(j);
                            }
                        }
                    }
                }
            }
        }
    }
}
