import java.util.Scanner;
public class uppg10{
  public static String[][] behandling = {
    {"Kontroll","600"},
    {"Lagning hål", "1500"},
    {"Rengöring", "300"}
  };
  public static void main(String args[]){
    Scanner scanner = new Scanner(System.in);
    String[][] kvitto = new String[10][2];
    int c = 0;
    printMenu();
    int in = scanner.nextInt();
    scanner.nextLine();
    while(in != -1){
      if(c <= 9)kvitto[c] = behandling[in];
      c++;
      printMenu();
      in = scanner.nextInt();
      scanner.nextLine();
    }
    System.out.println("-----KVITTO-----");
    int summa = 0;
    for(String[] l: kvitto){
      if(l[0] != null){
        summa += Integer.parseInt(l[1]);
        System.out.printf("%-20s%skr\n",l[0], l[1]);
        
        }
    }
    System.out.println("----------------------");
    System.out.printf("%-20s%skr\n","Kostnad", summa);
    System.out.printf("%-20s%.0fkr\n","Rabatt", summa*0.1);
    System.out.printf("%-20s%.0fkr\n","Kostnad", summa-summa*0.1);
  }

  public static void printMenu(){
    int c = 0;
    for(String[] b: behandling){
      System.out.printf("%-5d%-20s%s\n",c,b[0],b[1]);
      c++;
    }
    System.out.printf("%-5d%-20s\n",++c,"Klar");
  }
}
