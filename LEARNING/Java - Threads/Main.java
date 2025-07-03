
class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("Extending Thread");
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Implementing Runnable interface");
    }
}

public class Main {

    public static void main(String[] args) {
        MyThread t1 = new MyThread(); // Create thread object
        t1.start(); // Start the thread (calls run() in a new thread)

        MyRunnable runnable = new MyRunnable();       // Create Runnable object
        Thread t2 = new Thread(runnable);             // Wrap in Thread
        t2.start();                                   // Start the thread
    }
}
