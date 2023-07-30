package produtosPereciveisComExcecao.exceptions;

public class ProdutoJaCadastrado extends Exception {
	public ProdutoJaCadastrado() {
		super("Produto ja cadastrado");
	}
}
