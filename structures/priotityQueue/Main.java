package priotityQueue;

import java.util.Scanner;

public class Main {
	public static void print(String text) {
		System.out.println(text);
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int opcao = 0;

		while (true) {
			print("1. chegada de pessoa para atendimento normal\n" +
					"2. chegada de pessoa para atendimento prioritário\n" +
					"3. atendimento de uma pessoa \n" +
					"4. listar todas as pessoas da fila" +
					"5. gerar estatísticas \n" +
					"0. Sair\n");

			opcao = Integer.parseInt(scan.nextLine());

			if (opcao == 0) {
				break;
			} else if (opcao == 1) {

			} else if (opcao == 2) {

			} else if (opcao == 3) {

			} else if (opcao == 4) {

			} else if (opcao == 5) {

			}

		}

		scan.close();
	}
}
