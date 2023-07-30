package associacao;

import java.util.Date;

public class Reuniao {
	Date data;
	String ata;

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
