package de.hrw.wi.persistence;

import java.util.List;

import de.hrw.wi.persistence.dto.OrderDTO;
import de.hrw.wi.persistence.dto.ProductDTO;



/**
 * 
 * @author Mateo
 *
 */
public interface DBReadInterfaceProduct {

    /**
     * @param articleCode
     *            Artikelnummer
     * @return Status des Produkts
     * @author Laura Eberhard
     */
    String getProductStatus(String articleCode);
    
    /**
     * @param articleCode
     *          Artikelnummer
     * @return
     *          Liste mit allen Bestellungen von diesem Produkt
     * @author Aykut Topal
     */
    List<OrderDTO> getOrdersByProduct(String articleCode); 
    
    /**
     * @param articleCode
     *          Artikelnummer
     * @return
     *         Produkt mit dieser Artikelnummer
     * @author Mateo Kevric
     */
    ProductDTO getProductByArticleCode(String articleCode); 
    
}
