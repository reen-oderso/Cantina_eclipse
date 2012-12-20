import java.io.*; // Aufgrund des Einlesens der Datei
import java.util.ArrayList;
/**
 * Die Rezeptverwaltung erstellt aus der Rezeptdatei die Objektabbildungen der Rezepte und 
 * verwaltet diese. Sie stellt Methoden bereit um Referenzen auf bestimmte Rezept-Objekte 
 * zu übergeben. Außerdem kann sie auch die Hitliste einlesen und die Positionen den einzelnen Rezepten zuweisen.
 * 
 * Sie steht in Assoziation zur Verwaltungsklasse Lieferantenverwaltung, der Kantinenplanung sowie dem Kantinenplan.
 * Sie aggregiert die Objekte Rezept und Zutat.
 * 
 * @author Lukas Krotki 
 * @version 
 */
public class Rezeptverwaltung
{
    /** Die ArrayList Rezeptliste enthält die Referenz zu allen erzeugten Rezeptobjekten, ist also eine Rezeptliste*/
    private ArrayList<Rezept> rezeptListe;    
    /** Die Lieferantenverwaltung wird der Rezeptverwaltung übergeben und wird als Objekt übergeben*/
    private Lieferantenverwaltung lieferantenverw;
    /** Angabe des Rezeptpfades */
    private String rezeptPfad;
    /** Angabe des Hitlistenpfades */
    private String hitlistenPfad;

    /** Bereits bei der Konstruktion muss eine Referenz zur Lieferantenverwaltung übergeben werden, da diese zur Typprüfung benötigt wird.
     * 
     * @param lieferantenverw Die zur Typprüfung zu verwendende Lieferantenverwaltung
     */
    public Rezeptverwaltung() //parameter entfernt zum testen (Lieferantenverwaltung lieferantenverw)
    {
     
    }

    /**
     * Die Methode liest die Rezeptdatei ein, erstellt Rezeptobjekte und Zutatenobjekte daraus und weist die Zutaten den Rezepten zu. Dann werden die Rezepte in den RezeptArrayList gelegt.
     * 
     * @param   rezeptpfad Den Pfad zur Rezeptdatei.
     * @return  Gibt True zurück, wenn die Rezepte vollständig eingelesen wurden. Gibt False zurück, falls Fehler aufgetreten sind.   
     */
    public boolean liesRezepte(String rezeptpfad) 
    {
    	//Datei öffnen
		Datei inFile = new Datei(rezeptpfad);
		inFile.openInFile_FS();
		int zeilennummer = 0;
        
		// Abfrage, ob das Oeffen funktioniert hat
        if (!inFile.state()){
            // Ausgabe des Fehlers im Terminalfenster
            System.out.println("Fehler beim öffnen der Eingabedatei "+rezeptPfad);
            // Abbrechen der Methode
            return false;
        }

        //Datei-Schleife
        while (!inFile.eof()){
        	
        	//Zeilenzähler erhöhen
        	zeilennummer = zeilennummer++; 
        	// Zeile einer Datei einlesen
        	String zeile = inFile.readLine_FS();
        	//Debug-Print
        	//System.out.println(zeile);
        	
   	
        	//Wenn aktuelle Zeile einen NullPointer enthält, wird gebrochen. 
        	if (!(zeile==null)){
        		
        		//Der CSVService macht aus den Eingabe-String (Zeile aus Datei) eine ArrayList, die die einzelnen Werte getrennt enthält
				ArrayList<String> fields = Kantinenplanung.CSVService.getFields(zeile);
        		
				//Debug-Print
				System.out.println("Zeile: "+zeilennummer+" Wert1: "+fields.get(0)+" Wert2: "+fields.get(1)+" Wert3: "+fields.get(2)+" Wert4: "+fields.get(3));
				/*
        		// Zeilenschleife zum einlesen jeder Zeile der Datei
        		for (int k = 0; k < zeile.length(); k++) {
        			// gibt die Zeichen an der Indexstelle k
        			char ch = zeile.charAt(k);
        			// sobald die Zeichkette ein Zeilenende erreicht, setzte Feldstart negiert (true)
        			if (ch == '\"') {
        				feldStart = !feldStart;
        			}
        			// ansonsten Trenne die Strings, wenn Felstart negiert und "," vorhanden
        			else if ( (ch == ',' && !feldStart) ) {
        				//Debug-Print
            	    	//System.out.println(k);
        				zeile = zeile.replaceAll("\"", ""); //entfernt die Anführungszeichen aus den String
            	    	//System.out.println(zeile.substring(0,k));
        				

        			}
        			
        		}
        		*/
        	}
        }
        return true;
        }
       
        
        
      
        		
    

       	   
    
    
    /**
     * Die Methode liest die Hitlistendatei ein und weist den im RezeptArrayList enthaltenen Rezeptobjekten ihre Hitlistenposition zu.
     * 
     * @param hitlistenpfad Den Pfad zur Hitlistendatei  
     * @return  True für vollständige Zuweisung, False für unvollständig    
     */
    public boolean liesHitliste(String hitlistenPfad) //
    {
        return true;
    }
    
    /**
     * Die Methode gibt ein zufälliges Fischrezept aus dem RezeptArrayList zurück.
     * 
     * @return Ein zufälliges Fischrezept 
     */
    public Rezept gibFisch() 
    {
        Rezept x = new Rezept("a"); 
        return x;
    }
   
    /**
     * Die Methode gibt ein zufälliges Fleischrezept aus dem RezeptArrayList zurück.
     * 
     * @return       Ein zufälliges Fleischrezept
     */
    public Rezept gibFleisch() 
    {
        Rezept x=new Rezept("a");
        return x;
    }
    
    /**
     * Die Methode gibt ein zufälliges vegetarisches Rezept aus dem RezeptArrayList zurück.
     * 
     * @return       Ein zufälliges vegetetarisches Rezept 
     */
    public Rezept gibVeggie() 
    {
        Rezept x=new Rezept("a");
        return x;
    }
    
    /**
     * Die Methode gibt ein zufälliges Rezept aus dem RezeptArrayList zurück.
     * 
     * @return        Ein zufälliges Rezept 
     */
    public Rezept gibRandom() 
    {
        Rezept x=new Rezept("a");
        return x;
    }
}
