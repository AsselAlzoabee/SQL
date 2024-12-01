package guiSportstaetten;

import java.io.IOException;

import business.GetraenkModel;
import javafx.stage.Stage;

public class SportstaettenControl {

	public SportstaettenView getraenkView;
	public GetraenkModel getraenkModel;

	public SportstaettenControl(Stage primaryStage) {
		this.getraenkModel = GetraenkModel.getGetraenkModel();
		this.getraenkView = new SportstaettenView(this, getraenkModel, primaryStage);
//		this.getraenkModel.addObserver(this);
	}

	public void leseAusDatei(String typ) {
		try {

			this.getraenkModel.leseAusDatei(typ);
			this.getraenkView.zeigeInformationsfensterAn("Der Getrank wurden gelesen!");
		} catch (IOException exc) {
			this.getraenkView.zeigeFehlermeldungsfensterAn("IOException beim Lesen!");
		} catch (Exception exc) {
			this.getraenkView.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Lesen!");
		}
	}

	public void schreibeGetraenkInCsvDatei() {
		try {
			this.getraenkModel.schreibeGetraenkInCsvDatei();
			this.getraenkView.zeigeInformationsfensterAn("Die Getranke wurden gespeichert!");
		} catch (IOException exc) {
			this.getraenkView.zeigeFehlermeldungsfensterAn("IOException beim Speichern!");
		} catch (Exception exc) {
			this.getraenkView.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Speichern!");
		}
	}

//	@Override
//	public void update() {
//		this.getraenkView.update();
//		// test
//		JOptionPane.showMessageDialog(null, "irgendwas2");
//	}

//	public void nehmeGetraenkAuf() {
//		try {
//
//			getraenkModel.setGetraenk(new Getraenk(this.getraenkView.getTxtArtikelnummer().getText(),
//					Float.parseFloat(this.getraenkView.getTxtEinkaufspreis().getText()),
//					Float.parseFloat(this.getraenkView.getTxtVerkaufspreis().getText()),
//					this.getraenkView.getTxtMitAlkohol().getText(),
//					this.getraenkView.getTxtBehaeltnis().getText().split(";")));
//			this.getraenkView.zeigeInformationsfensterAn("Der Getraenk wurde aufgenommen!");
//		} catch (Exception exc) {
//			getraenkView.zeigeFehlermeldungsfensterAn(exc.getMessage());
//		}
//	}

}
