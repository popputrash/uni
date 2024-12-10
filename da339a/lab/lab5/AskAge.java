import java.util.Scanner;
public class AskAge{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("ålder?");
        int age = input.nextInt();
        
        if(age <= 17){
            System.out.println("Du är ett barn");

        }else{
            System.out.println("Du är vuxen");
        }
    }
}
