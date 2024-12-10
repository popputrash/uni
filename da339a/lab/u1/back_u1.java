//Maximilian Andersern
//aq3915
//THDTB24h

import java.util.Scanner;

public class DA339A_U1{
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        boolean run = true;

        String[][] guestList = {
            {"Emil", "10"},
            {"Max", "11"},
            {"", ""}, 
            {"Julia", "12"},
            {"Markus", "13"},
            {"", ""}, 
            {"Ted", "14"},
            {"Kalle", "15"},
            {"Lukas", "16"},
            {"", ""}, 
            {"Bempan", "17"},
            {"", ""},
            {"Lillan", "18"},
            {"Barbro", "19"},
            {"", ""} 
        };

        do{
            printMenu();
            String input = scanner.nextLine();
            switch(input){
                case "1":
                    printGuestList(guestList);
                    break;
                case "2":
                    guestListStatistics(guestList);
                    break;
                case "3":
                    input = scanner.nextLine();
                    addGuest(input, guestList);
                    break;
                case "q":
                    run = false;
                    break;
                default:
                    System.out.println("invalid command");
                    break;
            }

        }while(run);
    }

    public static void printMenu(){
        System.out.println("guestlist manager");
        System.out.println("------------------");
        System.out.println("1: print guestlist");
        System.out.println("2: statistics");
        System.out.println("3: add guest");
        System.out.println("q: exit");

    }

    public static void printGuestList(String[][] guestList){
        int index = 0;
        System.out.println("Name\tAge\tIndex");
        System.out.println("------------------");
        for(String[] guest : guestList){
            if(guest[0] != "" && guest[1] != ""){
                System.out.printf("%s\t%s\t%s",guest[0],guest[1],index);
                System.out.println();
            }
            index++;
        }
    }

    public static void guestListStatistics(String[][] guestList){
        int adults = 0;
        int children = 0;
        String[] oldest = {"","0"};
        String[] youngest = {"","200"};
        for(String[] guest : guestList){
            if(guest[0] != ""){
                if(Integer.parseInt(guest[1]) >= Integer.parseInt(oldest[1]))oldest = guest;
                if(Integer.parseInt(guest[1]) <= Integer.parseInt(youngest[1]))youngest = guest;
                if(guest[0] != "" && Integer.parseInt(guest[1]) > 13){
                    adults++;
                }else if(guest[0] != ""){
                    children++;
                } 
            }
        }
        System.out.printf("guests: %d (children: %d adults: %d)\n", (children + adults), children,adults);
        System.out.printf("youngest: %s - %s\n", youngest[0], youngest[1]);
        System.out.printf("oldest: %s - %s\n", oldest[0], oldest[1]);

    }

    public static void addGuest(String s, String[][] guestList){
        String[] guest = s.split(" ");
        if(guest.length > 2){
            System.out.println("invalid input");
            return;
        }
        for(int i = 0; i < guestList.length; i++){
            if(guestList[i][0] == ""){
                guestList[i] = guest;
                return;
            }
        }
    }
}
