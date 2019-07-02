package de.hrw.wi.persistence;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;
import de.hrw.wi.persistence.dto.AddressDTO;
import de.hrw.wi.persistence.dto.CustomerDTO;

/**
 * 
 * @author Sinan Ayten, Laura Eberhard
 *
 */

public class DBWriteInterfaceCustomerTest {

    private final String dbURL = "jdbc:hsqldb:file:../wawi-db-layer/database/wawi_db";
    private final String user = "sa";
    private final String password = "";

    RealDatabase db;
    IDatabaseTester databaseTester;

    @Before
    public void setUp() throws Exception {
        databaseTester = new JdbcDatabaseTester("org.hsqldb.jdbcDriver", dbURL, user, password);
        databaseTester
                .setDataSet(new FlatXmlDataSetBuilder().build(new File("db_full_export.xml")));
        databaseTester.onSetup();
        db = new RealDatabase();
    }

    /**
     * @autor Sinan Ayten
     */
    @Test
    public void testAddCustomer() throws SQLException, Exception {

        AddressDTO address = new AddressDTO("Plochingerstrasse", 8, "Köln", 55609, "Germany",
                "schweizer@info.de", "07088/23488");
        CustomerDTO custDTO = new CustomerDTO(1007, "Gerhard", "Schweizer", address, 1979, "M",
                10000);

        db.addCustomer(custDTO);

        assertEquals(7, db.getAllCustomerIds().size());

        IDataSet dataSet = databaseTester.getConnection().createDataSet();
        Assertion.assertEquals(new FlatXmlDataSetBuilder().build(new File("addCustomer.xml")),
                dataSet);
    }

    @Test
    public void testCheckBirthYear() throws SQLException {
        AddressDTO adress = new AddressDTO("Musterstraße", 15, "Baienfurt", 78623, "Deutschland",
                "muster@email.de", "01762936282");
        CustomerDTO custDTO = new CustomerDTO(1234, "Ebeng", "Ami", adress, 999999, "M", 800);

        try {

            db.addCustomer(custDTO);
        } catch (PersistenceException e) {

            assertEquals("Customer could not be added.", e.getMessage());
        }

    }

    /**
     * @throws SQLException
     *             SQLException
     * @throws Exception
     *             Exception
     * @author Laura Eberhard
     */
    @Test
    public void testChangeCustomer1006() throws SQLException, Exception {

        // change last name, country, eMail, phone number, postal code,
        // house number, city and street of the customer with the ID "1006"

        db.changeCustomerLastName(1006, "Eberhard");
        db.changeCustomerCountry(1006, "Deutschland");
        db.changeCustomerMail(1006, "eberla@gmail.com");
        db.changeCustomerPhone(1006, "0173967315");
        db.changeCustomerPostalCode(1006, 88333);
        db.changeCustomerHouseNr(1006, 3);
        db.changeCustomerCity(1006, "Weingarten");
        db.changeCustomerStreet(1006, "Hauptstraße");

        IDataSet dataSet = databaseTester.getConnection().createDataSet();
        Assertion.assertEquals(
                new FlatXmlDataSetBuilder().build(new File("changeCustomer1006.xml")), dataSet);

    }

    /**
     * @throws SQLException
     *             SQLException
     * @throws Exception
     *             Exception
     * @author Laura Eberhard
     */
    @Test
    public void testChangeCustomer1002() throws SQLException, Exception {

        // change last name, country, eMail, phone number, postal code,
        // house number, city and street of the customer with the ID "1002"

        db.changeCustomerLastName(1002, "Meyer");
        db.changeCustomerCountry(1002, "Schweiz");
        db.changeCustomerMail(1002, "alexme@gmail.com");
        db.changeCustomerPhone(1002, "0176123111");
        db.changeCustomerPostalCode(1002, 65734);
        db.changeCustomerHouseNr(1002, 123);
        db.changeCustomerCity(1002, "Luzern");
        db.changeCustomerStreet(1002, "Feldstraße");

        IDataSet dataSet = databaseTester.getConnection().createDataSet();
        Assertion.assertEquals(
                new FlatXmlDataSetBuilder().build(new File("changeCustomer1002.xml")), dataSet);
    }

