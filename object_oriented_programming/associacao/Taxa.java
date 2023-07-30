package associacao;

public class Taxa {
	String nome;
	int vigencia;
	double valorAno;
	int parcelas;
	boolean administrativa;

	public Taxa(String nome, int vigencia, double valorAno, int parcelas, boolean administrativa) {
		this.nome = nome;
		this.vigencia = vigencia;
		this.valorAno = valorAno;
		this.parcelas = parcelas;
		this.administrativa = administrativa;
	}

	public String getNome() {
		return nome;
	}

	public int getVigencia() {
		return vigencia;
	}

	public double getValorAno() {
		return valorAno;
	}

	public int getParcelas() {
		return parcelas;
	}

	public boolean isAdministrativa() {
		return administrativa;
	}


}
