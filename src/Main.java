public class Main {

    public static void main(String[] args) {
        int queueCapacity = 10;

        int processToGenerate1 = 10;
        int processToGenerate2 = 30;

        System.out.println("\nQueue capacity = [" + queueCapacity + "], will be generated " + processToGenerate1 + " processes\n");
        System.out.println("\nQueue capacity = [" + queueCapacity + "], will be generated " + processToGenerate2 + " processes\n");

        CPUQueue q = new CPUQueue(queueCapacity);

        CPUProcess Cp1 = new CPUProcess(q, processToGenerate1);
        CPUProcess Cp2 = new CPUProcess(q, processToGenerate2);

        CPU C1 = new CPU(q);
        CPU C2 = new CPU(q);

        new Thread(Cp1).start();
        new Thread(Cp2).start();

        new Thread(C1).start();
        new Thread(C2).start();

    }
}