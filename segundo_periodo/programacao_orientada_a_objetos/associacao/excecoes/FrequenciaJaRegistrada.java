package associacao.excecoes;

public class FrequenciaJaRegistrada extends Exception {
	public FrequenciaJaRegistrada() {
		super("Frequencia ja registrada");
	}
}
