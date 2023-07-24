package estoqueComProdutoPerecivel;

import java.util.ArrayList;
import java.util.Date;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

class ProdutoPerecivel extends Produto {
	private final ArrayList<Lote> lotes;

	public ProdutoPerecivel(int cod, String desc, int min, double lucro, Fornecedor forn) {
		super(cod, desc, min, lucro, forn);
		lotes = new ArrayList<>();
	}

	public ArrayList<Lote> getLotes() {
		return lotes;
	}

	public boolean temLotesVencidos() {
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		for (Lote lote : lotes) {
			if (lote.getValidade().compareTo(data) < 0 && !lote.getValidade().equals(new Date(0))) {
				return true;
			}
		}
		return false;
	}

	public int getQuantidadeLotesVencidos() {
		Date data = Date.from(Instant.now(Clock.system(ZoneId.of("America/Sao_Paulo"))));
		int quantidadeVencidos = 0;
		for (Lote lote : lotes) {
			if (lote.getValidade().compareTo(data) < 0 && !lote.getValidade().equals(new Date(0))) {
				quantidadeVencidos += lote.getQuantidade();
			}
		}
		return quantidadeVencidos;
	}
}