    /**
     * tests if there is a Exception if someone tries to change the street of a deleted customer
     * 
     * @throws SQLException
     *             Exception
     * @author Laura Eberhard
     */
    @Test
    public void changeStreetDeletedCustomer() throws SQLException {

        int customerID = 1001;

        // customer to delete is the customer with the ID 1001
        CustomerDTO customerDTO = db.getCustomerById(customerID);

        // delete the customer 1001
        db.deleteCustomer(customerDTO);

        try {

            // try to change the street of the deleted customer
            db.changeCustomerStreet(customerID, "Hauptstraße");

        } catch (PersistenceException e) {

            // test if the Exception is the right one
            assertEquals("Customer is deleted.", e.getMessage());
        }
    }

    /**
     * tests if there is a Exception if someone tries to change the postal code of a deleted
     * customer
     * 
     * @throws SQLException
     *             Exception
     * @author Laura Eberhard
     */
    @Test
    public void changePostalCodeDeletedCustomer() throws SQLException {

        int customerID = 1001;

        // customer to delete is the customer with the ID 1001
        CustomerDTO customerDTO = db.getCustomerById(customerID);

        // delete the customer 1001
        db.deleteCustomer(customerDTO);

        try {

            // try to change the postal code of the deleted customer
            db.changeCustomerPostalCode(customerID, 12345);

        } catch (PersistenceException e) {

            // test if the Exception is the right one
            assertEquals("Customer is deleted.", e.getMessage());
        }
    }

    /**
     * tests if there is a Exception if someone tries to change the eMail of a deleted customer
     * 
     * @throws SQLException
     *             Exception
     * @author Laura Eberhard
     */
    @Test
    public void changeMailDeletedCustomer() throws SQLException {

        int customerID = 1001;

        // customer to delete is the customer with the ID 1001
        CustomerDTO customerDTO = db.getCustomerById(customerID);

        // delete the customer 1001
        db.deleteCustomer(customerDTO);

        try {

            // try to change the eMail of the deleted customer
            db.changeCustomerMail(customerID, "test@test.de");

        } catch (PersistenceException e) {

            // test if the Exception is the right one
            assertEquals("Customer is deleted.", e.getMessage());
        }
    }

    /**
     * tests if there is a Exception if someone tries to change the last name of a deleted customer
     * 
     * @throws SQLException
     *             Exception
     * @author Laura Eberhard
     */
    @Test
    public void changeLastNameDeletedCustomer() throws SQLException {

        int customerID = 1001;

        // customer to delete is the customer with the ID 1001
        CustomerDTO customerDTO = db.getCustomerById(customerID);

        // delete the customer 1001
        db.deleteCustomer(customerDTO);

        try {

            // try to change the last name of the deleted customer
            db.changeCustomerLastName(customerID, "Fritz");

        } catch (PersistenceException e) {

            // test if the Exception is the right one
            assertEquals("Customer is deleted.", e.getMessage());
        }
    }

    /**
     * tests if there is a Exception if someone tries to change the house number of a deleted
     * customer
     * 
     * @throws SQLException
     *             Exception
     * @author Laura Eberhard
     */
    @Test
    public void changeHouseNrDeletedCustomer() throws SQLException {

        int customerID = 1001;

        // customer to delete is the customer with the ID 1001
        CustomerDTO customerDTO = db.getCustomerById(customerID);

        // delete the customer 1001
        db.deleteCustomer(customerDTO);

        try {

            // try to change the house number of the deleted customer
            db.changeCustomerHouseNr(customerID, 234);

        } catch (PersistenceException e) {

            // test if the Exception is the right one
            assertEquals("Customer is deleted.", e.getMessage());
        }
    }

