package de.hrw.wi.persistence;

import java.util.List;
import java.util.Set;

import de.hrw.wi.persistence.dto.CustomerDTO;

/**
 * 
 * @author Sinan Ayten
 *
 */

public interface DBReadInterfaceCustomer {

    /**
     * 
     * @param id
     *            ID
     * @return CustomerDTO
     * @author Sinan Ayten
     */

    CustomerDTO getCustomerById(int id);

    /**
     * 
     * 
     * @return Set<Integer
     * @author Sinan Ayten
     */

    Set<Integer> getAllCustomerIds();

    /**
     * Liefert den Kunden-Nachnamen mit der gegebenen ID
     * 
     * @param id
     *            die Kunden-ID
     * @return Kundennachname
     */

    /**
     * Liefert die Kunden, sortiert nach dem Umsatz 
     * 
     * @return List<CustomerDTO> eine Liste mit Kunden
     * @author Aykut Topal
     */

    List<CustomerDTO> getSortedCustomerBySales();

    /**
     * 
     * @Author Alexander Böhm 
     * 
     * @return gibt Kundennamen sortiert nach Alphabet zurück
     */

    List<CustomerDTO> kundenNamenSortiert();

}
