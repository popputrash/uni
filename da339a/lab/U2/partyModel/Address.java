/*
    Namn: Maximilian Andersen
    DatorID: aq3915
    Program: THDTB24H
 */

package partyModel;

import javax.swing.*;

public class Address {
   /* Declare instance variables for street, city, zip code as String-objects
      and country by using the enum Countries
    */
    String street, city, zip;
    Countries country;

  /* Write a default constructor (with no parameters) that gives default values for instance variables.
     Set default values for instance variables by calling the other constructor
     below using the this reserved word and:
     - Alternative 1: with arguments that are default values
       of your choice that will inform a user that this value isn't really set.
     - Alternative 2: Use null for all arguments and call the constructor below and handle that there.
   */

    public Address(){
        this.street = "";
        this.city = "";
        this.zip = "";
        this.country = Countries.Unknown;
    }

  /* Write a constructor with parameters for all instance variables
     given above. Set instance variables to values from parameters.

     Check that the values for the parameters street, zipCode and city
     isn't empty strings or null before assigning the values to the
     corresponding instance variables.

     If any value is empty or null assign a default value of your choice
     that will inform a user that this value isn't really set.

     If the parameter country is null set this to Countries.Unknown
   */
  public Address(String street, String city, String zip, Countries country){
      if(street != null && !street.isEmpty()){
          this.street = street;
      }else{
          this.street = "default";
          JOptionPane.showMessageDialog(null, "invalid street, setting default value");
      }
      if(city != null && !city.isEmpty()){
          this.city = city;
      }else{
          this.city = "default";
          JOptionPane.showMessageDialog(null, "invalid city, setting default value");
      }
      if(zip != null && !zip.isEmpty()){
          this.zip = zip;
      }else{
          this.zip = "default";
          JOptionPane.showMessageDialog(null, "invalid zip, setting default value");
      }
      if(country == null){
          this.country = Countries.Unknown;
      }else{
          this.country = country;
      }
  }

  /* Implement get- and set-methods for all instance variables.
     Remember to check parameters in set-methods with the same
     rules as in the constructor above.
   */

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if(street != null && !street.isEmpty()){
            this.street = street;
        }else{
            this.street = "default";
            JOptionPane.showMessageDialog(null, "invalid street, setting default value");
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if(city != null && !city.isEmpty()){
            this.city = city;
        }else{
            this.city = "default";
            JOptionPane.showMessageDialog(null, "invalid city, setting default value");
        }
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        if(zip != null && !zip.isEmpty()){
            this.zip = zip;
        }else{
            this.zip = "default";
            JOptionPane.showMessageDialog(null, "invalid zip, setting default value");
        }
    }

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        if(country == null){
            this.country = Countries.Unknown;
            JOptionPane.showMessageDialog(null, "invalid country, setting default value");

        }else{
            this.country = country;
        }
    }
    /* Write a toString method to return a String-object made of the address details,
     formatted as one line (this will be shown in the window under "Guest Register" ).
   */

    @Override
    public String toString() {
        return street + ", " + city + ", " + zip + ", " +country;
    }
}
