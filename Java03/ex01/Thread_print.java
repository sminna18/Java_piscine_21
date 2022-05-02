class Thread_print extends Thread {

    int lock = 1;

    void printEgg(Integer count) throws InterruptedException {
        while (count > 0) {
            synchronized (this) {
                if (lock == 0)
                    wait();
                System.out.println("Egg");
                count--;
                lock = 0;
                notify();
            }
        }
    }

    void printHen(Integer count) throws InterruptedException {
        while (count > 0) {
            synchronized (this) {
                if (lock == 1)
                    wait();
                System.out.println("Hen");
                count--;
                lock = 1;
                notify();
            }
        }
    }
}