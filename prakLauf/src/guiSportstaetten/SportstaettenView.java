package guiSportstaetten;

import business.GetraenkModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.MeldungsfensterAnzeiger;
import ownUtil.Observer;

public class SportstaettenView implements Observer {

	public static SportstaettenControl getraenkControl;
	public static GetraenkModel getraenkModel;
	

	// ---Anfang Attribute der grafischen Oberflaeche---
	private Pane pane = new Pane();
	private Label lblEingabe = new Label("Eingabe");
	private Label lblAnzeige = new Label("Anzeige");
	private Label lblName = new Label("Name:");
	private Label lblGeoeffnetVon = new Label("Ge�ffnet von:");
	private Label lblGeoeffnetBis = new Label("Ge�ffnet bis:");
	private Label lblBeckenlaenge = new Label("Beckenl�nge:");
	private Label lblWassTemperatur = new Label("Wassertemperatur:");
	private TextArea txtAnzeige = new TextArea();
	private Button btnEingabe = new Button("Eingabe");
	private Button btnAnzeige = new Button("Anzeige");
	private MenuBar mnbrMenuLeiste = new MenuBar();
	// -------Ende Attribute der grafischen Oberflaeche-------

	public SportstaettenView(SportstaettenControl sportstaettenControl, GetraenkModel getraenkModel,
			Stage primaryStage) {
		this.getraenkModel = getraenkModel;
		this.getraenkControl = sportstaettenControl;

		Scene scene = new Scene(this.pane, 700, 340);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Verwaltung von Getraenk");
		primaryStage.show();
		this.initKomponenten();
		this.initListener();
		this.getraenkModel.addObserver(this);
	}

	private void initKomponenten() {
		// Labels
		lblEingabe.setLayoutX(20);
		lblEingabe.setLayoutY(40);
		Font font = new Font("Arial", 24);
		lblEingabe.setFont(font);
		lblEingabe.setStyle("-fx-font-weight: bold;");
		lblAnzeige.setLayoutX(400);
		lblAnzeige.setLayoutY(40);
		lblAnzeige.setFont(font);
		lblAnzeige.setStyle("-fx-font-weight: bold;");

		pane.getChildren().addAll(lblAnzeige);

		// Textbereich
		txtAnzeige.setEditable(false);
		txtAnzeige.setLayoutX(400);
		txtAnzeige.setLayoutY(90);
		txtAnzeige.setPrefWidth(270);
		txtAnzeige.setPrefHeight(185);
		pane.getChildren().add(txtAnzeige);

		btnAnzeige.setLayoutX(400);
		btnAnzeige.setLayoutY(290);
		pane.getChildren().addAll(btnAnzeige);

		pane.getChildren().add(mnbrMenuLeiste);
	}

	private void initListener() {

		btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				update();

			}
		});

	}

//	public void zeigeGetraenkAn() {
//		if (this.getraenkModel.getGetraenk() != null) {
//			txtAnzeige.setText(this.getraenkModel.getGetraenk().gibGetraenkZurueck(' '));
//		} else {
//			zeigeInformationsfensterAn("Bisher wurde kein Getraenk aufgenommen!");
//		}
//	}

	void zeigeInformationsfensterAn(String meldung) {
		new MeldungsfensterAnzeiger(AlertType.INFORMATION, "Information", meldung).zeigeMeldungsfensterAn();
	}

	void zeigeFehlermeldungsfensterAn(String meldung) {
		new MeldungsfensterAnzeiger(AlertType.ERROR, "Fehler", meldung).zeigeMeldungsfensterAn();
	}

	@Override
	public void update() {

		if (this.getraenkModel.getGetraenk() != null) {
			txtAnzeige.setText(this.getraenkModel.getGetraenk().gibGetraenkZurueck(' '));
		} else {
			zeigeInformationsfensterAn("Bisher wurde kein Getraenk aufgenommen!");
		}
	}

}
