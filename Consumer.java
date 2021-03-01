import java.util.Random;

public class Consumer extends Thread{
    private final BoundedBuffer buffer;
    BoundedBufferTable table;
	Random generator;

  /**
	 * Constructs a consumer of items from a bounded buffer
	 * @param b the bounded buffer the consumer takes items from
	 */
	public Consumer(BoundedBuffer b, BoundedBufferTable table) {
		buffer = b;                  // The bounded buffer
		generator = new Random();    // Used to generate a random wait time
        this.table = table;
	}

	/**
	 * Get a word from a buffer and insert it into the hastable
	 */
	public void run() {
		try{
			while (true) {
				String word = buffer.get();
				table.count(word);
                int waitTime = 100 + generator.nextInt(200);
                sleep(waitTime);
			}
		} catch(InterruptedException e){}
	}
}
