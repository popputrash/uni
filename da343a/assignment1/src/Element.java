import se.mau.DA343A.VT25.assignment1.Direction;
import se.mau.DA343A.VT25.assignment1.IElementIcon;
import se.mau.DA343A.VT25.assignment1.MovedOutOfGridException;

import java.awt.image.BufferedImage;

/**
 * Parent class for the different elements,
 * @author Maximilian Andersen
 */
public class Element implements IElementIcon{
    /**
     * The Image icon of the element
     */
    private BufferedImage icon;
    /**
     * Position variables
     */
    private int row, col;
    /**
     * Variable holding pollution dropped by element
     */
    private double pollution;
    /**
     * Variable holding whether element is movable or not
     */
    private boolean moveable;
    /**
     * Variable holding element Speed.
     */
    private int speed;


    /**
     * Constructior for element, specifiying generic attributes for all elements.
     * @param row
     * @param col
     * @param icon
     * @param moveable
     */
    public Element(int row, int col, BufferedImage icon, boolean moveable) {
        this.row = row;
        this.col = col;
        this.icon = icon;
        this.moveable = moveable;
    }

    /**
     * Getter for Icon
     * @return IconImage for the element
     */
    @Override
    public BufferedImage getIcon() {
        return icon;
    }

    /**
     * Setter for row
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * getter for row
     * @return row
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * setter for column
     * @param col
     */
    public void setColumn(int col) {
        this.col = col;
    }

    /**
     * getter for column
     * @return column
     */
    @Override
    public int getColumn() {
        return col;
    }

    /**
     * getter for speed
     * @return speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * setter for speed
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * getter for pollution
     * @return pollution
     */
    public double getPollution() {
        return pollution;
    }

    /**
     * setter for pollution
     * @param pollution
     */
    public void setPollution(double pollution) {
        this.pollution = pollution;
    }

    /**
     * getter for movable
     * @return movable
     */
    public boolean isMoveable() {
        return moveable;
    }

    /**
     * Method to update current position of an element and check whether its inside of bounds
     * Overrided by Subclasses to implement moving logic.
     * @throws MovedOutOfGridException
     */
    public void update() throws MovedOutOfGridException {
        if(getColumn() < 0 || getColumn() >= 100 || getRow() < 0 || getRow() >= 100) {
            throw new MovedOutOfGridException("Element moved out of bounds");
        }
    }

}
