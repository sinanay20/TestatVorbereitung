package de.hrw.wi.service;

import java.sql.SQLException;

import de.hrw.wi.business.Customer;
import de.hrw.wi.business.Order;

/**
 * 
 * @author Mateo Kevric
 *
 */

public interface OrderManagementServiceInterface {

    /**
     * 
     * @param order
     *            Bestellung
     * @throws SQLException
     * @author Mateo Kevric
     */

    void addOrder(Order order);

    /**
     * 
     * @param orderNumber
     *            Bestellnummer
     * @return Order
     * @author Mateo Kevric
     */
    Order getOrderByNumber(int orderNumber);

    /**
     * @param customer
     *            Kunde
     * @author Aykut Topal
     */
    void ordersByCustomer(Customer customer);

    /**
     * 
     * @param orderDate
     *            Bestelldatum
     * @author Alexander Böhm
     */
    void printOrdersByDate(String orderDate);

    /**
     * 
     * @param orderDate
     *            Bestelldatum
     * @author Sinan Ayten
     */
    void dateOrdersByPrice(String orderDate);

    /**
     * @param customer
     *            Umsatz
     * @author Mateo Kevric
     */

    void getOrdersBySales(Customer customer);
}