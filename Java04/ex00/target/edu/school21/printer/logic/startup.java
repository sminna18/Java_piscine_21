import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class startup {
    static final String BMP = "/Users/sminna/Desktop/Java04/ex00/target/edu/school21/printer/";

    public startup() {
    }

    static BufferedImage read_file(String name) throws IOException {
        File file = new File(BMP + name);
        return ImageIO.read(file);
    }
}