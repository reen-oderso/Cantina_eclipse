import java.util.ArrayList;
/**
 * Die Klasse Kantinenplan repr�sentiert einen Kantinenplan im Rahmen der Planungsperiode.
 * Sie aggregiert die Tagesgerichtobjekte und erstellt auf Basis der Anzahl der 
 * Mitarbeiter je Kantine und Standort einen Plan mit insgesamt 45 Tagesgerichten.
 * Sie steht in Assoziation zu der Einkaufsliste um, durch die �bergabe eines
 * Kantinenplans, die Bestellpositonen zu generieren. Der Kantinenplan hat durch die Assoziation zur
 * Reezptverwaltung und Lieferantenverwaltung Zugriff auf deren Methoden um eine g�ltigen Kantinenplan
 * zu erstellen. Um den Export eine Kantinenplans in eine Datei zu erm�glichen steht der Kantinenplan 
 * auch in Assoziation zum Exporter. Damit ein Planungslauf von der Kantinenplanung durchgf�hrt werden kann, 
 * liegt auch mit dieser Klasse eine Assoziationsbeziehung vor.
 * 
 * @author Rene Wiederhold
 * @version
 */
public class Kantinenplan
{
    /** Der Standort, f�r den der Kantinenplan erzeugt wird */ 
    private String standort;
    /** Die Anzahl der Mitarbeiter, welche die Kantine am Standort besuchen*/
    private int anzMitarbeiter;
    /** Die ArrayList enth�lt die zum Speiseplan geh�renden Tagesgerichte */
    public ArrayList<Tagesgericht> tagesgerichtArrayList;
    
    // Die f�r die Kantinenplanerstellung notwendigen Lieferanten- und Rezeptverwaltungen
    private Lieferantenverwaltung lieferantenverw;
    private Rezeptverwaltung rezeptverw;

