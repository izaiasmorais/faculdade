package estoque;

public class Fornecedor {
	private int cnpj;
	private String nome;

	public Fornecedor(int cnpj, String nome) {
		this.cnpj = cnpj;
		this.nome = nome;
	}

	public int getCnpj() {
		return cnpj;
	}

	public String getNome() {
		return nome;
	}
}
