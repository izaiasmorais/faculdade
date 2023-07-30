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
		if (pesquisarAssociacao(numAssociacao) == null)
			throw new AssociacaoNaoExistente();

		Associado associado = pesquisarAssociado(codigoAssociado, numAssociacao);

		if (associado == null)
			throw new AssociacaoNaoExistente();

		Reuniao reuniao = pesquisarReuniao(numAssociacao, dataReuniao);

		if (reuniao == null)
			throw new ReuniaoNaoExistente();

		for (int frequencia : reuniao.getFrequencia()) {
			if (frequencia == codigoAssociado) {
				throw new FrequenciaJaRegistrada();
			}
		}

		if (associado.getDataAssociacao().before(dataReuniao))
			throw new FrequenciaIncompativel();

		reuniao.getFrequencia().add(codigoAssociado);
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

	// Métodos adicionar

	public void adicionarAssociacao(Associacao a) throws AssociacaoJaExistente, ValorInvalido {
		if (a.getNumero() < 0 || a.getNome() == null || a.getNome().equals(""))
			throw new ValorInvalido();

		if (pesquisarAssociacao(a.getNumero()) != null)
			throw new AssociacaoJaExistente();

		asssociacoes.add(a);
	}

	public void adicionarAssociado(int associacao, Associado a)
			throws AssociacaoNaoExistente, AssociadoJaExistente, ValorInvalido {
		if (pesquisarAssociacao(associacao) == null)
			throw new AssociacaoNaoExistente();

		if (a.getNumero() < 0 || a.getNome() == null || a.getNome().equals("") || a.getTelefone() == null
				|| a.getTelefone().equals("") || a.getNascimento() == null || a.getDataAssociacao() == null)
			throw new ValorInvalido();

		if (pesquisarAssociado(a.getNumero(), associacao) != null)
			throw new AssociadoJaExistente();

		pesquisarAssociacao(associacao).getAssociados().add(a);
	}

	public void adicionarReuniao(int associacao, Reuniao r)
			throws AssociacaoNaoExistente, ReuniaoJaExistente, ValorInvalido {
		if (pesquisarAssociacao(associacao) == null)
			throw new AssociacaoNaoExistente();

		if (r.getAta() == null || r.getAta() == "" || r.getData() == null)
			throw new ValorInvalido();

		if (pesquisarReuniao(associacao, r.getData()) != null)
			throw new ReuniaoJaExistente();

		pesquisarAssociacao(associacao).getReunioes().add(r);
	}

	public void adicionarTaxa(int associacao, Taxa t) throws AssociacaoNaoExistente, TaxaJaExistente, ValorInvalido {
		if (pesquisarAssociacao(associacao) == null)
			throw new AssociacaoNaoExistente();

		if (t.getNome() == null || t.getNome() == "" || t.getParcelas() <= 0 || t.getValorAno() <= 0
				|| t.getVigencia() <= 0)
			throw new ValorInvalido();

		if (pesquisarTaxa(associacao, t) != null)
			throw new TaxaJaExistente();

		pesquisarAssociacao(associacao).setTaxa(t);
	}

	public Associacao pesquisarAssociacao(int numeroAssociacao) {
		for (Associacao associacao : asssociacoes) {
			if (associacao.getNumero() == numeroAssociacao) {
				return associacao;
			}
		}
		return null;
	}

	public Associado pesquisarAssociado(int numeroAssociado, int numeroAssociacao) {
		if (pesquisarAssociacao(numeroAssociacao) != null) {
			for (Associado associado : pesquisarAssociacao(numeroAssociacao).getAssociados()) {
				if (associado.getNumero() == numeroAssociado) {
					return associado;
				}
			}
		}
		return null;
	}

	public Reuniao pesquisarReuniao(int numeroAssociacao, Date dataReuniao) {
		for (Reuniao reuniao : pesquisarAssociacao(numeroAssociacao).getReunioes()) {
			if (reuniao.getData() == dataReuniao) {
				return reuniao;
			}
		}
		return null;
	}

	public Taxa pesquisarTaxa(int numeroAssociacao, Taxa t) {
		Taxa taxa = pesquisarAssociacao(numeroAssociacao).getTaxa();
		if (taxa == t) {
			return taxa;
		}
		return null;
	}

	public void listarAssociacoes() {
		for (Associacao associacao : asssociacoes) {
			System.out.println(associacao.getNome());
		}
	}
}
