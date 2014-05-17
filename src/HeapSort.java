
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public abstract class HeapSort<T extends Comparable> implements Queue<T> {
    protected T[] tree;
    protected int heapSize = -1;
    protected int maxSize = 100;
    protected Class<T> c = null;
    protected int countMod = 0;
    
    public HeapSort(Class<T> c) {
        this.c = c;
        if (c != null) {
            tree = (T[]) Array.newInstance(c, maxSize);
        } else {
            tree = (T[]) new Comparable[maxSize];
        }
    }

    public HeapSort(Class<T> c, int maxSize) {
        this.c = c;
        this.maxSize = maxSize;
        if (c != null) {
            tree = (T[]) Array.newInstance(c, maxSize);
        } else {
            tree = (T[]) new Comparable[maxSize];
        }
    }

    public HeapSort() {
        tree = (T[]) new Comparable[maxSize];
    }

    public HeapSort(int maxSize) {
        this.maxSize = maxSize;
        tree = (T[]) new Comparable[maxSize];
    }

    public HeapSort(HeapSort<T> m) {
        this.maxSize = m.maxSize;
        this.c = m.c;
        this.tree=m.toArray();
        this.heapSize=m.heapSize;
    }

    public boolean isFull() {
        if (heapSize + 1 == maxSize) {
            return true;
        }
        return false;
    }

    protected void swap(int i, int j) {
        T t = tree[i];
        tree[i] = tree[j];
        tree[j] = t;
    }

    protected abstract void heapify(int i);

    protected int parentIndex(int i) {
        int index;
        if (i % 2 == 0) {
            index = i / 2 - 1;
        } else {
            index = i / 2;
        }
        if (index > heapSize) {
            index = -1;
        }
        return index;
    }

    protected T parent(int i) {
        int index = parentIndex(i);
        if (index != -1) {
            return tree[index];
        }
        return null;
    }

    protected int leftIndex(int i) {
        int index = i * 2 + 1;
        if (index > heapSize) {
            index = -1;
        }
        return index;
    }

    protected T left(int i) {
        int index = leftIndex(i);
        if (index != -1) {
            return tree[index];
        }
        return null;
    }

    protected int rightIndex(int i) {
        int index = i * 2 + 2;
        if (index > heapSize) {
            index = -1;
        }
        return index;
    }

    protected T right(int i) {
        int index = rightIndex(i);
        if (index != -1) {
            return tree[index];
        }
        return null;
    }

    public T extract() {
        return remove();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> t = new Iterator() {
            protected int itCurrent = -1;
            protected int currentMod = countMod;

            @Override
            public boolean hasNext() {
                if (itCurrent < heapSize) {
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                if (currentMod != countMod) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return tree[++itCurrent];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        return t;
    }

    @Override
    public T[] toArray() {
        T[] t = makeArray();
        System.arraycopy(tree, 0, t, 0, heapSize + 1);
        return t;
    }

    public abstract T[] toArraySorted();

    protected T[] makeArray() {
        T[] t;
        if (c != null) {
            t = (T[]) Array.newInstance(c, heapSize + 1);
        } else {
            t = (T[]) new Comparable[heapSize + 1];
        }
        return t;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(T e) {
        if (!isFull()) {
            heapSize++;
            countMod++;
            tree[heapSize] = e;
            int i = parentIndex(heapSize);
            while (i > -1) {
                heapify(i);
                i = parentIndex(i);
            }

        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        try {
            for (T item : c) {
                add(item);
            }
            countMod++;
        } catch (ClassCastException | ArrayStoreException cce ) {
            this.heapSize--;
            return false;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        heapSize = -1;
        countMod++;
    }

    @Override
    public String toString() {
        String s = "{";
        for (int i = 0; i <= heapSize; i++) {
            s += tree[i] + ",";
        }
        if (isEmpty()) {
            s += " ";
        }
        return s.substring(0, s.length() - 1) + "}";
    }

    @Override
    public int size() {
        return heapSize + 1;
    }

    @Override
    public boolean isEmpty() {
        if (heapSize != -1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean offer(T e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        T t = tree[heapSize];
        heapSize--;
        countMod++;
        return t;
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        T t = tree[0];
        swap(0, heapSize);
        heapSize--;
        countMod++;
        heapify(0);
        return t;
    }

    @Override
    public T poll() {
        try {
            T t = remove();
            return t;
        } catch (NullPointerException ne) {
            return null;
        }
    }

    @Override
    public T element() {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        return tree[0];
    }

    @Override
    public T peek() {
        try {
            T t = element();
            return t;
        } catch (NullPointerException ne) {
            return null;
        }
    }
}
