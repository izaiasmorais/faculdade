package extrato;

public class Main {
	public static void main(String[] args) {

		Pessoa pessoa = new Pessoa("Izaias", 123);

		Conta minhaConta = new Conta(456, pessoa);

		Extrato extratoConta = minhaConta.getExtrato();

		extratoConta.credito(1000);
		extratoConta.debito(300);
		extratoConta.credito(800);
		extratoConta.debito(250);

		System.out.println(minhaConta.getExtratoLista());

		System.out.println("Saldo Total: R$ " + extratoConta.getSaldo());

		System.out.println("Dono: " + minhaConta.getDono().getNome());
	}
}
