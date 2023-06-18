public class Node {
	People people;
	Node prev;
	Node next;

	public Node(People people) {
		this.people = people;
		this.prev = null;
		this.next = null;
	}
}
