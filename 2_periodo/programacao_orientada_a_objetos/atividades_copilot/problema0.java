import java.util.List;

public class problema0 {

	public static boolean hasCloseElements(List<Double> numbers, double threshold) {
		for (int i = 0; i < numbers.size(); i++) {
			for (int j = i + 1; j < numbers.size(); j++) {
				if (Math.abs(numbers.get(i) - numbers.get(j)) < threshold) {
					return true;
				}
			}
		}
		return false;
	}
}
