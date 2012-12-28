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
    /** Angabe des Rezeptpfades */
    private String rezeptPfad;
    /**Rezeptname*/
    private String rezeptName;
    private Lieferantenverwaltung lieferenatenverw;
    private ArrayList<Rezept> rezeptListe;

    /** Bereits bei der Konstruktion muss eine Referenz zur Lieferantenverwaltung übergeben werden, da diese zur Typprüfung benötigt wird.
     * 
     * @param lieferantenverw Die zur Typprüfung zu verwendende Lieferantenverwaltung
     */ 
    public Rezeptverwaltung(Lieferantenverwaltung lieferantenverw) //parameter entfernt zum testen (Lieferantenverwaltung lieferantenverw)
    {
    	this.lieferenatenverw = lieferantenverw;
     rezeptListe = new ArrayList<Rezept>();
    }

    /**
     * Rezepte in Rezeptliste aufnehmen
     * 
     * @param: rezept Rezept auf Rezeptliste
     */
    public void addRezept (Rezept rezept){
    	rezeptListe.add (rezept);
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
		
        
		// Abfrage, ob das Oeffen funktioniert hat
        if (!inFile.state()){
            // Ausgabe des Fehlers im Terminalfenster
            System.out.println("Fehler beim öffnen der Eingabedatei "+rezeptPfad);
            // Abbrechen der Methode
            return false;
        }

        //Rezept initialisieren
        Rezept rezept = null;
        
        //Datei-Schleife
        while (!inFile.eof()){
        	
            // Zeile einer Datei einlesen
        	String zeile = inFile.readLine_FS();
        	//Debug-Print
        	//System.out.println(zeile);
        	
   	
        	//Wenn aktuelle Zeile einen NullPointer enthält, wird gebrochen. 
        	if (zeile != null){
        		
        		//Der CSVService macht aus den Eingabe-String (Zeile aus Datei) eine ArrayList, die die einzelnen Werte getrennt enthält
				ArrayList<String> fields = CSVService.getFields(zeile);
				
				//Debug-Print
				//System.out.println("Zeile: "+zeilennummer+" Wert1: "+fields.get(0)+" Wert2: "+fields.get(1)+" Wert3: "+fields.get(2)+" Wert 4: " +fields.get(3));
			
			
			
				
				// prüfe ob die eingelesene Zeile dem aktuellen Rezept entspricht
				if ( !fields.get(0).equals(rezeptName)) {
					
					//uebergebe der Variable rezeptNamen den Strin aus dem Feld 0 
					rezeptName = fields.get(0).toString();
					
					// erzeuge ein neues Objekt Rezept mit rezeptName als zu übergebenden Parameter
					rezept = new Rezept(rezeptName);
					
										
					// Setze den Rezeptnamen -> nicht mehr benötigt, da schon als Parameter übergeben
					//rezept.setName(fields.get(0).toString()); 
					
					
					//Rezept in Rezeptliste aufnehmen
					rezeptListe.add(rezept);
					
					//Debug Print
					System.out.println(rezeptName);
					
					
				}
				
					// Zutatenobjekt erzeugen
					Zutat zutat = new Zutat(fields.get(3).toString(), Float.valueOf(fields.get(1).toString().replace(",", ".")), fields.get(2).toString());
					// Zutat zu einem Rezept hinterlegen
					rezept.addZutat( zutat );
					// Debug Print
					System.out.println(" Zutat: "+fields.get(3).toString());
					
				
			  
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
    public boolean liesHitliste(String hitlistenPfad) 
    {
    	//Datei öffnen
    			Datei inFile = new Datei(hitlistenPfad);
    			inFile.openInFile_FS();
    			
    	        
    			// Abfrage, ob das Oeffen funktioniert hat
    	        if (!inFile.state()){
    	            // Ausgabe des Fehlers im Terminalfenster
    	            System.out.println("Fehler beim öffnen der Eingabedatei "+hitlistenPfad);
    	            // Abbrechen der Methode
    	            return false;
    	        }
    	            //int zeilennummer = 0;
    	        
    	             Rezept rezeptName = null;
    	             
    	            //Datei-Schleife
    	            while (!inFile.eof()){
    	            	
    	            	//zeilennummer++;
    	            	
    	                // Zeile einer Datei einlesen
    	            	String zeile = inFile.readLine_FS();
    	            	
    	            	//Debug-Print
    	            	//System.out.println(zeile);
    	            	
    	            	//Wenn aktuelle Zeile einen NullPointer enthält, wird gebrochen. 
    	            	if (zeile != null){
    	            		
    	            		//Der CSVService macht aus den Eingabe-String (Zeile aus Datei) eine ArrayList, die die einzelnen Werte getrennt enthält
    	    				ArrayList<String> fields = CSVService.getFields(zeile);
    	    				
    	    				//Debug-Print
    	    				//System.out.println("Zeile: "+zeilennummer+" Wert1: "+fields.get(0)+" Wert2: "+fields.get(1));
    	        

    				// Suche Rezept, dessen Name in der Hitliste eingelesen wurde
    				rezeptName = getRezeptWithName( fields.get(1).toString() );
    				
    				// Prüfe ob die eingelesene Zeile noch dem aktuellen Rezept entspricht
    				if ( rezeptName != null) {
    					// speichere aktuellen Rezeptname, da dieser ja für jede Zutat wiederholt wird
    					rezeptName.setHitlistenpos( fields.get(0) );
    					
    					// Debug Print
    					System.out.println("Rezept: "+rezeptName.getName()+" Position: "+fields.get(0));
    				} else
    					//Debug Print
    					System.out.println("Rezept: "+fields.get(1)+" nicht gefunden.");
    				    				
            	}
            }
            return true;

    }
        /** Prüfen, ob der Rezeptname bereits in der Rezeptliste enhalten ist
         * 
         * @param rezeptName Name eines Rezeptes
         * 
         * @return null
         */
        public Rezept getRezeptWithName( String rezeptName ) {
        	// Für ein Rezeptelement aus der Liste
        	for(Rezept rezeptElement : rezeptListe ) {
        		// Ist der Name des Rezeptes aus der rezeptListe identisch mit dem rezeptNamen
        		if(rezeptElement.getName().equals(rezeptName))
        			// gebe das rezeptElement aus
        			return rezeptElement;
        	}
        	
        	return null;
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
