import se.mau.DA343A.VT25.assignment1.IsLand;
import se.mau.DA343A.VT25.assignment1.MovedOutOfGridException;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Car extends Element{

    IsLand is = new IsLand();
    public Car(int row, int col, BufferedImage icon) {
        super(row, col, icon, true);
        super.setPollution(5);
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
