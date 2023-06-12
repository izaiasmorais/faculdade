import java.util.ArrayList;

class ProdutoPerecivel extends Produto {
	private ArrayList<Lote> lotes;

	public ProdutoPerecivel(int cod, String desc, int min, double lucro, Fornecedor forn) {
		super(cod, desc, min, lucro, forn);
		lotes = new ArrayList<>();
	}

	public ArrayList<Lote> getLotes() {
		return lotes;
	}

	// public boolean temLotesVencidos() {

	// }

	// public int getQuantidadeLotesVencidos() {

	// }
}
