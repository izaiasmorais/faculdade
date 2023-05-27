package encadeada;

public class Node {
	public String nome;
	public Node ant;
	public Node prox;

	public Node(String nome) {
		this.nome = nome;
		this.ant = null;
		this.prox = null;
	}
}
