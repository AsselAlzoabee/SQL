package business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import csvImport.ConcreteReaderCreator;
import csvImport.ReaderCreator;
import csvImport.ReaderProduct;
import ownUtil.Observable;
import ownUtil.Observer;

public class GetraenkModel implements Observable {

	private static Getraenk getraenk;
	// static instance
	private static GetraenkModel getraenkModel;

	// vector
	private Vector<Observer> observers = new Vector<Observer>();

	// private const
	private GetraenkModel() {

	}

	public Vector<Observer> getObservers() {
		return observers;
	}

	public void setObservers(Vector<Observer> observers) {
		this.observers = observers;
	}

	// zugriff methode
	public static GetraenkModel getGetraenkModel() {
		if (getraenkModel == null) {
			return getraenkModel = new GetraenkModel();
		}
		return getraenkModel;

	}

	public void schreibeGetraenkInCsvDatei() throws IOException {

		BufferedWriter aus = new BufferedWriter(new FileWriter("GetraenkAusgabe.csv", false));
		aus.write(getraenk.gibGetraenkZurueck(';'));
		aus.close();
		notifyObservers();
	}

	public void leseAusDatei(String typ) throws IOException {

		ReaderCreator creator = new ConcreteReaderCreator();
		ReaderProduct readerProduct = creator.factoryMethod(typ);

		String[] zeile = readerProduct.leseAusDatei();
		readerProduct.schliesseDatei();
		this.getraenk = new Getraenk(zeile[0], Float.parseFloat(zeile[1]), Float.parseFloat(zeile[2]), zeile[3],
				zeile[4].split("_"));
		notifyObservers();
	}

	public static Getraenk getGetraenk() {
		return getraenk;
	}

	public static void setGetraenk(Getraenk getraenk) {
		GetraenkModel.getraenk = getraenk;
	}

	@Override
	public void addObserver(Observer obs) {
		this.observers.addElement(obs);
	}

	@Override
	public void removeObserver(Observer obs) {

		this.observers.removeElement(obs);

	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update();
		}

	}

}
