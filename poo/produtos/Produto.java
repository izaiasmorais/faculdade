class Produto {
	private int codigo;
	private String descricao;
	private int quantidadeMinima;
	private double lucro;
	private Fornecedor fornecedor;
	private double precoCompra;
	private double precoVenda;

	public Produto(int cod, String desc, int min, double lucro, Fornecedor forn) {
		this.codigo = cod;
		this.descricao = desc;
		this.quantidadeMinima = min;
		this.lucro = lucro;
		this.fornecedor = forn;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(int quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}

	public double getLucro() {
		return lucro;
	}

	public void setLucro(double lucro) {
		this.lucro = lucro;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public double getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	// public int calcularQuantidadeDisponivel() {

	// }
}
