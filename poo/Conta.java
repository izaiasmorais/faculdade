package poo;

public class Conta {
	private int numero;
	private Pessoa dono;
	private Extrato extrato;
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

	public float getSaldo() {
		return extrato.getSaldo();
	}

	public Pessoa getDono() {
		return dono;
	}

	public void credito(float value) {
		extrato.credito(value);
	}

	public void debito(float value) {
		extrato.debito(value);
	}

	public String getExtrato() {
		for (Transacao transacao : extrato.getExtrato()) {
			extratoLista = extratoLista + transacao.nome + ": R$ " + transacao.valor + "\n";
		}

		return extratoLista;
	}
}
