import java.util.ArrayList;
/**
 * Die Klasse Einkaufsliste kann aus den Kantinenplan-Objekten eine Einkaufsliste mit einzelnen 
 * Einkaufslistenpositionen erzeugen. Dazu erzeugt sie als Zwischenschritt BedarfPos-Objekte 
 * für den Gesamtbedarf einer Zutat. Die Bedarfe werden dann in BestellPos-Objekte überführt.
 * 
 * Eine Einkaufsliste steht in einer Kompositionsbeziehung zur Bestellpositionen, weil eine Einkaufsliste nie 
 * ohne eine Bestellpsotion existieren kann. Sie verwaltet zudem die BedarfsPos und aggregeiert
 * diese. Sie aggregiert auch die Kantinenpläne um den Gesamtbedarf an Artikeln zu ermitteln, die als 
 * BedarfPos geführt werden. Die Assoziation zur Lieferantenverwaltung ermöglicht der Einkaufsliste die 
 * günstigsten Artikel zu ermitteln und aus der BedarfsPos die BestellPos zu generieren.
 * Für den Planungslauf steht sie in Assoziation zur Kantinenplanung. Zudem wird sie als Datei
 * exportiert, weshalb auch zum Exporter eine Assoziation gegeben ist. 
 * 
 * @author Rene Wiederhold 
 * @version
 */
public class Einkaufsliste
{
    /** Enthält die Objekte der Klasse BestellPos */
    private ArrayList<BestellPos> bestellArrayList;
    /** Enthält die Objekte der Klasse BedarfPos */
    private ArrayList<BedarfPos> bedarfArrayList;
    /** Enthält die Kantinenplanobjekte, für die die Einkaufsliste zu berechnen ist */
    private ArrayList<Kantinenplan> kantinenplanArrayList;
    /** Enthält die Gesamtkosten der Bestellung inklusive der Transportkosten */
    private float gesamtkosten;
    /** Enthält die zu verwendende Lieferantenverwaltung */
    private Lieferantenverwaltung lieferantenverw;

    /**
     * Dem Konstruktor wird die zu verwendende Lieferantenverwaltung übergeben.
     */
    public Einkaufsliste(Lieferantenverwaltung lieferantenverw)
    {
        
    }
    
    /**
     * Fügt dem kantinenplanArrayList Instanzen vom Kantinenplan hinzu (im Regelfall 2 Instanzen)
     *
     * @param  kantinenplan   Ein Kantinenplanobjekt
     * @return     True für ein erfolgreiches hinzufügen, False falls ein Problem aufgetreten ist.
     */
    public boolean addKantinenplan(Kantinenplan kantinenplan)
    { 
        return true;
    }

    /**
     * Erzeugt aus den im KantinenplanArrayList enthaltenen Kantinenplänen eine Einkaufsliste
     * Es muss vorher mindestens ein Kantinenplan über addKantinenplan referenziert worden sein.
     * 
     * @return     true, für eine erfolgreich erstellte Einkaufsliste, false, falls Fehler aufgetreten sind.
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
     * Gibt die Gesamtkosten der Einkaufsliste zurück, die vorher mit berechneGesamtkosten() berechnet wurden.
     *

     * @return Die Gesamtkosten der Einkaufsliste.
     */
    public float getGesamtkosten()
    {
        return gesamtkosten;
    }
    
    /**
     * Liefert einen ArrayList zurück, der alle BestellPos-Objekte der Einkaufsliste enthält
     * 
     * @return Einen ArrayList der alle BestellPos-Objekte enthält
     */
    public ArrayList<BestellPos> getBestellPos()
    {
        return bestellArrayList;
    }
}

