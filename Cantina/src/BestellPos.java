
/**
 * Die Klasse BestellPos beschreibt eine einzelne Position der Einkaufsliste, also welche Menge
 * von welchen Artikel bei welchen Lieferanten bestellt/beschafft werden sollen.
 * 
 * Die Klasse BestellPos steht in Assoziation zur Klasse Artikel, weil genau eine Bestellposition
 * zu einem Artikel inklusive der zu bestellenden Mengen geh�rt.
 * Da eine Einkaufliste nie ohne eine Bestellposition existieren kann, sind Objekte dieser
 * Klasse eine Komposition der Einkaufliste.
 * 
 * @author Rene Wiederhold
 * @version
 */
public class BestellPos
{
    /** Enth�lt den Artikel der Position ( Mit Name, Lieferant, Gebindegr��e, Einheit und Einzelpreis)*/
    private Artikel artikel;
    /** Enth�lt die zu beschaffende Menge an Gebinden */
    private int menge;

    /**
     * Konstruktor der Klasse
     * 
     * @param art Der Artikel der Bestellposition
     */
    public BestellPos(Artikel art)
    {
        
    }
    
    /**
     * Gibt die Menge der zu bestellenden Artikel zur�ck.
     *
     * @return    Die zu bestellende Menge des Artikels
     */
    public int getMenge()
    {
        return menge;
    }

    /**
     * Gibt den Artikel der Bestellposition zur�ck.
     *
     * @return    Der Artikel der Bestellposition
     */
    public Artikel getArtikel()
    {
        return artikel;
    }
    
    /**
     * Setz die Menge des zu bestellenden Artikels der Bestellposition.
     *
     * @param menge Die Menge des zu bestellendenh Artikels der Bestellposition
     */
    public void setMenge(int menge)
    {

    }
}

