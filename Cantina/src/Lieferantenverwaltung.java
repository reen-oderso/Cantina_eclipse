import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

/**
 * Die Klasse Lieferantenverwaltung erstellt und verwaltet die Objekte der
 * Klassen Lieferant, Artikel und Lebensmittel und aggregiert diese. Sie stellt
 * Methoden zur Verf�gung, um die g�nstigsten Artikel der Lieferanten zu
 * berechnen, zu �berpr�fen, ob ausreichende Mengen an Zutaten beschafft werden
 * k�nnen und ob Tagesgerichte als Fleisch-, Fisch- oder vegetarische Gerichte
 * beschrieben werden k�nnen.
 * 
 * Die Rezeptverwaltung ist mit dieser Klasse assoziiert, da die
 * Rezeptverwaltung Methoden der Lieferantenverwaltung zur Erstellung ihrer
 * eigenen Datenschicht (Rezept, Zutat) verwendet. Die Einkaufsliste verwendet
 * die Lieferantenverwaltung um Artikelobjekte zu erhalten. F�r die
 * Planungsdurchf�hrung steht sie in Assoziation zu der Kantinenplanung, da die
 * Kantinenplanung die Lieferantenverwaltung konstruiert und die Erzeugung der
 * Datenschicht anst��t.
 * 
 * @author Rene Wiederhold
 * @version 0.01
 */
public class Lieferantenverwaltung {
	private ArrayList<Artikel> artList;
	private ArrayList<Lieferant> liefList;
	private ArrayList<Lebensmittel> lmList;

	/**
	 * Der Konstruktor der Lieferantenverwaltung
	 */
	public Lieferantenverwaltung() {
		artList = new ArrayList<Artikel>();
		liefList = new ArrayList<Lieferant>();
		lmList = new ArrayList<Lebensmittel>();
	}

	/**
	 * Die Methode pr�ft, von welchem Typ eine Zutat ist (Fleisch, Fisch,
	 * Veggie). Dies wird von der Rezeptverwaltung zur Erstellung der Rezepte
	 * verwendet.
	 * 
	 * @param zutat
	 *            Die zu pr�fende Zutat
	 * @return Einen standardisierten String, der die Typ-Bezeichnung enth�lt.
	 */
	public RezeptTyp holeTyp(Zutat zutat){
		String typ = null;
		Iterator<Lebensmittel> it = lmList.iterator();
		while (it.hasNext()){
			Lebensmittel lm=it.next();
			if (lm.getName().equals(zutat.getName())){
				typ=lm.getTyp();
				break;
			}
		}
		if (typ == null)
			return RezeptTyp.Veggie;
		else if (typ.equals("m"))
			return RezeptTyp.Fleisch;
		else if (typ.equals("f"))
			return RezeptTyp.Fisch;
		else
			return RezeptTyp.Veggie;
	}

	/**
	 * Die Methode pr�ft, ob die ben�tigten Mengen Lebensmittel am Markt
	 * vorhanden sind, um ein Tagesgericht in der Planungs- periode anbieten zu
	 * k�nnen. Beim Aufruf wird die ben�tigte Menge vom zugeh�rigen
	 * Lebensmittel-Objekt abgezogen, sofern ausreichend. Dies wird vom
	 * Kantinenplan verwendet.
	 * 
	 * @param Tagesgericht
	 *            Das zu �berpr�fende Tagesgericht
	 * @return True, falls ausreichend Lebensmittel beschafft werden k�nnen,
	 *         ansonsten False
	 */

