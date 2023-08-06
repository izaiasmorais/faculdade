package associacao;

import java.util.Date;
import java.util.GregorianCalendar;

public class Main {
	public static void main(String[] args) throws Exception {
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
		System.out.println(valor);
	}
}
