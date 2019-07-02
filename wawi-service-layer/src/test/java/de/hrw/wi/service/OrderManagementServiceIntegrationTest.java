package de.hrw.wi.service;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;

import de.hrw.wi.business.Customer;
import de.hrw.wi.business.Order;
import de.hrw.wi.persistence.DBReadInterfaceCustomer;
import de.hrw.wi.persistence.DBReadInterfaceOrder;
import de.hrw.wi.persistence.DBWriteInterfaceCustomer;
import de.hrw.wi.persistence.DBWriteInterfaceOrder;
import de.hrw.wi.persistence.RealDatabase;

/**
 * @author Mateo Kevric
 *
 */

public class OrderManagementServiceIntegrationTest {

    OrderManagementService orderManagement = new OrderManagementService();
    private final String dbURL = "jdbc:hsqldb:file:../wawi-db-layer/database/wawi_db";
    private final String user = "sa";
    private final String password = "";

    private DBReadInterfaceOrder dbR;
    private DBWriteInterfaceOrder dbW;
    
    private DBReadInterfaceCustomer dbRC; 
    private DBWriteInterfaceCustomer dbWC; 

    IDatabaseTester databaseTester;
    DBReadInterfaceOrder dbRead;
    DBWriteInterfaceOrder dbWrite;
    OrderManagementServiceInterface omService;
    
    CustomerManagementServiceInterface cmService; 

    private OrderManagementServiceInterface osService;

    /**
     * @throws Exception
     *             Exception
     * @author Mateo Kevric
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
        
        dbRC = new RealDatabase();
        dbWC = new RealDatabase();

        osService = new OrderManagementService(dbR, dbW);
        
        cmService = new CustomerManagementService(dbRC, dbWC); 

    }

    /**
     * @author Mateo
     * @throws Exception
     *             wirft Exception
     */
    
    @Test
    public void addOrderTest() throws Exception {
        Order o = new Order(10, 1004, "0885909560462", "2020.01.18", 9, 500, 800);
        osService.addOrder(o);
        
        assertEquals(21, dbR.getAllOrders().size());

    }
    
    
    /**
     * gibt eine Liste mit den Bestellungen eines Kunden sortiert nach Datum aus
     * @throws Exception
     *          Exception
     * @author Aykut Topal
     */
    @Test
    public void sortOrderByDateTest() throws Exception {
        
        Customer customer = cmService.getCustomerById(1004); 
        
        osService.ordersByCustomer(customer); 

    }
    
    /**
     * gibt eine Liste mit den Bestellungen eines Kunden sortiert nach Umsatz
     * @throws Exception
     * @author Mateo Kevric
     */
    @Test 
    public void getOrdersBySalesTest() throws Exception {
        
        Customer customer = cmService.getCustomerById(1005);
        
        System.out.println("---------------------------------------------------------------");
        
        osService.getOrdersBySales(customer);
        
        System.out.println("---------------------------------------------------------------");
    }
            
    @Test
    public void dateOrdersByPrice() {
        System.out.println("Anzeige von Bestellungen vom 2019.01.18 sortiert nach Gesamtumsatz: ");
        System.out.println("---------------------------------------------------------------");
        osService.dateOrdersByPrice("2019.01.18");
    }
    @Test
    public void printOrdersByDate() {
        System.out.println("Anzeige von Bestellungen am 2019.01.18: ");
        System.out.println("---------------------------------------------------------------");
        osService.printOrdersByDate("2019.01.18");
    }
    
    @Test
    public void printOrdersByDate2() {
        System.out.println("Anzeige von Bestellungen am 2019.04.07: ");
        System.out.println("---------------------------------------------------------------");
        osService.printOrdersByDate("2019.04.07");
    }
    
    
}