package associacao;

import java.util.ArrayList;

import associacao.excecoes.*;

public class MinhaAssociacao implements InterfaceAssociacao {
	ArrayList<Associacao> asssociacoes = new ArrayList<>();

	public double calcularFrequencia(int numAssociado, int numAssociacao, long inicio, long fim)
			throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente {
		Associacao associacao = pesquisarAssociacao(numAssociacao);
		if (associacao == null)
			throw new AssociacaoNaoExistente();

		Associado associado = pesquisarAssociado(numAssociado, numAssociacao);
		if (associado == null)
			throw new AssociacaoNaoExistente();

		double reunioes = 0;
		double frequencia = 0;

		for (Reuniao reuniao : associacao.getReunioes()) {
			if (reuniao.data >= inicio && reuniao.data <= fim) {
				reunioes++;
				if (reuniao.getFrequencia().contains(associado.getNumero())) {
					frequencia++;
				}
			}
		}

		if (reunioes == 0)
			throw new ReuniaoNaoExistente();

		return frequencia / reunioes;
	}

	public void registrarFrequencia(int codigoAssociado, int numAssociacao, long dataReuniao)
			throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente,
			FrequenciaJaRegistrada, FrequenciaIncompativel {
		if (pesquisarAssociacao(numAssociacao) == null)
			throw new AssociacaoNaoExistente();

		Associado associado = pesquisarAssociado(codigoAssociado, numAssociacao);

		if (associado == null)
			throw new AssociacaoNaoExistente();

		if (associado.getDataAssociacao() > dataReuniao)
			throw new FrequenciaIncompativel();

		Reuniao reuniao = pesquisarReuniao(numAssociacao, dataReuniao);

		if (reuniao == null)
			throw new ReuniaoNaoExistente();

		for (int frequencia : reuniao.getFrequencia()) {
			if (frequencia == codigoAssociado) {
				throw new FrequenciaJaRegistrada();
			}
		}

		reuniao.getFrequencia().add(codigoAssociado);
	}

	public void registrarPagamento(int numAssociacao, String taxa, int vigencia, int numAssociado, long data,
			double valor)
			throws AssociacaoNaoExistente, AssociadoNaoExistente, AssociadoJaRemido, TaxaNaoExistente, ValorInvalido {
		Associacao associacao = pesquisarAssociacao(numAssociacao);
		if (associacao == null)
			throw new AssociacaoNaoExistente();

		Associado associado = pesquisarAssociado(numAssociado, numAssociacao);
		if (associado == null)
			throw new AssociacaoNaoExistente();

		Taxa t = pesquisarTaxa(numAssociacao, taxa);
		if (t == null)
			throw new TaxaNaoExistente();

		if (t.administrativa == true && associado instanceof AssociadoRemido)
			throw new AssociadoJaRemido();

		double valorRestante = t.getValorAno() - t.somarPagamentoTotal(numAssociado);
		boolean ultimaParcela = valorRestante <= t.getValorParcela();

		if (valor > t.getValorAno())
			throw new ValorInvalido();

		if (!ultimaParcela && valor < t.getValorParcela())
			throw new ValorInvalido();

		t.adicionarPagamentoDeAssociado(numAssociado, data, valor);
	}

	public double somarPagamentoDeAssociado(int numAssociacao, int numAssociado, String nomeTaxa, int vigencia,
			long inicio, long fim)
			throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente {
		if (pesquisarAssociacao(numAssociacao) == null)
			throw new AssociacaoNaoExistente();

		Associado associado = pesquisarAssociado(numAssociado, numAssociacao);
		if (associado == null)
			throw new AssociadoNaoExistente();

		Taxa taxa = pesquisarTaxa(numAssociacao, nomeTaxa);
		if (taxa == null)
			throw new TaxaNaoExistente();

		if (taxa.administrativa == true && associado instanceof AssociadoRemido)
			return 0;

		return taxa.somarPagamentoPeriodo(numAssociado, inicio, fim);
	}

	public double calcularTotalDeTaxas(int numAssociacao, int vigencia)
			throws AssociacaoNaoExistente, TaxaNaoExistente {
		Associacao associacao = pesquisarAssociacao(numAssociacao);
		if (associacao == null)
			throw new AssociacaoNaoExistente();

		boolean taxaExiste = false;
		double total = 0;

		for (Taxa taxa : associacao.getTaxas()) {
			if (taxa.vigencia == vigencia) {
				taxaExiste = true;
				total = total + taxa.valorAno;
			}
		}

		if (!taxaExiste)
			throw new TaxaNaoExistente();

		return total;
	}

	// Métodos adicionar

	public void adicionar(Associacao a) throws AssociacaoJaExistente, ValorInvalido {
		if (a.getNumero() < 0 || a.getNome() == null || a.getNome().equals(""))
			throw new ValorInvalido();

		if (pesquisarAssociacao(a.getNumero()) != null)
			throw new AssociacaoJaExistente();

		asssociacoes.add(a);
	}

	public void adicionar(int associacao, Associado a)
			throws AssociacaoNaoExistente, AssociadoJaExistente, ValorInvalido {
		if (pesquisarAssociacao(associacao) == null)
			throw new AssociacaoNaoExistente();

		if (a.getNumero() < 0 || a.getNome() == null || a.getNome().equals("") || a.getTelefone() == null
				|| a.getTelefone().equals("") || a.getNascimento() == 0 || a.getDataAssociacao() == 0)
			throw new ValorInvalido();

		if (pesquisarAssociado(a.getNumero(), associacao) != null)
			throw new AssociadoJaExistente();

		pesquisarAssociacao(associacao).getAssociados().add(a);
	}

	public void adicionar(int associacao, Reuniao r)
			throws AssociacaoNaoExistente, ReuniaoJaExistente, ValorInvalido {
		if (pesquisarAssociacao(associacao) == null)
			throw new AssociacaoNaoExistente();

		if (r.getAta() == null || r.getAta() == "" || r.getData() == 0)
			throw new ValorInvalido();

		if (pesquisarReuniao(associacao, r.getData()) != null)
			throw new ReuniaoJaExistente();

		pesquisarAssociacao(associacao).getReunioes().add(r);
	}

	public void adicionar(int associacao, Taxa t) throws AssociacaoNaoExistente, TaxaJaExistente, ValorInvalido {
		if (pesquisarAssociacao(associacao) == null)
			throw new AssociacaoNaoExistente();

		if (t.getNome() == null || t.getNome() == "" || t.getParcelas() <= 0 || t.getValorAno() <= 0
				|| t.getVigencia() <= 0)
			throw new ValorInvalido();

		if (pesquisarTaxa(associacao, t.nome) != null)
			throw new TaxaJaExistente();

		pesquisarAssociacao(associacao).getTaxas().add(t);
	}

	// Métodos pesquisar

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

	public Reuniao pesquisarReuniao(int numeroAssociacao, long dataReuniao) {
		for (Reuniao reuniao : pesquisarAssociacao(numeroAssociacao).getReunioes()) {
			if (reuniao.getData() == dataReuniao) {
				return reuniao;
			}
		}
		return null;
	}

	public Taxa pesquisarTaxa(int numeroAssociacao, String nome) {
		Associacao associacao = pesquisarAssociacao(numeroAssociacao);

		for (Taxa taxa : associacao.getTaxas()) {
			if (taxa.nome == nome) {
				return taxa;
			}
		}
		return null;
	}

}
