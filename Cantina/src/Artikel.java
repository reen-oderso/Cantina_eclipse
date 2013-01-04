
/**
 * Die Klasse Artikel enth�lt als Datenklasse die Informationen aus den Preislisten der Lieferanten, 
 * welche genau einen Artikel (=eine Zeile in einer Lieferanten-Preisliste) beschreiben.
 * 
 * Artikel werden von der Lieferantenverwaltung aggregiert, welche die Artikelobjekte verwaltet.
 * Jedes Artikelobjekt geh�rt zu einer Instanz eines Lieferanten. Artikel stehen mit der Klasse 
 * BestellPos in einer Assoziationsbeziehung, da eine Bestellpositon einer 
 * einem Artikel und der Menge an zu bestellenden Gebinden entspricht.
 * 
 * @author Rene Wiederhold
 * @version 
 */
public class Artikel
{
    /** Der Name des Artikels */ //Dritter Wert der Liste
    private String name;
    /** Die Einheit, in der der Artikel gemessen wird */ //Zweiter Wert der Liste
    private String einheit;
    /** Die Gebindegr��e beim Lieferanten */ //Erster Wert der Liste
    private float gebindeGr;
    /** Die Anzahl der Gebinde, die beim Lieferanten f�r diesen Artikel maximal beschafft werden 
     *  k�nnen */ //Sechster Wert der Liste
    private int anz;
    /** Der Einzelpreis (Netto, ohne Transportkosten) je Gebinde */ //F�nfter Wert der Liste
    private float einzelpreis; 
    /** Der Lieferant des Artikels */
    private Lieferant lieferant;

    /**
     * Konstruktor f�r Objekte der Klasse Artikel
     */
    public Artikel(String name)
    {
        this.name = name;
    }
    /**
     * Setzt den Artikelnamen
     * 
     * @param name Der zu schreibende Name des Artikels
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * Gibt den Namen des Artikels zur�ck 
     * 
     * @return Der Namen des Artikels
     */
    public String getName(){
        return name;
    }
    
    /**
     * Setzt die Bezeichnung der Artikeleinheit
     * 
     * @param einheit Einen String mit der Einheitenbezeichnung
     *        
     */
    public void setEinheit (String einheit){
        this.einheit = einheit;
    }
    /**
    * Gibt den Bezeichnung der Artikeleinheit zur�ck 
     * 
     * @return Artikeleinheit des Artikels
     */
    public String getEinheit(){
        return einheit;
    }
    
    /**
     * Setzt die Gebindegroesse (Menge) eines Artikels
     * 
     * @param gebindeGr Die Gr��e des Gebindes.      
     */
    public void setGebindegroesse (float gebindeGr){
        this.gebindeGr = gebindeGr;
    }
    /**
     * Gibt die Gebindegroesse (Menge) von einem Artikel aus
     * 
     * @return Gebindegroesse des Artikels
     */
    public float getGebindegroesse(){
        return gebindeGr;
    }
    
    /**
     * Setzt die Anzahl eines Artikels bei einem Lieferanten
     * 
     * @param anz Die Anzahl der verf�gbaren Gebinde des Artikels
     *        
     */
    public void setArikelanzahl (int anz){
        this.anz = anz;
    }
    /**
     * Gibt die Anzahl eines Artikels bei einem Lieferanten
     * 
     * @return Artikelanzahl des Artikels
     */
    public int getArtikelanzahl(){
        return anz;
    }
    
    /**
     * Setzt den Preis eines Artikels
     * 
     * @param einzelpreis Der Preis eines Gebindes des Artikels.
     */
    public void setPreis (float einzelpreis){
        this.einzelpreis = einzelpreis;
    }
    /**
     * Gibt den Preis eines Artikels
     * 
     * @return Preis des Artikels
     */
    public float getPreis(){
        return einzelpreis;
    }
    /**
     * Setzt den Lieferanten eines Artikels
     * 
     * @param lieferant Der Lieferant eines Artikels  
     */
    public void setLieferant (Lieferant lieferant){
        this.lieferant = lieferant;
    } 
    /**
     * Gibt den Lieferanten eines Artikels 
     * 
     * @return Lieferant des Artikels
     */
    public Lieferant getLieferant (){
        return lieferant;
        
    }
}

