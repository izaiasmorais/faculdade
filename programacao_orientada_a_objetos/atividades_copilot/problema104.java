import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class problema104 {
	public static void main(String[] args) {
		List<Integer> numeros = new ArrayList<>();
		numeros.add(15);
		numeros.add(33);
		numeros.add(1422);
		numeros.add(1);

		int[] arrayInteiros = new int[digitosUnicos(numeros).size()];

		for (int i = 0; i < digitosUnicos(numeros).size(); i++) {
			arrayInteiros[i] = digitosUnicos(numeros).get(i);
		}

		Arrays.sort(arrayInteiros);

		for (int numero : arrayInteiros) {
			System.out.print(numero);
			System.out.print(" ");
		}

	}

	public static List<Integer> digitosUnicos(List<Integer> l) {
		List<Integer> numeros = new ArrayList<>();
		boolean possuiPar = false;

		for (int numero : l) {
			List<Integer> digitos = numeroParaArray(numero);
			for (int digito : digitos) {
				if (digito % 2 == 0) {
					possuiPar = true;
				}
			}
			if (possuiPar == false) {
				numeros.add(numero);
			}
			possuiPar = false;
		}

		return numeros;
	}

	public static ArrayList<Integer> numeroParaArray(int numero) {
		ArrayList<Integer> digitos = new ArrayList<>();

		String numeroString = Integer.toString(numero);

		for (int i = 0; i < numeroString.length(); i++) {
			int digito = Character.getNumericValue(numeroString.charAt(i));
			digitos.add(digito);
		}

		return digitos;
	}
}
