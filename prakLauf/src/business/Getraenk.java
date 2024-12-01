package business;

public class Getraenk {

	// artikelnummer des Buergeramtes
	private String artikelnummer;
	// Oeffnungszeiten
	private float einkaufspreis;
	private float verkaufspreis;
	// Strasse und Hausnummer des Buergeramtes
	private String mitAlkohol;
	// behaelnis des Buergeramtes
	private String[] behaelnis;

	public Getraenk(String artikelnummer, float einkaufspreis, float verkaufspreis, String mitAlkohol,
			String[] behaelnis) {
		this.artikelnummer = artikelnummer;
		this.einkaufspreis = einkaufspreis;
		this.verkaufspreis = verkaufspreis;
		this.mitAlkohol = mitAlkohol;
		this.behaelnis = behaelnis;
	}

	public String getartikelnummer() {
		return artikelnummer;
	}

	public void setartikelnummer(String artikelnummer) {
		this.artikelnummer = artikelnummer;
	}

	public float geteinkaufspreis() {
		return einkaufspreis;
	}

	public void seteinkaufspreis(float einkaufspreis) {
		this.einkaufspreis = einkaufspreis;
	}

	public float getverkaufspreis() {
		return verkaufspreis;
	}

	public void setverkaufspreis(float verkaufspreis) {
		this.verkaufspreis = verkaufspreis;
	}

	public String getmitAlkohol() {
		return mitAlkohol;
	}

	public void setmitAlkohol(String mitAlkohol) {
		this.mitAlkohol = mitAlkohol;
	}

	public String[] getbehaelnis() {
		return behaelnis;
	}

	public void setbehaelnis(String[] behaelnis) {
		this.behaelnis = behaelnis;
	}

	public String getbehaelnisAlsString(char trenner) {
		String ergebnis = "";
		int i = 0;
		for (i = 0; i < this.getbehaelnis().length - 1; i++) {
			ergebnis = ergebnis + this.getbehaelnis()[i] + trenner;
		}
		return ergebnis + this.getbehaelnis()[i];
	}

	public String gibGetraenkZurueck(char trenner) {
		return this.getartikelnummer() + trenner + this.geteinkaufspreis() + trenner + this.getverkaufspreis() + trenner
				+ this.getmitAlkohol() + trenner + this.getbehaelnisAlsString(trenner);
	}
}
