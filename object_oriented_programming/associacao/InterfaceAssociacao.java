package associacao;

import associacao.excecoes.*;
import java.util.Date;

public interface InterfaceAssociacao {

	// Calcula a frequência de um associado nas reuniões ocorridas durante um
	// determinado período, retornando um número entre 0 e 1 (ex: 0,6, indicando que
	// o associado participou de 60% das reuniões.

	public double calcularFrequencia(int numAssociado, int numAssociacao, Date inicio, Date fim)
			throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente;

	// Registra a frequencia de um associado em uma reunião. não deveria registrar
	// participacao em reunioes acontecidas antes da sua filiacao na associação.

	public void registrarFrequencia(int codigoAssociado, int numAssociacao, Date dataReuniao)
			throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente, FrequenciaJaRegistrada,
			FrequenciaIncompativel;

	// Registra o pagamento de uma taxa, em uma associação, dentro uma determinada
	// competência, para um associado. O valor a ser pago não pode ser menor que uma
	// parcela, embora não precise ser exatamente duas parcelas. Uma parcela de
	// R$20,00 por mês aceita um pagamento de R$30,00, sendo uma parcela completa e
	// um pedaço da próxima. Associados remidos não deveriam mais realizar
	// pagamentos de taxas administrativas vigentes em datas antes da sua remissão,
	// gerando exceção de AssociadoJaRemido se houver tentativa de se pagar algo
	// para esse caso. Caso o valor a ser pago seja menor que o mínimo (não sendo o
	// ultimo do ano!) ou gerando pagamento maior que a taxa anual, gerar exceção de
	// ValorInvalido. Lembrar de verificar valores negativos.

	public void registrarPagamento(int numAssociacao, String taxa, int vigencia, int numAssociado, Date data,
			double valor)
			throws AssociacaoNaoExistente, AssociadoNaoExistente, AssociadoJaRemido, TaxaNaoExistente, ValorInvalido;

	// Calcula o total de pagamentos realizado por um associado, em uma associação,
	// para uma taxa, que possui uma vigência, dentro de um certo período de tempo.

	public double somarPagamentoDeAssociado(int numAssociacao, int numAssociado, String nomeTaxa, int vigencia,
			Date inicio, Date fim) throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente;

	// Calcula o total de taxas previstas para um dado ano, em uma associação.

	public double calcularTotalDeTaxas(int numAssociacao, int vigencia) throws AssociacaoNaoExistente, TaxaNaoExistente;

	// Adiciona uma associação a ser gerenciada. Valida todos os campos para evitar
	// dados não preenchidos.

	public void adicionarAssociacao(Associacao a) throws AssociacaoJaExistente, ValorInvalido;

	// Adiciona um associado a uma associação. Valida todos os campos para evitar
	// dados não preenchidos.

	public void adicionarAssociado(int associacao, Associado a) throws AssociacaoNaoExistente, AssociadoJaExistente, ValorInvalido;

	// Adiciona uma reunião a uma associação. Valida todos os campos para evitar
	// dados não preenchidos.

	public void adicionarReuniao(int associacao, Reuniao r) throws AssociacaoNaoExistente, ReuniaoJaExistente, ValorInvalido;

	// Adiciona uma taxa a uma associação. Valida todos os campos para evitar dados
	// não preenchidos.

	public void adicionarTaxa(int associacao, Taxa t) throws AssociacaoNaoExistente, TaxaJaExistente, ValorInvalido;

}
