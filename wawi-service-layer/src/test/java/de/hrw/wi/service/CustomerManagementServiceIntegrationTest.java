package de.hrw.wi.service;

import java.io.File;
import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;

import de.hrw.wi.business.Address;
import de.hrw.wi.business.Customer;
import de.hrw.wi.persistence.DBReadInterfaceCustomer;
import de.hrw.wi.persistence.DBWriteInterfaceCustomer;
import de.hrw.wi.persistence.RealDatabase;

/**
 * @author Laura Eberhard, Alexander Böhm, Aykut Topal, Mateo Kevric
 *
 */

public class CustomerManagementServiceIntegrationTest {

    CustomerManagementService customerManagement = new CustomerManagementService();
    private final String dbURL = "jdbc:hsqldb:file:../wawi-db-layer/database/wawi_db";
    private final String user = "sa";
    private final String password = "";
    
    private DBReadInterfaceCustomer dbR;
    private DBWriteInterfaceCustomer dbW;
    
    IDatabaseTester databaseTester;
    DBReadInterfaceCustomer dbRead; 
    DBWriteInterfaceCustomer dbWrite; 
    CustomerManagementServiceInterface cmService; 
    
    private CustomerManagementServiceInterface csService;
    
    /**
     * @throws Exception
     *          Exception
     * @author Laura Eberhard
     */
    @Before
    public void setUp() throws Exception {
        
        databaseTester = new JdbcDatabaseTester("org.hsqldb.jdbcDriver", dbURL, user, password);
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File("db_full_export.xml"));
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
        
        dbRead = new RealDatabase(); 
        dbWrite = new RealDatabase(); 
        
        
        dbR = new RealDatabase();
        dbW = new RealDatabase();
        
        csService = new CustomerManagementService(dbR, dbW);

        
    }
    
    @Test
    public void test2() {
        
        customerManagement.sotiertNachSales();
    }
    
    @Test
    public void kundenNameSortiert() {
        System.out.println("---------------------------------------------");
        customerManagement.sotiertNachNamen();
    }
    /**
     * @author Laura Eberhard
     * @throws Exception 
     *          Exception
     */
    @Test 
    public void changeCustomerLastNameTest() throws Exception {
        
        Customer customer = csService.getCustomerById(1001); 
        
        //change the last name of the customer to "Meyer"
        csService.changeCustomerLastName(customer, "Meyer");
        
        //test if the last name was changed successfully 
        IDataSet dataSet = databaseTester.getConnection().createDataSet();
        Assertion.assertEquals(new FlatXmlDataSetBuilder().build(new File(
                "changeCustomer1001LastName.xml")), dataSet); 
        
    }
    
    /**
     * @author Laura Eberhard
     * @throws Exception 
     *          Exception
     */
    @Test 
    public void changeCustomerCityTest() throws Exception {
        
        Customer customer = csService.getCustomerById(1001); 
        
      //change the city of the customer to "Stuttgart"
        csService.changeCustomerCity(customer, "Stuttgart");
        
      //test if the city was changed successfully 
        IDataSet dataSet = databaseTester.getConnection().createDataSet();
        Assertion.assertEquals(new FlatXmlDataSetBuilder().build(new File(
                "changeCustomer1001City.xml")), dataSet); 
    }
    
    /**
     * @author Laura Eberhard
     * @throws Exception 
     */
    @Test 
    public void changeCustomerCountryTest() throws Exception {
        
        Customer customer = csService.getCustomerById(1001); 
        
        //change the country of the customer to "England"
        csService.changeCustomerCountry(customer, "England");
        
      //test if the country was changed successfully 
        IDataSet dataSet = databaseTester.getConnection().createDataSet();
        Assertion.assertEquals(new FlatXmlDataSetBuilder().build(new File(
                "changeCustomer1001Country.xml")), dataSet); 
    }
    
    /**
     * @author Laura Eberhard
     * @throws Exception 
     *          Exception
     */
    @Test
    public void changeCustomerHouseNrTest() throws Exception {
        
        Customer customer = csService.getCustomerById(1001); 
        
        //change the house number of the customer to "12345"
        csService.changeCustomerHouseNr(customer, 12345);
        
      //test if the house number was changed successfully 
        IDataSet dataSet = databaseTester.getConnection().createDataSet();
        Assertion.assertEquals(new FlatXmlDataSetBuilder().build(new File(
                "changeCustomer1001HouseNr.xml")), dataSet); 
    }
    
    /**
     * @author Laura Eberhard
     * @throws Exception 
     *          Exception
     */
    @Test 
    public void changeCustomerMailTest() throws Exception {
         
        Customer customer = csService.getCustomerById(1001); 
        
        //change the e-mail address of the customer to "test@test.com"
        csService.changeCustomerMail(customer, "test@test.com");
        
        //test if the e-mail address was changed successfully 
        IDataSet dataSet = databaseTester.getConnection().createDataSet();
        Assertion.assertEquals(new FlatXmlDataSetBuilder().build(new File(
                "changeCustomer1001Mail.xml")), dataSet);
    }
    
    /**
     * @author Laura Eberhard
     * @throws Exception 
     *          Exception
     */
    @Test 
    public void changeCustomerNumberTest() throws Exception {
        

        Customer customer = csService.getCustomerById(1001); 
        
        //change the phone number of the customer to "0123456789"
        csService.changeCustomerPhone(customer, "0123456789");
        
        //test if the phone number was changed successfully 
        IDataSet dataSet = databaseTester.getConnection().createDataSet();
        Assertion.assertEquals(new FlatXmlDataSetBuilder().build(new File(
                "changeCustomer1001Number.xml")), dataSet);
    }
    
    /**
     * @author Laura Eberhard
     * @throws Exception 
     *          Exception
     */
    @Test 
    public void changeCustomerPostalCodeTest()throws Exception {
        
        Customer customer = csService.getCustomerById(1001); 
        
        //change the postal code of the customer to "45678"
        csService.changeCustomerPostalCode(customer, 45678);
        
        //test if the postal code was changed successfully    
        IDataSet dataSet = databaseTester.getConnection().createDataSet();
        Assertion.assertEquals(new FlatXmlDataSetBuilder().build(new File(
                "changeCustomer1001PostalCode.xml")), dataSet);
    }
    
    /**
     * @author Laura Eberhard
     * @throws Exception 
     *          Exception
     */
    @Test
    public void changeCustomerStreetTest() throws Exception {
        
        Customer customer = csService.getCustomerById(1001); 
        
        //change the street of the customer to "Einhornstraße"
        csService.changeCustomerStreet(customer, "Einhornstraße");
        
        //test if the street was changed successfully
        IDataSet dataSet = databaseTester.getConnection().createDataSet();
        Assertion.assertEquals(new FlatXmlDataSetBuilder().build(new File(
                "changeCustomer1001Street.xml")), dataSet);
    }
