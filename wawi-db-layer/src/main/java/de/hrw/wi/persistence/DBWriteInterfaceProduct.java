package de.hrw.wi.persistence;

import java.sql.SQLException;

/**
 * 
 * @author Mateo
 *
 */

public interface DBWriteInterfaceProduct {

    /**
     * @param articleCode
     *            Artikelnummer des Produkts
     * @param status
     *            Status des Produkts
     * @throws SQLException
     *             SQLException
     * @author Laura Eberhard
     */
    void changeProductStatus(String articleCode, String status) throws SQLException;
}
