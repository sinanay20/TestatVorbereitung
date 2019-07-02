package de.hrw.wi.service;

import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.ArrayList;
//import java.util.List;
import java.util.List;

//import de.hrw.wi.business.Customer;
import de.hrw.wi.business.Normal;
//import de.hrw.wi.business.Order;
import de.hrw.wi.business.Penner;
import de.hrw.wi.business.Product;
import de.hrw.wi.business.ProductStateInterface;
import de.hrw.wi.business.Renner;
import de.hrw.wi.persistence.DBReadInterfaceOrder;
import de.hrw.wi.persistence.DBReadInterfaceProduct;
//import de.hrw.wi.persistence.DBWriteInterfaceOrder;
import de.hrw.wi.persistence.DBWriteInterfaceProduct;
import de.hrw.wi.persistence.RealDatabase;
import de.hrw.wi.persistence.dto.OrderDTO;
//import de.hrw.wi.persistence.dto.AddressDTO;
//import de.hrw.wi.persistence.dto.CustomerDTO;
//import de.hrw.wi.persistence.dto.OrderDTO;
import de.hrw.wi.persistence.dto.ProductDTO;

/**
 * @author Mateo
 *
 */
public class ProductManagementService implements ProductManagementServiceInterface {

    private DBWriteInterfaceProduct dbWrite = new RealDatabase();
    private DBReadInterfaceProduct dbRead = new RealDatabase();
//    private DBWriteInterfaceOrder dbWriteOrder = new RealDatabase();
    private DBReadInterfaceOrder dbReadOrder = new RealDatabase(); 

    /**
     * @param dbRP
     *          dbReadInterfaceProduct
     * @param dbWP
     *          dbWriteInterfaceProduct
     * @author Mateo Kevric
     */
    public ProductManagementService(DBReadInterfaceProduct dbRP, DBWriteInterfaceProduct dbWP) {
        
        this.dbRead = dbRP; 
        this.dbWrite = dbWP; 
    }

    @Override
    public Product getProductByArticleCode(String articleCode) {
        
        Product product = null; 
        ProductDTO prodDTO; 
        
        prodDTO = dbRead.getProductByArticleCode(articleCode); 
        
        if (prodDTO == null) {
            return null;
        }
        
        product = new Product(prodDTO.getName(), prodDTO.getArticleCode()); 
        
        product.setSize(prodDTO.getSize());
        
        return product;
    }
    

    /**
     * Vergleich Anzahl aller Bestellungen eines Produkts mit der Gesamtanzahl aller Bestellung 
     * Bestellungen mit mehr als 50% sind Renner
     * Bestellungen von 10% bis 50% sind Normal
     * Bestellungen unter 10% sind Penner 
     * 
     * @author Laura Eberhard, Mateo Kevric 
     */
    @Override
    public ProductStateInterface evaluateProductState(Product product) throws SQLException {
        
        String articleCode = product.getProductCode(); 
        
        double countProduct =  dbRead.getOrdersByProduct(articleCode).size(); 
        
        double countAll = dbReadOrder.getAllOrders().size(); 
        
        double percentage = Math.round((countProduct / countAll) * 100); 
        
        if (percentage > 50) {
            
            product.setCurrentState(new Renner(product));
            
            dbWrite.changeProductStatus(articleCode, "Renner");
            
            return new Renner(product); 
            
        } else if (percentage >= 10 && percentage <= 50) {
            
            product.setCurrentState(new Normal(product));
            
            dbWrite.changeProductStatus(articleCode, "normal");
            
            return new Normal(product); 
            
        } else if (percentage < 10) {
            
            product.setCurrentState(new Penner(product));
            
            dbWrite.changeProductStatus(articleCode, "Penner");
            
            return new Penner(product); 
        }
        
        return null;
    }
    
    /**
     * @atuhor Aykut Topal
     */

    @Override
    public void getOrdersByProduct(Product product) {
        double priceGes = 0;
        String articelCode = product.getProductCode();

        List<OrderDTO> getOrdersByProduct = new ArrayList<OrderDTO>(
                dbRead.getOrdersByProduct(articelCode));

        String bs = "\n";
        String euro = " €";

        for (OrderDTO orderDTO : getOrdersByProduct) {

            System.out.println("Bestellnummer: " + orderDTO.getOrdernumber() + bs + "Kundennummer: "
                    + orderDTO.getCustomerId() + bs + "Artikelnummer: " + orderDTO.getArticleCode()
                    + bs + "Bestelldatum: " + orderDTO.getOrderDate() + bs + "Menge: "
                    + orderDTO.getAmount() + bs + "Einzelpreis: " + orderDTO.getPrice() + euro + bs
                    + "Gesamtpreis Bestellung: " + orderDTO.getPriceTotal() + euro);
            priceGes = priceGes + orderDTO.getPriceTotal();
            System.out.println(" ");

        }
        System.out.println("Durch ein Artikel generierter Umsatz: " + priceGes + bs);

    }
}