/**
 * @author Mateo
 * @throws Exception wirft Exception
 */

    @Test
    public void addCustomerTest() throws Exception {
        Address a = new Address("Benzstraße", 1, "Benztown", 11111, "Daimlerika", "01792983746",
                "daimler@benz.de");
        Customer c = new Customer(1111, "Karl", "Benz", a, 10, "M", 1750);
        csService.addCustomer(c);
        IDataSet dataSet = databaseTester.getConnection().createDataSet();
        Assertion.assertEquals(new FlatXmlDataSetBuilder().build(new File("addCustomer2.xml")),
                dataSet);
    }
    
    /**
     * tests the method deleteCustomer 
     * @throws SQLException
     *          Exception
     * @author Laura Eberhard
     */
    @Test 
    public void deleteCustomerTest1001() throws Exception {
        
        Customer customer = csService.getCustomerById(1001);
        
        // delete the customer with the ID 1001
        csService.deleteCustomer(customer);
        
        // test if the customer is deleted successfully
        IDataSet dataSet = databaseTester.getConnection().createDataSet();
        Assertion.assertEquals(new FlatXmlDataSetBuilder().build(new File(
                "deleteCustomer1001.xml")), dataSet);
    }
    
    /**
     * tests the method deleteCustomer 
     * @throws SQLException
     *          Exception
     * @author Laura Eberhard
     */
    @Test 
    public void deleteCustomerTest1002() throws Exception {
        
        Customer customer = csService.getCustomerById(1002);
        
        // delete the customer with the ID 1002
        csService.deleteCustomer(customer);
        
        // test if the customer is deleted successfully
        IDataSet dataSet = databaseTester.getConnection().createDataSet();
        Assertion.assertEquals(new FlatXmlDataSetBuilder().build(new File(
                "deleteCustomer1002.xml")), dataSet);
    }
}
