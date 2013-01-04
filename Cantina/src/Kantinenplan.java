import java.util.ArrayList;
/**
 * Die Klasse Kantinenplan repräsentiert einen Kantinenplan im Rahmen der Planungsperiode.
 * Sie aggregiert die Tagesgerichtobjekte und erstellt auf Basis der Anzahl der 
 * Mitarbeiter je Kantine und Standort einen Plan mit insgesamt 45 Tagesgerichten.
 * Sie steht in Assoziation zu der Einkaufsliste um, durch die Übergabe eines
 * Kantinenplans, die Bestellpositonen zu generieren. Der Kantinenplan hat durch die Assoziation zur
 * Reezptverwaltung und Lieferantenverwaltung Zugriff auf deren Methoden um eine gültigen Kantinenplan
 * zu erstellen. Um den Export eine Kantinenplans in eine Datei zu ermöglichen steht der Kantinenplan 
 * auch in Assoziation zum Exporter. Damit ein Planungslauf von der Kantinenplanung durchgführt werden kann, 
 * liegt auch mit dieser Klasse eine Assoziationsbeziehung vor.
 * 
 * @author Rene Wiederhold
 * @version
 */
public class Kantinenplan
{
    /** Der Standort, für den der Kantinenplan erzeugt wird */ 
    private String standort;
    /** Die Anzahl der Mitarbeiter, welche die Kantine am Standort besuchen*/
    private int anzMitarbeiter;
    /** Die ArrayList enthält die zum Speiseplan gehörenden Tagesgerichte */
    public ArrayList<Tagesgericht> tagesgerichtArrayList;
    
    // Die für die Kantinenplanerstellung notwendigen Lieferanten- und Rezeptverwaltungen
    private Lieferantenverwaltung lieferantenverw;
    private Rezeptverwaltung rezeptverw;

    /**
     * Konstruktor für Objekte der Klasse Kantinenplan
     * 
     * @param name Der Name der Kantine/des Standortes
     * @param anzMA Die Anzahl der Mitarbeiter des Standortes.
     */
    public Kantinenplan(String name, int anzMA)
    {
    	this.standort=name;
    	this.anzMitarbeiter=anzMA;
    	
    	//Debug-Print
    	System.out.println("Die Kantine "+standort+" mit "+anzMitarbeiter+" Mitarbeitern wurde erzeugt.");
    }

    /**
     * Die Methode füllt den Speiseplan mit Tagesgerichten, die sie aus Rezepten erzeugt. Die Rezepte erhält 
     * sie von der als Parameter zugewiesenen Rezeptverwaltung. Um die Verfügbarkeit der Zutaten am Markt sicher-
     * zustellen, wird auf die Lieferantenverwaltung zurückgegriffen.
     * 
     * @param lieferantenverw Die Lieferantenverwaltung, welche die Verfügbarkeitsinformation zur Verfügung stellt.
     * @param rezeptverw Die Rezeptverwaltung, die die Rezepte für den Speiseplan zur Verfügung stellen kann. 
     * @return True, für erfolgreiche Erstellung, False falls Fehler aufgetreten sind.       
     */
    public boolean erzeugePlan(Lieferantenverwaltung lieferantenverw, Rezeptverwaltung rezeptverw)
    {
        this.lieferantenverw=lieferantenverw;
        this.rezeptverw=rezeptverw;
    	
    	
        
        
        /*
        * Für eine Woche, das ganze wird dann 2x wiederholt:
        * Von der Rezeptverwaltung werden 5 Fleischgericht-Rezepte und 5 vegetarische Rezepte angefordert, dann ein 
        * Fischgericht, die restlichen 4 mit zufälligen Rezepten aufgefüllt. Die 4 Teile werden in temporären Arrays hinterlegt.
        * Nun werden alle Rezepte auf ihre Verfügbarkeit bei der Lieferantenverwaltung geprüft. Falls etwas nicht verfügbar ist,
        * muss neu angefordert und im temporären Array ersetzt werden.  Vll. sollte man auch sicherstellen, dass nicht zu viele 
        * Fischgerichte über das Zufallsrezept reinkommen.
        * Dann werden die Tagesgerichtobjekte daraus erstellt. Als Datum wird 1-5 gesetzt, das Fischgericht bekommt immer
        * die 5 (bzw. 10 oder 15 für die 2. und 3. Woche). 
        * Für die Tagesgerichte mit gleichem Datum können dann die Absatzmengen berechnet und in die Attribute geschrieben werden.
        * Die Tagesgerichtobjekte kommen, wenn alles passt in den tagesgerichtArrayList und die Rezepte werden dann auf 
        * "verwendet=true gesetzt. 
        */
       return true;
    }
    
    /**
     * Die Methode gibt eine ArrayList zurück, der alle Tagesgerichte des Kantinenplans enthält.
     * 
     * @return Eine ArrayList, der alle Tagesgerichte enthält.
     */
    public ArrayList<Tagesgericht> getTagesgerichte()
    {
        return tagesgerichtArrayList;
    }
    
    /**
     * Gibt den Namen der Kantine zurück.
     * 
     * @return Der Name der Kantine.
     */
    public String getStandort()
    {
        return standort;
    }
    
    /**
     * Gibt die Anzahl der Mitarbeiter am Standort zurück.
     * 
     * @return Der Name der Kantine.
     */
    public int getAnzMA()
    {
        return anzMitarbeiter;
    }
}