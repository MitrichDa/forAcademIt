package tk.dmitriikorenev.listclasses;

import java.util.NoSuchElementException;
import java.util.Objects;

public class MyLinkedList<T> {
    private ListNode<T> head;
    private int size;

    public MyLinkedList() {
    }

    public int getSize() {
        return size;
    }

    public T getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("This list is empty");
        }
        return head.getValue();
    }

    private ListNode<T> getElement(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("This list does not have index " + index);
        }

        ListNode<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNextElement();
        }
        return node;
    }

    public T get(int index) {
        ListNode<T> node = getElement(index);
        return node.getValue();
    }

    public T set(int index, T newValue) {
        ListNode<T> node = getElement(index);
        T oldValue = node.getValue();
        node.setValue(newValue);
        return oldValue;
    }

    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("This list does not have index " + index);
        }

        T value;
        if (index == 0) {
            value = head.getValue();
            head = head.getNextElement();
        } else {
            ListNode<T> previousNode = getElement(index - 1);
            ListNode<T> nodeToRemove = previousNode.getNextElement();
            value = nodeToRemove.getValue();
            previousNode.setNextElement(nodeToRemove.getNextElement());
        }
        --size;
        return value;
    }

    public void addToBeginning(T value) {
        head = new ListNode<>(value, head);
        ++size;
    }

    public void add(int index, T value) {
        if (index == 0) {
            addToBeginning(value);
            return;
        }

        if (index == size) {
            getElement(index - 1).setNextElement(new ListNode<>(value));
            ++size;
            return;
        }

        ListNode<T> previousNode = getElement(index - 1);
        ListNode<T> newNode = new ListNode<>(value, previousNode.getNextElement());
        previousNode.setNextElement(newNode);
        ++size;
    }

    public boolean remove(T value) {
        if (Objects.equals(head.getValue(), value)) {
            head = head.getNextElement();
            --size;
            return true;
        }

        for (ListNode<T> node = head.getNextElement(), prev = head; node != null; prev = node, node = node.getNextElement()) {
            if (Objects.equals(node.getValue(), value)) {
                prev.setNextElement(node.getNextElement());
                --size;
                return true;
            }
        }
        return false;
    }

    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }

        T value = head.getValue();
        --size;
        head = head.getNextElement();
        return value;
    }

    public void reverse() {
        if (size < 2) {
            return;
        }

        ListNode<T> previousNode = head.getNextElement();
        ListNode<T> currentNode = previousNode.getNextElement();
        previousNode.setNextElement(head);
        head.setNextElement(null);

        while (currentNode != null) {
            ListNode<T> nextNode = currentNode.getNextElement();
            currentNode.setNextElement(previousNode);
            previousNode = currentNode;
            currentNode = nextNode;
        }

        head = previousNode;
    }

    public MyLinkedList<T> copy() {
        MyLinkedList<T> listCopy = new MyLinkedList<>();
        if (size == 0) {
            return listCopy;
        }

        listCopy.size = size;
        ListNode<T> nodeCopy = new ListNode<>(head.getValue());
        listCopy.head = nodeCopy;
        for (ListNode<T> node = head.getNextElement(); node != null; node = node.getNextElement()) {
            nodeCopy.setNextElement(new ListNode<>(node.getValue()));
            nodeCopy = nodeCopy.getNextElement();
        }
        return listCopy;
    }
}
