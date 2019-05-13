package tk.dmitriikorenev.main;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

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

        traverseByBreadth(connectedGraph);
        System.out.println();
        traverseByBreadth(disconnectedGraph);
        System.out.println();

        traverseByDepth(connectedGraph);
        System.out.println();
        traverseByDepth(disconnectedGraph);
        System.out.println();
    }

    public static void traverseByBreadth(int[][] graph) {
        boolean[] visited = new boolean[graph.length];

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    int index = queue.remove();
                    if (!visited[index]) {
                        System.out.println(index + 1 + " is now visited");
                        for (int j = 0; j < graph.length; j++) {
                            if (graph[index][j] == 1) {
                                queue.add(j);
                            }
                            visited[index] = true;
                        }
                    }
                }
                System.out.println("Закончен обход компоненты связности.");
            }
        }
    }

    public static void traverseByDepth(int[][] graph) {
        boolean[] visited = new boolean[graph.length];

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                deque.addLast(i);
                while (!deque.isEmpty()) {
                    int index = deque.removeLast();
                    if (!visited[index]) {
                        System.out.println(index + 1 + " is now visited");
                        for (int j = graph.length - 1; j >= 0; j--) {
                            if (graph[index][j] == 1) {
                                deque.addLast(j);
                            }
                            visited[index] = true;
                        }
                    }
                }
                System.out.println("Закончен обход компоненты связности.");
            }
        }
    }
}
