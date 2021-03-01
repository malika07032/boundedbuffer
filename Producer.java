import java.io.*;
import java.util.*;

public class Producer extends Thread{
    private final BoundedBuffer buffer;
    String[] filenames;
    /**
	 * Create a Producer
	 * @param b the buffer the producer puts into
	 */
	public Producer(BoundedBuffer b, String[] filenames) {
		this.buffer = b;
        this.filenames = filenames;
	}

	/**
	 * Open up files given the file names from an array of Strings.
     * Every word from files gets inserter into BoundedBuffer
	 */
	public void run() {
		try {
            for(String s : filenames){
                System.out.println(s);
                File f = new File(s);
                Scanner sc = null;
                try {
                    sc = new Scanner(f);
                    while (sc.hasNext()){
                        String word = sc.next();
                        buffer.put(word);
                        System.out.println(word);
                    }
                    } catch (FileNotFoundException e) {
                        System.out.println("cannot open scanner");
                        System.exit(1);
                    };
            }
		} catch (InterruptedException e) {	}
	}
}