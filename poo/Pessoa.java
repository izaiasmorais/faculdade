package poo;

public class Pessoa {
	private String nome;
	private int cpf;
	private Conta conta;

	public Pessoa(String nome, int cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public int getCpf() {
		return cpf;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		if (this.conta != null) {
			System.out.println("Não foi possível definir uma conta");
		} else {
			this.conta = conta;
		}
	}
}
