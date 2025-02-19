import se.mau.DA343A.VT25.assignment1.Direction;
import se.mau.DA343A.VT25.assignment1.IsLand;
import se.mau.DA343A.VT25.assignment1.MovedOutOfGridException;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Subclass for a bus,
 * handles moving and pollution drop by a bus
 * @author Maximilian Andersen
 */
public class Bus extends Element {
    /**
     * Random object, used to randomize initial directiomn
     */
    Random rand;
    /**
     * Direction variable
     */
    Direction direction;
    /**
     * IsLand object used to determine if travel would land outside land
     */
    IsLand is = new IsLand();

    /**
     * Constructior for bus, calls super constructor with passed params
     * and sets pollution value, speed and direction.
     * @param i
     * @param i1
     * @param busImage
     */
    public Bus(int i, int i1, BufferedImage busImage) {
        super(i, i1, busImage, true);
        rand = new Random();
        setPollution(8);
        setSpeed(1);
        direction = Direction.values()[rand.nextInt(Direction.values().length)];
    }

    /**
     * Update method to handle travel with bus.
     * Moves in the randomized direction until certain condition is met then
     * updates direction clockwise.
     * throws exeption if object moved outside grid.
     * @throws MovedOutOfGridException
     */
    @Override
    public void update() throws MovedOutOfGridException {
        switch (direction) {
            case EAST:
                if(!is.isLand(getRow() + getSpeed(), getColumn()))return;
                setRow(getRow() + getSpeed());
                if(getRow() % 8 == 0){
                    direction = Direction.SOUTH;
                }
                break;
            case WEST:
                if(!is.isLand(getRow() - getSpeed(), getColumn()))return;
                setRow(getRow() - getSpeed());
                if(getRow() % 8 == 0){
                    direction = Direction.NORTH;
                }
                break;
            case SOUTH:
                if(!is.isLand(getRow(), getColumn() + getSpeed()))return;
                setColumn(getColumn() + getSpeed());
                if(getColumn() % 8 == 0){
                    direction = Direction.WEST;
                }
                break;
            case NORTH:
                if(!is.isLand(getRow(), getColumn() - getSpeed()))return;
                setColumn(getColumn() - getSpeed());
                if(getColumn() % 8 == 0){
                    direction = Direction.EAST;
                }
                break;

        }
        super.update();
    }
}
