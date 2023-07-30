package estoqueComProdutoPerecivel;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class EstoqueComProdutoPerecivelTest {
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
	public void verificaFornecedorProduto() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		Fornecedor fornecedor = estoque.fornecedor(12);
		assertEquals(forn1, fornecedor);
	}

	// ------------------------------Incluir -> Produto e Produto
	// Perecivel----------------------------------------
	@Test
	public void incluirProdutoJaIncluido() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		Produto prod2 = new Produto(12, "Sorvete", 5, 0, forn1);
		assertFalse(estoque.incluir(prod2));
	}

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
	public void incluirProdutoNull() {
		Estoque estoque = new Estoque();

		assertFalse(estoque.incluir(null));
	}

	@Test
	public void incluirProdutoComCodigoNegativoOuZero() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(33, "Nestle");
		Produto prod1 = new Produto(-12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(0, "Sorvete", 5, 2, forn2);

		assertFalse(estoque.incluir(prod1));
		assertFalse(estoque.incluir(prod2));
		assertNull(estoque.pesquisar(prod1.getCodigo()));
		assertNull(estoque.pesquisar(prod2.getCodigo()));
	}

	public void incluirProdutoComNomeNull() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(33, "Nestle");
		Produto prod1 = new Produto(-12, null, 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(-14, null, 5, 2, forn2);

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
	public void incluirProdutoComMinNegativoOuZero() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(33, "Nestle");
		Produto prod1 = new Produto(-12, "Condicionador", -1, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(-14, "Shampoo", 0, 2, forn2);

		assertFalse(estoque.incluir(prod1));
		assertFalse(estoque.incluir(prod2));
		assertNull(estoque.pesquisar(prod1.getCodigo()));
		assertNull(estoque.pesquisar(prod2.getCodigo()));
	}

	@Test
	public void incluirProdutoComLucroNegativoOuZero() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(33, "Nestle");
		Produto prod1 = new Produto(-12, "Condicionador", 5, -1, forn1);
		Produto prod2 = new ProdutoPerecivel(-14, "Shampoo", 5, 0, forn2);

		assertFalse(estoque.incluir(prod1));
		assertFalse(estoque.incluir(prod2));
		assertNull(estoque.pesquisar(prod1.getCodigo()));
		assertNull(estoque.pesquisar(prod2.getCodigo()));
	}

	@Test
	public void incluirProdutoComFornecedorComNomeNull() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(-48, null);
		Fornecedor forn2 = new Fornecedor(-33, null);
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
		Fornecedor forn1 = new Fornecedor(-48, " ");
		Fornecedor forn2 = new Fornecedor(-33, " ");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn2);

		assertFalse(estoque.incluir(prod1));
		assertFalse(estoque.incluir(prod2));
		assertNull(estoque.pesquisar(prod1.getCodigo()));
		assertNull(estoque.pesquisar(prod2.getCodigo()));
	}

	@Test
	public void incluirProdutoComFornecedorComCnpjNegativoOuZero() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(-48, "Unilever");
		Fornecedor forn2 = new Fornecedor(0, "Nestle");
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

	// ------------------------------Comprar----------------------------------------

	@Test
	public void compraItens() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.incluir(prod2);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));

		// Verifica se a quantidade de itens foi atualizada corretamente
		assertTrue(estoque.comprar(12, 10, 4, null));
		assertTrue(estoque.comprar(15, 10, 4, data));

	}

	@Test
	public void compraItensAtualizacaoValor() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 20, 4, null);
		estoque.comprar(12, 20, 6, null);

		// Verifica se a quantidade de itens foi atualizada corretamente
		assertTrue(estoque.vender(12, 1) == 10);
	}

	@Test
	public void compraItensComQuantNegativoOuZero() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn1);

		estoque.incluir(prod1);
		estoque.incluir(prod2);

		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));

		assertFalse(estoque.comprar(12, -1, 5, null));
		assertFalse(estoque.comprar(12, -1, 5, null));
		assertFalse(estoque.comprar(15, 0, 5, data));
		assertFalse(estoque.comprar(15, -5, 5, data));
	}

	@Test
	public void compraItensComPrecoNegativo() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn1);

		estoque.incluir(prod1);
		estoque.incluir(prod2);

		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));

		assertFalse(estoque.comprar(12, 5, -5, null));
		assertFalse(estoque.comprar(15, 5, -5, data));

	}

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

	// ------------------------------Vender -> Produto
	// Perecivel----------------------------------------

	@Test
	public void vendaProdutoPerecivel() throws InterruptedException {
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
		Thread.sleep(100);
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
		// correção do teste a data usada não era data.setTime(data.getTime() + 120000);
		data.setTime(data.getTime() + 120000);
		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.comprar(prod1.getCodigo(), 10, 5, data);
		estoque.comprar(prod2.getCodigo(), 5, 2.5, data);
		data.setTime(data.getTime() - 120000);
		assertEquals(-1, estoque.vender(14, 3), 0);
		assertEquals(-1, estoque.vender(15, 3), 0);
	}

	@Test
	public void vendeItens() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 20, 5, null);
		// Verifica se o valor total da venda esta correto
		assertTrue(estoque.vender(12, 1) == 10.0);
	}

	@Test
	public void vendeItensQuantidadeNegativa() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 20, 5, null);
		// Verifica se o valor total da venda esta correto
		assertTrue(-1 == estoque.vender(12, -1));
	}

	@Test
	public void vendeItensQueNaoExistem() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 20, 5, null);
		// Verifica se o valor total da venda esta correto
		assertTrue(-1 == estoque.vender(120, -1));
	}

	@Test
	public void vendeItensQuantidadeMaiorQueEstoque() {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 20, 5, null);
		// Verifica se o valor total da venda esta correto
		assertTrue(-1 == estoque.vender(12, 30));
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

}
