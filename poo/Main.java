package poo;

public class Main {
	public static void main(String[] args) {

		Pessoa pessoa = new Pessoa("Izaias", 123);

		Conta minhaConta = new Conta(456, pessoa);

		// minhaConta.credito(1000);
		// minhaConta.debito(300);
		// minhaConta.credito(800);
		// minhaConta.debito(250);

		// System.out.println(minhaConta.getExtrato());

		System.out.println("Saldo Total: R$ " + minhaConta.getSaldo());

		System.out.println("Dono: " + minhaConta.getDono().getNome());
	}
}
