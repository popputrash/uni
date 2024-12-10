package partyView;


import partyController.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SouthPanelButtons extends JPanel
{
    private Controller controller;
    private int width;
    private int height;

    JButton btnOK, btnChange, btnDelete, btnStatistics;


    public SouthPanelButtons(Controller controller, int width, int height) {
        this.controller = controller;
        this.width = width;
        this.height = height;

        int buttonHeight = height - 4;  //4 = margin
        int buttonWidth = width/6;

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        JPanel pnlButtons = new JPanel();

        btnOK = new JButton("Add");
        Dimension dim = new Dimension(buttonWidth,buttonHeight);
        btnOK.setSize(dim);

        btnChange = new JButton("Change");
        btnChange.setSize(dim);

        btnDelete = new JButton("Delete");
        btnChange.setSize(dim);

        btnStatistics = new JButton("Statistics");
        btnChange.setSize(dim);

        pnlButtons.add(btnOK);
        pnlButtons.add(btnChange);
        pnlButtons.add(btnDelete);
        pnlButtons.add(btnStatistics);

        add(pnlButtons);

        addListeners();

    }

    private void addListeners() {
        ActionListener listener = new ButtonActionListeners();

        btnOK.addActionListener(listener);
        btnChange.addActionListener(listener);
        btnDelete.addActionListener(listener);
        btnStatistics.addActionListener(listener);
    }

    //This is an inner class handling button events
    class ButtonActionListeners implements ActionListener {

        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == btnOK) {
                controller.buttonPressed(ButtonType.Add);
            }
            else if (e.getSource()== btnChange) {
                controller.buttonPressed(ButtonType.Change);
            }
            else if (e.getSource() == btnDelete) {
                controller.buttonPressed(ButtonType.Delete);
            }
            else if (e.getSource() == btnStatistics) {
                controller.buttonPressed(ButtonType.Statistics);
            }

        }
    }


}//class
