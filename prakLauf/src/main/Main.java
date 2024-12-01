package main;

import guiGetraenke.GetraenkControl;
import guiSportstaetten.SportstaettenControl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		// Fenster zu Freizeitbaedern
		new GetraenkControl(primaryStage);
		// Fenster zu Sportstaettten
		Stage fensterSportstaetten = new Stage();
		new SportstaettenControl(fensterSportstaetten);
	}
}
//--module-path "<Pfad_zum_JavaFX_SDK>\lib" --add-modules javafx.controls,javafx.fxml
