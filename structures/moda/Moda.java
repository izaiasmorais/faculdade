package moda;

import java.util.Scanner;

public class Moda {
	public static class Posicao {
		int numero;
		int qtd;

		public Posicao(int numero, int qtd) {
			this.numero = numero;
			this.qtd = qtd;
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Posicao array[] = new Posicao[5];

		for (int i = 0; i < 5; i++) {
			System.out.println("Digite um numero: ");
			int numero = scan.nextInt();

			if (array[i] != null && array[i].numero == numero) {
				array[i].qtd = array[i].qtd + 1;
			} else {
				array[i] = new Posicao(numero, 1);
			}
		}

		for (Posicao posicao : array) {
			System.out.println("Numero: " + posicao.numero + " | " + "Quantiade: " + posicao.qtd);
		}

		scan.close();
	}
}
