package associacao;

public class Associado {
	int numero;
	String nome;
	String telefone;
	long dataAssociacao;
	long nascimento;

	public Associado(int numero, String nome, String telefone, long dataAssociacao, long nascimento) {
		this.numero = numero;
		this.nome = nome;
		this.telefone = telefone;
		this.dataAssociacao = dataAssociacao;
		this.nascimento = nascimento;
	}

	public int getNumero() {
		return numero;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public long getDataAssociacao() {
		return dataAssociacao;
	}

	public long getNascimento() {
		return nascimento;
	}
}
