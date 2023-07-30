package associacao.excecoes;

public class ReuniaoJaExistente extends Exception {
	public ReuniaoJaExistente() {
		super("Reuniao ja existente");
	}
}
