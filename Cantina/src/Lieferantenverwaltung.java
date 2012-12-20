import java.util.ArrayList;
import java.io.*;

/**
 * Die Klasse Lieferantenverwaltung erstellt und verwaltet die Objekte der
 * Klassen Lieferant, Artikel und Lebensmittel und aggregiert diese. Sie stellt
 * Methoden zur Verfügung, um die günstigsten Artikel der Lieferanten zu
 * berechnen, zu überprüfen, ob ausreichende Mengen an Zutaten beschafft werden
 * können und ob Tagesgerichte als Fleisch-, Fisch- oder vegetarische Gerichte
 * beschrieben werden können.
 * 
 * Die Rezeptverwaltung ist mit dieser Klasse assoziiert, da die
 * Rezeptverwaltung Methoden der Lieferantenverwaltung zur Erstellung ihrer
 * eigenen Datenschicht (Rezept, Zutat) verwendet. Die Einkaufsliste verwendet
 * die Lieferantenverwaltung um Artikelobjekte zu erhalten. Für die
 * Planungsdurchführung steht sie in Assoziation zu der Kantinenplanung, da die
 * Kantinenplanung die Lieferantenverwaltung konstruiert und die Erzeugung der
 * Datenschicht anstößt.
 * 
 * @author Rene Wiederhold
 * @version 0.01
 */
public class Lieferantenverwaltung {
	private ArrayList<Artikel> artikelArrayList;
	private ArrayList<Lieferant> lieferantArrayList;
	private ArrayList<Lebensmittel> lebensmittelArrayList;

	/**
	 * Der Konstruktor der Lieferantenverwaltung
	 */
	public Lieferantenverwaltung() {

	}

	/**
	 * Die Methode prüft, von welchem Typ eine Zutat ist (Fleisch, Fisch,
	 * Veggie). Dies wird von der Rezeptverwaltung zur Erstellung der Rezepte
	 * verwendet.
	 * 
	 * @param zutat
	 *            Die zu prüfende Zutat
	 * @return Einen standardisierten String, der die Typ-Bezeichnung enthält.
	 */
	public String holeTyp(Zutat zutat) {
		// tragen Sie hier den Code ein
		String typ = "FLEISCH";
		return typ;
	}

	/**
	 * Die Methode prüft, ob die benötigten Mengen Lebensmittel am Markt
	 * vorhanden sind, um ein Tagesgericht in der Planungs- periode anbieten zu
	 * können. Beim Aufruf wird die benötigte Menge vom zugehörigen
	 * Lebensmittel-Objekt abgezogen, sofern ausreichend. Dies wird vom
	 * Kantinenplan verwendet.
	 * 
	 * @param Tagesgericht
	 *            Das zu überprüfende Tagesgericht
	 * @return True, falls ausreichend Lebensmittel beschafft werden können,
	 *         ansonsten False
	 */

	public boolean lebensmittelVerfuegbar(Tagesgericht tagesgericht) {
		return true;
	}

	/**
	 * Die Methode dient zum Aufbau der Lieferantenverwaltung. Sie erzeugt aus
	 * den Eingabe-Dateien die Bauernhof-, Grosshandel- und Artikelobjekte sowie
	 * die Lebensmittel-Objekte.
	 * 
	 * 
	 * @param String
	 *            Der Pfad zum Ordner, der die Lieferantenpreislisten enthält.
	 * @return True, falls die Lieferantendateien eingelesen werden konnten,
	 *         False, falls Probleme aufgetreten sind.
	 */
	public boolean liesLieferantenDateien(String lieferantenOrdner) {
		File folder = new File(lieferantenOrdner);
		// Debug-Print
		System.out.println("Angegebener Lieferantenordner ist ein Ordner: "
				+ folder.isDirectory());

		String[] fileList = folder.list();
		if (folder.isDirectory()) {
			// Debug-Print
			System.out.println("Der Ordner " + lieferantenOrdner
					+ " enthält folgende Dateien:");
			//Test der preisliste3.csv statt allen Preislisten
			
			// Start der Ordner-Schleife
  			for (int i = 0; i < fileList.length; i++) {

				// Debug-Print
				System.out.println(fileList[i]);

				// Datei öffnen
				//readFile(lieferantenOrdner + "//" + fileList[i]);
			}
  			readFile(lieferantenOrdner + "//" + fileList[2]);
		}
		return true;
	}

