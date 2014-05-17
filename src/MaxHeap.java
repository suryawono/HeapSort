
import java.lang.reflect.Array;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class MaxHeap<T extends Comparable> extends HeapSort<T> {

    public MaxHeap(Class<T> c) {
        this.c = c;
        if (c != null) {
            tree = (T[]) Array.newInstance(c, maxSize);
        } else {
            tree = (T[]) new Comparable[maxSize];
        }
    }

    public MaxHeap(Class<T> c, int maxSize) {
        this.c = c;
        this.maxSize = maxSize;
        if (c != null) {
            tree = (T[]) Array.newInstance(c, maxSize);
        } else {
            tree = (T[]) new Comparable[maxSize];
        }
    }

    public MaxHeap() {
        tree = (T[]) new Comparable[maxSize];
    }

    public MaxHeap(int maxSize) {
        parent(maxSize);
    }

    public MaxHeap(MaxHeap<T> m) {
        this.maxSize = m.maxSize;
        this.c = m.c;
        this.tree = m.toArray();
        this.heapSize = m.heapSize;
    }

    @Override
    protected void heapify(int i) {
        int left = leftIndex(i);
        int right = rightIndex(i);
        int smallest = i;
        if (left != -1) {
            if (tree[i].compareTo(tree[left]) < 0) {
                smallest = left;
            }
        }
        if (right != -1) {
            if (tree[smallest].compareTo(tree[right]) < 0) {
                smallest = right;
            }
        }
        if (i != smallest) {
            swap(i, smallest);
            heapify(smallest);
        }
    }

    @Override
    public T[] toArraySorted() {
        MaxHeap<T> min = new MaxHeap(this);
        T[] t = makeArray();
        int i = 0;
        while (min.size() > 0) {
            t[i++] = min.extract();
        }
        return t;
    }

}
