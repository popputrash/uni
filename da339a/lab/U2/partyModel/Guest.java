/*
    Namn: Maximilian Andersen
    DatorID: aq3915
    Program: THDTB24H
 */

package partyModel;

import javax.swing.*;

public class Guest {
    /* Declare instance variables for first name, last name as String-objects,
       and age as an int and address as an Address-object.
     */
    String name, lastName;
    int age;
    Address address;

    /* Write a default constructor (no parameters) that gives default values for
       instance variables and creates the Address-object using
       the default constructor for class Address.
       Use default values for first name, last name and age of your choice
       that will inform a user that this value isn't really set 
       and that will work for calculating statistics.

       You can handle this in this constructor or call
       the constructor below with null for all values and handle that there.
     */
    public Guest(){
        this(null, null, -1, null);
    }

    /* Write a constructor with parameters for all instance variables
       given above where the address is a reference to an Address-object
       and first name and last name are of type String adn age an int.

       Check that the values for the parameters for first name and last name
       isn't empty strings or null before assigning the values to the corresponding
       instance variables. If any value is empty or null assign a default value of your choice
       that will inform a user that this value isn't really set.

       Check the value of the parameter for age so that this is not null or a value
       less than 0 (negative age is not allowed). If age is a value less than 0 
       or null set the value of age to 0.

       If the parameter for the Address-object is null create an Address-object
       by using the default constructor for class Address.
     */

    public Guest(String name, String lastName, int age, Address address){
        if(name != null && !name.isEmpty()){
            this.name = name;
        }else{
            this.name = "default";
            JOptionPane.showMessageDialog(null, "invalid name, setting default value");
        }

        if(lastName != null && !lastName.isEmpty()){
            this.lastName = lastName;
        }else{
            this.lastName = "default";
            JOptionPane.showMessageDialog(null, "invalid lastname, setting default value");
        }

        if(age >= 0){
            this.age = age;
        }else{
            JOptionPane.showMessageDialog(null, "invalid age, setting default value (-1)");
            this.age = -1;
        }

        if(address != null){
            this.address = address;
        }else{
            this.address = new Address();
        }
    }

    public Guest(String name, String lastName, int age, String city, String street, String zip, Countries country){
        this(name, lastName, age, new Address(street, city, zip, country));
    }
    /* Write a constructor with parameters for all instance variables
       given above and all instance variables for the address.
       Create the Address-object with the values given in the parameters.
       Let the Address class take care of assigning default values if any
       parameters for this object is an empty string or null.
   */

    /* Implement get- and set-methods for all instance variables,
       including variables matching the instance variables of
       the Address-object (those call getters and setters in the Address-object).

       Remember to check parameters in set-methods with the same
       rules as in the constructor above.
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null && !name.isEmpty()){
            this.name = name;
        }else{
            this.name = "default";
            JOptionPane.showMessageDialog(null, "invalid name, setting default value");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName != null && !lastName.isEmpty()){
            this.lastName = lastName;
        }else{
            this.lastName = "default";
            JOptionPane.showMessageDialog(null, "invalid lastname, setting default value");
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age >= 0){
            this.age = age;
        }else{
            JOptionPane.showMessageDialog(null, "invalid age, setting defualt value (-1)");
            this.age = -1;
        }
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setStreet(String street){
        this.address.setStreet(street);
    }
    public String getStreet() {
        return this.address.getStreet();
    }

    public void setCity(String city){
        this.address.setCity(city);
    }
    public String getCity() {
        return this.address.getCity();
    }

    public void setZip(String zip){
        this.address.setZip(zip);
    }
    public String getZip() {
        return this.address.getZip();
    }

    public void setCountry(Countries country){
        this.address.setCountry(country);
    }

    public Countries getCountry() {
        return this.address.getCountry();
    }
    /* Write a toString method to return a String made of first name,
       last name adn age and the address (by calling the toString method of the Address-object)
       formatted in one line (this will be shown in the window under "Guest Register" ).
     */

    @Override
    public String toString() {
        return String.format("%s, %s, %d, %s",name, lastName, age, address.toString());
    }
}
