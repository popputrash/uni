/*
    Namn: Maximilian Andersen
    DatorID: aq3915
    Program: THDTB24H
 */

package partyController;

import javax.swing.*;

public class MainProgram
{
    public static void main(String[] args)
    {
        String input = JOptionPane.showInputDialog("Max number of guests:");
        while(!input.matches("^\\d*$")){
            JOptionPane.showMessageDialog(null,"Only numbers allowed");
            input = JOptionPane.showInputDialog("Max number of guests:");
        }

         // Change this line later. Only using 10 as a default value to make compilations possible.
        /* Write code to read the number of guests to start with from the user by using one of
         - JOptionPane
         - Scanner and prompt
        */

        Controller controller = new Controller(Integer.parseInt(input));
    }
}
