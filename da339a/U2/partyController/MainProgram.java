package partyController;

public class MainProgram
{
    public static void main(String[] args)
    {
        int maxNbrOfGuests = 10; // Change this line later. Only using 10 as a default value to make compilations possible.
        /* Write code to read the number of guests to start with from the user by using one of
         - JOptionPane
         - Scanner and prompt
        */

        Controller controller = new Controller(maxNbrOfGuests);
    }
}
