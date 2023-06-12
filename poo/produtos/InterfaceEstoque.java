import java.util.ArrayList;
import java.util.Date;

public interface InterfaceEstoque {
	boolean incluir(Produto p);

	boolean comprar(int cod, int quant, double preco, Date val);

	double vender(int cod, int quant);

	Produto pesquisar(int cod);

	ArrayList<Produto> estoqueAbaixoDoMinimo();

	ArrayList<Produto> estoqueVencido();

	int quantidadeVencidos(int cod) throws ProdutoInexistente;
}
