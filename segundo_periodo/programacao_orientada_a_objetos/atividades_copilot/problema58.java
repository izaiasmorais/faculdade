import java.util.ArrayList;
import java.util.List;

public class problema58 {
	public static void main(String[] args) {
		List<Integer> l1 = new ArrayList<>();
		List<Integer> l2 = new ArrayList<>();
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l2.add(3);
		l2.add(4);
		l2.add(5);

		List<Integer> result = comum(l1, l2);

		System.out.println(result); // Deve retornar [3]
	}

	public static List<Integer> comum(List<Integer> l1, List<Integer> l2) {
		List<Integer> comumItems = new ArrayList<>();

		for (int item : l1) {
			for (int item2 : l2) {
				if (item == item2) {
					comumItems.add(item);
				}
			}
		}

		return comumItems;
	}
}
