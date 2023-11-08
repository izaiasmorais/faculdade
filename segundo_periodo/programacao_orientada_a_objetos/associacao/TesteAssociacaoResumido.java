package associacao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import associacao.excecoes.*;

public class TesteAssociacaoResumido {

	@Test
	public void testarCadastroDeAssociacao() throws AssociacaoJaExistente, ValorInvalido {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "");
		a1.setNome("Cruzeiro do Sul V");
		controle.adicionar(a1);

		try {
			controle.adicionar(a1);
			fail("Deveria ter dado erro! Cadastro de mesma associacao");
		} catch (AssociacaoJaExistente e) {
			// Ok, era pra dar erro mesmo!
		}
	}

	@Test
	public void testarCadastroDeAssociacaoComNomeVazio() throws AssociacaoJaExistente, ValorInvalido {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "");

		// Tentativa de cadastrar com sem nome
		try {
			controle.adicionar(a1);
			fail("Deveria ter dado erro! Associacao sem nome");
		} catch (ValorInvalido e) {
			// Ok. Era pra dar erro mesmo!
		}
	}

	@Test
	public void testarCadastroDeAssociacaoComNomeNulo() throws AssociacaoJaExistente, ValorInvalido {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, null);

		// Tentativa de cadastrar com sem nome
		try {
			controle.adicionar(a1);
			fail("Deveria ter dado erro! Associacao nome nulo");
		} catch (ValorInvalido e) {
			// Ok. Era pra dar erro mesmo!
		}
	}

	@Test
	public void testarCadastroDeAssociado()
			throws AssociacaoJaExistente, ValorInvalido, AssociacaoNaoExistente, AssociadoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		Date hoje = new Date();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", hoje.getTime(), nasc);
		controle.adicionar(1306, associado1);

		try {
			controle.adicionar(1306, associado1);
			fail("Era pra ter dado erro! Cadastro de mesmo associado");
		} catch (AssociadoJaExistente e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void testarCadastroDeReuniao()
			throws AssociacaoNaoExistente, ValorInvalido, ReuniaoJaExistente, AssociacaoJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(2021, 01, 01);
		long dataReuni = gc.getTimeInMillis();

		Reuniao reuniao1 = new Reuniao(dataReuni, "Aumento de taxas");
		controle.adicionar(1306, reuniao1);
		try {
			controle.adicionar(1306, reuniao1);
			fail("Era pra ter dado erro! Cadastro de mesma reuniao");
		} catch (ReuniaoJaExistente e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void testarCadastroDeTaxa()
			throws AssociacaoNaoExistente, AssociacaoJaExistente, ValorInvalido, TaxaJaExistente {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		Taxa taxa1 = new Taxa("ManutenâˆšÃŸâˆšÂ£o", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);
		try {
			controle.adicionar(1306, taxa1);
			fail("Era pra ter dado erro! Cadastro de mesma taxa");
		} catch (TaxaJaExistente e) {
			// Ok, era pra ter dado erro!
		}
	}

	@Test
	public void testarCalculoDeFrequencia() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		Reuniao reuniao1 = new Reuniao(data1, "Aumento de taxas");
		controle.adicionar(1306, reuniao1);
		controle.registrarFrequencia(1, 1306, data1);

		Reuniao reuniao2 = new Reuniao(data1 + 1000000, "ConfirmaâˆšÃŸâˆšÂ£o de aumento");
		controle.adicionar(1306, reuniao2);

		double freq = controle.calcularFrequencia(1, 1306, data1, data1 + 1000000);
		assertEquals(0.5, freq, 0.01);
	}

	@Test
	public void testeFrequenciaDeAssociacaoQueNaoExiste() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		Reuniao reuniao1 = new Reuniao(data1, "Aumento de taxas");
		controle.adicionar(1306, reuniao1);

		try {
			controle.registrarFrequencia(1, 1000, data1);
			fail("Frequencia de associacao que nao existe!");
		} catch (AssociacaoNaoExistente e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ok erro frequencia de associacao que nao existe!");
		}

	}

	@Test
	public void testeFrequenciaIncompativel() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		Reuniao reuniao1 = new Reuniao(data1, "Aumento de taxas");
		controle.adicionar(1306, reuniao1);

		try {
			controle.registrarFrequencia(1, 1306, data1 - 10000);
			fail("Frequemcia incompativel");
		} catch (FrequenciaIncompativel e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ok frequencia incompativel!");
		}

	}

	@Test
	public void testeFrequenciaJaRegistrada() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		Reuniao reuniao1 = new Reuniao(data1, "Aumento de taxas");
		controle.adicionar(1306, reuniao1);

		controle.registrarFrequencia(1, 1306, data1);

		try {
			controle.registrarFrequencia(1, 1306, data1);
			fail("Frequencia ja registrada!");
		} catch (FrequenciaJaRegistrada e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ok frequencia ja registrada!");
		}

	}

	@Test
	public void testarPagamento() throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente,
			AssociacaoJaExistente, ValorInvalido, AssociadoJaExistente, TaxaJaExistente, AssociadoJaRemido {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		// RemissâˆšÂ£o em 01/03/2021
		gc.set(2021, 02, 01);
		long remissao = gc.getTimeInMillis();
		Associado associado2 = new AssociadoRemido(2, "Raimundo", "3232-3232", data1, nasc, remissao);
		controle.adicionar(1306, associado2);

		Taxa taxa1 = new Taxa("ManutenâˆšÃŸâˆšÂ£o", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		Taxa taxa2 = new Taxa("Reforma", 2021, 1200, 12, false);
		controle.adicionar(1306, taxa2);

		Date hoje = new Date();

		try {
			controle.registrarPagamento(1306, "ManutenâˆšÃŸâˆšÂ£o", 2021, 1, hoje.getTime(), 49);
			fail(
					"NâˆšÂ£o deveria deixar pagar esse valor, pois 49 estÂ· abaixo do valor da parcela, que deve ser o valor mÃŒnimo a ser pago");
		} catch (ValorInvalido e) {
			// Ok. Barrou pagamento abaixo da parcela!
		}
		controle.registrarPagamento(1306, "ManutenâˆšÃŸâˆšÂ£o", 2021, 1, hoje.getTime(), 60);
		controle.registrarPagamento(1306, "Reforma", 2021, 1, hoje.getTime(), 100);

		double valor = controle.somarPagamentoDeAssociado(1306, 1, "ManutenâˆšÃŸâˆšÂ£o", 2021, hoje.getTime(),
				hoje.getTime());
		assertEquals(60, valor, 0.01);
		double valor2 = controle.somarPagamentoDeAssociado(1306, 1, "Reforma", 2021, hoje.getTime(), hoje.getTime());
		assertEquals(100, valor2, 0.01);

		try {
			controle.registrarPagamento(1306, "ManutenâˆšÃŸâˆšÂ£o", 2021, 2, hoje.getTime(), 60);
			fail("NâˆšÂ£o deveria deixar pagar esse valor, pois este associado Ãˆ remido");
		} catch (AssociadoJaRemido e) {
			// Ok. Barrou pagamento de taxa adminsitrativa!
		}

		controle.registrarPagamento(1306, "Reforma", 2021, 2, hoje.getTime(), 100);
		valor = controle.somarPagamentoDeAssociado(1306, 2, "ManutenâˆšÃŸâˆšÂ£o", 2021, hoje.getTime(), hoje.getTime());
		assertEquals(0, valor, 0.01);
		valor2 = controle.somarPagamentoDeAssociado(1306, 2, "Reforma", 2021, hoje.getTime(), hoje.getTime());
		assertEquals(100, valor2, 0.01);
		controle.registrarPagamento(1306, "Reforma", 2021, 2, hoje.getTime(), 1080);
		valor2 = controle.somarPagamentoDeAssociado(1306, 2, "Reforma", 2021, hoje.getTime(), hoje.getTime());
		assertEquals(1180, valor2, 0.01);
		controle.registrarPagamento(1306, "Reforma", 2021, 2, hoje.getTime(), 20);
		valor2 = controle.somarPagamentoDeAssociado(1306, 2, "Reforma", 2021, hoje.getTime(), hoje.getTime());
		assertEquals(1200, valor2, 0.01);
	}

	@Test
	public void testaPagamentoDeTaxaNaoExistente() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);
		// RemissâˆšÂ£o em 01/03/2021
		gc.set(2021, 02, 01);
		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		try {
			controle.registrarPagamento(1306, "B", 2021, 1, data1, 70);
			fail("Pagamento de taxa nao existente");
		} catch (TaxaNaoExistente e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Ok pagamento de taxa nao existente!");
		}

	}

	@Test
	public void testeCalcularTotalDeTaxa() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);
		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		Taxa taxa2 = new Taxa("B", 2021, 100, 12, true);
		controle.adicionar(1306, taxa1);
		controle.adicionar(1306, taxa2);
		assertEquals(700, controle.calcularTotalDeTaxas(1306, 2021), 0.001);
	}

	@Test
	public void testarPagamentoAbaixoDaParcela() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		// Remissâ€žo em 01/03/2021
		gc.set(2021, 02, 01);
		long remissao = gc.getTimeInMillis();
		Associado associado2 = new AssociadoRemido(2, "Raimundo", "3232-3232", data1, nasc, remissao);
		controle.adicionar(1306, associado2);

		Taxa taxa1 = new Taxa("ManutenÃ�â€žo", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		Taxa taxa2 = new Taxa("Reforma", 2021, 1200, 12, false);
		controle.adicionar(1306, taxa2);
		Date hoje = new Date();

		try {
			controle.registrarPagamento(1306, "ManutenÃ�â€žo", 2021, 1, hoje.getTime(), 49);
			fail(
					"Nâ€žo deveria deixar pagar esse valor, pois 49 estÂ· abaixo do valor da parcela, que deve ser o valor mÃŒnimo a ser pago");
		} catch (ValorInvalido e) {
			// Ok. Barrou pagamento abaixo da parcela!
		}

	}

	@Test
	public void testePagamentoDeAssociadoRemido() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		// Remissâ€žo em 01/03/2021
		gc.set(2021, 02, 01);
		long remissao = gc.getTimeInMillis();
		Associado associado2 = new AssociadoRemido(2, "Raimundo", "3232-3232", data1, nasc, remissao);
		controle.adicionar(1306, associado2);

		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		Taxa taxa2 = new Taxa("B", 2021, 1200, 12, false);
		controle.adicionar(1306, taxa2);
		Date hoje = new Date();

		try {
			controle.registrarPagamento(1306, "A", 2021, 2, hoje.getTime(), 60);
			fail("Nâ€žo deveria deixar pagar esse valor, pois este associado Ãˆ remido");
		} catch (AssociadoJaRemido e) {
			// Ok. Barrou pagamento de taxa adminsitrativa!
		}

	}

	@Test
	public void testeTotalDePagamentoDeAssociadoRemido() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		// Remissâ€žo em 01/03/2021
		gc.set(2021, 02, 01);
		long remissao = gc.getTimeInMillis();
		Associado associado2 = new AssociadoRemido(2, "Raimundo", "3232-3232", data1, nasc, remissao);
		controle.adicionar(1306, associado2);

		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		Taxa taxa2 = new Taxa("B", 2021, 1200, 12, false);
		controle.adicionar(1306, taxa2);
		Date hoje = new Date();

		controle.registrarPagamento(1306, "B", 2021, 2, hoje.getTime(), 100);
		double valor = controle.somarPagamentoDeAssociado(1306, 2, "A", 2021, hoje.getTime(), hoje.getTime());
		assertEquals(0, valor, 0.01);

	}

	@Test
	public void testePagarOQueFaltaMenorQueOValorDaParcela() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		// Remissâ€žo em 01/03/2021
		gc.set(2021, 02, 01);
		long remissao = gc.getTimeInMillis();
		Associado associado2 = new AssociadoRemido(2, "Raimundo", "3232-3232", data1, nasc, remissao);
		controle.adicionar(1306, associado2);

		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		Taxa taxa2 = new Taxa("B", 2021, 1200, 12, false);
		controle.adicionar(1306, taxa2);
		Date hoje = new Date();

		controle.registrarPagamento(1306, "B", 2021, 2, hoje.getTime(), 100);
		controle.registrarPagamento(1306, "B", 2021, 2, hoje.getTime(), 1080);

		try {
			controle.registrarPagamento(1306, "B", 2021, 2, data1, 20);
		} catch (ValorInvalido e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			fail("O valor Ãˆ menor do que a parcela, mas deveria aceitar, uma vez que Ãˆ sÃ› o que falta para quitar o ano!");
		}

	}

	@Test
	public void testeTotalDePagamentosTudo() throws Exception {
		InterfaceAssociacao controle = new MinhaAssociacao();
		Associacao a1 = new Associacao(1306, "Cruzeiro do Sul V");
		controle.adicionar(a1);

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(1974, 10, 14);
		long nasc = gc.getTimeInMillis();
		gc.set(2021, 01, 01);
		long data1 = gc.getTimeInMillis();
		Associado associado1 = new Associado(1, "Pedro", "3232-3232", data1, nasc);
		controle.adicionar(1306, associado1);

		// Remissâ€žo em 01/03/2021
		gc.set(2021, 02, 01);
		long remissao = gc.getTimeInMillis();
		Associado associado2 = new AssociadoRemido(2, "Raimundo", "3232-3232", data1, nasc, remissao);
		controle.adicionar(1306, associado2);

		Taxa taxa1 = new Taxa("A", 2021, 600, 12, true);
		controle.adicionar(1306, taxa1);

		Taxa taxa2 = new Taxa("B", 2021, 1200, 12, false);
		controle.adicionar(1306, taxa2);
		Date hoje = new Date();

		controle.registrarPagamento(1306, "B", 2021, 2, hoje.getTime(), 100);
		controle.registrarPagamento(1306, "B", 2021, 2, hoje.getTime(), 1080);
		controle.registrarPagamento(1306, "B", 2021, 2, hoje.getTime(), 20);
		double valor = controle.somarPagamentoDeAssociado(1306, 2, "B", 2021, hoje.getTime(), hoje.getTime());
		assertEquals(1200, valor, 0.01);
	}

}
