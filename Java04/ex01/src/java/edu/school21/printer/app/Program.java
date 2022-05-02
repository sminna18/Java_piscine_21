import java.awt.image.BufferedImage;
import java.io.IOException;

class Program {
    static final int WHITE = -1;
    static final int BLACK = -16777216;

    Program() {
    }

    public static void main(String[] args) throws IOException {
        BufferedImage image = startup.read_file(args[2]);

        for(int i = 0; i < image.getHeight(); ++i) {
            for(int j = 0; j < image.getWidth(); ++j) {
                if (image.getRGB(j, i) == -1) {
                    System.out.print(args[0]);
                } else if (image.getRGB(j, i) == -16777216) {
                    System.out.print(args[1]);
                } else {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }

    }
}