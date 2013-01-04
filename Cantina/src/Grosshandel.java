
/**
 * Die Objekte der Klasse Grosshandel erben alle ihre wesentlichen Attribute und Methoden von der Klasse Lieferant,
 * nur die Methode zur Ausgabe des Lieferkostensatz wird hier zus�tzlich implementiert.
 * Die Klasse Grosshandel ist eine Subklasse der Oberklasse Lieferant.
 * 
 * @author Rene Wiederhold 
 * @version 0.01
 */
public class Grosshandel extends Lieferant
{
    private float kostensatz;     //Der Kostensatz, welcher pro bestellten Artikel f�llig wird. 

    /**
     * Konstruktor f�r Objekte der Klasse Grosshandel
     */
    public Grosshandel()
    {
        kostensatz = 0;
    }
    
     /**
     * Gibt den Lieferkostensatz eines Grosshandels zur�ck. Dies wird f�r die Transportkostenberechnung innerhalb der 
     * Einkaufsliste ben�tigt.
     *
     * @return    Der Lieferkostensatz des Grosshandels.
     */
    public float getLieferkostensatz()
    {
        return kostensatz;
    }
    
    /**
     * Setzt den Lieferkostensatz eines Grosshandels.
     *
     * @param  liefko  Der Lieferkostensatz des Grosshandels
     */
    public void setKostensatz(float liefko)
    {
    	kostensatz=liefko;
    }
}
