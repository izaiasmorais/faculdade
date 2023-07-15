package estoque;

public class Produto {
	int cod;
	int min;
	String desc;
	double lucro;
	int quantidade;
	Fornecedor forn;
	double precoCompra;
	double precoVenda;

	public Produto(int cod, String desc, int min, double lucro, Fornecedor forn) {
		this.cod = cod;
		this.desc = desc;
		this.min = min;
		this.lucro = lucro;
		this.forn = forn;
		this.quantidade = 0;
		this.precoCompra = 0.0;
		this.precoVenda = 0.0;
	}

	public int getCod() {
		return cod;
	}

	public String getDesc() {
		return desc;
	}

	public int getMin() {
		return min;
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

	public Fornecedor getForn() {
		return forn;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}
}
