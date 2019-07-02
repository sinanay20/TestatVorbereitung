package de.hrw.wi.service;

import java.sql.SQLException;

import de.hrw.wi.business.Product;
import de.hrw.wi.business.ProductStateInterface;

/**
 * 
 * @author Mateo
 *
 */

public interface ProductManagementServiceInterface {
    
    /**
     * @param articleCode
     *          Artikelnummer
     * @return
     *          Produkt mit dieser Artikelnummer
     * @author Mateo Kevric
     */
    Product getProductByArticleCode(String articleCode);
    
    /**
     * @param product
     *          Produkt
     * @author Aykut Topal
     */
    void getOrdersByProduct(Product product);
    
    /**
     * @param product
     *          Produkt
     * @return
     *          Status des Produkts nach der Analyse des Verkaufsverhaltens
     * @author Mateo Kevric, Laura Eberhard
     * @throws SQLException 
     */
    ProductStateInterface evaluateProductState(Product product) throws SQLException; 
}