    /**
     * Konstruktor f�r Objekte der Klasse Kantinenplan
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
     * Die Methode f�llt den Speiseplan mit Tagesgerichten, die sie aus Rezepten erzeugt. Die Rezepte erh�lt 
     * sie von der als Parameter zugewiesenen Rezeptverwaltung. Um die Verf�gbarkeit der Zutaten am Markt sicher-
     * zustellen, wird auf die Lieferantenverwaltung zur�ckgegriffen.
     * 
     * @param lieferantenverw Die Lieferantenverwaltung, welche die Verf�gbarkeitsinformation zur Verf�gung stellt.
     * @param rezeptverw Die Rezeptverwaltung, die die Rezepte f�r den Speiseplan zur Verf�gung stellen kann. 
     * @return True, f�r erfolgreiche Erstellung, False falls Fehler aufgetreten sind.       
     */
    public boolean erzeugePlan(Lieferantenverwaltung lieferantenverw, Rezeptverwaltung rezeptverw)
    {
        this.lieferantenverw=lieferantenverw;
        this.rezeptverw=rezeptverw;

        /*Week 1 */
        
        // Fischrezept-Z�hler
    	int fishCnt=0;
    	// Anzahl der �ffnungstage pro Woche
    	int tageProWoche =5;
    	Rezept[] tmpMeatArr=new Rezept[tageProWoche];	//enth�lt die 5 Fleischrezepte, die pro Woche mindestens angeboten werden sollen
        Rezept[] tmpVeggieArr=new Rezept[tageProWoche]; //enth�lt die 5 vegetarischen Rezepte, die pro Woche mindestens angeboten werden sollen
        Rezept[] tmpRndArr=new Rezept[tageProWoche];	//enth�lt 5 zuf�llige Rezepte, die pro Woche mindestens angeboten werden sollen, wobei min. eines ein Fischgericht sein muss.

        // tempor�ren Wochen-Plan erzeugen
        for (int i=0; i<tageProWoche;i++){
        	tmpMeatArr[i]=rezeptverw.gibFleisch();
           	tmpVeggieArr[i]=rezeptverw.gibVeggie();
        	tmpRndArr[i]=rezeptverw.gibRandom();

        	//Wenn das Zufallsrezept ein Fischrezept ist, wird der Z�hler erh�ht.
        	if(tmpRndArr[i].getTyp()==RezeptTyp.Fisch){
        		fishCnt++;
        		
        		//Debug-Print
        		//System.out.println("ist Fisch Nr. "+fishCnt);
        		
        		//Sollte der Z�hler gr��er als 2 sein (das Zufallsgericht also das 3. Fischgericht in der Woche), wird der Schleifenz�hler um 1 verringert.
        		//Im n�chsten Schleifen-Lauf wird dann um eins erh�ht, also der selbe Tag nochmals angesto�en. Dies geschieht so oft, bis ein 
        		//Nicht-Fischgericht vorgeschlagen wird.
        		if (fishCnt>2){
        			
        			//Debug-Print
        			//System.out.println("mehr als 2 Fischgerichte");
        			
        			i--;
        		}
        	}
        	/*Sollte beim letzten Durchlauf (der 5.) der Zufall nicht f�r mindestens ein Fischgericht gesorgt haben (Z�hler also = 0), dann
        	wird explizit ein Fischgericht aufgerufen. Das sorgt nat�rlich daf�r, dass die Wahrscheinlichkeit, dass es christlich-traditionell 
        	am Freitag Fisch gibt, recht hoch ist. Dies kann hier aber auch auf einen anderen Tag gelegt werden, denn letztlich ist es egal, 
        	welcher Tag �berschrieben wird. */
    		if (i==4 && fishCnt==0){
    			tmpRndArr[i]=rezeptverw.gibFisch();
    			
    			//Debug-Print
    			//System.out.println("Immernoch kein Fischgericht. Ersetze mit "+tmpRndArr[i].getName());
    		}
        }
        // Verf�gbarkeits-Pr�fung
        Tagesgericht tg= new Tagesgericht(tmpRndArr[0]);
        tg.setMenge(250);
        
        if (lieferantenverw.lebensmittelVerfuegbar(tg)==false){
        	return false;
        }
        
        
        
        
        
        
        
        /*Debug-Print 
        for (int i=0;i<5;i++){
        	System.out.println("Tag "+(i+1)+": "+tmpMeatArr[i].getName());
        	System.out.println("Tag "+(i+1)+": "+tmpVeggieArr[i].getName());
        	System.out.println("Tag "+(i+1)+": "+tmpRndArr[i].getName());
        } */
        
    	
        
        
        /*
        * F�r eine Woche, das ganze wird dann 2x wiederholt:
        * Von der Rezeptverwaltung werden 5 Fleischgericht-Rezepte und 5 vegetarische Rezepte angefordert, dann ein 
        * Fischgericht, die restlichen 4 mit zuf�lligen Rezepten aufgef�llt. Die 4 Teile werden in tempor�ren Arrays hinterlegt.
        * Nun werden alle Rezepte auf ihre Verf�gbarkeit bei der Lieferantenverwaltung gepr�ft. Falls etwas nicht verf�gbar ist,
        * muss neu angefordert und im tempor�ren Array ersetzt werden.  
        * Dann werden die Tagesgerichtobjekte daraus erstellt. Als Datum wird 1-5 gesetzt, das Fischgericht bekommt immer
        * die 5 (bzw. 10 oder 15 f�r die 2. und 3. Woche). 
        * F�r die Tagesgerichte mit gleichem Datum k�nnen dann die Absatzmengen berechnet und in die Attribute geschrieben werden.
        * Die Tagesgerichtobjekte kommen, wenn alles passt in den tagesgerichtArrayList und die Rezepte werden dann auf 
        * "verwendet=true gesetzt. 
        */
       return true;
    }
    
    /**
     * Die Methode gibt eine ArrayList zur�ck, der alle Tagesgerichte des Kantinenplans enth�lt.
     * 
     * @return Eine ArrayList, der alle Tagesgerichte enth�lt.
     */
    public ArrayList<Tagesgericht> getTagesgerichte()
    {
        return tagesgerichtArrayList;
    }
    
    /**
     * Gibt den Namen der Kantine zur�ck.
     * 
     * @return Der Name der Kantine.
     */
    public String getStandort()
    {
        return standort;
    }
    
    /**
     * Gibt die Anzahl der Mitarbeiter am Standort zur�ck.
     * 
     * @return Der Name der Kantine.
     */
    public int getAnzMA()
    {
        return anzMitarbeiter;
    }
}