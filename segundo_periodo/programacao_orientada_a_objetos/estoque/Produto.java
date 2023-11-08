package estoque;

public class Produto {
	private int codigo;
	private String descricao;
	private double precoCompra;
	private double precoVenda;
	private double lucro;
	private int quantidade;
	private int estoqueMinimo;
	private Fornecedor fornecedor;

	public Produto(int codigo, String descricao, int estoqueMinimo, double lucro, Fornecedor fornecedor) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.precoCompra = 0.0;
		this.precoVenda = 0.0;
		this.lucro = lucro;
		this.quantidade = 0;
		this.estoqueMinimo = estoqueMinimo;
		this.fornecedor = fornecedor;
	}

	public void compra(int quantidade, double valor) {
		if (quantidade > 0 && valor > 0.0) {
			double novoPrecoCompra = ((this.quantidade * this.precoCompra) + (quantidade * valor))
					/ (this.quantidade + quantidade);
			this.precoCompra = novoPrecoCompra;
			this.precoVenda = this.precoCompra + (this.precoCompra * this.lucro);
			this.quantidade += quantidade;
		}
	}

	public double venda(int quantidade) {
		if (quantidade > 0 && quantidade <= this.quantidade) {
			this.quantidade -= quantidade;
			return quantidade * this.precoVenda;
		}
		return -1;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getPrecoCompra() {
		return precoCompra;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public double getLucro() {
		return lucro;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public int getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}
}
