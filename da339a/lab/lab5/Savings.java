import java.util.Scanner;

public class Savings {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int amount;
        int weeks;
        int sum;

        System.out.println ("Hur mycket sparar du var vecka?" );
        amount = in.nextInt();
        System.out.println ("Hur många veckor sparar du?" );
        weeks = in.nextInt();
        sum = weeks * amount;
        System.out.println("Efter "+weeks+ " veckor har du "+sum+ " kr.");
    }
}
