package de.hrw.wi.persistence;

import java.sql.SQLException;

import de.hrw.wi.persistence.dto.CustomerDTO;






/**
 * 
 * @author Sinan Ayten, Laura Eberhard
 *
 */

/**
 * @author lauraeberhard
 *
 */
public interface DBWriteInterfaceCustomer {

   

    /**
     * @param custDTO
     * Kunde
     * 
     * @throws SQLException
     *             Exception
     * @author Sinan Ayten
     */
    void addCustomer(CustomerDTO custDTO) throws SQLException;
    
    /**
     * changes the last name of the customer
     * @param customerID
     *        customer ID
     * @param lastNameNew
     *        new last name 
     * @throws SQLException
     *         Exception
     * @author Laura Eberhard
     */
    void changeCustomerLastName(int customerID, String lastNameNew) throws SQLException; 
    
    /**
     * changes the street of the customer
     * @param customerID
     *        customer ID
     * @param streetNew
     *        new street 
     * @throws SQLException
     *         Exception
     * @author Laura Eberhard
     */
    void changeCustomerStreet(int customerID, String streetNew) throws SQLException;
    
    /**
     * changes the housenumber of the customer 
     * @param customerID
     *        customer ID
     * @param houseNrNew
     *        new houseNr
     * @throws SQLException
     *         Exception 
     * @author Laura Eberhard    
     */
    void changeCustomerHouseNr(int customerID, int houseNrNew) throws SQLException; 
    
    /**
     * changes the city of the customer
     * @param customerID
     *        customer ID
     * @param cityNew
     *        new city
     * @throws SQLException
     *         Exception
     * @author Laura Eberhard
     */
    void changeCustomerCity(int customerID, String cityNew) throws SQLException; 
    
    /**
     * changes the postal code of the customer
     * @param customerID
     *        customer ID
     * @param postalCodeNew
     *        new postal code 
     * @throws SQLException
     *         Exception
     * @author Laura Eberhard
     */
    void changeCustomerPostalCode(int customerID, int postalCodeNew) throws SQLException; 
   
    /**
     * changes the country of the customer
     * @param customerID
     *        customer ID
     * @param countryNew
     *        country 
     * @throws SQLException
     *         Exception
     * @author Laura Eberhard
     */
    void changeCustomerCountry(int customerID, String countryNew) throws SQLException; 
  
    /**
     * changes the E-Mail of the customer
     * @param customerID
     *        customer ID
     * @param mailNew
     *        new mail address
     * @throws SQLException
     *         Exception 
     * @author Laura Eberhard            
     */
    void changeCustomerMail(int customerID, String mailNew) throws SQLException; 
 
    /**
     * changes the phone number of the customer
     * @param customerID
     *        customer ID
     * @param numberNew
     *        new phone number 
     * @throws SQLException
     *         Exception
     * @author Laura Eberhard
     */
    void changeCustomerPhone(int customerID, String numberNew) throws SQLException; 
    
    /**
     * delete the customer
     * @param customer
     *          customer
     * @throws SQLException
     *          Exception
     * @author Laura Eberhard
     */
    void deleteCustomer(CustomerDTO customer) throws SQLException; 

}