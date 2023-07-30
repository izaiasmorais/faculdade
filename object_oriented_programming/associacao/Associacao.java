package associacao;

import java.util.ArrayList;

public class Associacao {
	int numero;
	String nome;
	Taxa taxa;
	ArrayList<Associado> associados = new ArrayList<>();
	ArrayList<Reuniao> reunioes = new ArrayList<>();

	public Associacao(int numero, String nome) {
		this.numero = numero;
		this.nome = nome;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Associado> getAssociados() {
		return associados;
	}

	public ArrayList<Reuniao> getReunioes() {
		return reunioes;
	}

	public Taxa getTaxa() {
		return taxa;
	}

	public void setTaxa(Taxa taxa) {
		this.taxa = taxa;
	}

}
