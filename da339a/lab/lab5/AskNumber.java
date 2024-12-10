import java.util.Scanner;
public class AskNumber{
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);  //create scanner for input

    System.out.println("Ange ett heltal: ");
    int number = input.nextInt();
   
    if(number >= 100){
       System.out.println("Större än 100"); 
    }else{
        System.out.println("Högst 100");
    }
  }
}
