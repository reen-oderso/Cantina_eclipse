import java.util.*;
import java.io.*;

/**
 * Die Klasse Kantinenplanung ist die auszuf�hrende Klasse, welche die
 * Ablauflogik des Programms umfasst. Sie enth�lt neben der Main-Methode auch
 * Attribute f�r die wesentlichen Parameter, die zum Programmstart notwendig
 * sind. Diese werden �ber Java-Properties realisiert. Au�erdem enth�lt sie die
 * Export-Methoden f�r die Kantinenplaene und die Einkaufsliste
 * 
 * F�r den Planungsdurchgang steht diese Klasse mit der Lieferantenverwaltung,
 * der Rezeptverwaltung, dem Kantinenplan, der Einkaufliste und zuletzt dem
 * Exporter in Assoziation.
 * 
 * @author Rene Wiederhold
 * @version 0.01
 */
public class Kantinenplanung {

	// Die wesentlichen Parameter, welche f�r die Anwendung ben�tigt werden
	/** Die Anzahl der zu erstellenden Kantinenpl�ne */
	private int anzKantinen;
	/** Der relative oder absolute Pfad zur Rezepte-Datei */
	private String rezeptPfad;
	/** Der relative oder absolute Pfad zur Hitliste-Datei */
	private String hitListenPfad;
	/**
	 * Der relative oder absolute Pfad zum Ordner, welche die Preislisten der
	 * Lieferanten enth�lt
	 */
	private String preisListenOrdner;
	/** Die Pauschale, welche mit der Entfernung der Bauernh�fe multipliziert die Transportkosten ergibt */
	private float kmSatz;

	// Attribute f�r die Verwaltungsklassen
	private Rezeptverwaltung rezeptverw;
	private Lieferantenverwaltung lieferantenverw;
	//Attribute f�r die Gesch�ftslogikklassen
	private Einkaufsliste einkaufsliste;
	private ArrayList<Kantinenplan> kantPlanList;

	/**
	 * Konstruktor f�r das Objekt, bei Erstellung des Objektes werden die
	 * Parameter aus der config.properties eingelesen.
	 */
	public Kantinenplanung() {

	}

	/**
	 * F�hrt das Programm aus und ruft die einzelnen Methoden gem�� der
	 * Ablauflogik auf.
	 * 
	 * @param String[] �bergegebene Parameter werden nicht verwendet.
	 */
	public static void main(String[] args) {
		// Erzeugen des Application-Objektes
		Kantinenplanung app = new Kantinenplanung();
		// Erste Parameter lesen
		app.loadProperties();
		// Alle Dateien einlesen und Rezeptverwaltung, Lieferantenverwaltung
		// (inklusive Datenobjekten) erzeugen
		app.startDateienEinlesen();
		// Einen (oder zwei) Kantinenplan erzeugen und mit Tagesgerichten f�llen
		app.startKantinenplanung();
		// Die Einkaufsliste f�r ALLE Kantinenpl�ne erzeugen
		app.startEinkaufsplanung();
	}

	/**
	 * Die Methode l�dt die Parameter aus der Datei "config.properties" im
	 * Root-Ordner in die Attribute der Klasse.
	 */
	private void loadProperties() {
		try {
			Properties properties = new Properties();
			BufferedInputStream stream = new BufferedInputStream(
					new FileInputStream("config.properties"));
			properties.load(stream);
			stream.close();
			anzKantinen = Integer.parseInt(properties
					.getProperty("AnzahlKantinen"));
			rezeptPfad = properties.getProperty("RezeptbuchPfad");
			hitListenPfad = properties.getProperty("HitlistenPfad");
			preisListenOrdner = properties.getProperty("PreislistenOrdner");

			/*
			 * Debug-Print
			 * System.out.println("Anzahl der Kantinen: "+anzKantinen);
			 * System.out.println("Pfad zur Rezept-Datei: "+rezeptPfad);
			 * System.out.println("Pfad zur Hitlisten-Datei: "+hitListenPfad);
			 * System
			 * .out.println("Pfad zum Lieferanten-Ordner: "+preisListenOrdner);
			 */
		} catch (IOException e) {
			System.out.println(e.toString());
			System.out
					.println("Die Datei config.properties konnte nicht gelesen werden. Pr�fen Sie, ob sie im Anwendungsordner vorhanden ist.");
		}
	}

	/**
	 * Hier wird die Steuerung des Datei-Einlesens, die Erstellung der
	 * Verwaltungsklassen und der Datenschicht hinterlegt. Die Parameter wurden
	 * bereits vorher aus der config.properties extrahiert.
	 */
	public void startDateienEinlesen() {
		lieferantenverw = new Lieferantenverwaltung();
		lieferantenverw.readLiefFolder(preisListenOrdner);

		rezeptverw = new Rezeptverwaltung(lieferantenverw); // �bergabe der Lieferantenverwaltung derzeit im Konstruktor bei Rezeptverwaltung
		rezeptverw.liesRezepte(rezeptPfad);
		rezeptverw.liesHitliste(hitListenPfad);
	}

	/**
	 * Hier wird die Steuerung der gesamten Kantinenplanerstellung hinterlegt.
	 * Die Parameter wurden bereits vorher aus der config.properties extrahiert.
	 */
	public void startKantinenplanung() {
		kantPlanList = new ArrayList<Kantinenplan>();
		int anzMA = 0;
		String name = null;
		for (int i = 1; i<=anzKantinen; i++) {
			try {
				Properties properties = new Properties();
				BufferedInputStream stream = new BufferedInputStream(new FileInputStream("config.properties"));
				properties.load(stream);
				stream.close();
				name = properties.getProperty("NameKantine"+i);
				anzMA = Integer.parseInt(properties.getProperty("AnzahlKantine"+i));	
			} catch (IOException e) {
				System.out.println(e.toString());
				System.out.println("Die Datei config.properties konnte nicht gelesen werden. Pr�fen Sie, ob sie im Anwendungsordner vorhanden ist.");
			}
			Kantinenplan plan = new Kantinenplan(name, anzMA);
			plan.erzeugePlan(lieferantenverw, rezeptverw);
			kantPlanList.add(plan);
		}
	}

	/**
	 * Hier wird die Steuerung der gesamten Einkaufslistenerstellung hinterlegt.
	 * Die Parameter wurden bereits vorher aus der config.properties extrahiert.
	 */
	public void startEinkaufsplanung() {

	}
}
