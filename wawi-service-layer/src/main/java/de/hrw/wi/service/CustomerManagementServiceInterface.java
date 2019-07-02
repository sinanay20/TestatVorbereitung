package de.hrw.wi.service;
import java.sql.SQLException;

import de.hrw.wi.business.Customer;

/**
 * 
 * @author Mateo Kevric, Laura Eberhard
 *
 */
public interface CustomerManagementServiceInterface {
    /**
     * 
     * @param customer
     *            Kundenobjekt
     */
    void addCustomer(Customer customer);
/**
     * changes the last name of the customer
     * @param customer
     *        customer 
     * @param lastNameNew
     *        new last name 
     * @throws SQLException
     *         Exception
     * @author Laura Eberhard
     */
    void changeCustomerLastName(Customer customer, String lastNameNew); 
    
    /**
     * changes the street of the customer
     * @param customer
     *        customer 
     * @param streetNew
     *        new street 
     * @throws SQLException
     *         Exception
     * @author Laura Eberhard
     */
    void changeCustomerStreet(Customer customer, String streetNew);
    
    /**
     * changes the housenumber of the customer 
     * @param customer
     *        customer 
     * @param houseNrNew
     *        new houseNr
     * @throws SQLException
     *         Exception 
     * @author Laura Eberhard    
     */
    void changeCustomerHouseNr(Customer customer, int houseNrNew); 
    
    /**
     * changes the city of the customer
     * @param customer
     *        customer 
     * @param cityNew
     *        new city
     * @throws SQLException
     *         Exception
     * @author Laura Eberhard
     */
    void changeCustomerCity(Customer customer, String cityNew); 
    
    /**
     * changes the postal code of the customer
     * @param customer
     *        customer 
     * @param postalCodeNew
     *        new postal code 
     * @throws SQLException
     *         Exception
     * @author Laura Eberhard
     */
    void changeCustomerPostalCode(Customer customer, int postalCodeNew); 
   
    /**
     * changes the country of the customer
     * @param customer
     *        customer 
     * @param countryNew
     *        country 
     * @throws SQLException
     *         Exception
     * @author Laura Eberhard
     */
    void changeCustomerCountry(Customer customer, String countryNew); 
  
    /**
     * changes the E-Mail of the customer
     * @param customer
     *        customer 
     * @param mailNew
     *        new mail address
     * @throws SQLException
     *         Exception 
     * @author Laura Eberhard            
     */
    void changeCustomerMail(Customer customer, String mailNew); 
 
    /**
     * changes the phone number of the customer
     * @param customer
     *        customer 
     * @param numberNew
     *        new phone number 
     * @throws SQLException
     *         Exception
     * @author Laura Eberhard
     */
    void changeCustomerPhone(Customer customer, String numberNew); 

    /**
     * @author Aykut
     * Ausgabe der KundenListe sortiert nach Sales, auf der Konsole
     */
    void sotiertNachSales();
    
    /**
     * @author Alexander
     * Gibt Alle Kundennamen(Nachname) alphabetisch sortiert aus mit allen Daten
     */
    void sotiertNachNamen();
    /**
     * 
     * @param id 
     *        Kundennummer
     * @return gibt Kundennummer zurück
     * @throws Exception 
     */
    Customer getCustomerById(int id) throws Exception;
    
    /**
     * delete the selected customer
     * @param customer
     *          customer
     * @author Laura Eberhard
     */
    void deleteCustomer(Customer customer); 
}
