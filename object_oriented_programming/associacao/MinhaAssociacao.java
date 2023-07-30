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

	public void adicionarAssociacao(Associacao a) throws AssociacaoJaExistente, ValorInvalido {
		if (pesquisaAssociacao(a.getNumero()) != null)
			throw new AssociacaoJaExistente();

		if (a.getNumero() <= 0 || a.getNome() == null || a.getNome().equals(""))
			throw new ValorInvalido();

		asssociacoes.add(a);
	}

	public void adicionarAssociado(int associacao, Associado a)
			throws AssociacaoNaoExistente, AssociadoJaExistente, ValorInvalido {
		if (pesquisaAssociacao(associacao) == null)
			throw new AssociacaoNaoExistente();

		if (pesquisaAssociado(a.getNumero(), associacao) != null)
			throw new AssociadoJaExistente();

		if (a.getNumero() <= 0 || a.getNome() == null || a.getNome().equals("") || a.getTelefone() == null
				|| a.getTelefone().equals("") || a.getNascimento() == null || a.getDataAssociacao() == null)
			throw new ValorInvalido();

		pesquisaAssociacao(associacao).getAssociados().add(a);
	}

	public void adicionarReuniao(int associacao, Reuniao r)
			throws AssociacaoNaoExistente, ReuniaoJaExistente, ValorInvalido {
		if (pesquisaAssociacao(associacao) == null)
			throw new AssociacaoNaoExistente();

		if (r.getAta() == null || r.getAta() == "" || r.getData() == null)
			throw new ValorInvalido();

		if (pesquisaReuniao(associacao, r) != null)
			throw new ReuniaoJaExistente();

		pesquisaAssociacao(associacao).getReunioes().add(r);
	}

	public void adicionarTaxa(int associacao, Taxa t) throws AssociacaoNaoExistente, TaxaJaExistente, ValorInvalido {
		// Implementation here...
	}

	public Associacao pesquisaAssociacao(int numeroAssociacao) {
		for (Associacao associacao : asssociacoes) {
			if (associacao.getNumero() == numeroAssociacao) {
				return associacao;
			}
		}
		return null;
	}

	public Associado pesquisaAssociado(int numeroAssociado, int numeroAssociacao) {
		if (pesquisaAssociacao(numeroAssociacao) != null) {
			for (Associado associado : pesquisaAssociacao(numeroAssociacao).getAssociados()) {
				if (associado.getNumero() == numeroAssociado) {
					return associado;
				}
			}
		}
		return null;
	}

	public Reuniao pesquisaReuniao(int numeroAssociacao, Reuniao r) {
		for (Reuniao reuniao : pesquisaAssociacao(numeroAssociacao).getReunioes()) {
			if (reuniao == r) {
				return reuniao;
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
