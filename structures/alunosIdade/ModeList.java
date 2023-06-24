package alunosIdade;

public class ModeList {
	ModeNode start;
	ModeNode end;
	int size;
	int bigger = 1;

	public ModeList() {
		this.start = null;
		this.end = null;
		this.size = 0;
		this.bigger = 0;
	}

	public int getSize() {
		return this.size;
	}

	public boolean alreadyAdded(int age) {
		ModeNode aux = this.start;
		boolean already = false;

		while (aux != null) {
			if (age == aux.age) {
				already = true;
				break;
			}
			aux = aux.next;
		}

		return already;
	}

	public void incrementQuantity(int age) {
		ModeNode aux = this.start;

		while (aux != null) {
			if (age == aux.age) {
				aux.quantity++;

				if (aux.quantity > bigger) {
					bigger = aux.quantity;
				}

				System.out.println("Incremented quantity");
				break;
			}
			aux = aux.next;
		}
	}

	public void addAge(int age) {
		ModeNode newNode = new ModeNode(age, 1);

		if (this.getSize() == 0) {
			this.start = newNode;
			this.end = newNode;
			this.size++;
		} else {
			if (alreadyAdded(newNode.age)) {
				incrementQuantity(newNode.age);
			} else {
				newNode.prev = this.end;
				this.end.next = newNode;
				this.end = newNode;
				this.size++;
			}
		}
	}

	public void listModes() {
		ModeNode aux = this.start;

		while (aux != null) {
			if (aux.next != null) {
				System.out.print("[" + aux.age + "," + aux.quantity + "]" + ", ");
			} else {
				System.out.print("[" + aux.age + "," + aux.quantity + "]");
			}
			aux = aux.next;
		}
		System.out.println("\n");
	}

	public void listMode() {
		ModeNode aux = this.start;

		System.out.print("Mode: ");
		while (aux != null) {
			if (aux.quantity == bigger) {
				if (aux.next != null) {
					System.out.print(aux.age + " ");
				} else {
					System.out.print(aux.age);
				}
			}
			aux = aux.next;
		}
		System.out.println("\n");
	}
}
