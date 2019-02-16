package tk.dmitriikorenev.listclasses;

class ListNode<T> {
    private T value;
    private ListNode<T> nextElement;

    ListNode(T value) {
        this.value = value;
    }

    ListNode(T value, ListNode<T> nextElement) {
        this.value = value;
        this.nextElement = nextElement;
    }

    T getValue() {
        return value;
    }

    void setValue(T value) {
        this.value = value;
    }

    ListNode<T> getNextElement() {
        return nextElement;
    }

    void setNextElement(ListNode<T> nextElement) {
        this.nextElement = nextElement;
    }
}
