package associacao;

import java.util.Date;
import java.util.ArrayList;

import associacao.excecoes.*;

public class MinhaAssociacao implements InterfaceAssociacao {
	ArrayList<Associacao> asssociacoes = new ArrayList<>();

	public double calcularFrequencia(int numAssociado, int numAssociacao, Date inicio, Date fim)
			throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente {
		return 0.0;
	}

	public void registrarFrequencia(int codigoAssociado, int numAssociacao, Date dataReuniao)
			throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente,
			FrequenciaJaRegistrada, FrequenciaIncompativel {
		// Implementation here...
	}

	public void registrarPagamento(int numAssociacao, String taxa, int vigencia, int numAssociado, Date data,
			double valor)
			throws AssociacaoNaoExistente, AssociadoNaoExistente, AssociadoJaRemido, TaxaNaoExistente, ValorInvalido {
		// Implementation here...
	}

	public double somarPagamentoDeAssociado(int numAssociacao, int numAssociado, String nomeTaxa, int vigencia,
			Date inicio, Date fim)
			throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente {
		return 0.0;
	}

	public double calcularTotalDeTaxas(int numAssociacao, int vigencia)
			throws AssociacaoNaoExistente, TaxaNaoExistente {
		return 0.0;
	}

	public void adicionar(Associacao a) throws AssociacaoJaExistente, ValorInvalido {
		if (pesquisaAssociacao(a.getNumero()) != null)
			throw new AssociacaoJaExistente();

		if (a.getNumero() <= 0 || a.getNome() == null || a.getNome().equals(""))
			throw new ValorInvalido();

		asssociacoes.add(a);
	}

	public void adicionar(int associacao, Associado a)
			throws AssociacaoNaoExistente, AssociadoJaExistente, ValorInvalido {
		// Implementation here...
	}

	public void adicionar(int associacao, Reuniao r) throws AssociacaoNaoExistente, ReuniaoJaExistente, ValorInvalido {
		// Implementation here...
	}

	public void adicionar(int associacao, Taxa t) throws AssociacaoNaoExistente, TaxaJaExistente, ValorInvalido {
		// Implementation here...
	}

	public Associacao pesquisaAssociacao(int numero) {
		for (Associacao associacao : asssociacoes) {
			if (associacao.getNumero() == numero) {
				return associacao;
			}
		}
		return null;
	}

	public void listarAssociacoes() {
		for (Associacao associacao : asssociacoes) {
			System.out.println(associacao.getNome());
		}
	}
}
