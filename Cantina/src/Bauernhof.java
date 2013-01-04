
/**
 * Die Objekte der Klasse Bauernhof erben alle ihre wesentlichen Attribute und Methoden von der Klasse Lieferant,
 * nur die Methode zur Ausgabe der Entfernung wird hier zus�tzlich implementiert. Die Klasse Bauernhof ist eine 
 * Subklasse der Oberklasse Lieferant.
 * 
 * @author Rene Wiederhold
 * @version
 */
public class Bauernhof extends Lieferant
{
    private float entfernung; //Die Entfernung des Bauernhofes, welche mit dem pauschalen km-Satz multipliziert wird.

    /**
     * Konstruktor f�r Objekte der Klasse Bauernhof
     */
    public Bauernhof()
    {
        entfernung = 0;

    }
    
    /**
     * Gibt die Entfernung eins Bauernhof zur�ck. Dies wird f�r die Transportkostenberechnung innerhalb der 
     * Einkaufsliste ben�tigt.
     *
     * @return    Die Entfernung des Bauernhofes.
     */
    public float getEntfernung()
    {
        return entfernung;
    }
    
    /**
     * Setzt die Entfernung eines Bauernhofes.
     *
     * @param  entf  Die Entfernung des Bauernhofes
     */
    public void setEntfernung(float entf)
    {

    }

}
