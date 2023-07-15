package estoque;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
	List<Produto> produtos;

	public Estoque() {
		produtos = new ArrayList<>();
	}

	public void incluir(Produto p) {
		for (Produto produto : produtos) {
			if (p.cod == produto.cod) {
				System.out.println("Produto com o mesmo codigo ja adicionado");
				return;
			}
		}

		produtos.add(p);
	}

	public void comprar(int cod, int quantidade, double preco) {
		if (quantidade < 0 || preco < 0) {
			return;
		}

		for (Produto produto : produtos) {
			if (produto.getCod() == cod) {
				produto.setQuantidade(produto.getQuantidade() + quantidade);

				double novoPreco = ((produto.getQuantidade() * produto.getPrecoCompra()) + (quantidade * preco))
						/ (produto.getQuantidade() + quantidade);

				produto.setPrecoCompra(novoPreco);

				produto.setPrecoVenda(novoPreco + (novoPreco * produto.getLucro()));
			}
		}
	}

	public double vender(int cod, int quant) {

		for (Produto produto : produtos) {
			if (produto.getCod() == cod) {
				int quantidadeAtual = produto.getQuantidade();
				if (quantidadeAtual < 0 || quantidadeAtual < quant) {
					break;
				}
				produto.setQuantidade(quantidadeAtual - quant);
				return (quant * produto.getPrecoVenda());
			}
		}

		return -1;
	}

	public int quantidade(int cod) {

		for (Produto produto : produtos) {
			if (produto.getCod() == cod) {
				return produto.getQuantidade();
			}
		}
		return -1;
	}

	public Fornecedor fornecedor(int cod) {
		for (Produto produto : produtos) {
			if (produto.getCod() == cod) {
				return produto.getForn();
			}
		}
		return null;
	}

	public Produto[] estoqueAbaixoDoMinimo() {
		List<Produto> produtosAbaixoDoEstoque = new ArrayList<>();

		for (Produto produto : produtos) {
			if (produto.getQuantidade() < produto.getMin()) {
				produtosAbaixoDoEstoque.add(produto);
			}
		}

		return produtosAbaixoDoEstoque.toArray(new Produto[0]);
	}
}
