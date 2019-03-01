package tk.dmitriikorenev.hashtable;

import java.lang.reflect.Array;
import java.util.*;

public class MyHashTable<E> implements Collection<E> {
    private static final int DEFAULT_ARRAY_SIZE = 11;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int threshold;
    private float loadFactor;
    private LinkedList<E>[] itemLists;
    private int size;
    private int modCount;

    @SuppressWarnings("unchecked")
    public MyHashTable(int arraySize, float loadFactor) {
        if (arraySize <= 0) {
            throw new IllegalArgumentException("Array size must be > 0");
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Load factor must be > 0");
        }

        this.loadFactor = loadFactor;
        itemLists = (LinkedList<E>[]) new LinkedList[arraySize];
        threshold = (int) (arraySize * loadFactor);
    }

    public MyHashTable(Collection<? extends E> c) {
        this(Math.max(2 * c.size(), DEFAULT_ARRAY_SIZE), DEFAULT_LOAD_FACTOR);
        addAll(c);
    }

    public MyHashTable() {
        this(DEFAULT_ARRAY_SIZE, DEFAULT_LOAD_FACTOR);
    }

    public MyHashTable(int arraySize) {
        this(arraySize, DEFAULT_LOAD_FACTOR);
    }

    private int getIndex(Object o) {
        return o == null ? 0 : Math.abs(o.hashCode() % itemLists.length);
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        int newLength = itemLists.length * 2;
        LinkedList<E>[] newItemLists = (LinkedList<E>[]) new LinkedList[newLength];
        for (E element : this) {
            int index = element == null ? 0 : Math.abs(element.hashCode() % newLength);
            if (newItemLists[index] == null) {
                newItemLists[index] = new LinkedList<>();
            }
            newItemLists[index].add(element);
        }

        modCount++;
        threshold = (int) (newLength * loadFactor);
        itemLists = newItemLists;
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
        int index = getIndex(o);

        if (itemLists[index] == null) {
            return false;
        }

        return itemLists[index].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] resultArray = new Object[size];
        int i = 0;
        for (E e : this) {
            resultArray[i] = e;
            ++i;
        }
        return resultArray;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }

        int i = 0;
        for (E e : this) {
            a[i] = (T) e;
            ++i;
        }
        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        if (size >= threshold) {
            rehash();
        }

        int index = getIndex(e);
        if (itemLists[index] == null) {
            itemLists[index] = new LinkedList<>();
        }

        LinkedList<E> list = itemLists[index];
        ++modCount;
        ++size;
        return list.add(e);
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);
        if (itemLists[index] == null || itemLists[index].isEmpty()) {
            return false;
        }

        if (itemLists[index].remove(o)) {
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
        int oldModCount = modCount;
        for (E element : c) {
            add(element);
        }

        return oldModCount != modCount;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Iterator<E> iterator = iterator();
        int oldModCount = modCount;
        while (iterator.hasNext()) {
            if (c.contains(iterator.next())) {
                iterator.remove();
            }
        }

        return oldModCount != modCount;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Iterator<E> iterator = iterator();
        int oldModCount = modCount;
        while (iterator.hasNext()) {
            if (!c.contains(iterator.next())) {
                iterator.remove();
            }
        }

        return oldModCount != modCount;
    }

    @Override
    public void clear() {
        for (int i = 0; i < itemLists.length; i++) {
            itemLists[i] = null;
        }
        modCount++;
        size = 0;
    }

    class MyIterator implements Iterator<E> {
        private int listIndex = -1;
        private int iteratorModCount = modCount;
        private Iterator<E> listIterator;
        private int nextIndex;

        MyIterator() {
            moveToValidList();
        }

        private void moveToValidList() {
            ++listIndex;
            while (listIndex < itemLists.length && (itemLists[listIndex] == null || itemLists[listIndex].isEmpty())) {
                ++listIndex;
            }
            if (listIndex < itemLists.length) {
                listIterator = itemLists[listIndex].iterator();
            }
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in this list.");
            }
            if (iteratorModCount != modCount) {
                throw new ConcurrentModificationException("List has been changed");
            }
            if (!listIterator.hasNext()) {
                moveToValidList();
            }

            ++nextIndex;
            return listIterator.next();
        }

        @Override
        public void remove() {
            if (iteratorModCount != modCount) {
                throw new ConcurrentModificationException("List has been changed");
            }
            listIterator.remove();
            ++modCount;
            ++iteratorModCount;
            --size;
            --nextIndex;
        }
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (E element : this) {
            joiner.add(String.valueOf(element));
        }
        return joiner.toString();
    }
}
