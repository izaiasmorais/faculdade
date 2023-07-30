package estoqueComProdutoPerecivel;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Estoque implements InterfaceEstoque {
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

	public boolean incluir(Produto p) {

		if (p != null && !p.getDescricao().trim().isEmpty() && p.getCodigo() > 0 && p != null
				&& !existeProdutoComCodigo(p.getCodigo())
				&& p.getFornecedor().cnpj > 0) {
			produtos.add(p);
			return true;
		}

		return false;
	}

	public boolean comprar(int cod, int quantidade, double preco, Date val) {
		Produto produto = pesquisar(cod);

		if (produto == null) {
			return false;
		}

		if (preco <= 0) {
			return false;
		}

		if (quantidade <= 0) {
			return false;
		}

		if (!(produto instanceof ProdutoPerecivel perecivel) && !(val == null)) {
			return false;
		}

		if (produto instanceof ProdutoPerecivel perecivel && val == null) {
			return false;
		}

		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));

		if (val != null && val.before(data)) {
			return false;
		}

		if (produto instanceof ProdutoPerecivel perecivel) {
			Lote lote = new Lote(quantidade, val);
			perecivel.getLotes().add(lote);
			double novoPreco = ((produto.getQuantidade() * produto.getPrecoCompra()) + (quantidade * preco))
					/ (produto.getQuantidade() + quantidade);
			produto.setPrecoCompra(novoPreco);
			produto.setQuantidade(produto.getQuantidade() + quantidade);
			produto.setPrecoVenda(novoPreco + (novoPreco * produto.getLucro()));
			return true;
		} else {
			double novoPreco = ((produto.getQuantidade() * produto.getPrecoCompra()) + (quantidade * preco))
					/ (produto.getQuantidade() + quantidade);
			produto.setPrecoCompra(novoPreco);
			produto.setQuantidade(produto.getQuantidade() + quantidade);
			produto.setPrecoVenda(novoPreco + (novoPreco * produto.getLucro()));
			return true;
		}
	}

	public double vender(int cod, int quant) {
		Produto produto = pesquisar(cod);

		if (produto instanceof ProdutoPerecivel perecivel && quant > 0) {
			int quantVendidos = 0;

			for (Lote lote : perecivel.getLotes()) {
				if (lote.getQuantidade() > 0 && lote.getValidade().after(new Date())) {
					if (quant <= lote.getQuantidade()) {
						lote.setQuantidade(lote.getQuantidade() - quant);
						quantVendidos += quant;
						break;
					} else {
						quantVendidos += lote.getQuantidade();
						quant -= lote.getQuantidade();
						lote.setQuantidade(0);
					}
				}
			}

			if (quantVendidos > 0) {
				produto.setQuantidade(produto.getQuantidade() - quant);
				return (produto.getPrecoVenda() * quant);
			}
		} else if (quant > 0 && produto.getQuantidade() >= quant) {
			produto.setQuantidade(produto.getQuantidade() - quant);
			return (produto.getPrecoVenda() * quant);
		}

		return -1;
	}

	public Produto pesquisar(int cod) {
		for (Produto produto : produtos) {
			if (produto.getCodigo() == cod) {
				return produto;
			}
		}
		return null;
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
		if (produto == null) {
			throw new ProdutoInexistente();
		}
		if (produto instanceof ProdutoPerecivel perecivel) {
			return perecivel.getQuantidadeLotesVencidos();
		}
		return 0;
	}

	public Fornecedor fornecedor(int cod) {
		for (Produto produto : produtos) {
			if (produto.getCodigo() == cod) {
				return produto.getFornecedor();
			}
		}
		return null;
	}
}
