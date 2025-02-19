import java.awt.image.BufferedImage;

public class Woodland extends Element{
    public Woodland(int row, int col, BufferedImage icon) {
        super(row, col, icon, false);
        setPollution(-5);
    }

}
