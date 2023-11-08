package associacao.excecoes;

public class AssociacaoJaExistente extends Exception {
	public AssociacaoJaExistente() {
		super("Taxa nao existente");
	}
}