    /**
     * tests if there is a Exception if someone tries to change the country of a deleted customer
     * 
     * @throws SQLException
     *             Exception
     * @author Laura Eberhard
     */
    @Test
    public void changeCountryDeletedCustomer() throws SQLException {

        int customerID = 1001;

        // customer to delete is the customer with the ID 1001
        CustomerDTO customerDTO = db.getCustomerById(customerID);

        // delete the customer 1001
        db.deleteCustomer(customerDTO);

        try {

            // try to change the country of the deleted customer
            db.changeCustomerCountry(customerID, "Österreich");

        } catch (PersistenceException e) {

            // test if the Exception is the right one
            assertEquals("Customer is deleted.", e.getMessage());
        }
    }

    /**
     * tests if there is a Exception if someone tries to change the city of a deleted customer
     * 
     * @throws SQLException
     *             Exception
     * @author Laura Eberhard
     */
    @Test
    public void changeCityDeletedCustomer() throws SQLException {

        int customerID = 1001;

        // customer to delete is the customer with the ID 1001
        CustomerDTO customerDTO = db.getCustomerById(customerID);

        // delete the customer 1001
        db.deleteCustomer(customerDTO);

        try {

            // try to change the city of the deleted customer
            db.changeCustomerCity(customerID, "Friedrichshafen");

        } catch (PersistenceException e) {

            // test if the Exception is the right one
            assertEquals("Customer is deleted.", e.getMessage());
        }
    }

    /**
     * tests if there is a Exception if someone tries to change the phone number of a deleted
     * customer
     * 
     * @throws SQLException
     *             Exception
     * @author Laura Eberhard
     */
    @Test
    public void changeNumberDeletedCustomer() throws SQLException {

        int customerID = 1001;

        // customer to delete is the customer with the ID 1001
        CustomerDTO customerDTO = db.getCustomerById(customerID);

        // delete the customer 1001
        db.deleteCustomer(customerDTO);

        try {

            // try to change the phone number of the deleted customer
            db.changeCustomerPhone(customerID, "0123456789");

        } catch (PersistenceException e) {

            // test if the Exception is the right one
            assertEquals("Customer is deleted.", e.getMessage());
        }
    }

    /**
     * tests the method deleteCustomer
     * 
     * @author Laura Eberhard
     * @throws Exception
     *             Exception
     */
    @Test
    public void deleteCustomerTest1001() throws Exception {

        // customer to delete is the customer with the ID 1001
        CustomerDTO customerDTO = db.getCustomerById(1001);

        // delete the customer 1001
        db.deleteCustomer(customerDTO);

        // compare the actual database with the excepted database
        IDataSet dataSet = databaseTester.getConnection().createDataSet();
        Assertion.assertEquals(
                new FlatXmlDataSetBuilder().build(new File("deleteCustomer1001.xml")), dataSet);
    }

    /**
     * tests the method deleteCustomer
     * 
     * @throws Exception
     *             Exception
     * @author Laura Eberhard
     */
    @Test
    public void deleteCustomerTest1002() throws Exception {

        // customer to delete is the customer with the ID 1002
        CustomerDTO customerDTO = db.getCustomerById(1002);

        // delete the customer 1001
        db.deleteCustomer(customerDTO);

        // compare the actual database with the excepted database
        IDataSet dataSet = databaseTester.getConnection().createDataSet();
        Assertion.assertEquals(
                new FlatXmlDataSetBuilder().build(new File("deleteCustomer1002.xml")), dataSet);
    }

    /**
     * test if the method recognize not existing customer IDs
     * 
     * @throws Exception
     *             Exception
     * @author Laura Eberhard
     */
    @Test
    public void deleteCustomerFalseIdTest() throws Exception {

        // create a customer with an ID outside the range
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setCustomerID(2001);

        try {

            // delete the Customer with the false ID
            db.deleteCustomer(customerDTO);

        } catch (PersistenceException e) {

            // test if the Exception is the right one
            assertEquals("Customer doesn't exist", e.getMessage());
        }

    }
}
