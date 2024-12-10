import java.util.Scanner;
public class MaxNumber{
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);  //create scanner for input

    System.out.println("Ange tal 1: "); //read first number
    int nbr1 = input.nextInt();

    System.out.println("Ange tal 2: "); //read second number
    int nbr2 = input.nextInt();

    if(nbr1==nbr2){
      System.out.println("Talen är lika stora!");
    }
    else if(nbr1>nbr2){
      System.out.println("Tal 1 är störst!");
    }
    else {
      System.out.println("Tal 2 är störst!");
    }
  }
}
