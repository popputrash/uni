import java.util.Scanner;
public class DentistReception{
  public static void main(String[] args) {
    double cost = 0;
    int treatment = 0;


    final double checkUp = 600.00;
    final double cleaning = 300.00;
    final double cavity = 1500.00;

    Scanner input = new Scanner(System.in);  //create scanner for input
    System.out.println("Ange vilken ebhandling som utförts: ");
    System.out.println("Kontroll: 1");
    System.out.println("Rengöring: 2");
    System.out.println("Laga hål: 3");
    System.out.println("Avbryt: -1");
    treatment = input.nextInt();

    
    //Skriv din kod här för att lägga till rätt kostnad till variablen cost


    System.out.println("Kostnaden utan avdrag är:"+cost);

    //Skriv din kod här för att se om avdrag ska göras


  }
}
