package de.hrw.wi.persistence;

import java.util.List;

import de.hrw.wi.persistence.dto.CustomerDTO;
import de.hrw.wi.persistence.dto.OrderDTO;

/**
 * 
 * @author Alexander Böhm
 *
 */
public interface DBReadInterfaceOrder {

    /**
     * 
     * @param customerID
     *            Kundenummer
     * @return OrderDTO
     * @author Sinan Ayten
     */

    OrderDTO getOrderById(int customerID);

    /**
     * 
     * @param orderNr
     *            Bestellnummer
     * @return OrderDTO
     * @author Alexander Böhm
     */
    OrderDTO getOrderByNumber(int orderNr);

    /**
     * 
     * 
     * 
     * @return Liste mit Ordern in String
     * @author Alexander Böhm
     */

    List<String> getAllOrders();
    
    /**
     * @param customer
     *          Kunde
     * @return List
     *          Liste mit allen Bestellungen eines ausgewählten Kunden
     * @author Aykut Topal
     */
    List<OrderDTO> getAllOrdersFromCustomer(CustomerDTO customer); 
    
    
    /**
     * 
     * @param orderDate
     *          Bestelldatum
     * @return
     *          Gibt alle Bestellungen des jeweiligen Tages aus
     * @author Alexander Böhm
     */
    List<OrderDTO> getAllOrdersByDate(String orderDate);
    /**
     * 
     * @param orderDate
     *          Bestelldatum
     * @return
     *          gibt alle Bestellungen von einem Tag sortiert nach Gesamtumsatz aus
     * @author Sinan Ayten
     */
    List<OrderDTO> dateSortedOrdersByPrice(String orderDate);

    
    /**
     * @param customer
     *            Kunde
     * @return
     *          gibt alle Bestellungen von einem Kunden nach Umsatz sortiert 
     * @author Mateo Kevric 
     */
    List<OrderDTO> getOrdersBySales(CustomerDTO customer);

    
    
}
