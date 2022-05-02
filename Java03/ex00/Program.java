class Program {

    private static final String COUNT = "--count=";

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1 && !args[0].startsWith(COUNT))
        {
            System.out.println("1Error arguments!");
            System.exit(-1);
        }
        args[0] = args[0].replace(COUNT,"");
        int num = -1;
        try {
            num = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException error) {
            System.out.println("2Error arguments!");
            System.exit(-1);
        }
        if (num < 1){
            System.out.println("3Error arguments!");
            System.exit(-1);
        }

        Thread_print threadFirst = new Thread_print(num, "Egg");
        Thread_print threadSecond = new Thread_print(num, "Hen");

        threadFirst.start();
        threadSecond.start();
        threadFirst.join();
        threadSecond.join();

        for (int i = 0; i < num; i++) {
            System.out.println("Human");
        }
    }
}