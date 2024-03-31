package estoqueComProdutoPerecivel;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class TestesEstoque2 {

	// ------------------------------Pesquisar -> Produto e Produto
	// Perecivel----------------------------------------
	@Test
	public void pesquisarProdutosJaIncluidos() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(19, "Gilette");
		Fornecedor forn3 = new Fornecedor(33, "Nestle");
		Fornecedor forn4 = new Fornecedor(24, "Ambev");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new Produto(13, "Aparelho de Barbear", 5, 1, forn2);
		Produto prod3 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn3);
		Produto prod4 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn4);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.incluir(prod3);
		estoque.incluir(prod4);
		assertEquals(prod1, estoque.pesquisar(prod1.getCodigo()));
		assertEquals(prod2, estoque.pesquisar(prod2.getCodigo()));
		assertEquals(prod3, estoque.pesquisar(prod3.getCodigo()));
		assertEquals(prod4, estoque.pesquisar(prod4.getCodigo()));
	}

	@Test
	public void pesquisarProdutosAindaNaoIncluidos() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(19, "Gilette");
		Fornecedor forn3 = new Fornecedor(33, "Nestle");
		Fornecedor forn4 = new Fornecedor(24, "Ambev");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new Produto(13, "Aparelho de Barbear", 5, 1, forn2);
		Produto prod3 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn3);
		Produto prod4 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn4);

		assertNull(estoque.pesquisar(prod1.getCodigo()));
		assertNull(estoque.pesquisar(prod2.getCodigo()));
		assertNull(estoque.pesquisar(prod3.getCodigo()));
		assertNull(estoque.pesquisar(prod4.getCodigo()));
	}

	// ------------------------------Incluir -> Produto e Produto
	// Perecivel----------------------------------------
	@Test
	public void incluirProdutoAindaNaoIncluido() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(33, "Nestle");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn2);

		assertTrue(estoque.incluir(prod1));
		assertTrue(estoque.incluir(prod2));
		assertEquals(prod1, estoque.pesquisar(prod1.getCodigo()));
		assertEquals(prod2, estoque.pesquisar(prod2.getCodigo()));
	}

	@Test
	public void incluirProdutoComCodigoRepetido() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(19, "Gilette");
		Fornecedor forn3 = new Fornecedor(33, "Nestle");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new Produto(12, "Aparelho de Barbear", 5, 1, forn2);
		Produto prod3 = new ProdutoPerecivel(12, "Sorvete", 5, 2, forn3);

		estoque.incluir(prod1);
		assertFalse(estoque.incluir(prod2));
		assertFalse(estoque.incluir(prod3));
		assertEquals(prod1, estoque.pesquisar(prod1.getCodigo()));
	}

	@Test
	public void incluirProdutoRepetido() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(33, "Nestle");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn2);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		assertFalse(estoque.incluir(prod1));
		assertFalse(estoque.incluir(prod2));
	}

	@Test
	public void incluirProdutoNull() {
		Estoque estoque = new Estoque();

		assertFalse(estoque.incluir(null));
	}

	@Test
	public void incluirProdutoComCodigoNegativo() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(33, "Nestle");
		Produto prod1 = new Produto(-12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(-14, "Sorvete", 5, 2, forn2);

		assertFalse(estoque.incluir(prod1));
		assertFalse(estoque.incluir(prod2));
		assertNull(estoque.pesquisar(prod1.getCodigo()));
		assertNull(estoque.pesquisar(prod2.getCodigo()));
	}

	@Test
	public void incluirProdutoComDescricaoVazia() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(33, "Nestle");
		Produto prod1 = new Produto(12, "", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(14, "", 5, 2, forn2);

		assertFalse(estoque.incluir(prod1));
		assertFalse(estoque.incluir(prod2));
		assertNull(estoque.pesquisar(prod1.getCodigo()));
		assertNull(estoque.pesquisar(prod2.getCodigo()));
	}

	@Test
	public void incluirProdutoComDescricaoSoComEspacosEmBranco() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(33, "Nestle");
		Produto prod1 = new Produto(12, "    ", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(14, "    ", 5, 2, forn2);

		assertFalse(estoque.incluir(prod1));
		assertFalse(estoque.incluir(prod2));
		assertNull(estoque.pesquisar(prod1.getCodigo()));
		assertNull(estoque.pesquisar(prod2.getCodigo()));
	}

	@Test
	public void incluirProdutoComEstoqueMinimoNegativo() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(33, "Nestle");
		Produto prod1 = new Produto(12, "Shampoo", -5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(14, "Sorvete", -5, 2, forn2);

		assertFalse(estoque.incluir(prod1));
		assertFalse(estoque.incluir(prod2));
		assertNull(estoque.pesquisar(prod1.getCodigo()));
		assertNull(estoque.pesquisar(prod2.getCodigo()));
	}

	@Test
	public void incluirProdutoComEstoqueMinimoZero() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(33, "Nestle");
		Produto prod1 = new Produto(12, "Shampoo", 0, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(14, "Sorvete", 0, 2, forn2);

		assertTrue(estoque.incluir(prod1));
		assertTrue(estoque.incluir(prod2));
		assertEquals(prod1, estoque.pesquisar(prod1.getCodigo()));
		assertEquals(prod2, estoque.pesquisar(prod2.getCodigo()));
	}

	@Test
	public void incluirProdutoComLucroNegativo() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(33, "Nestle");
		Produto prod1 = new Produto(12, "Shampoo", 5, -2, forn1);
		Produto prod2 = new ProdutoPerecivel(14, "Sorvete", 5, -2, forn2);

		assertFalse(estoque.incluir(prod1));
		assertFalse(estoque.incluir(prod2));
		assertNull(estoque.pesquisar(prod1.getCodigo()));
		assertNull(estoque.pesquisar(prod2.getCodigo()));
	}

	// ------------------------------Incluir -> Produto ->
	// Fornecedor----------------------------------------
	@Test
	public void incluirProdutoComFornecedorNull() {
		Estoque estoque = new Estoque();
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, null);
		ProdutoPerecivel prod2 = new ProdutoPerecivel(14, "Sorvete", 5, 2, null);

		assertFalse(estoque.incluir(prod1));
		assertFalse(estoque.incluir(prod2));
		assertNull(estoque.pesquisar(prod1.getCodigo()));
		assertNull(estoque.pesquisar(prod2.getCodigo()));
	}

	@Test
	public void incluirProdutoComFornecedorComCnpjNegativo() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(-48, "Unilever");
		Fornecedor forn2 = new Fornecedor(-33, "Nestle");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn2);

		assertFalse(estoque.incluir(prod1));
		assertFalse(estoque.incluir(prod2));
		assertNull(estoque.pesquisar(prod1.getCodigo()));
		assertNull(estoque.pesquisar(prod2.getCodigo()));
	}

	@Test
	public void incluirProdutoComFornecedorComCnpjZero() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(0, "Unilever");
		Fornecedor forn2 = new Fornecedor(0, "Nestle");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn2);

		assertFalse(estoque.incluir(prod1));
		assertFalse(estoque.incluir(prod2));
		assertNull(estoque.pesquisar(prod1.getCodigo()));
		assertNull(estoque.pesquisar(prod2.getCodigo()));
	}

	@Test
	public void incluirProdutoComFornecedorComNomeVazio() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "");
		Fornecedor forn2 = new Fornecedor(33, "");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn2);

		assertFalse(estoque.incluir(prod1));
		assertFalse(estoque.incluir(prod2));
		assertNull(estoque.pesquisar(prod1.getCodigo()));
		assertNull(estoque.pesquisar(prod2.getCodigo()));
	}

	@Test
	public void incluirProdutoComFornecedorComNomeSoComEspacosEmBranco() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "    ");
		Fornecedor forn2 = new Fornecedor(33, "    ");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn2);

		assertFalse(estoque.incluir(prod1));
		assertFalse(estoque.incluir(prod2));
		assertNull(estoque.pesquisar(prod1.getCodigo()));
		assertNull(estoque.pesquisar(prod2.getCodigo()));
	}

	// ------------------------------Comprar----------------------------------------
	@Test
	public void compraProdutoNaoExistente() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(24, "Ambev");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn2);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));

		assertFalse(estoque.comprar(prod1.getCodigo(), 10, 5, null));
		assertFalse(estoque.comprar(prod2.getCodigo(), 5, 2, data));
	}

	@Test
	public void compraQuantidadeNegativaDeProdutos() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(24, "Ambev");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn2);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		assertFalse(estoque.comprar(prod1.getCodigo(), -10, 5, null));
		assertFalse(estoque.comprar(prod2.getCodigo(), -5, 2, data));
	}

	@Test
	public void compraQuantidadeZeroDeProdutos() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(24, "Ambev");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn2);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		assertFalse(estoque.comprar(prod1.getCodigo(), 0, 5, null));
		assertFalse(estoque.comprar(prod2.getCodigo(), 0, 2, data));
	}

	@Test
	public void compraProdutosComPrecoNegativo() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(24, "Ambev");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn2);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		assertFalse(estoque.comprar(prod1.getCodigo(), 10, -5, null));
		assertFalse(estoque.comprar(prod2.getCodigo(), 5, -2, data));
	}

	// ------------------------------Comprar -> Produto Nao
	// Perecivel----------------------------------------
	@Test
	public void compraProdutoNaoPerecivel() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(19, "Gilette");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new Produto(13, "Aparelho de Barbear", 5, 1, forn2);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		assertTrue(estoque.comprar(prod1.getCodigo(), 32, 1, null));
		assertTrue(estoque.comprar(prod1.getCodigo(), 10, 5, null));
		assertTrue(estoque.comprar(prod2.getCodigo(), 50, 20, null));
		assertTrue(estoque.comprar(prod2.getCodigo(), 5, 2.5, null));
	}

	@Test
	public void compraProdutoNaoPerecivelComData() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(19, "Gilette");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new Produto(13, "Aparelho de Barbear", 5, 1, forn2);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		data.setTime(data.getTime() + 120000);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		assertFalse(estoque.comprar(prod1.getCodigo(), 10, 5, data));
		assertFalse(estoque.comprar(prod2.getCodigo(), 5, 2.5, data));
	}

	// ------------------------------Comprar -> Produto
	// Perecivel----------------------------------------
	@Test
	public void compraProdutoPerecivel() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(33, "Nestle");
		Fornecedor forn2 = new Fornecedor(24, "Ambev");
		Produto prod1 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn2);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		data.setTime(data.getTime() + 120000);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		assertTrue(estoque.comprar(prod1.getCodigo(), 24, 8, data));
		assertTrue(estoque.comprar(prod1.getCodigo(), 10, 5, data));
		assertTrue(estoque.comprar(prod2.getCodigo(), 11, 4.23, data));
		assertTrue(estoque.comprar(prod2.getCodigo(), 5, 2.5, data));
	}

	@Test
	public void compraProdutoPerecivelComValidadeMenorQueDataAtual() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(33, "Nestle");
		Fornecedor forn2 = new Fornecedor(24, "Ambev");
		Produto prod1 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn2);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		data.setTime(data.getTime() - 120000);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		assertFalse(estoque.comprar(prod1.getCodigo(), 24, 8, data));
		assertFalse(estoque.comprar(prod1.getCodigo(), 10, 5, data));
		assertFalse(estoque.comprar(prod2.getCodigo(), 11, 4.23, data));
		assertFalse(estoque.comprar(prod2.getCodigo(), 5, 2.5, data));
	}

	@Test
	public void compraProdutoPerecivelComDataNull() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(33, "Nestle");
		Fornecedor forn2 = new Fornecedor(24, "Ambev");
		Produto prod1 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn2);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		data.setTime(data.getTime() + 120000);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		assertFalse(estoque.comprar(prod1.getCodigo(), 10, 5, null));
		assertFalse(estoque.comprar(prod2.getCodigo(), 5, 2.5, null));
	}

	// ------------------------------Vender----------------------------------------
	@Test
	public void vendaProduto() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(24, "Ambev");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn2);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		data.setTime(data.getTime() + 120000);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.comprar(prod1.getCodigo(), 10, 5, null);
		estoque.comprar(prod1.getCodigo(), 10, 2.5, null);
		estoque.comprar(prod1.getCodigo(), 10, 7.5, null);
		estoque.comprar(prod2.getCodigo(), 5, 2.5, data);
		estoque.comprar(prod2.getCodigo(), 5, 1.25, data);
		estoque.comprar(prod2.getCodigo(), 5, 3.75, data);
		assertEquals(3 * 15, estoque.vender(12, 3), 0);
		assertEquals(3 * 5, estoque.vender(15, 3), 0);
	}

	@Test
	public void vendaProdutoNaoExistente() {
		Estoque estoque = new Estoque();

		assertEquals(-1, estoque.vender(12, 8), 0);
	}

	@Test
	public void vendaQuantidadeNegativaDeProdutos() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(24, "Ambev");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn2);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		data.setTime(data.getTime() + 120000);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.comprar(prod1.getCodigo(), 10, 5, null);
		estoque.comprar(prod2.getCodigo(), 5, 2.5, data);
		assertEquals(-1, estoque.vender(12, -3), 0);
		assertEquals(-1, estoque.vender(15, -3), 0);
	}

	@Test
	public void vendaQuantidadeZeroDeProdutos() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(24, "Ambev");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn2);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		data.setTime(data.getTime() + 120000);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.comprar(prod1.getCodigo(), 10, 5, null);
		estoque.comprar(prod2.getCodigo(), 5, 2.5, data);
		assertEquals(-1, estoque.vender(12, 0), 0);
		assertEquals(-1, estoque.vender(15, 0), 0);
	}

	@Test
	public void vendaQuantidadeMaiorQueEstoque() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(24, "Ambev");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn2);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		data.setTime(data.getTime() + 120000);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.comprar(prod1.getCodigo(), 10, 5, null);
		estoque.comprar(prod2.getCodigo(), 5, 2.5, data);
		estoque.vender(12, 5);
		estoque.vender(15, 2);
		assertEquals(-1, estoque.vender(12, 12), 0);
		assertEquals(-1, estoque.vender(15, 6), 0);
	}

	@Test
	public void vendaQuantidadeIgualAoEstoque() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(24, "Ambev");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn2);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		data.setTime(data.getTime() + 120000);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.comprar(prod1.getCodigo(), 10, 5, null);
		estoque.comprar(prod2.getCodigo(), 5, 2.5, data);
		assertEquals(10 * 15, estoque.vender(12, 10), 0);
		assertEquals(5 * 5, estoque.vender(15, 5), 0);
	}

	// ------------------------------Vender ->
	// Produto----------------------------------------
	@Test
	public void vendaProdutoNaoPerecivel() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(19, "Gilette");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new Produto(13, "Aparelho de Barbear", 5, 1, forn2);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.comprar(prod1.getCodigo(), 10, 5, null);
		estoque.comprar(prod1.getCodigo(), 10, 5, null);
		estoque.comprar(prod2.getCodigo(), 5, 2.5, null);
		estoque.comprar(prod2.getCodigo(), 5, 2.5, null);
		assertEquals(3 * 15, estoque.vender(12, 3), 0);
		assertEquals(3 * 5, estoque.vender(13, 3), 0);
	}

	// ------------------------------Vender -> Produto
	// Perecivel----------------------------------------
	@Test
	public void vendaProdutoPerecivel() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(33, "Nestle");
		Fornecedor forn2 = new Fornecedor(24, "Ambev");
		Produto prod1 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn2);
		Date data1 = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		Date data2 = new Date();
		Date data3 = new Date();
		data2.setTime(data1.getTime() + 120000);
		data3.setTime(data1.getTime() + 240000);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.comprar(prod1.getCodigo(), 10, 5, data1);
		estoque.comprar(prod1.getCodigo(), 10, 5, data2);
		estoque.comprar(prod1.getCodigo(), 10, 5, data3);
		estoque.comprar(prod2.getCodigo(), 5, 2.5, data1);
		estoque.comprar(prod2.getCodigo(), 5, 2.5, data2);
		estoque.comprar(prod2.getCodigo(), 10, 2.5, data3);
		assertEquals(3 * 15, estoque.vender(14, 3), 0);
		assertEquals(3 * 5, estoque.vender(15, 3), 0);
	}

	@Test
	public void vendaProdutoPerecivelVencido() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(33, "Nestle");
		Fornecedor forn2 = new Fornecedor(24, "Ambev");
		Produto prod1 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn2);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		data.setTime(data.getTime() + 120000);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.comprar(prod1.getCodigo(), 10, 5, data);
		estoque.comprar(prod2.getCodigo(), 5, 2.5, data);
		data.setTime(data.getTime() - 120000);
		assertEquals(-1, estoque.vender(14, 3), 0);
		assertEquals(-1, estoque.vender(15, 3), 0);
	}

	// ------------------------------Estoque abaixo do
	// minimo----------------------------------------
	@Test
	public void produtosAbaixoDoEstoqueMinimo() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(19, "Gilette");
		Fornecedor forn3 = new Fornecedor(33, "Nestle");
		Fornecedor forn4 = new Fornecedor(24, "Ambev");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new Produto(13, "Aparelho de Barbear", 5, 1, forn2);
		Produto prod3 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn3);
		Produto prod4 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn4);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		data.setTime(data.getTime() + 120000);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.incluir(prod3);
		estoque.incluir(prod4);
		estoque.comprar(prod1.getCodigo(), 10, 5, null);
		estoque.comprar(prod2.getCodigo(), 10, 2.5, null);
		estoque.comprar(prod3.getCodigo(), 10, 5, data);
		estoque.comprar(prod4.getCodigo(), 20, 2.5, data);
		estoque.vender(12, 9);
		estoque.vender(13, 1);
		estoque.vender(14, 2);
		estoque.vender(15, 18);
		ArrayList<Produto> abaixoMinActual = estoque.estoqueAbaixoDoMinimo();
		assertEquals(2, abaixoMinActual.size());
		for (Produto produto : abaixoMinActual) {
			assertTrue(produto == prod1 || produto == prod4);
		}
	}

	@Test
	public void zeroProdutosAbaixoDoEstoqueMinimo() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(19, "Gilette");
		Fornecedor forn3 = new Fornecedor(33, "Nestle");
		Fornecedor forn4 = new Fornecedor(24, "Ambev");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new Produto(13, "Aparelho de Barbear", 5, 1, forn2);
		Produto prod3 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn3);
		Produto prod4 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn4);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		data.setTime(data.getTime() + 120000);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.incluir(prod3);
		estoque.incluir(prod4);
		estoque.comprar(prod1.getCodigo(), 10, 5, null);
		estoque.comprar(prod2.getCodigo(), 10, 2.5, null);
		estoque.comprar(prod3.getCodigo(), 10, 5, data);
		estoque.comprar(prod4.getCodigo(), 20, 2.5, data);
		estoque.vender(12, 1);
		estoque.vender(13, 5);
		estoque.vender(14, 5);
		estoque.vender(15, 13);
		ArrayList<Produto> abaixoMinActual = estoque.estoqueAbaixoDoMinimo();
		assertEquals(0, abaixoMinActual.size());
	}

	// ------------------------------Estoque
	// Vencido----------------------------------------
	@Test
	public void produtosComLotesVencidos() throws InterruptedException {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(19, "Gilette");
		Fornecedor forn3 = new Fornecedor(33, "Nestle");
		Fornecedor forn4 = new Fornecedor(24, "Ambev");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new Produto(13, "Aparelho de Barbear", 5, 1, forn2);
		Produto prod3 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn3);
		Produto prod4 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn4);
		Produto prod5 = new ProdutoPerecivel(16, "Cerveja Pilsen", 5, 1, forn4);
		Date data1 = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		data1.setTime(data1.getTime() + 120000);
		Date data2 = new Date();
		data2.setTime(data1.getTime());

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.incluir(prod3);
		estoque.incluir(prod4);
		estoque.incluir(prod5);
		estoque.comprar(prod1.getCodigo(), 10, 5, null);
		estoque.comprar(prod2.getCodigo(), 30, 2.5, null);
		estoque.comprar(prod3.getCodigo(), 10, 5, data1);
		estoque.comprar(prod3.getCodigo(), 20, 5, data2);
		estoque.comprar(prod4.getCodigo(), 5, 2.5, data2);
		estoque.comprar(prod4.getCodigo(), 5, 2.5, data2);
		estoque.comprar(prod5.getCodigo(), 5, 2.5, data1);
		estoque.comprar(prod5.getCodigo(), 5, 2.5, data1);
		data1.setTime(data1.getTime() - 120000);
		Thread.sleep(100);
		ArrayList<Produto> produtosVencidos = estoque.estoqueVencido();
		assertEquals(2, produtosVencidos.size());
		for (Produto produto : produtosVencidos) {
			assertTrue(produto == prod3 || produto == prod5);
		}
	}

	@Test
	public void zeroProdutosComLotesVencidos() throws InterruptedException {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(19, "Gilette");
		Fornecedor forn3 = new Fornecedor(33, "Nestle");
		Fornecedor forn4 = new Fornecedor(24, "Ambev");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new Produto(13, "Aparelho de Barbear", 5, 1, forn2);
		Produto prod3 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn3);
		Produto prod4 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn4);
		Produto prod5 = new ProdutoPerecivel(16, "Cerveja Pilsen", 5, 1, forn4);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		data.setTime(data.getTime() + 120000);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.incluir(prod3);
		estoque.incluir(prod4);
		estoque.incluir(prod5);
		estoque.comprar(prod1.getCodigo(), 10, 5, null);
		estoque.comprar(prod2.getCodigo(), 30, 2.5, null);
		estoque.comprar(prod3.getCodigo(), 10, 5, data);
		estoque.comprar(prod3.getCodigo(), 20, 5, data);
		estoque.comprar(prod4.getCodigo(), 5, 2.5, data);
		estoque.comprar(prod4.getCodigo(), 5, 2.5, data);
		estoque.comprar(prod5.getCodigo(), 5, 2.5, data);
		estoque.comprar(prod5.getCodigo(), 5, 2.5, data);
		ArrayList<Produto> produtosVencidos = estoque.estoqueVencido();
		Thread.sleep(100);
		assertEquals(0, produtosVencidos.size());
	}

	// ------------------------------Quantidade
	// Vencida----------------------------------------
	@Test
	public void quantidadeDeUnidadesVencidas() throws InterruptedException {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(19, "Gilette");
		Fornecedor forn3 = new Fornecedor(33, "Nestle");
		Fornecedor forn4 = new Fornecedor(24, "Ambev");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new Produto(13, "Aparelho de Barbear", 5, 1, forn2);
		Produto prod3 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn3);
		Produto prod4 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn4);
		Produto prod5 = new ProdutoPerecivel(16, "Cerveja Pilsen", 5, 1, forn4);
		Date data1 = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		data1.setTime(data1.getTime() + 120000);
		Date data2 = new Date();
		data2.setTime(data1.getTime());

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.incluir(prod3);
		estoque.incluir(prod4);
		estoque.incluir(prod5);
		estoque.comprar(prod1.getCodigo(), 10, 5, null);
		estoque.comprar(prod2.getCodigo(), 30, 2.5, null);
		estoque.comprar(prod3.getCodigo(), 10, 5, data2);
		estoque.comprar(prod3.getCodigo(), 20, 5, data2);
		estoque.comprar(prod4.getCodigo(), 10, 2.5, data1);
		estoque.comprar(prod4.getCodigo(), 2, 2.5, data1);
		estoque.comprar(prod4.getCodigo(), 30, 2.5, data2);
		estoque.comprar(prod5.getCodigo(), 25, 2.5, data1);
		estoque.comprar(prod5.getCodigo(), 70, 2.5, data2);
		data1.setTime(data1.getTime() - 120000);
		Thread.sleep(100);
		try {
			assertEquals(12, estoque.quantidadeVencidos(15));
			assertEquals(25, estoque.quantidadeVencidos(16));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void zeroUnidadesVencidas() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(19, "Gilette");
		Fornecedor forn3 = new Fornecedor(33, "Nestle");
		Fornecedor forn4 = new Fornecedor(24, "Ambev");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new Produto(13, "Aparelho de Barbear", 5, 1, forn2);
		Produto prod3 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn3);
		Produto prod4 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn4);
		Produto prod5 = new ProdutoPerecivel(16, "Cerveja Pilsen", 5, 1, forn4);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		data.setTime(data.getTime() + 120000);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.incluir(prod3);
		estoque.incluir(prod4);
		estoque.incluir(prod5);
		estoque.comprar(prod1.getCodigo(), 10, 5, null);
		estoque.comprar(prod2.getCodigo(), 30, 2.5, null);
		estoque.comprar(prod3.getCodigo(), 10, 5, data);
		estoque.comprar(prod3.getCodigo(), 20, 5, data);
		estoque.comprar(prod4.getCodigo(), 10, 2.5, data);
		estoque.comprar(prod4.getCodigo(), 2, 2.5, data);
		estoque.comprar(prod4.getCodigo(), 30, 2.5, data);
		estoque.comprar(prod5.getCodigo(), 25, 2.5, data);
		estoque.comprar(prod5.getCodigo(), 70, 2.5, data);
		try {
			assertEquals(0, estoque.quantidadeVencidos(12));
			assertEquals(0, estoque.quantidadeVencidos(13));
			assertEquals(0, estoque.quantidadeVencidos(15));
			assertEquals(0, estoque.quantidadeVencidos(16));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void quantidadeDeUnidadesVencidasDeProdutoInexistente() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(19, "Gilette");
		Fornecedor forn3 = new Fornecedor(33, "Nestle");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new Produto(13, "Aparelho de Barbear", 5, 1, forn2);
		Produto prod3 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn3);
		Date data1 = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		Date data2 = new Date();
		data2.setTime(data1.getTime() + 120000);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.incluir(prod3);
		estoque.comprar(prod1.getCodigo(), 10, 5, null);
		estoque.comprar(prod2.getCodigo(), 30, 2.5, null);
		estoque.comprar(prod3.getCodigo(), 10, 5, data2);
		estoque.comprar(prod3.getCodigo(), 20, 5, data1);
		try {
			assertEquals(0, estoque.quantidadeVencidos(15));
			assertEquals(0, estoque.quantidadeVencidos(16));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
