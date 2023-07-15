package estoque;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteEstoque {
	@Test
	public void produtosAbaixoDoEstoqueMinimo() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Fornecedor forn2 = new Fornecedor(19, "Ambev");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		Produto prod2 = new Produto(15, "Cerveja", 5, 1, forn2);
		Produto prod3 = new Produto(18, "Cerveja Pilsen", 5, 1, forn2);
		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.incluir(prod3);
		estoque.comprar(12, 3, 5);
		estoque.comprar(15, 1, 10);
		estoque.comprar(18, 5, 8);
		Produto[] produtos = { prod1, prod2 };
		Produto[] produtosAbaixoDoMinimo = estoque.estoqueAbaixoDoMinimo();
		assertArrayEquals(produtosAbaixoDoMinimo, produtos);
	}

	@Test
	public void compraItens() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 10, 4);
		// Verifica se a quantidade de itens foi atualizada corretamente
		assertTrue(estoque.quantidade(12) == 10);
	}

	@Test
	public void compraItensComPrecoNegativo() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 10, -5);
		assertTrue(estoque.quantidade(12) == 0);
	}

	@Test
	public void vendeItens() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 20, 5);
		// Verifica se o valor total da venda esta correto
		assertTrue(estoque.vender(12, 1) == 5);
	}

	@Test
	public void verificaFornecedorProduto() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		Fornecedor fornecedor = estoque.fornecedor(12);
		assertEquals(forn1, fornecedor);
	}
}
