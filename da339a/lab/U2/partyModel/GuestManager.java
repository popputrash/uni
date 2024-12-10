/*
    Namn: Maximilian Andersen
    DatorID: aq3915
    Program: THDTB24H
 */

package partyModel;

import javax.swing.*;

public class GuestManager {
  /* Keep ONLY the following instance variables for the class:
     - number of guests currently stored in the list/array
       (not the total possible number of guests (use .length for this))
     - one array for the list with guests where
       the guests are objects of class Guest
  */
  private int nbrOfGuests = 0;
  private Guest[] guestList;

  /* Create a constructor with one parameter which is
     the max number of elements (guests) in the list/array.
     Create the array with the max number of elements.
     Check that the max number of Guests is larger than 0.
     If max number of guests is not larger than 0 ask the user to give a new value
     until you get a value that is larger than 0.
   */
  public GuestManager(int maxNbrOfGuests){
    System.out.println("Called constructor for GuestManager"); //You can remove this line if you want to
    if(maxNbrOfGuests > 0){
      guestList = new Guest[maxNbrOfGuests];
    }else{
      String input = JOptionPane.showInputDialog("Max number of guests:");
      while(!input.matches("^\\d*$")){
        JOptionPane.showMessageDialog(null,"Only numbers allowed");
        input = JOptionPane.showInputDialog("Max number of guests:");
      }
      guestList = new Guest[Integer.parseInt(input)];
    }

  }

  /* A method that returns the number of guests stored in
     the guest list.
   */
  public int getNbrOfGuests(){
    return nbrOfGuests;
  }

  /* Create a method to add a guest to the list with all the parameters
     matching instance variables of Guest and Address.
     In the method create a new Guest-object and let classes Guest and Address
     take care of issues with values of the parameters.
     Add the new Guest-object to the array at the first empty element (here is where you
     use the instance variable for number of guests stored in the list). If there are no
     more empty elements call a private method in GuestManager to increase the size
     of the array and then add the new Guest-object. Do not forget to update the value
     of the instance variable for number of guests in the list.
   */

  public void addGuest(String name, String lastName, int age, String city,
                       String street, String zip, Countries country){
    Guest temp = new Guest(name, lastName, age, city, street, zip, country);
    if(guestList[guestList.length-1] != null){
      increaseGuestList();
    }
    guestList[nbrOfGuests] = temp;
    nbrOfGuests++;
  }

  /* Create method to delete a guest by giving the index in the array
     for the object (guest) to delete as a parameter to the method.
     This method should call the private method moveElementsToLeft to remove
     the empty element in between other objects (may not be necessary
     if the guest removed was the last guest in the list).
     Remember to update the value of the instance variable for
     number of guests in the list.
   */

  public void deleteGuest(int index){
    guestList[index] = null;
    nbrOfGuests -= 1;
    moveElementsToLeft(index);

  }

  private void moveElementsToLeft(int index){
        /* Add code to remove empty element places that
           is not att the end of the array. Start at
           the parameter index that is the empty element
           whose gap we want to fill.

           You are not allowed to take a shortcut by using class Array or similar from a Java-library.
         */
    for(int i = index; i < nbrOfGuests; i++){
      guestList[i] = guestList[i+1];
    }
    guestList[nbrOfGuests] = null;
  }

  private void increaseGuestList(){
    /* Write code that creates a new array of Guest-objects
       that is 10 elements larger that the current array instance variable .

       Copy the current array to the larger array and after that is done
       point the instance variable to the new array.

       You are not allowed to take a shortcut by using class Array or similar from a Java-library.
     */
    Guest[] temp = guestList;
    guestList = new Guest[temp.length + 10];
    for(int i = 0; i < temp.length; i++){
      guestList[i] = temp[i];
    }
    System.out.println("increased guestList size by 10");

  }

  /* A method that returns the Guest-object at the given
     index n the parameter.
   */
  public Guest getGuestAt(int index){
    /* Add code to check that the parameter index
       has a value within the range of the array.
       If index is not in valid range return null.
       Yes, some checks are done in the Controller-object
       in this program but we do not want this class to depend
       on other classes for this, so sometimes we write redundant
       error handling in different ways to have more robust classes or code.
     */
    if(index >= nbrOfGuests){
      return null;
    }

    return guestList[index];
  }

  public String[] getInfoStrings() {
    /* Write code that returns an array of strings where each element
       represents information about one guest in the list by calling every
       Guest-object's toString method.

       The number of elements in the array should be equal to the
       number of guests currently stored in the list.
       (no strings should be created for empty places at the end of the array st)
    */

    String[] infoStrings = new String[nbrOfGuests];
    for(int i = 0; i < infoStrings.length; i++){
      if(guestList[i] == null)continue;
      infoStrings[i] = guestList[i].toString();
    }
    return infoStrings;
  }

  public String getStatistics(){
    /* Write code that calculates the following statistics and returns these as a
      formatted String using line breaks for each statistic.
      - total number of guests
      - number of adults where an adult guest is someone over the age of 13
      - number of children where a child is someone age 13 or younger
      - the name and age of the oldest guest
      - the name and age of the youngest guest

      If there are no guests in the guest list return a string that
      informs the user of this.
    */

    if(guestList[0]==null)return "Guestlist is empty";

    int adults = 0, children = 0, youngest = 0, oldest = 0;

    for(int i = 0; i < nbrOfGuests; i++){
      if(guestList[i].getAge() > 13)adults++;
      else children++;
      if(guestList[i].getAge() > guestList[oldest].getAge())oldest = i;
      if(guestList[i].getAge() < guestList[youngest].getAge())youngest = i;

    }

    //This return statement is just used for the code template. Erase and replace with code for calculating tha statistics as asked for above.
    return ("Number of guests: "+nbrOfGuests+"\n"+
            "Number of adults:"+adults+ "\n"+
            "Number of children:"+children+" \n"+
            "Oldest guest: "+guestList[oldest].getName()+ " of age " +guestList[oldest].getAge() + "\n"+
            "Youngest guest: "+guestList[youngest].getName()+ " of age " +guestList[youngest].getAge());
  
  }

}
