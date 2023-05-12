import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Aluno {
	private String nome;
	private int idade;

	public Aluno(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}

	public String getNome() {
		return nome;
	}

	public int getIdade() {
		return idade;
	}
}

public class alunosIdade {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<Aluno> alunos = new ArrayList<>();

		System.out.print("Digite a quantidade de alunos: ");
		int quantidadeAlunos = scanner.nextInt();

		for (int i = 0; i < quantidadeAlunos; i++) {
			System.out.print("Digite o nome do aluno: ");
			String nome = scanner.next();

			System.out.print("Digite a idade do aluno: ");
			int idade = scanner.nextInt();

			Aluno aluno = new Aluno(nome, idade);
			alunos.add(aluno);
		}

		Aluno alunoMaisVelho = alunos.get(0);
		Aluno alunoMaisNovo = alunos.get(0);
		int somaIdades = 0;

		for (Aluno aluno : alunos) {
			if (aluno.getIdade() > alunoMaisVelho.getIdade()) {
				alunoMaisVelho = aluno;
			}

			if (aluno.getIdade() < alunoMaisNovo.getIdade()) {
				alunoMaisNovo = aluno;
			}

			somaIdades += aluno.getIdade();
		}

		double mediaIdades = (double) somaIdades / alunos.size();

		System.out.println("Aluno mais velho: " + alunoMaisVelho.getNome() + ", idade: " + alunoMaisVelho.getIdade());

		System.out.println("Aluno mais novo: " + alunoMaisNovo.getNome() + ", idade: " + alunoMaisNovo.getIdade());

		System.out.println("Média de idades: " + mediaIdades);
	}
}
