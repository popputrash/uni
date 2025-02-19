import se.mau.DA343A.VT25.assignment1.Direction;
import se.mau.DA343A.VT25.assignment1.IsLand;
import se.mau.DA343A.VT25.assignment1.MovedOutOfGridException;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Bike extends Element{
    private Direction direction;
    IsLand is = new IsLand();
    public Bike(int row, int col, BufferedImage icon) {
        super(row, col, icon, true);
        setPollution(0);
        setSpeed(1);
    }

    @Override
    public void update() throws MovedOutOfGridException {
        Random rand = new Random();
        switch(rand.nextInt(4)){
            case 0:
                if(!is.isLand(getRow(), getColumn()-getSpeed()))return;
                setColumn(getColumn()-getSpeed());
                break;
            case 1:
                if(!is.isLand(getRow(), getColumn()+getSpeed()))return;
                setColumn(getColumn()+getSpeed());
                break;
            case 2:
                if(!is.isLand(getRow()+getSpeed(), getColumn()))return;
                setRow(getRow()+getSpeed());
                break;
            case 3:
                if(!is.isLand(getRow()-getSpeed(), getColumn()))return;
                setRow(getRow()-getSpeed());
                break;
        }
        super.update();
    }
}
