import java.util.ArrayList;
import java.util.Date;

public class Estoque implements InterfaceEstoque {
	private ArrayList<Produto> produtos;

	public Estoque() {
		produtos = new ArrayList<>();
	}

	public boolean incluir(Produto p) {
		if (p != null && !produtos.contains(p)) {
			produtos.add(p);
			return true;
		}
		return false;
	}

	public boolean comprar(int cod, int quant, double preco, Date val) {
		Produto produto = pesquisar(cod);
		if (produto != null && produto instanceof ProdutoPerecivel && quant >= 0 && preco >= 0) {
			ProdutoPerecivel perecivel = (ProdutoPerecivel) produto;
			Lote lote = new Lote(quant, val);
			perecivel.getLotes().add(lote);
			produto.setPrecoCompra(preco);
			produto.setPrecoVenda(preco + produto.getLucro());
			return true;
		}
		return false;
	}

	public double vender(int cod, int quant) {
		Produto produto = pesquisar(cod);
		if (produto != null && produto instanceof ProdutoPerecivel && quant > 0) {
			ProdutoPerecivel perecivel = (ProdutoPerecivel) produto;
			double totalLucro = 0;
			int quantVendidos = 0;

			for (Lote lote : perecivel.getLotes()) {
				if (lote.getQuantidade() > 0 && lote.getValidade().after(new Date())) {
					if (quant <= lote.getQuantidade()) {
						totalLucro += quant * produto.getLucro();
						lote.setQuantidade(lote.getQuantidade() - quant);
						quantVendidos += quant;
						break;
					} else {
						totalLucro += lote.getQuantidade() * produto.getLucro();
						quantVendidos += lote.getQuantidade();
						quant -= lote.getQuantidade();
						lote.setQuantidade(0);
					}
				}
			}

			if (quantVendidos > 0) {
				produto.setPrecoVenda(produto.getPrecoCompra() + produto.getLucro());
				return totalLucro;
			}
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
			if (produto.getQuantidadeMinima() > 0 && produto.getQuantidadeMinima() > produto.calcularQuantidadeDisponivel()) {
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
		if (produto instanceof ProdutoPerecivel) {
			ProdutoPerecivel perecivel = (ProdutoPerecivel) produto;
			return perecivel.getQuantidadeLotesVencidos();
		}
		return 0;
	}
}
