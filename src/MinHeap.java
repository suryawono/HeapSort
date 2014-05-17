
import java.lang.reflect.Array;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Surya Wono
 * @param <T>
 *
 */
public class MinHeap<T extends Comparable> extends HeapSort<T> {

    public MinHeap(Class<T> c) {
        super(c);
    }

    public MinHeap(Class<T> c, int maxSize) {
        super(c,maxSize);
    }

    public MinHeap() {
        super();
    }

    public MinHeap(int maxSize) {
        super(maxSize);
    }

    public MinHeap(HeapSort<T> m) {
        super(m);
    }

    @Override
    protected void heapify(int i) {
        int left = leftIndex(i);
        int right = rightIndex(i);
        int smallest = i;
        if (left != -1) {
            if (tree[i].compareTo(tree[left]) > 0) {
                smallest = left;
            }
        }
        if (right != -1) {
            if (tree[smallest].compareTo(tree[right]) > 0) {
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
        MinHeap<T> min = new MinHeap(this);
        T[] t = makeArray();
        int i = 0;
        while (min.size() > 0) {
            t[i++] = min.extract();
        }
        return t;
    }

}
