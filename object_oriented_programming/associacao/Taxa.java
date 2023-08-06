package associacao;

import java.util.ArrayList;

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

	public class Pagamento {
		long data;
		double valor;

		public Pagamento(long data, double valor) {
			this.data = data;
			this.valor = valor;
		}
	}

	public class Associado {
		int numero;
		ArrayList<Pagamento> pagamentos = new ArrayList<>();

		public Associado(int numero) {
			this.numero = numero;
		}

		public void adicionarPagamento(Pagamento novPagamento) {
			pagamentos.add(novPagamento);
		}
	}

	ArrayList<Associado> associados = new ArrayList<>();

	public void adicionarPagamentoDeAssociado(int numero, long data, double valor) {
		Pagamento novoPagamento = new Pagamento(data, valor);
		for (Associado associado : associados) {
			if (associado.numero == numero) {
				associado.adicionarPagamento(novoPagamento);
				return;
			}
		}
		Associado associado = new Associado(numero);
		associado.adicionarPagamento(novoPagamento);
		associados.add(associado);
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

	public ArrayList<Associado> getAssociados() {
		return associados;
	}

	public boolean isAdministrativa() {
		return administrativa;
	}

	public double getValorParcela() {
		return valorAno / parcelas;
	}

	public double somarPagamentoTotal(int numero) {
		double total = 0.0;
		for (Associado associado : associados) {
			if (associado.numero == numero) {
				for (Pagamento pagamento : associado.pagamentos) {
					total = total + pagamento.valor;
				}
			}
		}
		return total;
	}

	public double somarPagamentoPeriodo(int numero, long inicio, long fim) {
		double total = 0.0;
		for (Associado associado : associados) {
			if (associado.numero == numero) {
				for (Pagamento pagamento : associado.pagamentos) {
					if (pagamento.data >= inicio && pagamento.data <= fim) {
						total = total + pagamento.valor;
					}
				}
			}
		}

		return total;
	}
}
