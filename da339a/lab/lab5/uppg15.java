import java.util.Scanner;
public class uppg15 {
    public static void main(String[] args){
        //intialisera scanner
        Scanner input = new Scanner(System.in);

        //initialisera variabler
        boolean vip = false;
        boolean mindreÄnTio;
        float pris = 100;
        float rabatt = 0.9f;
        float vipFrakt = 50f;
        float normalFrakt = 69f;
        
        //prompt
        System.out.println("Skriv antal varor:");
        
        //preliminär kostnad
        float antal = input.nextFloat();
        float kostnad = antal * pris;
        
        //testa antal varor
        if(antal > 10){
            mindreÄnTio = false;
            kostnad = kostnad * rabatt;
            System.out.println("är du vip?y/n");
            String vipInput = input.next();
            if(vipInput.equals("y")){    
                vip = true;
                kostnad += vipFrakt;
                System.out.println("vip"); 
            }else {
                kostnad += normalFrakt;
            }
        }else {
            kostnad += normalFrakt;
            mindreÄnTio = true;
        }
    System.out.println(mindreÄnTio ? "Antal objekt som du vill köpa är 10 eller färre." : "Antal objekt som du vill köpa är mer än 10");
    System.out.println(vip ?  "Du är VIP och får reducerad frakt!" : "Du är inte VIP och får betala normal pris på frakt");
    System.out.printf("Kostnaden blir %s SEK", kostnad);
    
    }
}
