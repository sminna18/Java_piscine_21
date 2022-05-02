import java.util.Scanner;

class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int iter = 2;
        if (num < 2){
            System.out.println("IllegalArgument");
            System.exit(-1);
        }
        int quar = 0;
        while((quar * quar) < num)
        {
            quar++;
        }
        while (iter <= quar)
        {
            if (num % iter == 0){
                System.out.print("false ");
                System.out.println(iter - 1);
                System.exit(0);
            }
            iter++;
        }
        System.out.print("true ");
        System.out.println(iter - 2);

    }
}