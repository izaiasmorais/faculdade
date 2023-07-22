package estoque;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
	private List<Produto> produtos;

	public Estoque() {
		this.produtos = new ArrayList<>();
	}

	public void incluir(Produto produto) {
		if (produto.getCodigo() < 0 || produto.getFornecedor().getCnpj() <= 0 || produto.getLucro() < 0
				|| produto.getEstoqueMinimo() < 0) {
			return;
		}

		for (Produto p : produtos) {
			if (p.getCodigo() == produto.getCodigo()) {
				return;
			}
		}

		produtos.add(produto);
	}

	public void comprar(int codigo, int quantidade, double preco) {
		for (Produto produto : produtos) {
			if (produto.getCodigo() == codigo) {
				produto.compra(quantidade, preco);
				return;
			}
		}
	}

	public double vender(int codigo, int quantidade) {
		if (quantidade < 0) {
			return 0;
		}

		for (Produto produto : produtos) {
			if (produto.getCodigo() == codigo) {
				return produto.venda(quantidade);
			}
		}
		return -1;
	}

	public int quantidade(int codigo) {
		for (Produto produto : produtos) {
			if (produto.getCodigo() == codigo) {
				return produto.getQuantidade();
			}
		}
		return 0;
	}

	public Fornecedor fornecedor(int codigo) {
		for (Produto produto : produtos) {
			if (produto.getCodigo() == codigo) {
				return produto.getFornecedor();
			}
		}
		return null;
	}

	public Produto[] estoqueAbaixoDoMinimo() {
		List<Produto> abaixoDoMinimo = new ArrayList<>();
		for (Produto produto : produtos) {
			if (produto.getQuantidade() < produto.getEstoqueMinimo()) {
				abaixoDoMinimo.add(produto);
			}
		}
		return abaixoDoMinimo.toArray(new Produto[0]);
	}
}
