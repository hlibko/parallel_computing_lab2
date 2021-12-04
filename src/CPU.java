class CPU implements Runnable {

    CPUQueue queue;

    private static int counter = 0;
    private int id;


    CPU(CPUQueue q) {
        this.queue = q;
        setId();
    }

    private synchronized void setId() {  // set unique id to any process
        id = counter++;
        System.out.println(">>> New CPU ID: " + id);
    }

    public void run() {
        long processingTime;
        while (true) {
            int randMin = 100;
            int randMax = 200;
            processingTime = randMin + (int) (Math.random() * randMax);
            try {
                queue.get();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("CPU[" + id + "]: Processed in time " + processingTime + "\n");
            try {
                Thread.sleep(processingTime);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}