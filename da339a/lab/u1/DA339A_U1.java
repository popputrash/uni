
/*
	Author: <full namne>
	Id: <computer id from Mau>
	Study program: <Sys/DT/...>
*/
import java.util.Scanner;

public class DA339A_U1 {
	static Scanner input = new Scanner(System.in); // can be removed if another solution is used that do not require this
																									// scanner-object

	// an array to use for testing, replace as needed to test your code
	static String[][] guestList = { { "Adam Ason", "35" },
			{ "Berta Bson", "70" },
			{ "Ceasar Cson", "12" },
			{ "", "" },
			{ "", "" },
			{ "", "" },
			{ "", "" },
			{ "", "" },
			{ "", "" },
			{ "Hej Hej", "20" },
	};

	/*
	 * Parameters for methods below may NOT be changed and HAVE TO BE USED as
	 * intended for a passing grade.
	 */

	/**
	 * This method makes sure to print the information about the guests
	 * (name and age) from the guest list in the terminal for the user.
	 * The print out shall show name, age and a number representing the guest's
	 * place in the guest list.
	 * Empty places in the guest list will NOT be shown.
	 */
	public static void printGuestInformation() {
		System.out.println("You chose to print the information of the guests in the guest list"); // you don't need to keep
																																															// this line
		int index = 0;
		System.out.println("Name\t\t\tAge\tIndex");
		System.out.println("------------------------------");
		for (String[] guest : guestList) {
			if (guest[0] != "" && guest[1] != "") {
				System.out.format("%-20s\t%s\t%s", guest[0], guest[1], index);
				System.out.println();
			}
			index++;
		}
	}

	/**
	 * This method makes sure to print the whole guest list in the terminal for the
	 * user.
	 * If a place in the guest list contains data for a guest the guest's name and
	 * age is shown.
	 * If a place in the guest list is empty a text stating that the place in empty
	 * is shown.
	 * In both cases a number representing the place in the guest list is shown.
	 */
	public static void printGuestList() {
		System.out.println("You chose to print the guest list"); // you don't need to keep this line
		int index = 0;
		System.out.println("Name\t\t\tAge\tIndex");
		System.out.println("------------------------------");
		for (String[] guest : guestList) {
			if (guest[0] != "" && guest[1] != "") {
				System.out.format("%-20s\t%s\t%s", guest[0], guest[1], index);
				System.out.println();
			} else {
				System.out.format("%-20s\t\t%s", "Empty", index);
				System.out.println();
			}
			index++;
		}
	}

	/**
	 * This method makes sure to print statistics about the guest list to the user.
	 * The following statistics shall be shown:
	 * - Total number guests
	 * - Total number of adult guests
	 * - Total number of child guests
	 * - Who the oldest guest is and thier age
	 * - Who the youngest guest is and their age
	 */
	public static void printStatistics() {
		System.out.println("You chose to print statistics"); // you don't need to keep this line
		int adults = 0;
		int children = 0;
		String[] oldest = { "", "0" };
		String[] youngest = { "", "200" };
		for (String[] guest : guestList) {
			if (guest[0] != "") {
				if (Integer.parseInt(guest[1]) >= Integer.parseInt(oldest[1]))
					oldest = guest;
				if (Integer.parseInt(guest[1]) <= Integer.parseInt(youngest[1]))
					youngest = guest;
				if (guest[0] != "" && Integer.parseInt(guest[1]) > 13) {
					adults++;
				} else if (guest[0] != "") {
					children++;
				}
			}
		}
		System.out.printf("guests: %d (children: %d adults: %d)\n", (children + adults), children, adults);
		System.out.printf("youngest: %s - %s\n", youngest[0], youngest[1]);
		System.out.printf("oldest: %s - %s\n", oldest[0], oldest[1]);
	}

	/**
	 * This method adds a guest to the guest list.
	 * The information that is added to the guest list is defined in the method
	 * parameters.
	 * If a user tries to add a guest to a full guest list an error messages
	 * informing the user
	 * about this will be shown and no guest added to the list.
	 * 
	 * @param name The name of the guest to add
	 * @param age  The age of the guest to add as a String
	 */
	public static void addGuest(String name, String age) {
		System.out.println("adding name: " + name + " age:" + age);
		for (int i = 0; i < guestList.length; i++) {
			if (guestList[i][0] == "") {
				guestList[i][0] = name;
				guestList[i][1] = age;
				return;
			}
		}
		System.out.println("no space in guestlist");
	}

	/**
	 * This method changes the name of a specific guest in the guest list.
	 * Which guest's name is changed is decided by a method parameter.
	 * If a user tries to change the name at a place in the list that has
	 * no current guest an error message informing the user about this
	 * will be shown and no data in the guest list is changed.
	 * 
	 * @param index   The index of the guest whose name shall be changed.
	 * @param newName The new name of the guest at place given by index.
	 */
	public static void changeNameOfGuest(int index, String newName) {
		System.out.println("You chose to change the name of a guest"); // you don't need to keep this line
		if (index > guestList.length || index < 0) {
			System.out.printf("index %d doesnt exist in guestlist \n", index);
			return;
		}
		if (guestList[index][0] != "") {
			String oldName = guestList[index][0];
			guestList[index][0] = newName;
			System.out.printf("guest %s name changed to %s \n", oldName, newName);
		} else {
			System.out.printf("no guest at index %d \n", index);
		}
	}

