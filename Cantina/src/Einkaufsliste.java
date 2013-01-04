import java.util.ArrayList;
/**
 * Die Klasse Einkaufsliste kann aus den Kantinenplan-Objekten eine Einkaufsliste mit einzelnen 
 * Einkaufslistenpositionen erzeugen. Dazu erzeugt sie als Zwischenschritt BedarfPos-Objekte 
 * f�r den Gesamtbedarf einer Zutat. Die Bedarfe werden dann in BestellPos-Objekte �berf�hrt.
 * 
 * Eine Einkaufsliste steht in einer Kompositionsbeziehung zur Bestellpositionen, weil eine Einkaufsliste nie 
 * ohne eine Bestellpsotion existieren kann. Sie verwaltet zudem die BedarfsPos und aggregeiert
 * diese. Sie aggregiert auch die Kantinenpl�ne um den Gesamtbedarf an Artikeln zu ermitteln, die als 
 * BedarfPos gef�hrt werden. Die Assoziation zur Lieferantenverwaltung erm�glicht der Einkaufsliste die 
 * g�nstigsten Artikel zu ermitteln und aus der BedarfsPos die BestellPos zu generieren.
 * F�r den Planungslauf steht sie in Assoziation zur Kantinenplanung. Zudem wird sie als Datei
 * exportiert, weshalb auch zum Exporter eine Assoziation gegeben ist. 
 * 
 * @author Rene Wiederhold 
 * @version
 */
public class Einkaufsliste
{
    /** Enth�lt die Objekte der Klasse BestellPos */
    private ArrayList<BestellPos> bestellArrayList;
    /** Enth�lt die Objekte der Klasse BedarfPos */
    private ArrayList<BedarfPos> bedarfArrayList;
    /** Enth�lt die Kantinenplanobjekte, f�r die die Einkaufsliste zu berechnen ist */
    private ArrayList<Kantinenplan> kantinenplanArrayList;
    /** Enth�lt die Gesamtkosten der Bestellung inklusive der Transportkosten */
    private float gesamtkosten;
    /** Enth�lt die zu verwendende Lieferantenverwaltung */
    private Lieferantenverwaltung lieferantenverw;

    /**
     * Dem Konstruktor wird die zu verwendende Lieferantenverwaltung �bergeben.
     */
    public Einkaufsliste(Lieferantenverwaltung lieferantenverw)
    {
        
    }
    
    /**
     * F�gt dem kantinenplanArrayList Instanzen vom Kantinenplan hinzu (im Regelfall 2 Instanzen)
     *
     * @param  kantinenplan   Ein Kantinenplanobjekt
     * @return     True f�r ein erfolgreiches hinzuf�gen, False falls ein Problem aufgetreten ist.
     */
    public boolean addKantinenplan(Kantinenplan kantinenplan)
    { 
        return true;
    }

    /**
     * Erzeugt aus den im KantinenplanArrayList enthaltenen Kantinenpl�nen eine Einkaufsliste
     * Es muss vorher mindestens ein Kantinenplan �ber addKantinenplan referenziert worden sein.
     * 
     * @return     true, f�r eine erfolgreich erstellte Einkaufsliste, false, falls Fehler aufgetreten sind.
     */
    public boolean erzeugeEinkaufsliste()
    {
        return true;
    }
    

    /**
     * Berechnet die Gesamtkosten der Bestellung inklusive Transportkosten, die sich aus allen im BestellPosArrayList enthaltenen 
     * Bestellpositionen ergibt und schreibt sie in das Attribut gesamtkosten, welches mit getGesamtkosten() ausgelesen werden
     * kann.
     */
    public void berechneGesamtkosten()
    {
        
    }
    
    /**
     * Gibt die Gesamtkosten der Einkaufsliste zur�ck, die vorher mit berechneGesamtkosten() berechnet wurden.
     *

     * @return Die Gesamtkosten der Einkaufsliste.
     */
    public float getGesamtkosten()
    {
        return gesamtkosten;
    }
    
    /**
     * Liefert einen ArrayList zur�ck, der alle BestellPos-Objekte der Einkaufsliste enth�lt
     * 
     * @return Einen ArrayList der alle BestellPos-Objekte enth�lt
     */
    public ArrayList<BestellPos> getBestellPos()
    {
        return bestellArrayList;
    }
}

