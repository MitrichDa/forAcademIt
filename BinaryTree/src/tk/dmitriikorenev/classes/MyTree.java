package tk.dmitriikorenev.classes;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class MyTree<T> {
    private TreeNode root;
    private int size;
    private final Comparator<? super T> comparator;

    public MyTree(Comparator<? super T> comparator) {
        root = null;
        size = 0;
        this.comparator = comparator;
    }

    public MyTree() {
        root = null;
        size = 0;
        comparator = null;
    }

    public int getSize() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private int compareData(T data1, T data2) {
        if (data1 == null && data2 == null) {
            return 0;
        }

        if (data1 == null) {
            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        return comparator == null ? ((Comparable<? super T>) data1).compareTo(data2)
                : comparator.compare(data1, data2);
    }

    public void add(T elementData) {
        ++size;

        if (root == null) {
            root = new TreeNode(elementData);
            return;
        }

        TreeNode currentElement = root;
        TreeNode newElement = new TreeNode(elementData);
        while (true) {
            if (compareData(newElement.data, currentElement.data) < 0) {
                if (currentElement.leftChild != null) {
                    currentElement = currentElement.leftChild;
                } else {
                    currentElement.leftChild = newElement;
                    return;
                }
            } else if (currentElement.rightChild != null) {
                currentElement = currentElement.rightChild;
            } else {
                currentElement.rightChild = newElement;
                return;
            }
        }
    }

    private TreeNode findNode(T data) {
        TreeNode currentElement = root;
        while (currentElement != null) {
            int comparison = compareData(data, currentElement.data);
            if (comparison < 0) {
                currentElement = currentElement.leftChild;
            } else if (comparison > 0) {
                currentElement = currentElement.rightChild;
            } else {
                return currentElement;
            }
        }
        return null;
    }

    public boolean containsElement(T data) {
        return findNode(data) != null;
    }

    public boolean removeElement(T data) {
        if (size == 0) {
            return false;
        }

        TreeNode parentNode = null;
        TreeNode currentNode = root;
        boolean isLeftChild = true;
        int comparison = compareData(currentNode.data, data);
        while (comparison != 0) {
            parentNode = currentNode;
            if (comparison > 0) {
                currentNode = parentNode.leftChild;
                isLeftChild = true;
            } else {
                currentNode = parentNode.rightChild;
                isLeftChild = false;
            }
            if (currentNode == null) {
                return false;
            }
            comparison = compareData(currentNode.data, data);
        }

        --size;

        if (currentNode.leftChild == null && currentNode.rightChild == null) {
            if (currentNode == root) {
                root = null;
            } else if (isLeftChild) {
                parentNode.leftChild = null;
            } else {
                parentNode.rightChild = null;
            }
        } else if (currentNode.leftChild == null) {
            if (currentNode == root) {
                root = currentNode.rightChild;
            } else if (isLeftChild) {
                parentNode.leftChild = currentNode.rightChild;
            } else {
                parentNode.rightChild = currentNode.rightChild;
            }
        } else if (currentNode.rightChild == null) {
            if (currentNode == root) {
                root = currentNode.leftChild;
            } else if (isLeftChild) {
                parentNode.leftChild = currentNode.leftChild;
            } else {
                parentNode.rightChild = currentNode.leftChild;
            }
        } else {
            TreeNode replacementElement = getReplacementElement(currentNode);
            if (currentNode == root) {
                root = replacementElement;
            } else if (isLeftChild) {
                parentNode.leftChild = replacementElement;
            } else {
                parentNode.rightChild = replacementElement;
            }
        }

        return true;
    }

    private TreeNode getReplacementElement(TreeNode nodeToDelete) {
        TreeNode replacementElement = null;
        TreeNode replacementElementParent = null;
        TreeNode currentElement = nodeToDelete.rightChild;
        while (currentElement != null) {
            replacementElementParent = replacementElement;
            replacementElement = currentElement;
            currentElement = currentElement.leftChild;
        }

        replacementElement.leftChild = nodeToDelete.leftChild;
        if (replacementElement != nodeToDelete.rightChild) {
            replacementElementParent.leftChild = replacementElement.rightChild;
            replacementElement.rightChild = nodeToDelete.rightChild;
        }
        return replacementElement;
    }

    public void traverseByBreadth(Consumer<T> consumer) {
        if (size == 0) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode element = queue.remove();
            consumer.accept(element.data);

            if (element.leftChild != null) {
                queue.add(element.leftChild);
            }
            if (element.rightChild != null) {
                queue.add(element.rightChild);
            }
        }
    }

    public void traverseByDepth(Consumer<T> consumer) {
        if (size == 0) {
            return;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.addFirst(root);

        while (!stack.isEmpty()) {
            TreeNode element = stack.removeFirst();
            consumer.accept(element.data);

            if (element.rightChild != null) {
                stack.addFirst(element.rightChild);
            }
            if (element.leftChild != null) {
                stack.addFirst(element.leftChild);
            }
        }
    }

    private void traverseByDepthRecursive(TreeNode root, Consumer<T> consumer) {
        consumer.accept(root.data);

        if (root.leftChild != null) {
            traverseByDepthRecursive(root.leftChild, consumer);
        }

        if (root.rightChild != null) {
            traverseByDepthRecursive(root.rightChild, consumer);
        }
    }

    public void traverseByDepthRecursive(Consumer<T> consumer) {
        traverseByDepthRecursive(root, consumer);
    }

    public class TreeNode {
        private TreeNode leftChild;
        private TreeNode rightChild;
        private T data;

        TreeNode(T data) {
            this.data = data;
        }
    }
}