	public boolean lebensmittelVerfuegbar(Tagesgericht tagesgericht) {
		ArrayList<Zutat> zutatList= tagesgericht.getRezept().getZutaten();
		int c=0; //Z�hler f�r die verf�gbaren Lebensmittel
		
		//Zutatenschleife
		for (int i=0;i<zutatList.size();i++){
			Zutat z=zutatList.get(i);
			//Lebensmittelschleife
			for (Lebensmittel lm : lmList){
				if (lm.getName().equals(z.getName())) {
					// Differenz zwischen verf�gbaren Lebensmitteln und ben�tigter Zutatmenge
					float diff=(lm.getMenge()-(z.getMenge()*tagesgericht.getAbsatzMenge()));
					//Verf�gbare Restmenge des Lebensmittels ist kleiner 0, Verf�gbar: false
					if (diff<0){
						//Debug-Print
						System.out.println("Die Menge an "+z.getName()+" f�r "+tagesgericht.getRezept().getName()+" reicht nicht aus");
						return false;
					}
					//Die Restmenge ist gr��er als 0
					else if (diff>=0){
						c++; //Lebensmittel verf�gbar, Z�hler erh�hen
					}
				}
			} //Ende Lebensmittelschleife
		} // Ende Zutatenschleife
		
		if (c==zutatList.size()){
			//Es sind alle Lebensmittel ausreichend verf�gbar.
			for (int i=0;i<zutatList.size();i++){
				Zutat z=zutatList.get(i);
				//Lebensmittelschleife
				for (Lebensmittel lm : lmList){
					if (lm.getName().equals(z.getName())){
						lm.setMenge(lm.getMenge()-(z.getMenge()*tagesgericht.getAbsatzMenge()));
						//Debug-Print
						//System.out.println("Menge von "+lm.getName()+" auf "+lm.getMenge()+" angepasst");
					}
				} //Ende Lebensmittelschleife
			} // Ende Zutatenschleife
			return true;
		}
		//falls ein Lebensmittel garnicht verf�gbar ist, ist c<>Anzahl der Zutaten. Es wird false zur�ckgegeben.
		else {
			return false;
		}
	}

	/**
	 * Die Methode dient zum Aufbau der Lieferantenverwaltung. Sie erzeugt aus
	 * den Eingabe-Dateien die Bauernhof-, Grosshandel- und Artikelobjekte sowie
	 * die Lebensmittel-Objekte.
	 * 
	 * 
	 * @param String
	 *            Der Pfad zum Ordner, der die Lieferantenpreislisten enth�lt.
	 * @return True, falls die Lieferantendateien eingelesen werden konnten,
	 *         False, falls Probleme aufgetreten sind.
	 */
	public boolean readLiefFolder(String lieferantenOrdner) {
		File folder = new File(lieferantenOrdner);
		/* Debug-Print
		System.out.println("Angegebener Lieferantenordner ist ein Ordner: "
				+ folder.isDirectory());	*/

		String[] fileList = folder.list();
		if (folder.isDirectory()) {
			// Start der Ordner-Schleife
			for (int i = 0; i < fileList.length; i++) {
				// Datei �ffnen
				if (readLiefFile(lieferantenOrdner + "//" + fileList[i]) == true) {
					// Debug-Print
					System.out.println("Die Datei " + fileList[i]
							+ " wurde erfolgreich eingelesen");
				} 
			}

			/*// Debug-Print aus der Artikelliste
			for (int j = 0; j < artList.size(); j++) {
				Artikel art = artList.get(j);
				System.out.println("Artikelname: " + art.getName());
				System.out.println("Gebindegr��e: " + art.getGebindegroesse());
				System.out.println("Einheit: " + art.getEinheit());
				System.out.println("Einzelpreis: " + art.getPreis());
				System.out.println("Artikelanzahl: " + art.getArtikelanzahl());
				System.out.println("Lieferantname: "
						+ art.getLieferant().getLieferantenName());
			}
			// Debug-Print aus der Lieferantenliste
			for (int k = 0; k < liefList.size(); k++) {
				Lieferant lief = liefList.get(k);
				System.out.println(lief.getLieferantenName());
				System.out.println(lief.getClass().toString()
						.equals("class Bauernhof"));
			}
			//Debug-Print aus der Lebensmittelliste
			for (int l=0;l<lmList.size();l++){
				Lebensmittel lm=lmList.get(l);
				System.out.println("Name: "+lm.getName()+" Menge: "+lm.getMenge()+" Typ: "+lm.getTyp());
			}
			System.out.println(lmList.size());*/
		}
		return true;
	}

