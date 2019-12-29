import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String x;
        int n;
        Scanner in = new Scanner(System.in);
        x = in.nextLine();
        n = in.nextInt();
        Compression c = new Compression(x, n);

    }
}
