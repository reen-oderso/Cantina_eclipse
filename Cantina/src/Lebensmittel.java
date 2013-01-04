
/**
 * Die Klasse beschreibt Lebensmittel mit ihrer Kategorisierung nach dem Typ (Fleisch, Fisch, Vegetarisch)
 * sowie ihre Gesamtverf�gbarkeit �ber alle Lieferanten um in einer Vorpr�fung bei der Speiseplanerstellung
 * sicherstellen zu k�nnen, dass keine Tagesgerichte dem Speiseplan hinzugef�gt werden, f�r die die ben�tigten
 * Zutaten nicht in ausreichender Menge beschafft werden k�nnen.
 * 
 * Instanzen der Klasse Lebensmittel werden von der Lieferantenverwaltung aggregiert.
 * 
 * @author Rene Wiederhold 
 * @version
 */
public class Lebensmittel
{
    /** Der Name des Lebensmittels */
    private String name;
    /**Menge der am Markt Verfuegbaren Lebensmittel*/
    private float menge;
    /**Typ (Fleisch,Fisch,Vegatrisch) des Lebensmittels */
    private String typ;
    /**Einheit der Menge*/
    private String einheit;

    /**
     * Konstruktor f�r Lebensmittelobjekte
     */
    public Lebensmittel()
    {

    }
    /**
     * Setzt den Namen eines Lebensmittels
     * 
     * @param name Der Name des Lebensmittels      
     */
    public void setName (String name){
    	this.name=name;
    }
    /**
     * Gibt den Namen eines Lebensmittels am Markt 
     * 
     * @return Name des Lebensmittels
     */
    public String getName(){
        return name;
    }
    /**
     * Setzt die Menge eines Lebensmittels
     * 
     * @param marktMenge Die zu schreibende Marktmenge.
     *        
     */
    public void setMenge (float menge){
        this.menge=menge;
    }
    /**
     * Gibt die Menge des Lebensmittels am Markt 
     * 
     * @return Lebensmittelmenge am Markt
     */
    public float getMenge(){
        return menge;
    }
    /**
     * Setzt den Typ eines Lebensmittels
     * 
     * @param typ Der String mit der Typbezeichnung.
     *        
     */
    public void setTyp(String typ){
        this.typ=typ;
    }
    /**
     * Gibt den Typ des Lebensmittels am Markt 
     * 
     * @return Typ des Lebensmittels
     */
    public String getTyp(){
        return typ;
    }
	/**
	 * Gibt die Einheit der Menge eines Lebensmittels
	 * @return Einen String der das Einheitenzeichen enth�lt
	 */
	public String getEinheit() {
		return einheit;
	}
	/**
	 * Setzt die Einheit der Menge des Lebensmittels
	 * @param einheit Einen String der das Einheitenzeichen enth�lt
	 */
	public void setEinheit(String einheit) {
		this.einheit = einheit;
	}
}
