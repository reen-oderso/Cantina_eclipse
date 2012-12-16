import java.util.ArrayList;
/**
 * Die abstrakte Klasse Lieferant beinhaltet den Namen der Lieferanten. 
 * Jeder Lieferant besitzt Artikel. Die Klasse Lieferant aggregiert deshalb die Artikelobjekte. 
 * Verwaltet werden die Lieferantenobjekte von der Klasse Lieferantenverwaltung die diese aggregiert. 
 * Objekte der Klasse Lieferant können nicht erzeugt werden, da die Klasse Lieferant eine abstrakte Klasse ist.
 * Sie führt die Klassen Bauernhof und Grosshandel als Subklassen.
 * 
 * @author Rene Wiederhold
 * @version (eine Version-Nummer oder ein Datum)
 */
public abstract class Lieferant
{
    private String lieferantName;
    private ArrayList<Artikel> artikelArrayList;

    /**
     * Setzt den Namen eines Lieferanten
     * 
     * @param  lieferantName Der Name des Lieferanten
     */
    public void setLieferantName(String lieferantName)
    {
        
    }
    
    /**
     * Gibt den Namen des Lieferanten zurück
     *
     * @return     Der Name des Lieferanten
     */
    public String getLieferantenName()
    {
        return lieferantName;
    }

    /**
     * Prüft ob Artikel bei Lieferanten verfügbar ist
     * 
     * @param  artikelName Name der zu prüfenden Zutat
     * @return      true wenn Artikel vorhanden  
     */
    public boolean enthältArtikel(String artikelName)
    {
        return true;
    }

    /**
     * Fügt einen Artikel der ArtikelArrayList des Lieferanten hinzu.
     *
     * @param  art Ein Artikel-Objekt
     */
    public void addArtikel(Artikel art)
    {
        
    }

    /**
     * Die Methode gibt eine ArrayList zurück, der alle Artikel eines Lieferanten enthält.
     *
     * @return Eine ArrayList, der alle Artikel des Lieferanten enthält.
     */
    public ArrayList<Artikel> getArtikelArrayList()
    {
        return artikelArrayList;
    }

}
