package linkedList;

public class Lista {
	private Node inicio;
	private Node fim;
	private int tamanho = 0;

	public Lista() {
		this.inicio = null;
		this.fim = null;
		this.tamanho = 0;
	}

	public int getTamanho() {
		return this.tamanho;
	}

	public void adicionarInicio(String nome) {

		if (this.jaExiste(nome)) {
			System.out.println("Este nome ja esta na lista!");
			return;
		}

		Node novo = new Node(nome);

		if (this.getTamanho() == 0) {
			this.inicio = novo;
			this.fim = novo;
		} else {
			novo.prox = this.inicio;
			this.inicio.ant = novo;
			this.inicio = novo;
		}
		this.tamanho++;
	}

	public void adicionarFim(String nome) {

		if (this.jaExiste(nome)) {
			System.out.println("Este nome ja esta na lista!");
			return;
		}

		Node novo = new Node(nome);

		if (this.getTamanho() == 0) {
			this.inicio = novo;
		} else {
			novo.ant = this.fim;
			fim.prox = novo;
		}
		this.fim = novo;
		this.tamanho++;
	}

	public void listarNomes() {
		Node atual = this.inicio;

		System.out.print("Lista: ");

		while (atual != null) {
			if (atual.prox != null) {
				System.out.print(atual.nome + " - ");
			} else {
				System.out.print(atual.nome);
			}
			atual = atual.prox;
		}
		System.out.println("");
	}

	public void listarNomesInvertido() {
		Node atual = this.fim;

		System.out.print("Lista invertida: ");

		while (atual != null) {
			if (atual.ant != null) {
				System.out.print(atual.nome + " - ");
			} else {
				System.out.print(atual.nome);
			}
			atual = atual.ant;
		}
		System.out.println("");
	}

	public void deletar(String nome) {
		Node atual = this.inicio;

		while (atual != null) {
			if (atual.nome.equals(nome)) {

				if (atual == this.inicio && atual == this.fim) {
					this.inicio = null;
					this.fim = null;
				} else if (atual == this.inicio) {
					this.inicio = atual.prox;
					this.inicio.ant = null;
				} else if (atual == this.fim) {
					this.fim = atual.ant;
					this.fim.prox = null;
				} else {
					atual.ant.prox = atual.prox;
					atual.prox.ant = atual.ant;
				}

				tamanho--;
				break;
			}

			atual = atual.prox;
		}

		if (atual == null) {
			System.out.println("Este nome nao esta na lista");
		}
	}

	public Boolean jaExiste(String nome) {
		Node atual = this.inicio;
		Boolean exists = false;

		while (atual != null) {
			if (atual.nome.equals(nome)) {
				exists = true;
			}
			atual = atual.prox;
		}

		return exists;
	}
}
