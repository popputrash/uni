import se.mau.DA343A.VT25.assignment2.*;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class App extends GUI implements IPlayPauseButtonPressedCallback {

    private IPlayPauseButtonPressedCallback callback;
    private final Controller controller;

    public App(BufferedImage mapImage, Controller controller) {
        super(mapImage);
        addPlayPauseButtonCallback(this);
        this.controller = controller;
    }

    @Override
    public synchronized void addPlayPauseButtonCallback(IPlayPauseButtonPressedCallback iPlayPauseButtonPressedCallback) {
        callback = iPlayPauseButtonPressedCallback;
    }

    @Override
    public synchronized void removePlayPauseButtonCallback(IPlayPauseButtonPressedCallback iPlayPauseButtonPressedCallback) {
        callback = null;
    }

    @Override
    protected void invokePlayPauseButtonCallbacks() {
        callback.playPauseButtonPressed();
    }

    @Override
    protected void onExiting() {

    }

    @Override
    public void playPauseButtonPressed() {
        System.out.println("Play pause button pressed\n");
        controller.setQueryData();
    }

}
