package de.hrw.wi.service;

import java.sql.SQLException;
import java.util.List;

import de.hrw.wi.business.Customer;
import de.hrw.wi.business.Order;
import de.hrw.wi.business.StornoOrder;
import de.hrw.wi.persistence.dto.StornoOrderDTO;

/**
 * 
 * @author Alexander Böhm, Sinan Ayten
 *
 */
public interface StornoOrderManagementServiceInterface {

    /**
     * @param order
     *            Bestellung
     * @param reason
     *            StornierungsGrund
     * @param stornoDate
     *            StornierDatum
     * @throws SQLException
     *             Wirft SQLException
     */
    void cancelOrder(Order order, String reason, String stornoDate) throws SQLException;

    /**
     * 
     * @param orderNumber
     *            Bestellnummer
     * @return Gibt eine Stornierte Bestellung zurück
     */
    StornoOrder getStornoOrderByNumber(int orderNumber);

    /**
     * 
     * @param customer
     *            alle Stornos eines Kunden
     */
    void getStornoOrderByCustomer(Customer customer);

    /**
     * 
     * 
     * @param customer
     *          Kunde
     * @return List StornoOrderDTO
     */
    List<StornoOrderDTO> getStornoOrderByCustomer2(Customer customer);
}
