package csvImport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConcreteTxtReaderProduct extends ReaderProduct {

	BufferedReader ein;

	public ConcreteTxtReaderProduct() {
		// TODO Auto-generated constructor stub

		try {
			ein = new BufferedReader(new FileReader("GetraenkAusgabe.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String[] leseAusDatei() throws IOException {
		// TODO Auto-generated method stub
		String[] ergebnisZeile = new String[5];
		String zeile = ein.readLine();
		int i = 0;

		while (i < ergebnisZeile.length) {
			ergebnisZeile[i] = zeile;
			zeile = ein.readLine();
			i++;
		}

		return ergebnisZeile;
	}

	@Override
	public void schliesseDatei() throws IOException {
		// TODO Auto-generated method stub
		ein.close();
	}
}
