package extrato;

public class Conta {
	private int numero;
	private Pessoa dono;
	private Extrato extrato = new Extrato();
	private String extratoLista = "";

	public Conta(int numero, Pessoa dono) {
		this.numero = numero;

		if (dono.getConta() != null) {
			System.out.println("Não foi possível definir pessoa");
			return;
		} else {
			this.dono = dono;
		}
	}

	public int getNumero() {
		return numero;
	}

	public Pessoa getDono() {
		return dono;
	}

	public Extrato getExtrato() {
		return extrato;
	}

	public String getExtratoLista() {
		for (Transacao transacao : extrato.getExtrato()) {
			extratoLista = extratoLista + transacao.nome + ": R$ " + transacao.valor + "\n";
		}

		return extratoLista;
	}
}
