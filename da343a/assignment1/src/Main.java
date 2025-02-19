import se.mau.DA343A.VT25.assignment1.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        ImageResources imageResources = new ImageResources();
        String[] elements = {"airplane", "car", "bike", "bus", "woodland"};
        MyApp app = new MyApp(elements, imageResources);
        app.startGUIOnNewThread();

    }
}