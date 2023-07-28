package produtosPereciveisComExcecao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

import produtosPereciveisComExcecao.exceptions.*;

public class EstoqueComExcecoesTest {

	@Test
	public void testIncluirProdutoAindaNaoIncluido() throws ProdutoJaCadastrado, DadosInvalidos, ProdutoInexistente {
		// Criação de objetos necessários para o teste
		Fornecedor fornecedor = new Fornecedor(123456789, "Fornecedor A");
		Produto produto = new Produto(1, "Produto A", 10, 1.5, fornecedor);

		// Criação da instância do Estoque
		Estoque estoque = new Estoque();

		// Teste de inclusão de um produto
		try {
			estoque.incluir(produto);
		} catch (ProdutoJaCadastrado | DadosInvalidos e) {
			e.printStackTrace();
		}

		// Verificação do resultado esperado
		Produto produtoEncontrado = null;
		try {
			produtoEncontrado = estoque.pesquisar(1);
		} catch (ProdutoInexistente e) {
			e.printStackTrace();
		}
		Assertions.assertEquals(produto, produtoEncontrado);
	}

	@Test
	public void incluirProdutoComMinNegativo() throws ProdutoJaCadastrado, ProdutoInexistente {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(33, "Nestle");
		Produto prod1 = new Produto(-12, "Condicionador", -1, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(-14, "Shampoo", -1, 2, forn2);

		try {
			estoque.incluir(prod1);
		} catch (DadosInvalidos e) {
			// TODO: handle exception
		}
		try {
			estoque.incluir(prod2);
		} catch (DadosInvalidos e) {
			// TODO: handle exception
		}
	}

	@Test
	public void incluirProdutoComLucroNegativo() throws ProdutoJaCadastrado, ProdutoInexistente {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(33, "Nestle");
		Produto prod1 = new Produto(-12, "Condicionador", 5, -1, forn1);
		Produto prod2 = new ProdutoPerecivel(-14, "Shampoo", 5, -1, forn2);

		try {
			estoque.incluir(prod1);
		} catch (DadosInvalidos e) {
			// TODO: handle exception
		}
		try {
			estoque.incluir(prod2);
		} catch (DadosInvalidos e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testIncluirProdutoExistente() throws DadosInvalidos, ProdutoInexistente, ProdutoJaCadastrado {
		// Criação de objetos necessários para o teste
		Fornecedor fornecedor = new Fornecedor(123456789, "Fornecedor A");
		Produto produto = new Produto(1, "Produto A", 10, 1.5, fornecedor);

		// Criação da instância do Estoque
		Estoque estoque = new Estoque();
		estoque.incluir(produto);

		// Teste de inclusão do mesmo produto novamente
		try {
			estoque.incluir(produto);
		} catch (ProdutoJaCadastrado e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testIncluirProdutosComCodigoNegativo() throws DadosInvalidos, ProdutoInexistente, ProdutoJaCadastrado {
		// Criação de objetos necessários para o teste
		Fornecedor fornecedor = new Fornecedor(123456789, "Fornecedor A");
		Produto produto = new Produto(-1, "Produto A", 10, 1.5, fornecedor);

		// Criação da instância do Estoque
		Estoque estoque = new Estoque();

		try {
			estoque.incluir(produto);
		} catch (DadosInvalidos e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testIncluirProdutosComNomeNulo() throws DadosInvalidos, ProdutoInexistente, ProdutoJaCadastrado {
		// Criação de objetos necessários para o teste
		Fornecedor fornecedor = new Fornecedor(123456789, "Fornecedor A");
		Produto produto = new Produto(1, null, 10, 1.5, fornecedor);

		// Criação da instância do Estoque
		Estoque estoque = new Estoque();

		// Teste de inclusão do mesmo produto novamente
		try {
			estoque.incluir(produto);
		} catch (DadosInvalidos e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testIncluirProdutosComNomeVazio() throws DadosInvalidos, ProdutoInexistente, ProdutoJaCadastrado {
		// Criação de objetos necessários para o teste
		Fornecedor fornecedor = new Fornecedor(123456789, "Fornecedor A");
		Produto produto = new Produto(1, "", 10, 1.5, fornecedor);
		Produto produto2 = new Produto(2, "   ", 10, 1.5, fornecedor);

		// Criação da instância do Estoque
		Estoque estoque = new Estoque();

		// Teste de inclusão do mesmo produto novamente
		try {
			estoque.incluir(produto);
			estoque.incluir(produto2);
		} catch (DadosInvalidos e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPesquisarProdutoInexistente() throws ProdutoJaCadastrado, DadosInvalidos, ProdutoInexistente {
		// Criação de objetos necessários para o teste
		// Fornecedor fornecedor = new Fornecedor(123456789, "Fornecedor A");
		// Produto produto = new Produto(1, "Produto A", 10, 1.5, fornecedor);
		// Criação da instância do Estoque
		Estoque estoque = new Estoque();

		// Teste de inclusão do mesmo produto novamente
		try {
			estoque.pesquisar(1);
		} catch (ProdutoInexistente e) {
			e.printStackTrace();
		}
	}

	@Test
	public void compraProdutoNaoPerecivel()
			throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel, ProdutoJaCadastrado {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Unilever");
		Fornecedor forn2 = new Fornecedor(19, "Gilette");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new Produto(13, "Aparelho de Barbear", 5, 1, forn2);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.comprar(prod1.getCodigo(), 10, 5, null);
		estoque.comprar(prod2.getCodigo(), 5, 2.5, null);
	}

	@Test
	public void testComprarProdutoNaoPerecivelComData() throws ProdutoJaCadastrado, DadosInvalidos, ProdutoInexistente {
		// Criação de objetos necessários para o teste
		Fornecedor fornecedor = new Fornecedor(123456789, "Fornecedor A");
		Produto produto = new Produto(2, "Produto B", 5, 2.0, fornecedor);

		// Criação da instância do Estoque
		Estoque estoque = new Estoque();

		estoque.incluir(produto);

		try {
			estoque.comprar(2, 5, 2.0, new Date());
		} catch (ProdutoNaoPerecivel e) {
			e.printStackTrace();
		}
	}

	@Test
	public void compraProdutoPerecivel()
			throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel, ProdutoJaCadastrado {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(33, "Nestle");
		Fornecedor forn2 = new Fornecedor(24, "Ambev");
		Produto prod1 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn2);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		data.setTime(data.getTime() + 120000);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.comprar(prod1.getCodigo(), 24, 8, data);
		estoque.comprar(prod1.getCodigo(), 10, 5, data);
		estoque.comprar(prod2.getCodigo(), 11, 4.23, data);
		estoque.comprar(prod2.getCodigo(), 5, 2.5, data);

	}

	// @Test
	// public void compraProdutoPerecivelComValidadeMenorQueDataAtual()
	// throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel,
	// ProdutoJaCadastrado, ProdutoVencido {
	// Estoque estoque = new Estoque();
	// Fornecedor forn1 = new Fornecedor(33, "Nestle");
	// Fornecedor forn2 = new Fornecedor(24, "Ambev");
	// Produto prod1 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn1);
	// Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn2);
	// Date data =
	// Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
	// data.setTime(data.getTime() - 120000);

	// estoque.incluir(prod1);
	// estoque.incluir(prod2);
	// estoque.comprar(prod1.getCodigo(), 24, 8, data);
	// try {
	// estoque.comprar(prod1.getCodigo(), 10, 5, data);
	// } catch (ProdutoVencido e) {
	// // TODO: handle exception
	// }
	// try {
	// estoque.comprar(prod2.getCodigo(), 11, 4.23, data);
	// } catch (ProdutoVencido e) {
	// // TODO: handle exception
	// }
	// try {
	// estoque.comprar(prod2.getCodigo(), 5, 2.5, data);
	// } catch (ProdutoVencido e) {
	// // TODO: handle exception
	// }

	// }

	@Test
	public void vendaProdutoPerecivelComValidadeMenorQueDataAtual()
			throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel, ProdutoJaCadastrado, ProdutoVencido,
			InterruptedException {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(33, "Nestle");
		Fornecedor forn2 = new Fornecedor(24, "Ambev");
		Produto prod1 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn2);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		data.setTime(data.getTime() - 120000);

		estoque.incluir(prod1);
		estoque.incluir(prod2);
		estoque.comprar(prod1.getCodigo(), 24, 8, data);

		Thread.sleep(100);

		try {
			estoque.vender(prod1.getCodigo(), 20);
		} catch (ProdutoVencido e) {
			e.printStackTrace();
		}
	}

	@Test
	public void vendeItensQuantidadeNegativa()
			throws ProdutoJaCadastrado, DadosInvalidos, ProdutoInexistente, ProdutoNaoPerecivel, ProdutoVencido {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 20, 5, null);
		// Verifica se o valor total da venda esta correto
		try {
			estoque.vender(12, -1);
		} catch (DadosInvalidos e) {
			// TODO: handle exceptio
		}
	}

	@Test
	public void vendeItensQueNaoExistem()
			throws ProdutoJaCadastrado, DadosInvalidos, ProdutoInexistente, ProdutoNaoPerecivel, ProdutoVencido {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 20, 5, null);
		// Verifica se o valor total da venda esta correto
		try {
			estoque.vender(120, 2);
		} catch (ProdutoInexistente e) {
			// TODO: handle exception
		}
	}

	@Test
	public void vendaQuantidadeIgualAoEstoque()
			throws ProdutoJaCadastrado, DadosInvalidos, ProdutoInexistente, ProdutoNaoPerecivel, ProdutoVencido {
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

	@Test
	public void vendeItens()
			throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel, ProdutoJaCadastrado, ProdutoVencido {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 20, 5, null);
		// Verifica se o valor total da venda esta correto
		assertTrue(estoque.vender(12, 1) == 10.0);
	}

	@Test
	public void vendeItensQuantidadeMaiorQueEstoque()
			throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel, ProdutoJaCadastrado, ProdutoVencido {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.comprar(12, 20, 5, null);
		// Verifica se o valor total da venda esta correto
		try {
			estoque.vender(12, 30);
		} catch (DadosInvalidos e) {
			// TODO: handle exception
		}
	}

	@Test
	public void compraItens() throws ProdutoJaCadastrado, DadosInvalidos, ProdutoInexistente, ProdutoNaoPerecivel {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn1);
		estoque.incluir(prod1);
		estoque.incluir(prod2);
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		estoque.comprar(12, 10, 4, null);
		estoque.comprar(15, 10, 4, data);

	}

	@Test
	public void compraItensComQuantNegativo()
			throws ProdutoInexistente, ProdutoNaoPerecivel, ProdutoJaCadastrado, DadosInvalidos {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn1);

		estoque.incluir(prod1);
		estoque.incluir(prod2);

		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		try {
			estoque.comprar(12, -1, 5, null);

		} catch (DadosInvalidos e) {
			// TODO: handle exception
		}
		try {
			estoque.comprar(15, 5, -5, data);

		} catch (DadosInvalidos e) {
			// TODO: handle exception
		}

	}

	@Test
	public void compraItensComPrecoNegativo()
			throws ProdutoJaCadastrado, DadosInvalidos, ProdutoInexistente, ProdutoNaoPerecivel {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(48, "Nestle");
		Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		Produto prod2 = new ProdutoPerecivel(15, "Cerveja", 5, 1, forn1);

		estoque.incluir(prod1);
		estoque.incluir(prod2);

		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		try {
			estoque.comprar(12, 5, -5, null);
		} catch (DadosInvalidos e) {
			// TODO: handle exception
		}
		try {
			estoque.comprar(15, 5, -5, data);
		} catch (DadosInvalidos e) {
			// TODO: handle exception
		}

	}

	@Test
	public void comprarItensQueNaoExistem()
			throws ProdutoJaCadastrado, DadosInvalidos, ProdutoInexistente, ProdutoNaoPerecivel, ProdutoVencido {
		Estoque estoque = new Estoque();
		// Fornecedor forn1 = new Fornecedor(48, "Nestle");
		// Produto prod1 = new Produto(12, "Sorvete", 5, 1, forn1);
		// Verifica se o valor total da venda esta correto
		try {
			estoque.comprar(12, 20, 5, null);
		} catch (ProdutoInexistente e) {
			// TODO: handle exception
		}
	}

	@Test
	public void incluirProdutoNull() throws ProdutoJaCadastrado, ProdutoInexistente {
		Estoque estoque = new Estoque();

		try {
			estoque.incluir(null);
		} catch (DadosInvalidos e) {
			// TODO: handle exception
		}
	}

	@Test
	public void incluirProdutoComFornecedorComCnpjZero() throws ProdutoJaCadastrado, ProdutoInexistente {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(0, "Unilever");
		// Fornecedor forn2 = new Fornecedor(0, "Nestle");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		// Produto prod2 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn2);

		try {
			estoque.incluir(prod1);
		} catch (DadosInvalidos e) {
			// TODO: handle exception
		}
	}

	@Test
	public void incluirProdutoComFornecedorComCnpjNegativo() throws ProdutoJaCadastrado, ProdutoInexistente {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(-48, "Unilever");
		Fornecedor forn2 = new Fornecedor(-33, "Nestle");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn2);

		try {
			estoque.incluir(prod1);
		} catch (DadosInvalidos e) {
			// TODO: handle exception
		}
	}

	@Test
	public void incluirProdutoComFornecedorComNomeNull() throws ProdutoJaCadastrado, ProdutoInexistente {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(-48, null);
		Fornecedor forn2 = new Fornecedor(-33, null);
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn2);

		try {
			estoque.incluir(prod1);
		} catch (DadosInvalidos e) {
			// TODO: handle exception
		}
		try {
			estoque.incluir(prod2);
		} catch (DadosInvalidos e) {
			// TODO: handle exception
		}
	}

	@Test
	public void incluirProdutoComFornecedorComNomeSoComEspacosEmBranco() throws ProdutoJaCadastrado, ProdutoInexistente {
		Estoque estoque = new Estoque();
		Fornecedor forn1 = new Fornecedor(-48, " ");
		Fornecedor forn2 = new Fornecedor(-33, " ");
		Produto prod1 = new Produto(12, "Shampoo", 5, 2, forn1);
		Produto prod2 = new ProdutoPerecivel(14, "Sorvete", 5, 2, forn2);

		try {
			estoque.incluir(prod1);
		} catch (DadosInvalidos e) {
			// TODO: handle exception
		}
		try {
			estoque.incluir(prod2);
		} catch (DadosInvalidos e) {
			// TODO: handle exception
		}
	}
}
