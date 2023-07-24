package produtosPereciveisComExcecao;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import produtosPereciveisComExcecao.exceptions.*;

public class Estoque implements InterfaceEstoqueComExcecoes {
	private final ArrayList<Produto> produtos;

	public Estoque() {
		produtos = new ArrayList<>();
	}

	public boolean existeProdutoComCodigo(int codigo) {
		for (Produto produto : produtos) {
			if (produto.getCodigo() == codigo) {
				return true;
			}
		}
		return false;
	}

	public void incluir(Produto p) throws ProdutoJaCadastrado, DadosInvalidos {

		if (p == null) {
			return;
		}

		if (existeProdutoComCodigo(p.getCodigo())) {
			throw new ProdutoJaCadastrado();
		}

		if (p == null || p.getDescricao() == null || p.getDescricao().trim().isEmpty() || p.getCodigo() < 0 || p == null
				|| p.getFornecedor().cnpj <= 0) {
			throw new DadosInvalidos();
		}

		produtos.add(p);
	}

	public void comprar(int cod, int quantidade, double preco, Date val)
			throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel {
		Produto produto = pesquisar(cod);

		boolean isPerecivel = produto instanceof ProdutoPerecivel;

		if (!isPerecivel && val != null) {
			throw new ProdutoNaoPerecivel();
		}

		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		data.setTime(data.getTime() - 120000);

		if (val != null && val.before(data)) {
			throw new DadosInvalidos();
		}

		double novoPreco = ((produto.getQuantidade() * produto.getPrecoCompra()) + (quantidade * preco))
				/ (produto.getQuantidade() + quantidade);

		produto.setPrecoCompra(novoPreco);
		produto.setQuantidade(produto.getQuantidade() + quantidade);
		produto.setPrecoVenda(novoPreco + (novoPreco * produto.getLucro()));

		if (isPerecivel && quantidade >= 0 && preco >= 0) {
			if (!(produto instanceof ProdutoPerecivel)) {
				throw new ProdutoNaoPerecivel();
			}
			Lote lote = new Lote(quantidade, val);
			((ProdutoPerecivel) produto).getLotes().add(lote);
		}
	}

	public double vender(int cod, int quant) throws ProdutoInexistente, ProdutoVencido, DadosInvalidos {
		Produto produto = pesquisar(cod);

		if (produto == null) {
			throw new ProdutoInexistente();
		}

		if (produto instanceof ProdutoPerecivel perecivel && quant > 0) {
			double totalLucro = 0;
			int quantVendidos = 0;

			for (Lote lote : perecivel.getLotes()) {
				if (lote.getQuantidade() > 0 && lote.getValidade().after(new Date())) {
					int quantDisponivel = lote.getQuantidade();
					int quantAVender = Math.min(quant, quantDisponivel);

					totalLucro += quantAVender * produto.getLucro();
					lote.setQuantidade(quantDisponivel - quantAVender);
					quantVendidos += quantAVender;
					quant -= quantAVender;

					if (quant == 0) {
						break;
					}
				}
			}

			if (quantVendidos > 0) {
				produto.setQuantidade(produto.getQuantidade() - quantVendidos);
				return produto.getPrecoVenda() * quantVendidos;
			} else {
				throw new ProdutoVencido();
			}
		} else if (quant > 0) {
			produto.setQuantidade(produto.getQuantidade() - quant);
			return produto.getPrecoVenda() * quant;
		} else {
			throw new DadosInvalidos();
		}
	}

	public Produto pesquisar(int cod) throws ProdutoInexistente {
		for (Produto produto : produtos) {
			if (produto.getCodigo() == cod) {
				return produto;
			}
		}

		throw new ProdutoInexistente();
	}

	public ArrayList<Produto> estoqueAbaixoDoMinimo() {
		ArrayList<Produto> abaixoMinimo = new ArrayList<>();

		for (Produto produto : produtos) {
			if (produto.getQuantidade() < produto.getQuantidadeMinima()) {
				abaixoMinimo.add(produto);
			}
		}
		return abaixoMinimo;
	}

	public ArrayList<Produto> estoqueVencido() {
		ArrayList<Produto> vencidos = new ArrayList<>();
		for (Produto produto : produtos) {
			if (produto instanceof ProdutoPerecivel && ((ProdutoPerecivel) produto).temLotesVencidos()) {
				vencidos.add(produto);
			}
		}
		return vencidos;
	}

	public int quantidadeVencidos(int cod) throws ProdutoInexistente {
		Produto produto = pesquisar(cod);

		if (produto instanceof ProdutoPerecivel perecivel) {
			return perecivel.getQuantidadeLotesVencidos();
		}
		return 0;
	}
}
