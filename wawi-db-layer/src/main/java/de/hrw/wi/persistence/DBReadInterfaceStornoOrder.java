package de.hrw.wi.persistence;

import java.util.List;

import de.hrw.wi.persistence.dto.StornoOrderDTO;

/**
 * 
 * @author Sinan Ayten
 *
 */

public interface DBReadInterfaceStornoOrder {

    /**
     * 
     * @param orderNumber
     *            Bestellnummer
     * @return StornoOrderDTO
     * @author Sinan Ayten
     */
    StornoOrderDTO getStornoOrderByNumber(int orderNumber);

    /**
     * 
     * 
     * @return List<String>
     * @author Sinan Ayten
     */
    List<String> getAllStornoOrders();
    
    /**
     * 
     * @param customerId
     *            Id des Kunden
     * @return List stornoOrderDTO
     * @author Dennis
     */
    List<StornoOrderDTO> getStornoOrdersByCustomerId(int customerId);
}
    
