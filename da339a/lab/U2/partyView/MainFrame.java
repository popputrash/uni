package partyView;

import javax.swing.*;

import partyController.Controller;

//This is the main GUI class
public class MainFrame extends JFrame {
    private int width = 810;
    private int height = 410;

    Controller controller;
    MainPanel mainPanel;

    public MainFrame(Controller controller) {
        this.controller = controller;
        setupFrame();
    }

    //standard settings
    public void setupFrame() {
        final int offsetX = width/5;
        final int offsetY = height / 5;

        setSize(width, height);
        setTitle("Party Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(offsetX, offsetY);

        mainPanel = new MainPanel(controller, width, height);
        setContentPane(mainPanel);
        setResizable(false);
        pack();

        setVisible(true);
    }

    public String getFirstNameText() {
        /* get the CenterPanelGuestInput-object from the MainPanel-object and
           from the CenterPanelGuestInput-object get
           the content (that is a String-object) from the JTextField-object
         */
        return mainPanel.getPnlCenter().getFirstNameText();
    }

    public void setFirstNameText(String str) {
        mainPanel.getPnlCenter().setFirstNameText(str);
    }

    public String getLastNameText() {
        return mainPanel.getPnlCenter().getLastNameText();
    }

    public void setLastNameText(String str) {
        mainPanel.getPnlCenter().setLastNameText(str);
    }

    public String getAgeText() {
        return mainPanel.getPnlCenter().getAgeText();
    }

    public void setAgeText(String str) {
        mainPanel.getPnlCenter().setAgeText(str);
    }

    public String getStreetText() {
        return mainPanel.getPnlCenter().getStreetText();
    }

    public void setStreetText(String str) {
        mainPanel.getPnlCenter().setStreetText(str);
    }

    public String getCityText() {
        return mainPanel.getPnlCenter().getCityText();
    }

    public void setCityText(String str) {
        mainPanel.getPnlCenter().setCityText(str);
    }

    public String getZipCodeText() {
        return mainPanel.getPnlCenter().getZipCodeText();
    }

    public void setZipCodeText(String str) {
        mainPanel.getPnlCenter().setZipCodeText(str);
    }

    public void setNumGuest(String str) {
        mainPanel.getPnlCenter().setTxtNumGuest(str);
    }

    public Object getCountriesItem(){
        return mainPanel.getPnlCenter().getCountriesItem();
    }

    public void setCountriesItem(Object item){
        mainPanel.getPnlCenter().setCountriesItem(item);
    }


    public void errMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void updateGuestList(String [] stringList) {
        mainPanel.getPnlEast().updateGuestList(stringList);
    }

    public int getListIndex() {
        return mainPanel.getPnlEast().getListIndex();
    }
}
