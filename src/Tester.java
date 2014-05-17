
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
/**
 *
 * @author Surya Wono
 */
public class Tester {
    
    
    public static void main(String[] args){
        HeapSort<Integer> max=new MaxHeap();
        Random r=new Random();
        for (int i=0;i<10;i++){
            max.add(r.nextInt());
        }
        Object[] sorted=max.toArraySorted();
        for (Object x:sorted){
            System.out.println(x);
        }
        System.out.println("=====================");
        HeapSort<Integer> min=new MinHeap(max);
        sorted=min.toArraySorted();
        for (Object x:sorted){
            System.out.println(x);
        }
    }
}