	/**
	 * This method changes the age of a specific guest in the guest list.
	 * Which guest's age is changed is decided by a method parameter.
	 * If a user tries to change the age at a place in the list that has
	 * no current guest an error message informing the user about this
	 * will be shown and no data in the guest list is changed.
	 * 
	 * @param index  The index of the guest whose age shall be changed.
	 * @param newAge The new age, as a String, of the guest at place given by index.
	 */
	public static void changeAgeOfGuest(int index, String newAge) {
		System.out.println("You chose to change the age of a guest"); // you don't need to keep this line
		if (index >= guestList.length || index < 0) {
			System.out.printf("index %d doesnt exist in guestlist \n", index);
			return;
		}
		if (guestList[index][0] != "") {
			String oldAge = guestList[index][1];
			guestList[index][1] = newAge;
			System.out.printf("guest %s age changed to %s \n", guestList[index][0], newAge);
		} else {
			System.out.printf("no guest at index %d \n", index);
		}
	}

	/**
	 * This method removes a guest at a given index from the guest list.
	 * The data at this index is replaced by empty Strings.
	 * If no guest exists at the given index an error message informing
	 * the user about this will be shown and no data in the guest list is changed.
	 * 
	 * @param index The index of the place where a guest is to be removed.
	 */
	public static void removeGuest(int index) {
		System.out.println("You chose to remove a guest"); // you don't need to keep this line
		if (index < 0 || index >= guestList.length) {
			System.out.printf("index %d doesnt exist in the guestlist \n", index);
			return;
		}
		if (guestList[index][0] == "") {
			System.out.println("no guest at given index");
			return;
		}
		guestList[index][0] = "";
		guestList[index][1] = "";
	}

	/**
	 * This method changes places in the guest list for the data existing
	 * at those places given by the parameters. It is possible to change
	 * places between data even if one or both places contains empty strings.
	 * This gives the user the possibility to change the place of one guest
	 * to an empty place in the guest list.
	 * If the two parameters have the same value an error message informing
	 * the user about this will be shown and no data in the guest list is changed.
	 * 
	 * @param index1 First index involved in the change of places
	 * @param index2 Second index involved in the change of places
	 */
	public static void changePlaces(int index1, int index2) {
		System.out.println("You chose to switch places between index " + index1 + " and " + index2); // you don't need to
																																																	// keep this lin e
		if (index1 >= guestList.length || index2 >= guestList.length || index1 < 0 || index2 < 0) {
			System.out.println("index out of bounds");
			return;
		}
		if (index1 == index2) {
			System.out.println("same index of guests");
			return;
		}
		String[] placeholder = guestList[index2];
		guestList[index2] = guestList[index1];
		guestList[index1] = placeholder;
	}

	/**
	 * This method prints the program menu in the terminal for the user to view.
	 */
	public static void printMenu() {
		System.out.println("");
		System.out.println("");
		System.out.println("------------------");
		System.out.println("1: print guestlist");
		System.out.println("2: statistics");
		System.out.println("3: add guest");
		System.out.println("4: change name of guest");
		System.out.println("5: change age of guest");
		System.out.println("6: change places of two guests");
		System.out.println("7: print guest information");
		System.out.println("8: remove guest");
		System.out.println("9: exit");
	}

	/**
	 * This method reads from System.in and parses it into an Integer, otherwise it
	 * loops until it
	 * gets an integer as unout
	 * 
	 * @return Input from System.in as Integer.
	 */
	public static int parseIntegerInput() {
		int i;
		while (!input.hasNextInt()) {
			System.out.println("only numerical values allowed");
			input.next();
		}
		i = input.nextInt();
		input.nextLine();
		return i;
	}

	/**
	 * This method reads the menu choice from the user and returns the choice the
	 * user made as an integer.
	 * 
	 * @return The menu choice made by the user represented by an integer value of
	 *         type int.
	 */
	public static int readMenuChoice() {
		System.out.println("Make menu choice:");
		int choice = parseIntegerInput();
		return choice;
	}

	public static void main(String[] args) {
		int index;
		boolean run = true;

		do {
			printMenu();
			int choice = readMenuChoice();
			switch (choice) {
				case 1:
					printGuestList();
					break;
				case 2:
					printStatistics();
					break;
				case 3:
					System.out.println("input guest name:");
					String name = input.nextLine();
					System.out.println("input guest age:");
					int age = parseIntegerInput();
					addGuest(name, String.valueOf(age));
					break;
				case 4:
					System.out.println("input guest index:");
					index = parseIntegerInput();
					System.out.println("input guest new name");
					String newName = input.nextLine();
					changeNameOfGuest(index, newName);
					break;
				case 5:
					System.out.println("input guest index:");
					index = parseIntegerInput();
					System.out.println("input guest new age");
					String newAge = input.nextLine();
					changeAgeOfGuest(index, newAge);
					break;
				case 6:
					System.out.println("input index of first guest");
					index = parseIntegerInput();
					System.out.println("input index of second guest");
					int index2 = parseIntegerInput();
					changePlaces(index, index2);
					break;
				case 7:
					printGuestInformation();
					break;
				case 8:
					System.out.println("input index of guest");
					index = parseIntegerInput();
					removeGuest(index);
					break;
				case 9:
					run = false;
					break;
				default:
					System.out.println("invalid menu choice");
			}
		} while (run);
		System.out.println("bye.");
	}
}