	/**
	 * Liest eine Lieferantendatei und schreibt die Lieferanten und Artikel in
	 * die zugeh�rigen ArrayLists
	 * 
	 * @param in
	 *            Der Pfad zur einzulesenden Lieferantendatei
	 * @return Einen booleschen Wert, ob die Datei erfolgreich eingelesen wurde.
	 */
	private boolean readLiefFile(String in) {

		Datei inFile = new Datei(in);
		inFile.openInFile_FS(); // �ffnet den readbuffer

		// Abfrage, ob das Oeffen funktioniert hat
		if (!inFile.state()) {
			// Ausgabe des Fehlers im Terminalfenster
			System.out.println("Es ist ein Fehler beim �ffnen der Datei "+in+" aufgetreten");
			// Abbrechen der Methode
			return false;
		}

		// Zeilenz�hler f�r den Import
		int zeilennummer = 0;

		// Datei-Schleife
		while (!inFile.eof()) {
			// Erh�hung des Zeilenz�hlers
			zeilennummer++;
			// Liest eine Zeile aus der Eingabedatei
			String zeile = inFile.readLine_FS();

			// Pr�ft ob zeile keinen NullPointer enth�lt.
			if (!(zeile == null)) {
				// Der CSVService macht aus den Eingabe-String (Zeile aus Datei)
				// eine ArrayList, die die einzelnen Werte getrennt enth�lt
				ArrayList<String> fields = CSVService.getFields(zeile);

				// Erste Zeile enth�lt Lieferanteninformationen
				if (zeilennummer == 1) {
					// Es liegt ein Grosshandel-Einkaufsliste vor
					if ((fields.get(0)).equals("Grosshandel")) {
						// Grosshandel erzeugen
						Grosshandel lieferant = new Grosshandel();

						// Namen setzen
						lieferant.setLieferantName((fields.get(1)).toString());
						lieferant.setKostensatz(Float.valueOf(
								(fields.get(2).replaceAll(",", ".")))
								.floatValue());

						liefList.add(lieferant);

						// Debug-Print
						// System.out.println(lieferant.getLieferantenName());
					}
					// Es liegt eine Bauernhof-Einkaufsliste vor.
					else if ((fields.get(0)).equals("Bauer")) {
						// Bauernhof erzeugen
						Bauernhof lieferant = new Bauernhof();

						// Namen und Entfernung setzen
						lieferant.setLieferantName((fields.get(1)).toString());
						lieferant.setEntfernung(Float.valueOf(
								(fields.get(2).replaceAll(",", ".")))
								.floatValue());

						// Lieferant der Lieferantenliste hinzuf�gen
						liefList.add(lieferant);

						// Debug-Print
						// System.out.println(lieferant.getLieferantenName());
					}
				} else {
					// Artikel erzeugen und Lieferanten zuweisen
					if (!(fields.get(2).length() == 0)) {
						Artikel art = new Artikel(fields.get(2));
						art.setArikelanzahl(Integer.parseInt(fields.get(5)));
						art.setEinheit(fields.get(1));
						// Das Komma im String muss in einen Punkt umgewandelt
						// werden, sonst funktioniert der Typecast nicht.
						art.setPreis(Float.valueOf(
								(fields.get(4).replaceAll(",", ".")))
								.floatValue());
						art.setGebindegroesse(Float.valueOf(
								(fields.get(0).replaceAll(",", ".")))
								.floatValue());
						art.setLieferant(liefList.get(liefList.size() - 1));

						artList.add(art);
						addLebensmittel(art.getName(), art.getArtikelanzahl()
								* art.getGebindegroesse(),art.getEinheit(), fields.get(3));

					}
				}
			}

		}
		return true;
	}

	/**
	 * Die Methode gibt eine ArrayList zur�ck, die alle Artikel enth�lt, die den
	 * gleichen Namen haben, wie der �bergebene String-Parameter. Dies wird zur
	 * Erzeugung der BestellPos-Objekte von der Einkaufsliste genutzt.
	 * 
	 * @param name
	 *            Die Bezeichnung einer Zutat
	 * @return Einen ArrayList, der die Referenzen zu allen Artikel-Objekte
	 *         enth�lt, deren Name mit dem Parameter �bereinstimmen
	 */
	public ArrayList<Artikel> gibAlleArtikel(String name) {
		return new ArrayList<Artikel>();
	}

