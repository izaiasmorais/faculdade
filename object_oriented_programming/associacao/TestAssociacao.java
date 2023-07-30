package associacao;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import associacao.excecoes.*;

public class TestAssociacao {

	private MinhaAssociacao empresa;
	private Associacao associacao;

	@Before
	public void setUp() {
		empresa = new MinhaAssociacao();
		associacao = new Associacao(100, "Association");
	}

	@Test
	public void testCalcularFrequencia() {
		Associado associado = new Associado(1, "John Doe", "1234567890", new Date(), new Date());
		try {
			double frequencia = empresa.calcularFrequencia(associado.getNumero(), associacao.getNumero(), new Date(),
					new Date());
			double expectedValue = 0.6;
			assertEquals(expectedValue, frequencia, 0.001);
		} catch (AssociadoNaoExistente | ReuniaoNaoExistente | AssociacaoNaoExistente e) {
			assertFalse(true);
		}
	}

	@Test
	public void testRegistrarFrequencia() {
		Associado associado = new Associado(1, "John Smith", "7777777777", new Date(), new Date());
		Reuniao reuniao = new Reuniao(new Date(), "Meeting minutes");
		try {
			empresa.registrarFrequencia(associado.getNumero(), associacao.getNumero(), reuniao.getData());
			for (int frequencia : reuniao.getFrequencia()) {
				if (frequencia == associado.getNumero()) {
					assertTrue(true);
				} else {
					assertTrue(false);
				}
			}
		} catch (AssociadoNaoExistente | ReuniaoNaoExistente | AssociacaoNaoExistente | FrequenciaJaRegistrada
				| FrequenciaIncompativel e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testRegistrarPagamento() {
		Associado associado = new Associado(3, "Bob Smith", "5555555555", new Date(), new Date());
		try {
			empresa.registrarPagamento(associacao.getNumero(), "Membership Fee", 2023, associado.getNumero(), new Date(),
					100.0);
			assertTrue(false);
		} catch (AssociacaoNaoExistente | AssociadoNaoExistente | AssociadoJaRemido | TaxaNaoExistente | ValorInvalido e) {
				// TODO: handle exception
		}
	}

	@Test
	public void testSomarPagamentoDeAssociado() {
		Associado associado = new Associado(4, "Alice Johnson", "4444444444", new Date(), new Date());
		try {
			double totalPayments = empresa.somarPagamentoDeAssociado(associado.getNumero(), associacao.getNumero(),
					"Membership Fee", 2023, new Date(), new Date());
			double expectedValue = 1200.0;
			assertEquals(expectedValue, totalPayments, 0.001);
		} catch (AssociadoNaoExistente | AssociacaoNaoExistente | TaxaNaoExistente e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testCalcularTotalDeTaxas() {
		try {
			double totalTaxas = empresa.calcularTotalDeTaxas(associacao.getNumero(), 2023);
			double expectedValue = 2400.0;
			assertEquals(expectedValue, totalTaxas, 0.001);
		} catch (AssociacaoNaoExistente | TaxaNaoExistente e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testAdicionarAssociacao() {
		try {
			empresa.adicionarAssociacao(associacao);
			assertEquals(associacao, empresa.pesquisarAssociacao(associacao.getNumero()));
		} catch (AssociacaoJaExistente | ValorInvalido e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testAdicionarAssociado() {
		Associado associado = new Associado(7, "John Smith", "7777777777", new Date(), new Date());
		try {
			empresa.adicionarAssociado(associacao.getNumero(), associado);
			assertEquals(associado, empresa.pesquisarAssociado(associado.getNumero(), associacao.getNumero()));
		} catch (AssociacaoNaoExistente | AssociadoJaExistente | ValorInvalido e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testAdicionarReuniao() {
		Reuniao reuniao = new Reuniao(new Date(), "Meeting minutes");
		try {
			empresa.adicionarReuniao(associacao.getNumero(), reuniao);
			assertEquals(reuniao, empresa.pesquisarReuniao(associacao.getNumero(), reuniao.getData()));
		} catch (AssociacaoNaoExistente | ReuniaoJaExistente | ValorInvalido e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testAdicionarTaxa() {
		Taxa taxa = new Taxa("Membership Fee", 2023, 100.0, 12, true);
		try {
			empresa.adicionarTaxa(associacao.getNumero(), taxa);
			assertEquals(taxa, empresa.pesquisarTaxa(associacao.getNumero(), taxa));
		} catch (AssociacaoNaoExistente | TaxaJaExistente | ValorInvalido e) {
			// TODO: handle exception
		}
	}
}
