package alunosIdade;

public class List {
	Node start;
	Node end;
	int size;
	int oldest;
	int newest;
	float sum = 0;

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
		}

		if (newNode.age < this.newest) {
			this.newest = newNode.age;
		}

		if (newNode.age > this.oldest) {
			this.oldest = newNode.age;
		}

		this.sum += newNode.age;
	}

	public void listAges() {
		Node currentNode = this.start;

		while (currentNode != null) {
			System.out.print(currentNode.age + " ");
			currentNode = currentNode.next;
		}

		System.out.println(" ");
	}

	public int getNewest() {
		return this.newest;
	}

	public int getOldest() {
		return this.oldest;
	}

	public float getAverage() {
		return this.sum / size;
	}

	public int getMedian() {
		Node aux = this.start;
		int current = 1;

		if (this.getSize() % 2 != 0) {
			while (current <= ((this.size - 1) / 2)) {
				aux = aux.next;
				current++;
			}
			return aux.age;
		} else {
			while (current < (this.size / 2)) {
				aux = aux.next;
				current++;
			}
			return (aux.age + aux.next.age) / 2;
		}
	}

	public int getSize() {
		return this.size;
	}
}