	/**
	 * Die Methode pr�ft, ob in der Lebensmittelliste der Lieferantenverwaltung
	 * bereits ein Lebensmittel vorhanden ist, dessen Name mit dem �bergegebenen
	 * String lm �bereinstimmt. Ist dies der Fall wird nur die Menge m
	 * aufaddiert. Existiert noch kein Lebensmittel mit dem Namen lm, wird ein
	 * neues Lebensmittelobjekt erzeugt, die Menge m diesem Objekt zugewiesen
	 * und das Lebensmittel-Objekt in die Lebensmittelliste eingef�gt. Au�erdem
	 * wird der Typ eines Lebensmittels gesetzt, sofern ein neues
	 * Lebensmittelobjekt erzeugt werden muss.
	 * 
	 * @param lm
	 *            Der Name des Lebensmittel
	 * @param m
	 *            Die zu ber�cksichtigende Menge des Lebensmittel
	 * @param typ
	 *            Der Typ des Lebensmittel als standardisierter String. "m" f�r
	 *            Meat, "f" f�r Fisch, "" f�r vegetarisch.
	 */
	private void addLebensmittel(String lmName, float m, String einh, String typ) {
		//Wenn lmList leer ist, muss immer ein neues Lebensmittel hinzugef�gt werden.
		if (lmList.isEmpty()){
			Lebensmittel firstLm = new Lebensmittel();
			firstLm.setName(lmName);
			firstLm.setTyp(typ);
			firstLm.setMenge(m);
			firstLm.setEinheit(einh);
			lmList.add(firstLm);
			//Debug-Print
			//System.out.println(lmName+" wurde der Liste als Erstes hinzugef�gt");
		}
		else{
			//Es sind bereits Elemente in der lmList
			//Es muss gepr�ft werden ob ein Objekt gleichen Namens vorhanden ist
			//Hier wird eine "Iterator-Schleife verwendet
			Iterator<Lebensmittel> it=lmList.iterator();
			//Variable f�r die "Vorhanden"-Pr�fung
			Boolean vorh=false;
			//Variable f�r das bereits in der List vorhandene Lebensmittel
			Lebensmittel vorhLm=new Lebensmittel();
			//Solang die List noch ein weiteres Element hat, l�uft die Schleife
			while (it.hasNext()){
				//next() gibt das n�chste Lebensmittel-Objekt
				Lebensmittel lmIt=it.next();
				//Stimmt der Name des Lebensmittel mit dem zu pr�fenden �berein
				//Wenn ja, dass Objekt tempor�r gespeichert und die Schleife gebrochen.
				if(lmIt.getName().equals(lmName)){
					vorh=true;
					vorhLm=lmIt;
					//Debug-Print
					//System.out.println(lmName+" ist in lmList vorhanden");
					break;
				}
				//Stimmt der �bergebene Name des Lebensmittels nicht mit dem iterierten �berein, bleibt vorh auf false
				else{
					vorh=false;
					//Debug-Print
					//System.out.println(lmName+" ist noch nicht in lmList vorhanden");
				}	
			} //while-Ende - Jetzt steht entweder vorh auf true und vorhLm enth�lt das LM-Objekt
			  //oder vorh steht auf false
			
			// Lebensmittel-Objekt schon vorhanden
			if(vorh==true){
				vorhLm.setMenge(vorhLm.getMenge()+m);
				//Debug-Print
				//System.out.println(lmName+"-Menge angepasst");
			}
			// Lebensmittel-Objekt noch nicht vorhanden
			else if(vorh==false){
				vorhLm.setName(lmName);
				vorhLm.setTyp(typ);
				vorhLm.setMenge(m);
				vorhLm.setEinheit(einh);
				lmList.add(vorhLm);
				//Debug-Print
				//System.out.println(lmName+" der Liste hinzugef�gt!");
			}	
		} //else-Ende
	}

}
