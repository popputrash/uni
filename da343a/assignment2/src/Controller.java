import com.sun.security.jgss.GSSUtil;
import se.mau.DA343A.VT25.assignment2.*;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

public class Controller {
    private Weather weather;
    private App app;
    private MyLogger logger;
    private ImageResources ir;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Thread weatherThread, loggerThread;
    private Map<Integer, String> sensors;
    private ArrayList<GridTemperature> temps;
    private Timer timer;
    boolean queryData = true;
    private Buffer<String> logBuffer;

    public Controller() throws IOException {
        sensors = new HashMap<>();
        temps = new ArrayList<>();
        timer = new Timer();
        logBuffer = new Buffer<>();
        ir = new ImageResources();
        intializeLoggerThread();
        initializeWeatherThread();
        initializeGUI();
        dis = (DataInputStream) weather.getInput();
        dos = (DataOutputStream) weather.getOutput();
        querySensorList();
        querySensorData();
    }


    public void initializeGUI(){
        app = new App(ir.getMapImage(), this);
        app.startGUIOnNewThread();
    }

    public void initializeWeatherThread(){
        weather = new Weather(new IsLandFromMaskImage(ir.getMapIsLandMaskImage()));
        weatherThread = new Thread(weather);
        weatherThread.start();
    }

    public void intializeLoggerThread() throws IOException {
        logger = new MyLogger(logBuffer);
        loggerThread = new Thread(logger);
        loggerThread.start();
    }

    public void querySensorList() throws IOException {
        dos.writeInt(1);
        dos.writeInt(1);
        dos.flush();

        int a = dis.readInt();
        int v = dis.readInt();
        int nr = dis.readInt();
        for(int i = 0; i < nr; i++) {
            int id = dis.readInt();
            String name = dis.readUTF();
            System.out.println(id + " " + name);
            sensors.put(id, name);
        }
        System.out.println("query finished----");
    }

    public void querySensorData(){
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                temps = new ArrayList<>();
                if(queryData) {
                    for(var s : sensors.entrySet()) {
                        try {
                            dos.writeInt(1);
                            dos.writeInt(3);
                            //System.out.println(s.getKey() + " " + s.getValue());
                            dos.writeInt(s.getKey());
                            dos.flush();

                            int version = dis.readInt();
                            int msg = dis.readInt();


                            if(msg == 4) {
                                int sensorId = dis.readInt();
                                int row = dis.readInt();
                                int col = dis.readInt();
                                double temp = dis.readDouble();

                                //System.out.println(col + " " + row + " " + temp);
                                logBuffer.put("col: "+col + " row: " + row + " temp: " + temp + "C");
                                temps.add(new GridTemperature(row, col, 10));

                            }else{
                                System.out.println("fel messegetype");

                            }


                        } catch (IOException e) {
                            System.out.printf(e.getMessage());
                        }

                        SwingUtilities.invokeLater(()-> {
                            app.setTemperatures(temps);
                            app.repaint();
                        });

                    }
                }else{
                    System.out.println("stopped");
                }

            }

        }, 0, 500);
    }

    public void setQueryData(){
        this.queryData = !queryData;
    }

}
