package moda;

import java.util.Scanner;

public class Moda {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int[] numbers = new int[100];
		int[] quantities = new int[100];
		int index = 0;

		System.out.println("Digite os números (0 para sair):");

		while (true) {
			int input = scanner.nextInt();
			if (input == 0) {
				break;
			}

			int existingIndex = -1;
			for (int i = 0; i < index; i++) {
				if (numbers[i] == input) {
					existingIndex = i;
					break;
				}
			}

			if (existingIndex != -1) {
				quantities[existingIndex]++;
			} else {
				numbers[index] = input;
				quantities[index] = 1;
				index++;
			}
		}

		System.out.println("Elementos no formato {number: int, quantity: int}:");
		for (int i = 0; i < index; i++) {
			System.out.println("Numero: " + numbers[i] + " | Quantidade: " + quantities[i]);
		}

		scanner.close();
	}
}
