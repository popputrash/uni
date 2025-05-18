
import se.mau.DA343A.VT25.assignment2.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class App extends GUI implements IPlayPauseButtonPressedCallback {

    private ArrayList<IPlayPauseButtonPressedCallback> callbacks;
    private final Controller controller;

    public App(BufferedImage mapImage, Controller controller) {
        super(mapImage);
        callbacks = new ArrayList<>();
        addPlayPauseButtonCallback(this);
        this.controller = controller;
    }

    @Override
    public synchronized void addPlayPauseButtonCallback(IPlayPauseButtonPressedCallback iPlayPauseButtonPressedCallback) {
        callbacks.add(iPlayPauseButtonPressedCallback);
    }

    @Override
    public synchronized void removePlayPauseButtonCallback(IPlayPauseButtonPressedCallback iPlayPauseButtonPressedCallback) {
        callbacks.remove(iPlayPauseButtonPressedCallback);
    }

    @Override
    protected void invokePlayPauseButtonCallbacks() {
        for(IPlayPauseButtonPressedCallback callback : callbacks) {
            callback.playPauseButtonPressed();
        }
    }

    @Override
    protected void onExiting() {
        controller.closeThreads();
    }

    @Override
    public void playPauseButtonPressed() {
        System.out.println("Play pause button pressed\n");
        controller.setQueryData();
    }

}
