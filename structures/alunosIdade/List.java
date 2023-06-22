package alunosIdade;

public class List {
	Node start;
	Node end;
	int size;
	int oldest;
	int newest;

	public List() {
		this.start = null;
		this.end = null;
	}

	public void addAge(int age) {
		Node newNode = new Node(age);

		if (this.getSize() == 0) {
			this.start = newNode;
			this.end = newNode;
			this.size++;
			this.newest = newNode.age;
			this.oldest = newNode.age;
		} else {
			newNode.prev = this.end;
			this.end.next = newNode;
			this.end = newNode;
			this.size++;

			if (newNode.age < this.newest) {
				this.newest = newNode.age;
			}

			if (newNode.age > this.oldest) {
				this.oldest = newNode.age;
			}
		}
	}

	public void listAges() {
		Node currentNode = this.start;

		while (currentNode != null) {
			System.out.print(currentNode.age + " ");
			currentNode = currentNode.next;
		}

		System.out.println(" ");
	}

	public void getNewestAndOldest() {
		System.out.println("Newest student age: " + this.newest + "\n");
		System.out.println("Oldest student age: " + this.oldest + "\n");
	}

	public int getSize() {
		return this.size;
	}
}