	/**
	 * Liest eine Lieferantendatei und schreibt die Lieferanten und Artikel in
	 * die zugehörigen ArrayLists
	 * 
	 * @param in
	 *            Der Pfad zur einzulesenden Lieferantendatei
	 * @return Einen booleschen Wert, ob die Datei erfolgreich eingelesen wurde.
	 */
	private boolean readFile(String in) {

		Datei inFile = new Datei(in);
		inFile.openInFile_FS(); // öffnet den readbuffer

		// Abfrage, ob das Oeffen funktioniert hat
		if (!inFile.state()) {
			// Ausgabe des Fehlers im Terminalfenster
			System.out.println("Fehler beim Öffnen der Eingabedatei " + in);
			// Abbrechen der Methode
			return false;
		}

		// Zeilenzähler für den Import
		int zeilennummer = 0;
		Lieferant lieferant = new Lieferant();
		
		// Datei-Schleife
		while (!inFile.eof()) {
			//Erhöhung des Zeilenzählers
			zeilennummer++;
			//Liest eine Zeile aus der Eingabedatei
			String zeile = inFile.readLine_FS();

			//Prüft ob zeile keinen NullPointer enthält.
			if (!(zeile == null)) {
				//Der CSVService macht aus den Eingabe-String (Zeile aus Datei) eine ArrayList, die die einzelnen Werte getrennt enthält
				ArrayList<String> fields = Kantinenplanung.CSVService.getFields(zeile);
				
				//Debug-Print
				//System.out.println("Zeile: "+zeilennummer+" Wert1: "+fields.get(0)+" Wert2: "+fields.get(1)+" Wert3: "+fields.get(2));
				
				//Erste Zeile enthält Lieferanteninformationen
				if (zeilennummer == 1) {
					//Es liegt ein Grosshandel-Einkaufsliste vor
					if ((fields.get(0)).compareTo("Grosshandel")==0){
						//Grosshandel erzeugen
						lieferant = new Grosshandel();
						//Namen setzen
						lieferant.setLieferantName((fields.get(1)).toString());
						//Debug-Print
						System.out.println(lieferant.getLieferantenName());
					}
					//Es liegt eine Bauernhof-Einkaufsliste vor.
					else if ((fields.get(0)).compareTo("Bauer")==0){
						//Grosshandel erzeugen
						lieferant = new Bauernhof();
						//Namen setzen
						lieferant.setLieferantName((fields.get(1)).toString());
						//Debug-Print
						System.out.println(lieferant.getLieferantenName());
					} 
				} 
				else {
					//Artikel erzeugen und Lieferanten zuweisen
					if (!(fields.get(2).length()==0)){
						Artikel art = new Artikel(fields.get(2));
						art.setArikelanzahl(Integer.parseInt(fields.get(5)));		//Nur zu Testzwecken wegen IndexOutofBound für Index 5
						art.setEinheit(fields.get(1));
						art.setPreis( Float.valueOf( (fields.get(4).replaceAll(",",".")) ).floatValue() );  //anscheinend macht das Komma im Typecast-Probleme
						art.setGebindegroesse( Float.valueOf( (fields.get(0).replaceAll(",",".")) ).floatValue() );
						art.setLieferant(lieferant);
						
						System.out.println("Artikelname: "+art.getName());
						System.out.println("Artikelname: "+art.getGebindegroesse());
						System.out.println("Artikelname: "+art.getEinheit());
						System.out.println("Artikelname: "+art.getPreis());
						System.out.println("Artikelname: "+art.getArtikelanzahl());
						System.out.println("Artikelname: "+art.getLieferant().getLieferantenName());
					}
				}
			}

		}
		return true;
	}

	/**
	 * Die Methode gibt eine ArrayList zurück, die alle Artikel enthält, die den
	 * gleichen Namen haben, wie der übergebene String-Parameter. Dies wird zur
	 * Erzeugung der BestellPos-Objekte von der Einkaufsliste genutzt.
	 * 
	 * @param name
	 *            Die Bezeichnung einer Zutat
	 * @return Einen ArrayList, der die Referenzen zu allen Artikel-Objekte
	 *         enthält, deren Name mit dem Parameter übereinstimmen
	 */
	public ArrayList<Artikel> gibAlleArtikel(String name) {

		return new ArrayList<Artikel>();
	}

}
