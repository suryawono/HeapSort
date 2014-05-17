
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Surya Wono
 */
public class Tester {
    
    
    public static void main(String[] args){
        HeapSort<Integer> mh=new MaxHeap();
        Random r=new Random();
        for (int i=0;i<10;i++){
            mh.add(r.nextInt());
        }
        Object[] amh=mh.toArraySorted();
        for (Object x:amh){
            System.out.println(x);
        }
    }
}
