import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Program {
    public static void main(String[] args) throws IOException {

        String signature = "/Users/sminna/Desktop/Piscine/src/signatures.txt";
        String output = "/Users/sminna/Desktop/Piscine/src/result.txt";

        FileOutputStream fileResult = new FileOutputStream(output);
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                String read = scanner.nextLine();
                if (read.equals("42"))
                    System.exit(0);
                String input = read;

                Map<String, String> map = new HashMap<String, String>();

//            String input = "/Users/sminna/Desktop/Piscine/src/Des.jpg";
                FileInputStream fileSignature = new FileInputStream(signature);


                byte[] bytes = new byte[8];
                FileInputStream fileScanner = new FileInputStream(input);


                int lol = fileScanner.read(bytes, 0, 8);

                StringBuilder sb = new StringBuilder();
                for (byte b : bytes) {
                    sb.append(String.format("%02X ", b));
                }

                String sb_string = new String(sb);

                Scanner new_scan = new Scanner(fileSignature);

                while (new_scan.hasNextLine()) {
                    String line = new_scan.nextLine();
                    String hashed_line[] = line.split(",");
                    map.put(hashed_line[0], hashed_line[1].replace("\\s+", ""));
                }

                for (Map.Entry<String, String> xxx : map.entrySet()) {
                    if (sb_string.contains(xxx.getValue())) {
                        fileResult.write(xxx.getKey().getBytes());
                        fileResult.write("\n".getBytes());
                        System.out.println("PROCESSED");
                    }
//                System.out.println(sb);
                }
            }
            catch (Exception error) {
                System.out.println("Error\n");
            }

        }
    }
}