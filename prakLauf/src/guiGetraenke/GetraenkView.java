package guiGetraenke;

import business.GetraenkModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.MeldungsfensterAnzeiger;
import ownUtil.Observer;

public class GetraenkView implements Observer {

	public static GetraenkControl getraenkControl;
	public static GetraenkModel getraenkModel;

	// ---Anfang Attribute der grafischen Oberflaeche---
	private Pane pane = new Pane();
	private Label lblEingabe = new Label("Eingabe");
	private Label lblAnzeige = new Label("Anzeige");
	private Label lblArtikelnummer = new Label("Artikelnummer:");
	private Label lblEinkaufspreis = new Label("Einkaufspreis: ");
	private Label lblVerkaufspreis = new Label("Verkaufspreis:");
	private Label lblmitAlkohol = new Label("mitAlkohol:");
	private Label lblBehaeltnis = new Label("Behaeltnis:");
	private TextField txtArtikelnummer = new TextField();
	private TextField txtEinkaufspreis = new TextField();
	private TextField txtVerkaufspreis = new TextField();
	private TextField txtMitAlkohol = new TextField();
	private TextField txtBehaeltnis = new TextField();
	private TextArea txtAnzeige = new TextArea();
	private Button btnEingabe = new Button("Eingabe");
	private Button btnAnzeige = new Button("Anzeige");
	private MenuBar mnbrMenuLeiste = new MenuBar();
	private Menu mnDatei = new Menu("Datei");
	private MenuItem mnItmCsvImport = new MenuItem("csv-Import");
	private MenuItem mnItmTxtImport = new MenuItem("txt-Import");
	private MenuItem mnItmCsvExport = new MenuItem("csv-Export");
	// -------Ende Attribute der grafischen Oberflaeche-------

	public GetraenkView(GetraenkControl getraenkControl, GetraenkModel getraenkModel, Stage primaryStage) {
		this.getraenkModel = getraenkModel;
		this.getraenkControl = getraenkControl;

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
		lblArtikelnummer.setLayoutX(20);
		lblArtikelnummer.setLayoutY(90);
		lblEinkaufspreis.setLayoutX(20);
		lblEinkaufspreis.setLayoutY(130);
		lblVerkaufspreis.setLayoutX(20);
		lblVerkaufspreis.setLayoutY(170);
		lblmitAlkohol.setLayoutX(20);
		lblmitAlkohol.setLayoutY(210);
		lblBehaeltnis.setLayoutX(20);
		lblBehaeltnis.setLayoutY(250);
		pane.getChildren().addAll(lblEingabe, lblAnzeige, lblArtikelnummer, lblEinkaufspreis, lblVerkaufspreis,
				lblmitAlkohol, lblBehaeltnis);

		// Textfelder
		txtArtikelnummer.setLayoutX(170);
		txtArtikelnummer.setLayoutY(90);
		txtArtikelnummer.setPrefWidth(200);
		txtEinkaufspreis.setLayoutX(170);
		txtEinkaufspreis.setLayoutY(130);
		txtEinkaufspreis.setPrefWidth(200);
		txtVerkaufspreis.setLayoutX(170);
		txtVerkaufspreis.setLayoutY(170);
		txtVerkaufspreis.setPrefWidth(200);
		txtMitAlkohol.setLayoutX(170);
		txtMitAlkohol.setLayoutY(210);
		txtMitAlkohol.setPrefWidth(200);
		txtBehaeltnis.setLayoutX(170);
		txtBehaeltnis.setLayoutY(250);
		txtBehaeltnis.setPrefWidth(200);
		pane.getChildren().addAll(txtArtikelnummer, txtEinkaufspreis, txtVerkaufspreis, txtMitAlkohol, txtBehaeltnis);

		// Textbereich
		txtAnzeige.setEditable(false);
		txtAnzeige.setLayoutX(400);
		txtAnzeige.setLayoutY(90);
		txtAnzeige.setPrefWidth(270);
		txtAnzeige.setPrefHeight(185);
		pane.getChildren().add(txtAnzeige);

		// Buttons
		btnEingabe.setLayoutX(20);
		btnEingabe.setLayoutY(290);
		btnAnzeige.setLayoutX(400);
		btnAnzeige.setLayoutY(290);
		pane.getChildren().addAll(btnEingabe, btnAnzeige);

		// Menue
		this.mnbrMenuLeiste.getMenus().add(mnDatei);
		this.mnDatei.getItems().add(mnItmCsvImport);
		this.mnDatei.getItems().add(mnItmTxtImport);
		this.mnDatei.getItems().add(new SeparatorMenuItem());
		this.mnDatei.getItems().add(mnItmCsvExport);
		pane.getChildren().add(mnbrMenuLeiste);
	}

	private void initListener() {
		btnEingabe.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				getraenkControl.nehmeGetraenkAuf();
			}
		});
		btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				update();
			}
		});
		mnItmCsvImport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				getraenkControl.leseAusDatei("csv");
			}
		});
		mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				getraenkControl.leseAusDatei("txt");
			}
		});
		mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				getraenkControl.schreibeGetraenkInCsvDatei();
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

	public TextField getTxtArtikelnummer() {
		return txtArtikelnummer;
	}

	public void setTxtArtikelnummer(TextField txtArtikelnummer) {
		this.txtArtikelnummer = txtArtikelnummer;
	}

	public TextField getTxtEinkaufspreis() {
		return txtEinkaufspreis;
	}

	public void setTxtEinkaufspreis(TextField txtEinkaufspreis) {
		this.txtEinkaufspreis = txtEinkaufspreis;
	}

	public TextField getTxtVerkaufspreis() {
		return txtVerkaufspreis;
	}

	public void setTxtVerkaufspreis(TextField txtVerkaufspreis) {
		this.txtVerkaufspreis = txtVerkaufspreis;
	}

	public TextField getTxtMitAlkohol() {
		return txtMitAlkohol;
	}

	public void setTxtMitAlkohol(TextField txtMitAlkohol) {
		this.txtMitAlkohol = txtMitAlkohol;
	}

	public TextField getTxtBehaeltnis() {
		return txtBehaeltnis;
	}

	public void setTxtBehaeltnis(TextField txtBehaeltnis) {
		this.txtBehaeltnis = txtBehaeltnis;
	}

	public TextArea getTxtAnzeige() {
		return txtAnzeige;
	}

	public void setTxtAnzeige(TextArea txtAnzeige) {
		this.txtAnzeige = txtAnzeige;
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
