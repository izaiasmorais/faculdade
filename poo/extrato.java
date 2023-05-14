package poo;

import java.util.ArrayList;
import java.util.List;

public class Extrato {
	private List<Transacao> extrato;
	private float saldo;

	public Extrato() {
		extrato = new ArrayList<>();
		saldo = 0;
	}

	public float getSaldo() {
		return saldo;
	}

	public List<Transacao> getExtrato() {
		return new ArrayList<>(extrato);
	}

	public void credito(float valor) {
		saldo += valor;

		Transacao transacao = new Transacao("credito", valor);

		extrato.add(transacao);
	}

	public void debito(float valor) {
		if (saldo < valor) {
			System.out.println("Saldo insuficiente!");
		} else {
			saldo -= valor;

			Transacao transacao = new Transacao("debito", valor);

			extrato.add(transacao);
		}
	}
}
