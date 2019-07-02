package de.hrw.wi.persistence;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;

import de.hrw.wi.persistence.dto.CustomerDTO;

/**
 * 
 * @author Alexander Böhm
 *
 */
public class DBReadInterfaceOrderTest {

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
    public void testGetOrderById() {

        assertEquals(1, db.getOrderById(1005).getOrdernumber());
        assertEquals("2019.05.13", db.getOrderById(1005).getOrderDate());
        assertEquals(2, db.getOrderById(1001).getAmount());

    }

    /**
     * @autor Alexander Bähm
     */
    @Test
    public void testGetOrderByNumber() {
        assertEquals(1005, db.getOrderByNumber(1).getCustomerId());
    }

    /**
     * @autor Sinan Ayten
     */
    @Test
    public void testGetAllOrders() {
        assertEquals(20, db.getAllOrders().size());

    }

    /**
     * test if all orders from one customer are shown
     * 
     * @author Aykut Topal
     */
    @Test
    public void getAllOrdersByCustomerTest() {

        CustomerDTO custDTO = new CustomerDTO();
        custDTO = db.getCustomerById(1004);

        assertEquals(4, db.getAllOrdersFromCustomer(custDTO).size());

    }

    /**
     * @autor Mateo Kevric
     */
    @Test
    public void getOrdersBySalesTest() {

        CustomerDTO custDTO = new CustomerDTO();
        custDTO = db.getCustomerById(1005);

        assertEquals(2, db.getOrdersBySales(custDTO).size());
    }

    /**
     * @autor Alexander Böhm
     */
    @Test
    public void testGetAllOrdersByDate() {
        //Anzahl der Bestellungen am 18.01.2019(Insgesamt 4):
        assertEquals(4, db.getAllOrdersByDate("2019.01.18").size());
        //Anzahl der Bestellungen am 13.05.2019(Insgesamt 1):
        assertEquals(1, db.getAllOrdersByDate("2019.05.13").size());
        //Test auf einen Tag 
    }

    @Test
    public void testGetAllOrderByDate2() {
        //Geht durch die Liste und greift auf die jeweiligen Indexe zu
        
        //Anzahl der Bestellungen:
        
        assertEquals(4, db.getAllOrdersByDate("2019.01.18").size());
        
        //Vergleich auf die Bestellnummer:
        
        assertEquals(4, db.getAllOrdersByDate("2019.01.18").
                get(0).getOrdernumber());
        assertEquals(5, db.getAllOrdersByDate("2019.01.18").
                get(1).getOrdernumber());
        assertEquals(7, db.getAllOrdersByDate("2019.01.18").
                get(2).getOrdernumber());
        assertEquals(8, db.getAllOrdersByDate("2019.01.18").
                get(3).getOrdernumber());
        
        //Vergleich auf Kundennummer:
        //Drei Bestellungen von einem Kunden
        assertEquals(1004, db.getAllOrdersByDate("2019.01.18").
                get(0).getCustomerId());
        assertEquals(1004, db.getAllOrdersByDate("2019.01.18").
                get(1).getCustomerId());
        assertEquals(1004, db.getAllOrdersByDate("2019.01.18").
                get(2).getCustomerId());
        
        //4. Bestellung von einem anderem Kunden
        assertEquals(1005, db.getAllOrdersByDate("2019.01.18").
                get(3).getCustomerId());
        
        
        
        //Gleiche Tests mit neuem Datum:
        
        //Anzahl der Bestellungen:
        assertEquals(1, db.getAllOrdersByDate("2019.05.18").size());
        
        //Bestellnummer:
        assertEquals(6, db.getAllOrdersByDate("2019.05.18").get(0).getOrdernumber());
        
        //Kundenummer:
        assertEquals(1004, db.getAllOrdersByDate("2019.05.18").get(0).getCustomerId());
        
        //Produkt:
        assertEquals("0799637096608", db.getAllOrdersByDate("2019.05.18").get(0).getArticleCode());
        
        //Datum:
        assertEquals("2019.05.18", db.getAllOrdersByDate("2019.05.18").get(0).getOrderDate());
        
        //Bestellmenge:
        
        assertEquals(7, db.getAllOrdersByDate("2019.05.18").get(0).getAmount());
        
        //Umsatz
        assertEquals(100.0, db.getAllOrdersByDate("2019.05.18").get(0).getPrice(), 0);
        
        //Gesamtumsatz:
        assertEquals(700.0, db.getAllOrdersByDate("2019.05.18").get(0).getPriceTotal(), 0);
        
    }

    /**
     * @autor Sinan Ayten
     */
    @Test
    public void testDateSortedOrdersByPrice() {
        assertEquals(4, db.dateSortedOrdersByPrice("2019.01.18").size());
    }
}
