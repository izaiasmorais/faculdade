package associacao;

import java.util.Date;

public class Main {
	public static void main(String[] args) {
		// MinhaAssociacao empresa = new MinhaAssociacao();
		// Associacao associacao = new Associacao(100, "Anonymous");
		Associado associado = new Associado(777, "Izaias", "777", new Date(), new Date());

		System.out.println(associado.getNascimento());
	}
}
