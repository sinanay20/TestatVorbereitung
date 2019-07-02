package de.hrw.wi.persistence;

import java.sql.SQLException;

import de.hrw.wi.persistence.dto.StornoOrderDTO;

/**
 * 
 * @author Sinan Ayten, Alexander Böhm
 *
 */

public interface DBWriteInterfaceStornoOrder {

    /**
     * 
     * @param sOrder
     *            Stornierte Bestellung hinzufügen
     * @author Sinan Ayten
     */
    void addStornoOrder(StornoOrderDTO sOrder);

    /**
     * 
     * @param orderID
     *          Bestellnummer
     * @param reason
     *          Storniergrund
     * @param stornoDate
     *          StornierDatum
     * @throws SQLException
     *          Wirft SQLException
     */
    void cancelOrder(int orderID, String reason, String stornoDate) throws SQLException;

}
