package associacao;

public class AssociadoRemido extends Associado {

	protected long dataRemissao;

	public AssociadoRemido(int numero, String nome, String telefone, long dataAssociacao, long nascimento,
			long dataRemissao) {
		super(numero, nome, telefone, dataAssociacao, nascimento);
		this.dataRemissao = dataRemissao;
	}

	public long getDataRemissao() {
		return dataRemissao;
	}
}
