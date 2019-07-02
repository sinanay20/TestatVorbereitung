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
import de.hrw.wi.business.StornoOrder;
import de.hrw.wi.persistence.DBReadInterfaceCustomer;
import de.hrw.wi.persistence.DBReadInterfaceOrder;
import de.hrw.wi.persistence.DBReadInterfaceStornoOrder;
import de.hrw.wi.persistence.DBWriteInterfaceCustomer;
import de.hrw.wi.persistence.DBWriteInterfaceOrder;
import de.hrw.wi.persistence.DBWriteInterfaceStornoOrder;
import de.hrw.wi.persistence.RealDatabase;

/**
 * @author Alexander Böhm
 *
 */

public class StornoOrderManagementServiceIntegrationTest {

    StornoOrderManagementService stornoOrderManagement = new StornoOrderManagementService();
    private final String dbURL = "jdbc:hsqldb:file:../wawi-db-layer/database/wawi_db";
    private final String user = "sa";
    private final String password = "";

    private DBWriteInterfaceStornoOrder dbWriteStorno;
    private DBReadInterfaceStornoOrder dbReadStorno;

    private DBWriteInterfaceOrder dbWO;
    private DBReadInterfaceOrder dbRO;
    
    private DBWriteInterfaceCustomer dbWriteCust;
    private DBReadInterfaceCustomer dbReadCust;  

    IDatabaseTester databaseTester;

    StornoOrderManagementServiceInterface stornoService;

    private OrderManagementServiceInterface omService;
    
    CustomerManagementServiceInterface cmService;


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

        dbReadStorno = new RealDatabase();
        dbWriteStorno = new RealDatabase();
        
        dbReadCust = new RealDatabase(); 
        dbWriteCust = new RealDatabase(); 

        dbRO = new RealDatabase();
        dbWO = new RealDatabase();

        stornoService = new StornoOrderManagementService(dbReadStorno, dbWriteStorno);

        omService = new OrderManagementService(dbRO, dbWO);
        
        cmService = new CustomerManagementService(dbReadCust, dbWriteCust);
        
    }

    /**
     * gibt eine Liste mit den Bestellungen eines Kunden sortiert nach Umsatz
     * 
     * @throws Exception
     * @author Mateo Kevric
     */
    @Test
    public void stornoOrder() throws Exception {

        Order o = omService.getOrderByNumber(3);

        String reason = "Zu Spät geliefert";
        String stornoDate = "10.02.19";

        stornoService.cancelOrder(o, reason, stornoDate);

        StornoOrder stornOrd = stornoService.getStornoOrderByNumber(o.getOrdernumber());

        assertEquals(3, stornOrd.getOrdernumber());
        assertEquals(1003, stornOrd.getCustomerId());
        assertEquals("0636926062442", stornOrd.getArticleCode());
        assertEquals("2018.11.22", stornOrd.getOrderDate());
        assertEquals(1, stornOrd.getAmount());
        assertEquals(400, stornOrd.getPrice(), 0);
        assertEquals(400, stornOrd.getPriceTotal(), 0);
        assertEquals(reason, stornOrd.getReason());
        assertEquals(stornoDate, stornOrd.getStornoDate());

    }

    @Test
    public void testGetAllStornoOrdersByCustomer() throws Exception {

        System.out.println("Stornierte Bestellungen des Kunden '1005':");
        System.out.println("---------------------------------------------------------------");

        Customer customer = cmService.getCustomerById(1005);
        stornoService.getStornoOrderByCustomer2(customer);
        System.out.println("---------------------------------------------------------------");
        System.out.println("\n");

        Customer customer2 = cmService.getCustomerById(1003);

        System.out.println("Stornierte Bestellungen des Kunden '1003':");
        System.out.println("---------------------------------------------------------------");
        stornoService.getStornoOrderByCustomer2(customer2);
        System.out.println("---------------------------------------------------------------");
        System.out.println("\n");

    }

}