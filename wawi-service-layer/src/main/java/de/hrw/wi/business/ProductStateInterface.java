package de.hrw.wi.business;

/**
 * 
 * @author lauraeberhard
 *
 */
public interface ProductStateInterface {
    
    /**
     * der Status eines Produkts wird auf "Renner" gesetzt
     * @author Laura Eberhard
     */
    void setProductToRenner(); 
    
    /**
     * der Status eines Produkts wird auf "normal" gesetzt
     * @author Laura Eberhard
     */
    void setProductToNormal();
    
    /**
     * der Status eines Produkts wird auf "Penner" gesetzt
     * @author Laura Eberhard
     */
    void setProductToPenner(); 
    
    /**
     * @return
     *       true, wenn das Produkt ein "Renner" ist
     *       false, wenn das Produkt kein "Renner" ist
     */
    boolean isRenner(); 
    
    /**
     * @return
     *       true, wenn das Produkt den Status "normal" hat
     *       false, wenn das Produkt den Status "normal" hat
     */
    boolean isNormal(); 
    
    /**
     * @return
     *       true, wenn das Produkt ein "Penner" ist
     *       false, wenn das Produkt kein "Penner" ist
     */
    boolean isPenner();

}
