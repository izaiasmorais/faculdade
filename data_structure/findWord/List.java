package findWord;

public class List {
	Node start;
	Node end;
	int size;

	public List() {
		this.start = null;
		this.end = null;
		this.size = 0;
	}

	public int getSize() {
		return this.size;
	}

	public void addEnd(String name) {
		Node newName = new Node(name);

		if (getSize() == 0) {
			start = newName;
			end = newName;
			size++;
		} else {
			newName.prev = end;
			end.next = newName;
			end = newName;
			size++;
		}
	}

	public String listString() {
		Node curr = start;
		String list = "";

		while (curr != null) {
			if (curr.next != null) {
				list = list + curr.name + " - ";
			} else {
				list = list + curr.name;
			}
			curr = curr.next;
		}

		return list;
	}
}
