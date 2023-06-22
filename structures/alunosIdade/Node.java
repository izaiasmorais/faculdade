package alunosIdade;

public class Node {
	int age;
	Node prev;
	Node next;

	public Node(int age) {
		this.age = age;
		this.next = null;
		this.prev = null;
	}
}
