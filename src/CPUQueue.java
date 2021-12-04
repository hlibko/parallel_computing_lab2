import java.util.LinkedList;
import java.util.Queue;

class CPUQueue {

    private Queue<String> queue = new LinkedList<>();
    private int capacity;
    private int maxSize = 0;

    private int counter = 0;
    private int id;


    public CPUQueue(int capacity) {
        this.capacity = capacity;
        setId();
    }

    private synchronized void setId() {
        id = counter++;
        System.out.println(">>> New CPUQueue ID: " + id);
    }

    public int getMaxSize() {
        return maxSize;
    }

    public synchronized void put(String element) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("! Queue is FULL, waiting..");
            wait();
        }

        queue.add(element);

        if (queue.size() > maxSize)
            maxSize = queue.size();

        System.out.println("Process added " + element + ", queue size = [" + queue.size() + "]\n");
        notifyAll();
    }

    public synchronized String get() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Queue is EMPTY, waiting..");
            wait();
        }

        String item = queue.remove();
        System.out.println("Process "+ item + " removed, queue size = [" + queue.size() + "]");
        notifyAll();
        return item;
    }
}
