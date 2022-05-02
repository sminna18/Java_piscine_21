import java.util.Scanner;

class Program {
    public static void main(String[] args) {
        int i = 1;
        boolean cykl = true;
        int result = 0;
        int mir_result = 0;
        int tmp;
        while (cykl){
            Scanner scanner = new Scanner(System.in);
            String in = scanner.nextLine();
            String name = "Week " + i;
            if (in.equals(name)) {
                i++;
                int min = 10;
                for (int j = 0; j < 5; j++) {
                    tmp = scanner.nextInt();
                    if (tmp < min)
                        min = tmp;
                }
                result = result * 10 + min;
            }
            else if(in.equals("42"))
            {
                cykl = false;
            }
            else{
                System.out.println("IllegalArgument");
                System.exit(1);
            }

        }
        while (result > 0) {
            mir_result = (mir_result * 10) + (result % 10);
            result /= 10;
        }
        System.out.println(mir_result);
        for (int j = 1; j < i; j++) {
            tmp = mir_result % 10;
            mir_result /= 10;
            System.out.print("Week " + j + " ");
            for (int h = 0; h < tmp; h++) {
                System.out.print("=");
            }
            System.out.println(">");
        }
    }
}