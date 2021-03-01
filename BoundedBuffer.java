public class BoundedBuffer {
    protected int numSlots; 
    private String[] buffer;
    private int takeOut = 0, putIn = 0; 
    private int count=0;
 
    public BoundedBuffer(int numSlots) { 
       if(numSlots <= 0) {
          throw new IllegalArgumentException("numSlots <= 0");
       } 
       this.numSlots = numSlots; 
       buffer = new String[numSlots];
    }
  
      /**
       * Put an item in the bounded buffer.  Block if full.
       * @param word the thing to add to the rear of the buffer
       * @throws InterruptedException
       */
    public synchronized void put(String word) throws InterruptedException {
       while (count == numSlots) 
           wait();
       
       putIn = (putIn + 1) % numSlots;
       buffer[putIn] = word;
       count++;
       notifyAll();
    }
 
      /** 
       * Remove an item from a bounded buffer.  Block if empty
       * @return the item removed
       * @throws InterruptedException
       */
    public synchronized String get() throws InterruptedException {
       while (count == 0) 
           wait();
       
       String word = buffer[takeOut];
       takeOut = (takeOut + 1) % numSlots;
       count--;
       notifyAll();
       return word;
    }
 }