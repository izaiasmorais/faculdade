package encontrarPalavra;

public class findWord {

	public static void main(String[] args) {
		List list = new List();

		list.addEnd("Izaias");
		list.addEnd("Marcos");
		list.addEnd("Alfonse");
		list.addEnd("Levi");

		System.out.println(list.listString());
	}
}
