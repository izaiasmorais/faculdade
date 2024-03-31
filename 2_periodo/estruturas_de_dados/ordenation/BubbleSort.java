public class BubbleSort {
	public static void main(String[] args) {
		int[] numbers = { 9, 2, 6, 1, 7, 5 };
		bubbleSort(numbers);
	}

	public static void bubbleSort(int[] numbers) {
		boolean swap = true;
		while (swap) {
			swap = false;
			for (int i = 0; i < numbers.length - 1; i++) {
				if (numbers[i] > numbers[i + 1]) {
					swap = true;
					int temp = numbers[i];
					numbers[i] = numbers[i + 1];
					numbers[i + 1] = temp;
				}
				listNumbers(numbers);
				System.out.println("");
			}
		}
	}

	public static void listNumbers(int[] numbers) {
		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");
		}
	}
}
