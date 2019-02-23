package tk.dmitriikorenev.classes;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private Object[] items = new Object[10];
    private int size;
    private int modCount;

    public MyArrayList() {
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("List must have capacity > 0");
        }
        items = new Object[initialCapacity];
    }

    public MyArrayList(E[] array) {
        int arrayLength = array.length;
        items = Arrays.copyOf(array, arrayLength);
        size = arrayLength;
    }

    public MyArrayList(Collection<? extends E> c) {
        Object[] array = c.toArray();
        int arrayLength = array.length;
        if (c.size() != 0) {
            items = Arrays.copyOf(array, arrayLength);
            size = arrayLength;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(items, size);
        }
        System.arraycopy(items, 0, a, 0, size);
        return a;
    }

    @Override
    public boolean add(E t) {
        ensureCapacity(size);
        items[size] = t;
        ++size;
        ++modCount;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index != -1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
            --size;
            ++modCount;
            return true;
        }
        --size;
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] array = c.toArray();
        for (Object element : array) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] array = c.toArray();
        int arrayLength = array.length;
        if (arrayLength == 0) {
            return true;
        } else {
            modCount++;
        }
        ensureCapacity(size + arrayLength);
        System.arraycopy(array, 0, items, size, arrayLength);
        size += arrayLength;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndex(index);
        Object[] array = c.toArray();
        int arrayLength = array.length;
        if (arrayLength == 0) {
            return true;
        } else {
            modCount++;
        }
        ensureCapacity(size + arrayLength);
        System.arraycopy(items, index, items, size - index + 1, size - index);
        System.arraycopy(array, 0, items, index, arrayLength);
        size += arrayLength;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int oldModCount = modCount;
        Object[] array = c.toArray();
        for (Object element : array) {
            remove(element);
        }

        return oldModCount != modCount;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int oldModCount = modCount;
        for (int i = 0; i < size; i++) {
            if (!c.contains(items[i])) {
                remove(i);
                --i;
            }
        }
        return oldModCount == modCount;
    }

    @Override
    public void clear() {
        modCount++;
        size = 0;
    }

    @Override
    public E get(int index) {
        return getItem(index);
    }

    @Override
    public E set(int index, E element) {
        E oldElement = getItem(index);
        items[index] = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        ensureCapacity(size);
        if (index != size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }
        items[index] = element;
        modCount++;
        ++size;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("This list does not have index " + index);
        }
    }

    @Override
    public E remove(int index) {
        E oldElement = getItem(index);
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        --size;
        ++modCount;
        return oldElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
        }
        return -1;
    }

    private void ensureCapacity(int size) {
        if (items.length <= size) {
            items = Arrays.copyOf(items, size * 2);
        }
    }

    class MyIterator implements Iterator<E> {
        private int currentIndex = -1;
        private int iteratorModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            ++currentIndex;
            if (currentIndex >= size) {
                throw new NoSuchElementException("No more elements in this list.");
            }
            if (iteratorModCount != modCount) {
                throw new ConcurrentModificationException("List has been changed");
            }

            return getItem(currentIndex);
        }
    }

    @SuppressWarnings("unchecked")
    private E getItem(int index) {
        checkIndex(index);
        return (E) items[index];
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("This list doesn't support ListIterator.");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("This list doesn't support ListIterator.");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("This list doesn't support subList.");
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (int i = 0; i < size; i++) {
            joiner.add(items[i].toString());
        }
        return joiner.toString();
    }
}
