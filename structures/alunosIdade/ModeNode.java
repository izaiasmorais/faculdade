package alunosIdade;

public class ModeNode {
	int age;
	int quantity;
	ModeNode prev;
	ModeNode next;

	public ModeNode(int age, int quantity) {
		this.age = age;
		this.quantity = quantity;
		this.next = null;
		this.prev = null;
	}
}
