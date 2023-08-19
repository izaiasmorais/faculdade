import java.util.List;

public class problema43 {
	public static boolean pairsSumToZero(List<Integer> l) {
		for (int i = 0; i < l.size(); i++) {
			for (int j = i + 1; j < l.size(); j++) {
				if (l.get(i) + l.get(j) == 0) {
					return true;
				}
			}
		}
		return false;
	}

}
