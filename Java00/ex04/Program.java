import java.util.Scanner;

class Program {
    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        String str = scanner.nextLine();
        int length = str.length();
        char str_char[] = str.toCharArray();
        int cods[] = new int[65536];
        int top[] = new int[10];
        int top_char[] = new int[10];
        int top_schedule[] = new int[10];
        int i = 0;
        int j;

        if (length == 0) {
            System.out.println("Empty line");
            System.exit(0);
        }

        while(i < 65536)
        {
            cods[i] = 0;
            i++;
        }

        i = 0;
        while(i < 10)
        {
            top[i] = 0;
            top_char[i] = 0;
            i++;
        }

        i = 0;
        while(i < length)
        {
            cods[str_char[i]]++;
            i++;
        }

        j = 0;
        while (j < 10) {
            i = 0;
            while (i < 65536) {
                if (top[j] < cods[i]) {
                    top[j] = cods[i];
                    top_char[j] = i;
                }
                i++;
            }
            cods[top_char[j]] = -1;
            top_schedule[j] = 10 * top[j] / top[0];
            j++;
        }

        i = 0;

        while(i < 11)
        {
            j = 0;
            while (j < 10) {
                System.out.print(" ");
                if (top_schedule[j] + 1 == (11 - i)) {
                    if (top[j] < 10)
                        System.out.print(" ");
                    System.out.print(top[j]);
                }
                else if (top_schedule[j] > (10 - i))
                    System.out.print(" #");
                else
                    System.out.print("  ");
                j++;
            }
            System.out.println();
            i++;
        }
        j = 0;
        while (j < 10) {
            System.out.print("  ");
            System.out.print((char) top_char[j]);

            j++;
        }
        System.out.println();


    }
}