import se.mau.DA343A.VT25.assignment1.AirQualityApp;
import se.mau.DA343A.VT25.assignment1.ImageResources;
import se.mau.DA343A.VT25.assignment1.IsLand;
import se.mau.DA343A.VT25.assignment1.MovedOutOfGridException;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


/**
 * Subclass for AirQualityApp
 * Implements abstract methods.
 * @author Maximilian Andersen
 */
public class MyApp extends AirQualityApp{

    ImageResources imageResources;
    List<Element> elementIcons;
    private double[][] pollution;
    private int[][] directions = new int[][]{{1,0},
                                            {0,1},
                                            {-1,0},
                                            {0,-1}};
    private IsLand isLand;

    /**
     * Constructor
     * @param elementSelectorTypeNames
     * @param imageResources
     */
    public MyApp(String[] elementSelectorTypeNames, ImageResources imageResources) {
        super(elementSelectorTypeNames, imageResources.getMapImage());
        this.imageResources = imageResources;
        elementIcons = new ArrayList<Element>();
        pollution = new double[GRID_SIZE][GRID_SIZE];
        isLand = new IsLand();
    }

    /**
     * Method to handle mouseClicked,
     * switches over combobox text and handles creation of choosen object.
     * @param i
     * @param i1
     */
    @Override
    protected void mouseClicked(int i, int i1) {
        Element elementIcon = null;
        switch (getSelectedElementType()) {
            case "airplane":
                elementIcon = new Airplane(i, i1, imageResources.getAirPlaneImage());
                break;
            case "car":
                if(!isLand.isLand(i,i1))break;  
                elementIcon = new Car(i, i1, imageResources.getCarImage());
                break;
            case "bike":
                if(!isLand.isLand(i,i1))break;
                elementIcon = new Bike(i, i1, imageResources.getBikeImage());
                break;
            case "bus":
                if(!isLand.isLand(i,i1))break;
                elementIcon = new Bus(i, i1, imageResources.getBusImage());
                break;
            case "woodland":
                for(Element e : elementIcons){
                    if(e.getRow() == i && e.getColumn() == i1 && !e.isMoveable()){
                        return;
                    }
                }
                if(!isLand.isLand(i,i1))break;
                elementIcon = new Woodland(i, i1, imageResources.getTreesImage());
                break;
            default:
                return;
        }

        if(elementIcon != null) {
            elementIcons.add(elementIcon);
        }
        super.repaint();
    }

    /**
     * Method to handle nexttimestep, loops through all elements and gets their pollution value
     * then updates their position.
     */
    @Override
    protected void buttonNextTimeStepClicked() {
        for (int i = elementIcons.size() - 1; i >= 0; i--) {
            pollution[elementIcons.get(i).getRow()][elementIcons.get(i).getColumn()] += elementIcons.get(i).getPollution();
            try {
                elementIcons.get(i).update();
            }catch (MovedOutOfGridException e){
                elementIcons.remove(i);
            }
        }
        updatePollution();
        repaint();
    }

    @Override
    protected List elementIconsToPaint() {
        return elementIcons;
    }

    private BufferedImage getIcon(String name){
        switch (name){
            case "airplane":
                return imageResources.getAirPlaneImage();
            case "car":
                return imageResources.getCarImage();
            case "bike":
                return imageResources.getBikeImage();
            default:
                return null;
        }

    }

    /**
     * Method to handle diffusion of pollution in 2d array,
     * loops through current pollution array and caluclates average pollution value
     * surrounding current cell, and sets new pulltion to that value in a temporaray array.
     * Swaps current pollution array with the new diffused array and then places all the new pollution values
     * on the map.
     */
    private void updatePollution(){
        double temp[][] = new double[pollution.length][pollution[0].length];
        double tot = 0;
        int tx, ty;
        for(int x = 0; x < GRID_SIZE; x++){
            for(int y = 0; y < GRID_SIZE; y++){
                tot = pollution[x][y];
                for(int[] direction : directions){
                    tx = x + direction[0];
                    ty = y + direction[1];
                    if(0 <= tx && tx < GRID_SIZE && 0 <= ty && ty < GRID_SIZE){
                        tot += pollution[tx][ty];
                    }
                }
                if(tot/5 >= 0){
                    temp[x][y] = tot / 5;
                }else{
                    temp[x][y] = 0;
                }
            }
        }
        pollution = temp;
        for(int x = 0; x < GRID_SIZE; x++){
            for(int y = 0; y < GRID_SIZE; y++){
                setPollution(x, y, pollution[x][y]);
            }
        }
    }
}
