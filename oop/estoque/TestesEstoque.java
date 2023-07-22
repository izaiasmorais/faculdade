package estoque;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestesEstoque {

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

	// Comprar
	// ----------------------------------------------------------------------------------------

	@Test
	public void compraItensComPrecoNegativo() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);

		estoque.incluir(prod1);
		estoque.comprar(12, 5, -5);
		assertTrue(estoque.quantidade(12) == 0);

	}

	@Test
	public void compraProdutoNaoExistente() {
		Estoque estoque = new Estoque();

		estoque.comprar(12, 10, 5);

		assertTrue(estoque.quantidade(12) == 0);
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
	public void compraItensAtualizacaoValor() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 20, 4);
		estoque.comprar(12, 20, 6);

		// Verifica se a quantidade de itens foi atualizada corretamente
		assertTrue(estoque.vender(12, 1) == 10);
	}

	@Test
	public void compraItensComQuantNegativo() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, -1, 0);
		assertTrue(estoque.quantidade(12) == 0);
	}

	// Incluir -----------------------------------------------------------------
	@Test
	public void incluirProdutoJaIncluido() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		Produto prod2 = new Produto(12, "Sorvete", 5, 0, forn1);
		estoque.incluir(prod2);
		estoque.comprar(12, 10, 1);
		assertTrue(estoque.vender(12, 1) == 2);
	}

	@Test
	public void incluirProdutoAindaNaoIncluido() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 5, 3);
		assertTrue(estoque.quantidade(12) == 5);
	}

	@Test
	public void incluirProdutoComCodigoRepetido() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(19, "Gilette");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new Produto(12, "Aparelho de Barbear", 5, 1, forn2);

		estoque.incluir(prod1);
		estoque.incluir(prod2);

		estoque.comprar(12, 5, 1);

		assertTrue(estoque.vender(12, 1) == 3);
	}

	@Test
	public void incluirProdutoComCodigoNegativo() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Produto prod1 = new Produto(-12, "Shampoo", 5, 2, forn1);

		estoque.incluir(prod1);

		estoque.comprar(-12, 5, 1);

		assertTrue(estoque.quantidade(-12) == 0);
	}

	public void incluirProdutoComNomeNull() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Produto prod1 = new Produto(12, null, 5, 2, forn1);

		estoque.incluir(prod1);

		estoque.comprar(12, 5, 1);

		assertTrue(estoque.quantidade(12) == 0);
	}

	@Test
	public void incluirProdutoComDescricaoSoComEspacosEmBranco() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Produto prod1 = new Produto(12, "    ", 5, 2, forn1);

		estoque.incluir(prod1);

		estoque.comprar(12, 5, 1);

		assertTrue(estoque.quantidade(12) == 0);
	}

	@Test
	public void incluirProdutoComMinNegativo() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Produto prod1 = new Produto(12, "Condicionador", -1, 2, forn1);

		estoque.incluir(prod1);

		estoque.comprar(12, 5, 1);

		assertTrue(estoque.quantidade(12) == 0);
	}

	@Test
	public void incluirProdutoComLucroNegativo() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Produto prod1 = new Produto(12, "Condicionador", 5, -1, forn1);

		estoque.incluir(prod1);

		estoque.comprar(12, 5, 1);

		assertTrue(estoque.quantidade(12) == 0);
	}

	@Test
	public void incluirProdutoComFornecedorComNomeNull() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(-48, null);
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 5, 1);

		assertTrue(estoque.quantidade(12) == 0);

	}

	@Test
	public void incluirProdutoComFornecedorComNomeSoComEspacosEmBranco() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(-48, " ");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 5, 1);

		assertTrue(estoque.quantidade(12) == 0);
	}

	@Test
	public void incluirProdutoComFornecedorComCnpjNegativo() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(-48, "Unilever");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);

		estoque.incluir(prod1);
		estoque.comprar(12, 5, 1);

		assertTrue(estoque.quantidade(12) == 0);
	}

	@Test
	public void incluirProdutoComFornecedorComCnpjZero() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(0, "Unilever");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 5, 1);

		assertTrue(estoque.quantidade(12) == 0);
	}

	// Vender
	@Test
	public void vendeItens() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 20, 5);
		// Verifica se o valor total da venda esta correto
		assertTrue(estoque.vender(12, 1) == 10.0);
	}

	@Test
	public void vendeItensQuantidadeNegativa() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 20, 5);
		// Verifica se o valor total da venda esta correto
		assertTrue(0 == estoque.vender(12, -1));
	}

	@Test
	public void vendeItensQuantidadeMaiorQueEstoque() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 20, 5);
		// Verifica se o valor total da venda esta correto
		assertTrue(-1 == estoque.vender(12, 30));
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
