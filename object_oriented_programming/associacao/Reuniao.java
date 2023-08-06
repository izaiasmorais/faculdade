package associacao;

import java.util.ArrayList;

public class Reuniao {
	long data;
	String ata;
	ArrayList<Integer> frequencia = new ArrayList<>();

	public Reuniao(long data, String ata) {
		this.data = data;
		this.ata = ata;
	}

	public long getData() {
		return data;
	}

	public String getAta() {
		return ata;
	}

	public ArrayList<Integer> getFrequencia() {
		return frequencia;
	}

}
