package csvImport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConcreteCsvReaderProduct extends ReaderProduct {

	BufferedReader ein;

	public ConcreteCsvReaderProduct() {

		try {
			ein = new BufferedReader(new FileReader("GetraenkAusgabe.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String[] leseAusDatei() throws IOException {
		String[] zeile = ein.readLine().split(";");
		ein.close();

		return zeile;
	}

	@Override
	public void schliesseDatei() throws IOException {

		ein.close();
	}

}
