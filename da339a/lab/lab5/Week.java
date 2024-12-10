import java.util.Scanner;

public class Week {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int dag;  
    System.out.println("Mata indag 1-7");
    dag = in.nextInt(); 
    if (dag == 1) {
      System.out.println("måndag ");
    }
    if (dag == 2) {
      System.out.println("tisdag ");
    }
    if (dag == 3) {
      System.out.println("onsdag ");
    }
    if (dag == 4) {
      System.out.println("torsdag ");
    }
    if (dag == 5) {
      System.out.println("fredag ");
    }
    if (dag == 6) {
      System.out.println("lördag ");
    }
    if (dag == 7) {
      System.out.println("söndag");
    }
  }
}