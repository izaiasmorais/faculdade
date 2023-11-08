import java.util.ArrayList;

public class problema61 {
	public static void main(String[] args) {
		System.out.println(correctBracketing("("));
		// Falso

		System.out.println(correctBracketing("()"));
		// Verdadeiro

		System.out.println(correctBracketing("(()())"));
		// Verdadeiro

		System.out.println(correctBracketing(")(()"));
		// Falso
	}

	public static boolean correctBracketing(String texto) {

		ArrayList<Character> lista = new ArrayList<>();

		for (char c : texto.toCharArray()) {
			if (c == '(') {
				lista.add(c);
			} else if (c == ')') {
				if (lista.isEmpty() || lista.remove(lista.size() - 1) != '(') {
					return false;
				}
			}
		}
		return lista.isEmpty();
	}
}
