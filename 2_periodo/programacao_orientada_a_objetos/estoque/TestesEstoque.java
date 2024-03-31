package estoque;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestesEstoque {
	@Test
	public void incluiProdutosComMesmoCodigo() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Fornecedor forn2 = new Fornecedor(19, "Ambev");
		Produto prod1 = new Produto(12, "Sorvete", 5, 2, forn1);
		Produto prod2 = new Produto(12, "Cerveja", 5, 1, forn2);
		estoque.incluir(prod1);
		estoque.incluir(prod2);
		Produto[] produtos = { prod1 };
		Produto[] produtosAbaixoDoMinimo = estoque.estoqueAbaixoDoMinimo();
		assertArrayEquals(produtos, produtosAbaixoDoMinimo);
	}

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
	public void compraItensProdutoNaoIncluido() {
		Estoque estoque = new Estoque();
		estoque.comprar(12, 10, 4);
	}

	@Test
	public void compraQuantidadeNegativaDeItens() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, -10, 4);
		assertTrue(estoque.quantidade(12) == 0);
	}

	@Test
	public void compraQuantidadeZeroDeItens() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 0, 4);
		assertTrue(estoque.quantidade(12) == 0);
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
		// Verifica se o valor total da venda est� correto
		assertTrue(2 * 5 == estoque.vender(12, 1));
	}

	@Test
	public void vendeItensProdutoNaoIncluido() {
		Estoque estoque = new Estoque();
		estoque.vender(12, 1);
	}

	@Test
	public void quantidadeAposVendaProdutos() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 20, 5);
		estoque.vender(12, 10);
		assertEquals(10, estoque.quantidade(12));
	}

	@Test
	public void vendeMesmaQuantidadeQueEstoque() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 20, 5);
		estoque.vender(12, 20);
		assertEquals(0, estoque.quantidade(12));
	}

	@Test
	public void vendeQuantidadeNegativaDeItens() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 10, 4);
		assertTrue(-1 == estoque.vender(12, -5));
	}

	@Test
	public void vendeQuantidadeZeroDeItens() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 10, 4);
		assertTrue(-1 == estoque.vender(12, 0));
	}

	@Test
	public void vendeMaisItensQueEstoque() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 10, 4);
		assertTrue(-1 == estoque.vender(12, 1000));
	}

	@Test
	public void quantidadeProdutoNaoIncluido() {
		Estoque estoque = new Estoque();
		int retorno = estoque.quantidade(0);
		assertEquals(-1, retorno);
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

	@Test
	public void fornecedorProdutoNaoIncluido() {
		Estoque estoque = new Estoque();
		Fornecedor fornecedor = estoque.fornecedor(0);
		assertNull(fornecedor);
	}
}
