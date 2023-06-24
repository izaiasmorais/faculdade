package studentsAge;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		List newList = new List();

		ModeList newModeList = new ModeList();

		int option = 0;

		while (true) {
			System.out.println(
					"1. Add age \n" +
							"2. List ages\n" +
							"3. Newest and oldest\n" +
							"4. Average\n" +
							"5. Median\n" +
							"6. Add mode\n" +
							"7. List modes\n" +
							"8. Finish\n" +
							"Type a option: ");

			option = scan.nextInt();

			if (option == 1) {
				System.out.println("Type a age: ");
				int age;
				age = scan.nextInt();
				newList.addAge(age);
				System.out.println("Successfully added age!\n");
			} else if (option == 2) {
				newList.listAges();
			} else if (option == 3) {
				System.out.println("Newest student: " + newList.getNewest());
				System.out.println("Oldest student: " + newList.getOldest());
			} else if (option == 4) {
				System.out.println("Average age: " + newList.getAverage());
			} else if (option == 5) {
				System.out.println("Median: " + newList.getMedian());
			} else if (option == 6) {
				System.out.println("Type a age: ");
				int age;
				age = scan.nextInt();
				newModeList.addAge(age);
				System.out.println("Successfully added mode!\n");
			} else if (option == 7) {
				newModeList.listModes();
				newModeList.listMode();
			} else if (option == 8) {
				break;
			}
		}
		scan.close();
	}
}
