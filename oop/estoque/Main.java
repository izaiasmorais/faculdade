package estoque;

public class Main {
	public static void main(String[] args) {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Produto prod1 = new Produto(-12, "Shampoo", 5, 2, forn1);

		estoque.incluir(prod1);

		estoque.comprar(-12, 5, 1);

		System.out.println(estoque.quantidade(-12));
	}
}
