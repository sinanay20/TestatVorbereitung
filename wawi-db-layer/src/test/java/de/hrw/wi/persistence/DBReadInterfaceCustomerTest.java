package de.hrw.wi.persistence;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Sinan Ayten
 *
 */

public class DBReadInterfaceCustomerTest {

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
    public void testGetCustomerByID() {

        assertEquals("Sinan", db.getCustomerById(1001).getFirstName());
        assertEquals("Ayten", db.getCustomerById(1001).getLastName());
        assertEquals("ebengami@gmx.net", db.getCustomerById(1001).getAddress().geteMail());
        assertEquals("01767262222", db.getCustomerById(1001).getAddress().getPhoneNumber());
        assertEquals(72622, db.getCustomerById(1001).getAddress().getPostalCode());

    }

    /**
     * @autor Sinan Ayten
     */
    @Test
    public void testGetAllCustomerIds() {

        assertEquals(6, db.getAllCustomerIds().size());

    }

    /**
     * @autor Aykut Topal
     */
    @Test
    public void testGetSortedCustomer() {
        assertEquals(1002, db.getSortedCustomerBySales().get(0).getCustomerID());
        assertEquals(1001, db.getSortedCustomerBySales().get(1).getCustomerID());
        assertEquals(1003, db.getSortedCustomerBySales().get(2).getCustomerID());
        assertEquals(1005, db.getSortedCustomerBySales().get(3).getCustomerID());
        assertEquals(1006, db.getSortedCustomerBySales().get(4).getCustomerID());
        assertEquals(1004, db.getSortedCustomerBySales().get(5).getCustomerID());

    }
    
    /**
     * @autor Aykut Topal
     */
    @Test
    public void testGetSortedCustomerNames() {
        assertEquals("Ayten", db.kundenNamenSortiert().get(0).getLastName());
        assertEquals("Böhm", db.kundenNamenSortiert().get(1).getLastName());
        assertEquals("Kevric", db.kundenNamenSortiert().get(2).getLastName());
        assertEquals("Mond", db.kundenNamenSortiert().get(3).getLastName());
        assertEquals("Rammstein", db.kundenNamenSortiert().get(4).getLastName());
        assertEquals("Topal", db.kundenNamenSortiert().get(5).getLastName());

    }
}
