package tk.dmitriikorenev.classes;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private static final int DEFAULT_SIZE = 10;
    private E[] items;
    private int size;
    private int modCount;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        items = (E[]) new Object[DEFAULT_SIZE];
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("List must have capacity > 0");
        }
        items = (E[]) new Object[initialCapacity];
    }

    public MyArrayList(E[] array) {
        int arrayLength = array.length;
        items = Arrays.copyOf(array, arrayLength);
        size = arrayLength;
    }

    public MyArrayList(Collection<? extends E> c) {
        this(c.size());
        addAll(0, c);
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
    @SuppressWarnings({"unchecked", "SuspiciousSystemArraycopy"})
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        System.arraycopy(items, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(E t) {
        ++modCount;
        ensureCapacity(size);
        items[size] = t;
        ++size;
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
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndexForAdd(index);
        int collectionSize = c.size();
        if (collectionSize == 0) {
            return false;
        } else {
            ++modCount;
        }
        ensureCapacity(size + collectionSize);
        System.arraycopy(items, index, items, collectionSize + index, size - index);
        int i = index;
        for (E element : c) {
            items[i] = element;
            ++i;
        }
        size += collectionSize;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int oldModCount = modCount;
        for (Object element : c) {
            for (int i = 0; i < size; i++) {
                if (Objects.equals(element, items[i])) {
                    remove(i);
                    --i;
                }
            }
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
        checkIndexForAdd(index);
        ++modCount;
        ensureCapacity(size);
        if (index != size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }
        items[index] = element;
        ++size;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("This list does not have index " + index);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Can't add items to index " + index);
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

    public void ensureCapacity(int minCapacity) {
        if (minCapacity < items.length) {
            return;
        }

        int newCapacity = Math.max(minCapacity, items.length * 2);
        items = Arrays.copyOf(items, newCapacity);
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
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in this list.");
            }
            if (iteratorModCount != modCount) {
                throw new ConcurrentModificationException("List has been changed");
            }
            ++currentIndex;

            return getItem(currentIndex);
        }
    }

    private E getItem(int index) {
        checkIndex(index);
        return items[index];
    }

    public void trimToSize() {
        if (items.length != size) {
            items = Arrays.copyOf(items, size);
        }
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
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size; i++) {
            joiner.add(String.valueOf(items[i]));
        }
        return joiner.toString();
    }
}
