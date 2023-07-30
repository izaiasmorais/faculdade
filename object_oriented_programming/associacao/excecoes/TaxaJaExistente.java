package associacao.excecoes;

public class TaxaJaExistente extends Exception {
	public TaxaJaExistente() {
		super("Taxa nao existente");
	}
}
