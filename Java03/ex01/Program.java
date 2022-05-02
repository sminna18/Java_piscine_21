class Program {

    private static final String COUNT = "--count=";

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1 && !args[0].startsWith(COUNT)) {
            System.out.println("Error arguments!");
            System.exit(-1);
        }
        args[0] = args[0].replace(COUNT, "");
        int num = -1;
        try {
            num = Integer.parseInt(args[0]);
        } catch (NumberFormatException error) {
            System.out.println("Error arguments!");
            System.exit(-1);
        }
        if (num < 1) {
            System.out.println("Error arguments!");
            System.exit(-1);
        }

        final Integer Int_num = num;
        Thread_print threads = new Thread_print();

        Thread threadFirst = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    threads.printEgg(Int_num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        Thread threadSecond = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    threads.printHen(Int_num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        threadFirst.start();
        threadSecond.start();
    }
}
