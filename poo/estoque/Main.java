package estoque;

public class Main {
	public static void main(String[] args) {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 20, 5);

		System.out.println(estoque.vender(12, 1));
	}
}
