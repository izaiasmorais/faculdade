import java.util.Date;

class Lote {
	private int quantidade;
	private Date validade;

	public Lote(int quant, Date validade) {
		this.quantidade = quant;
		this.validade = validade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}
}
