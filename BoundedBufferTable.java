
import java.util.*;
public class BoundedBufferTable {
    Hashtable<String,Integer> map = new Hashtable<String,Integer>();
    //private BoundedBuffer in;

    public void count(String word){
        if (map.containsKey(word)) {
            //increment frequency
            int freq = (int)map.get(word);
            map.put(word, (Integer)(freq+1));
        } else {
            map.put(word, (Integer)1);
        }
    }

    public void print() {
		System.out.println("Word Table call");
		Set<String> set = map.keySet();
		Object[] words = set.toArray();
		Arrays.sort(words);
	
		for (Object s:words) {
			int freq = (int)map.get(s);
			System.out.println(s + ": " + freq);
		}
	}

    public static void main(String[] args){
        System.out.println("program starting");
        BoundedBuffer buffer = new BoundedBuffer(5);
        if (args.length < 1) {
            System.out.println("usage: WordFreq <filename>");
            System.exit(1);
        }
        //Producer first;
        BoundedBufferTable table = new BoundedBufferTable();
		
        Producer prod = new Producer(buffer, args);
        Consumer cons = new Consumer(buffer, table);
        System.out.println("param");
        prod.start();
        System.out.println("param1");
        cons.start();
        System.out.println("param2");
        try {
            prod.join();
            cons.interrupt();
            } catch (InterruptedException e) {}
        
        table.print();
    }
}
