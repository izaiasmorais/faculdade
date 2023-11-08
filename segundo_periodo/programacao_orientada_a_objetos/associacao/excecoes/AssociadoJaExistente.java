package associacao.excecoes;

public class AssociadoJaExistente extends Exception {
	public AssociadoJaExistente() {
		super("Associado ja existente");
	}
}
