
/**
 * Die Objekte der Klasse Grosshandel erben alle ihre wesentlichen Attribute und Methoden von der Klasse Lieferant,
 * nur die Methode zur Ausgabe des Lieferkostensatz wird hier zusätzlich implementiert.
 * Die Klasse Grosshandel ist eine Subklasse der Oberklasse Lieferant.
 * 
 * @author Rene Wiederhold 
 * @version 0.01
 */
public class Grosshandel extends Lieferant
{
    private float lieferkostenSatz;     //Der Kostensatz, welcher pro bestellten Artikel fällig wird. 

    /**
     * Konstruktor für Objekte der Klasse Grosshandel
     */
    public Grosshandel()
    {
        lieferkostenSatz = 0;
        //Debug-Print
        System.out.println("Ein Grosshandel wurde konstruiert");
    }
    
     /**
     * Gibt den Lieferkostensatz eines Grosshandels zurück. Dies wird für die Transportkostenberechnung innerhalb der 
     * Einkaufsliste benötigt.
     *
     * @return    Der Lieferkostensatz des Grosshandels.
     */
    public float getLieferkostensatz()
    {
        return lieferkostenSatz;
    }
    
    /**
     * Setzt den Lieferkostensatz eines Grosshandels.
     *
     * @param  entf  Der Lieferkostensatz des Grosshandels
     */
    public void setLieferkostensatz(float entf)
    {

    }
}
