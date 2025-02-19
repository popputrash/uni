import se.mau.DA343A.VT25.assignment1.Direction;
import se.mau.DA343A.VT25.assignment1.MovedOutOfGridException;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Airplane extends Element {
    Direction direction;
    Random rand;
    public Airplane(int row, int col, BufferedImage icon) {
        super(row, col, icon, true);
        setPollution(10);
        setSpeed(5);
        rand = new Random();
        direction = Direction.values()[rand.nextInt(Direction.values().length)];
    }

    @Override
    public void update() throws MovedOutOfGridException {
        switch (direction) {
            case EAST -> setRow(getRow() + getSpeed());
            case WEST -> setRow(getRow() - getSpeed());
            case NORTH -> setColumn(getColumn() - getSpeed());
            case SOUTH -> setColumn(getColumn() + getSpeed());
        }
        super.update();
    }

}
