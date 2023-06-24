package linkedList;

import java.util.Scanner;

public class Main {
	public static void print(String text) {
		System.out.println(text);
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Lista lista = new Lista();
		int opcao = 0;

		while (true) {
			String nome = "";
			print("1. Inserir nome no inicio\n" +
					"2. Inserir nome no final\n" +
					"3. Excluir nome da lista\n" +
					"4. Listar nomes\n" +
					"5. Lista nomes em ordem inversa\n" +
					"6. Sair da aplicação\n");

			System.out.print("Escolha uma opcao: ");

			opcao = Integer.parseInt(scan.nextLine());

			if (opcao == 1) {
				System.out.print("Digite o nome que quer adicionar ao inicio: ");
				nome = scan.nextLine();
				lista.adicionarInicio(nome);
				print("");

			} else if (opcao == 2) {
				System.out.print("Digite o nome que quer adicionar ao final: ");
				nome = scan.nextLine();
				lista.adicionarFim(nome);
				print("");

			} else if (opcao == 3) {
				System.out.print("Digite o nome que quer excluir da lista: ");
				nome = scan.nextLine();
				lista.deletar(nome);
				print("");

			} else if (opcao == 4) {
				print("Total: " + lista.getTamanho());
				lista.listarNomes();
				print("");

			} else if (opcao == 5) {
				lista.listarNomesInvertido();

			} else if (opcao == 6) {
				break;
			}
		}

		scan.close();
	}
}
