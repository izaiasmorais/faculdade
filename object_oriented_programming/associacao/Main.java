package associacao;

public class Main {
	public static void main(String[] args) {
		MinhaAssociacao store = new MinhaAssociacao();

		Associacao a = new Associacao(1, "Associação 1");
		Associacao b = new Associacao(2, "Associação 2");
		Associacao c = new Associacao(3, "Associação 3");

		try {
			store.adicionar(a);
			store.adicionar(b);
			store.adicionar(c);
		} catch (Exception e) {
			// TODO: handle exception
		}

		store.listarAssociacoes();
	}
}
