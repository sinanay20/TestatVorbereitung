package de.hrw.wi.persistence;

import de.hrw.wi.persistence.dto.OrderDTO;

/**
 * 
 * @author Alexander Böhm
 *
 */

public interface DBWriteInterfaceOrder {
    /**
     * 
     * @param order
     *            Bestellung
     * @author Sinan Ayten, Alexander Böhm, Laura Eberhard, Mateo Kevric
     */
    void addOrder(OrderDTO order);
}
