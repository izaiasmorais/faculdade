
public class Main {
	public static void main(String[] args) {
		int[] ints = { 1, 2, 4, 5, 7, 9, 11 };

		System.out.println(binarySearch(ints, 4));
	}

	public static int binarySearch(int[] numbers, int value) {
		int low = 0;
		int high = numbers.length - 1;

		while (low <= high) {
			int middleIndex = (low + high) / 2;
			int middleValue = numbers[middleIndex];

			if (value == middleValue) {
				return middleIndex;
			}
			if (value < middleValue) {
				high = middleIndex - 1;
			} else {
				low = middleIndex + 1;
			}
		}
		return -1;
	}
}
