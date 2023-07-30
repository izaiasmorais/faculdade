package associacao;

import java.util.ArrayList;
import java.util.Date;

public class Reuniao {
	Date data;
	String ata;
	ArrayList<Integer> frequencia = new ArrayList<>();

	public Reuniao(Date data, String ata) {
		this.data = data;
		this.ata = ata;
	}

	public Date getData() {
		return data;
	}

	public String getAta() {
		return ata;
	}
}
