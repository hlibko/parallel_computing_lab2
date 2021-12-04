class CPUProcess implements Runnable {

    CPUQueue queue;
    int generateNumber;

    private static int counter = 0;
    private int id;

    CPUProcess(CPUQueue q, int gN) {
        this.queue = q;
        this.generateNumber = gN;
        setId();
    }

    private synchronized void setId() {  // set unique id to any process
        id = counter++;
        System.out.println(">>> New CPUProcess ID: " + id);
    }

    public void run() {
        long generateDelay;
        for (int i = 0; i < generateNumber; i++) {
            int randMin = 500;
            int randMax = 1000;
            generateDelay = randMin + (int) (Math.random() * randMax);
            try {
                Thread.sleep(generateDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("Process ID[" + id + "] generated with delay " + generateDelay);
                queue.put("ID[" + id + "]: #" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("No more processes. Greatest queue size was " + queue.getMaxSize());
    }
}