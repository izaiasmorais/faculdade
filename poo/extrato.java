package poo;

import java.util.ArrayList;
import java.util.List;

class Conta {
	private int numero;
	private float saldo = 0;
	private List<Transacao> extrato = new ArrayList<>();

	class Transacao {
		String nome;
		float valor;

		public Transacao(String nome, float valor) {
			this.nome = nome;
			this.valor = valor;
		}
	}

	public Conta(int numero) {
		this.numero = numero;
	}

	public int getNumero() {
		return numero;
	}

	public float getSaldo() {
		return saldo;
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

	public void getExtrato() {
		for (Transacao transacao : extrato) {
			System.out.println(transacao.nome + ": " + transacao.valor);
		}
	}
}

public class extrato {
	public static void main(String[] args) {
		Conta minhaConta = new Conta(123);

		minhaConta.credito(1000);
		minhaConta.debito(300);
		minhaConta.credito(800);
		minhaConta.debito(250);

		minhaConta.getExtrato();

		System.out.println("Saldo Total: " + minhaConta.getSaldo());
	}
}
