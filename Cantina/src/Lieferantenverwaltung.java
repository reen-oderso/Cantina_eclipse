import java.util.ArrayList;
import java.io.*;

/**
 * Die Klasse Lieferantenverwaltung erstellt und verwaltet die Objekte der Klassen Lieferant, Artikel und Lebensmittel und aggregiert diese. 
 * Sie stellt Methoden zur Verfügung, um die günstigsten Artikel der Lieferanten zu berechnen, zu überprüfen, ob ausreichende Mengen
 * an Zutaten beschafft werden können und ob Tagesgerichte als Fleisch-, Fisch- oder vegetarische Gerichte beschrieben werden können.
 * 
 * Die Rezeptverwaltung ist mit dieser Klasse assoziiert, da die Rezeptverwaltung Methoden der Lieferantenverwaltung zur Erstellung 
 * ihrer eigenen Datenschicht (Rezept, Zutat) verwendet.
 * Die Einkaufsliste verwendet die Lieferantenverwaltung um Artikelobjekte zu erhalten.
 * Für die Planungsdurchführung steht sie in Assoziation zu der Kantinenplanung, da die Kantinenplanung die Lieferantenverwaltung konstruiert
 * und die Erzeugung der Datenschicht anstößt. 
 * 
 * @author Rene Wiederhold 
 * @version 0.01
 */
public class Lieferantenverwaltung{ 
    private ArrayList<Artikel> artikelArrayList;
    private ArrayList<Lieferant> lieferantArrayList;
    private ArrayList<Lebensmittel> lebensmittelArrayList;
    
    /**
     *  Der Konstruktor der Lieferantenverwaltung
     */
    public Lieferantenverwaltung (){
        
    }

    /**
     * Die Methode prüft, von welchem Typ eine Zutat ist (Fleisch, Fisch, Veggie). Dies wird von der Rezeptverwaltung zur Erstellung 
     * der Rezepte verwendet.
     * 
     * @param  zutat Die zu prüfende Zutat
     * @return  Einen standardisierten String, der die Typ-Bezeichnung enthält.
     */
    public String holeTyp(Zutat zutat)
    {
        // tragen Sie hier den Code ein
        String typ = "FLEISCH";
       return typ;
    }

    /**
     * Die Methode prüft, ob die benötigten Mengen Lebensmittel am Markt vorhanden sind, um ein Tagesgericht in der Planungs-
     * periode anbieten zu können. Beim Aufruf wird die benötigte Menge vom zugehörigen Lebensmittel-Objekt abgezogen, sofern
     * ausreichend. Dies wird vom Kantinenplan verwendet.
     * 
     * @param   Tagesgericht Das zu überprüfende Tagesgericht
     * @return  True, falls ausreichend Lebensmittel beschafft werden können, ansonsten False
     */
    
    public boolean lebensmittelVerfuegbar(Tagesgericht tagesgericht)
    {
        return true;
    }

    /**
     * Die Methode dient zum Aufbau der Lieferantenverwaltung. Sie erzeugt aus den Eingabe-Dateien die Bauernhof-, Grosshandel-
     * und Artikelobjekte sowie die Lebensmittel-Objekte. 
     * 
     * 
     * @param  String Der Pfad zum Ordner, der die Lieferantenpreislisten enthält.
     * @return      True, falls die Lieferantendateien eingelesen werden konnten, False, falls Probleme aufgetreten sind.
     */
    public boolean liesLieferantenDateien(String lieferantenOrdner)
    {
    	File folder = new File(lieferantenOrdner);
    	//Debug-Print
    	System.out.println("Angegebener Lieferantenordner ist ein Ordner: "+folder.isDirectory());
    	
    	String[] fileList = folder.list();
        if (folder.isDirectory()){        	
        	//Debug-Print
        	System.out.println("Der Ordner "+lieferantenOrdner+" enthält folgende Dateien:");
        	
        	//Start der Ordner-Schleife
        	for ( int i = 0; i < fileList.length; i++ ){
        		
        		//Debug-Print
        		System.out.println(fileList[i]);
        		
        		//Datei öffnen
        		Datei inFile = new Datei(lieferantenOrdner+"//"+fileList[i]);
        		inFile.openInFile_FS();
        		int zeilennummer = 0;
                // Abfrage, ob das Oeffen funktioniert hat
        		
                if (!inFile.state()){
                    // Ausgabe des Fehlers im Terminalfenster
                    System.out.println("Fehler beim öffnen der Eingabedatei "+lieferantenOrdner+"//"+fileList[i]);
                    // Abbrechen der Methode
                    return false;
                }
                
                //Datei-Schleife
                while (!inFile.eof()){
                	
                	zeilennummer = zeilennummer++; 
                	String zeile = inFile.readLine_FS();
                	//Debug-Print
                	//System.out.println(zeile);
                	

                	
                	boolean feldStart = false;
                	
                	//Wenn aktuelle Zeile einen NullPointer enthält, wird gebrochen. 
                	if (!(zeile==null)){
                		//Zeielnschleife
	                	for (int k = 0; k < zeile.length(); k++) {
	                	    char ch = zeile.charAt(k);
	                	    if (ch == '\"'){
	                	        feldStart = !feldStart;
	                	    } 
	                	    else if ((ch == ',' && !feldStart)) {
	                	    	//Debug-Print
	                	    	//System.out.println(k);
	                	    	//System.out.println(zeile.substring(0,k));
	                	    	
	                	    	//Erste Zeile enthält die Informationen zum Lieferanten
	                	    	if (zeilennummer==1){
	                	    		
	                	    		//genLieferant
                		
	                	    	}
	                	    	else {
	                	    		
	                	    		//genArtikel
	                	    	}
	                	    } 
	                	    else {
	                	        
	                	    }
	                	}
                	}
                	
                }
        	}
        }
        return true;
    }
    
    /**
     * Die Methode gibt eine ArrayList zurück, die alle Artikel enthält, die den gleichen Namen haben, wie der übergebene
     * String-Parameter. Dies wird zur Erzeugung der BestellPos-Objekte von der Einkaufsliste genutzt.
     *
     * @param  name Die Bezeichnung einer Zutat
     * @return     Einen ArrayList, der die Referenzen zu allen Artikel-Objekte enthält, deren Name mit dem Parameter 
     *             übereinstimmen
     */
    public ArrayList<Artikel> gibAlleArtikel(String name)
    {
        
        return new ArrayList<Artikel>();
    }

}
