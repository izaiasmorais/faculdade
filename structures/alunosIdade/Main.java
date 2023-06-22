package alunosIdade;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		List newList = new List();

		int option = 0;

		while (true) {
			System.out.println(
					"1. Add age \n" +
							"2. List ages\n" +
							"3. Encerrar\n" +
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
				newList.getNewestAndOldest();
				break;
			}
		}

		scan.close();
	}
}
