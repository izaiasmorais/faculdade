package encontrarPalavra;

public class Node {
	String name;
	Node prev;
	Node next;

	public Node(String name) {
		this.name = name;
		this.prev = null;
		this.next = null;
	}
}
