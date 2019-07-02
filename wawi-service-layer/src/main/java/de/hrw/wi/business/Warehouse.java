package de.hrw.wi.business;

/**
 * @author andriesc
 *
 *         Warehouse ist ein Lager. Bei der Erzeugung wird seine Groesse angegeben: Ein Warehouse
 *         kann n F�cher der jeweils gleichen Groesse k enthalten.
 * 
 *         In einem Fach koennen jeweils nur Gegenstaende eines Typs abgegelegt werden, von diesem
 *         Gegenstand aber dann soviele, solange die Summe der Groesse der Gegenstaende nicht
 *         groesser ist als die Groesse des Faches.
 * 
 *         In ein Lager koennen Gegenstaende eingelagert und ausgelagert werden.
 * 
 *         Auf ein Warehouse soll niemals direkt zugegriffen werden, sondern nur ueber einen
 *         entsprechenden Service.
 * 
 */
public class Warehouse {
//    private int binSize;
//    private int maxBin;
//
//    /**
//     * 
//     */
//    public Warehouse(int numberOfBins, int sizeOfBins) {
//        this.binSize = sizeOfBins;
//        this.maxBin = numberOfBins;
//    }
//
//    /**
//     * Die Methode ermittelt, welches Fach der naechste freie Lagerplatz ist, wenn ich etwas der
//     * Groesse size ablegen will.
//     * 
//     * @param size
//     *            Groesse des oder der Gegenstaende, die ich ablegen will
//     * 
//     * @return Nummer des naechsten freien Lagerplatzes passender Groesse. Die Lagerplaetze werden
//     *         bei 0 anfangend durchnummeriert. Ist kein Platz mehr frei, wird -1 zurueckgegeben.
//     */
//    public int nextFreeBin(int size) {
//        // TODO warehouse logic not implemented yet
//        return 0;
//    }
//
//    /**
//     * Lagert von einem Produkt die angegebene Anzahl in einem angegebenen Lagerplatz ein. 
    //Wenn das
//     * Einlagern fehlschlaegt, weil der Lagerplatz zu klein ist fuer das Produkt und seine Anzahl,
//     * dann wird das Produkt gar nicht eingelagert. Gegebenenfalls muss man also die Anzahl des
//     * Produktes anschliessend in zwei oder mehrere neue Einlagerversuche aufteilen.
//     * 
//     * @param p
//     *            Das einzulagernde Produkt
//     * @param quantity
//     *            Angabe, in welcher Stueckanzahl das Produkt eingelagert werden soll
//     * @param bin
//     *            der Ziel-Lagerplatz fuer die Einlagerung
//     * @return gibt true zurueck, wenn das Einlagern erfolgreich war oder false, wenn es nicht
//     *         erfolgreich war (etwa Lagerplatz nicht gross genug)
//     */
//    public boolean placeIntoStock(Product p, int quantity, StorageBin bin) {
//        // TODO warehouse logic not implemented yet
//        return false;
//    }
//
//    /**
//     * Entnimmt von einem Produkt die angegebene Anzahl aus dem angegebenen Lagerplatz. Wenn das
//     * Auslagern fehlschlaegt weil etwa nicht genuegend Stueck des Produktes vorhanden sind, wird
//     * gar nichts aus dem Lager entnommen. Gegebenenfalls muss also in einem weiteren
//     * Auslagerversuch versucht werden, eine reduzierte Anzahl von Produkten zu entnehmen.
//     * 
//     * @param p
//     *            Das auszulagernde Produkt
//     * @param quantity
//     *            Angabe, in welcher Stueckzahl das Produkt entnommen werden soll
//     * @param bin
//     *            Der Lagerplatz, aus dem das Produkt in angegebener Stueckzahl entnommen werden
//     *            soll
//     * @return gibt true zurueck, wenn das Auslagern erfolgreich war oder false, wenn es nicht
//     *         erfolgreich war (Produkt nicht in ausreichender Anzahl vorhanden)
//     */
//    public boolean removeFromStock(Product p, int quantity, StorageBin bin) {
//        // TODO warehouse logic not implemented yet
//        return false;
//    }
//
//    /**
//     * 
//     * Gibt aus, wie gross der Bestand in diesem Lager von dem jeweiligen Produkt ist.
//     * 
//     * @param p
//     *            das angefragte Produkt
//     * @return Die Anzahl
//     */
//    public int availableStock(Product p) {
//        // TODO warehouse logic not implemented yet
//        return 0;
//    }
//
//    /**
//     * Gibt aus, wo im Lager das Produkt gespeichert ist.
//     * 
//     * @param p
//     * @return Eine Menge von Lagerplaetzen oder eine leere Menge, wenn das Produkt gar nicht
//     *         vorhanden ist.
//     */
//    public Set<StorageBin> retrieveBinsOf(Product p) {
//        // TODO warehouse logic not implemented yet
//        return null;
//    }

}
