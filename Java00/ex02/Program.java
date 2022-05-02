import java.util.Scanner;

class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = 0;
        while(true)
        {
            int sum = 0;
            int num = scanner.nextInt();

            if (num == 42) {
                System.out.println("Count of coffee-request - " + count);
                System.exit(0);
            }

            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;

            int iter = 2;
            int tmp = 0;
            if (num < 2){
                tmp = 1;
            }
            int quar = 0;
            while((quar * quar) < num)
            {
                quar++;
            }
            while (iter <= quar)
            {
                if (num % iter == 0){
                    tmp = 1;
                }
                iter++;
            }
            if (tmp == 0)
                count++;
        }
    }
}