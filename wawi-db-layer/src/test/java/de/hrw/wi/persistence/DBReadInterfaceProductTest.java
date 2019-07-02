package de.hrw.wi.persistence;

import static org.junit.Assert.assertEquals;


import java.io.File;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Mateo, Laura
 *
 */

public class DBReadInterfaceProductTest {

    private final String dbURL = "jdbc:hsqldb:file:../wawi-db-layer/database/wawi_db";
    private final String user = "sa";
    private final String password = "";

    RealDatabase db;
    IDatabaseTester databaseTester;

    @Before
    public void setUp() throws Exception {
        databaseTester = new JdbcDatabaseTester("org.hsqldb.jdbcDriver", dbURL, user, password);
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File("db_full_export.xml"));
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
        db = new RealDatabase();
    }

    /**
     * testet ob die Methode den richtigen Produktstatus zurückgibt
     * 
     * @author Laura Eberhard, Mateo Kevric
     */
    @Test
    public void getProductStatusTest() {
        assertEquals("normal", db.getProductStatus("8806085948587"));
        assertEquals("normal", db.getProductStatus("0885909560462"));
        assertEquals("normal", db.getProductStatus("0636926062442"));
        assertEquals("normal", db.getProductStatus("8806084893826"));
        assertEquals("normal", db.getProductStatus("4250366833286"));
        assertEquals("normal", db.getProductStatus("0799637096608"));
    }
    
    /**
     * @author Aykut Topal
     */
    
    @Test
    public void getOrdersByProduct() {
        assertEquals(7, db.getOrdersByProduct("0885909560462").size());
        assertEquals(1, db.getOrdersByProduct("8806085948587").size());
        assertEquals(1, db.getOrdersByProduct("8806085948587").get(0).getOrdernumber());
        assertEquals(2, db.getOrdersByProduct("0885909560462").get(0).getOrdernumber());
        assertEquals(1005, db.getOrdersByProduct("8806085948587").get(0).getCustomerId());
        assertEquals(1006, db.getOrdersByProduct("0799637096608").get(6).getCustomerId());
        assertEquals("2019.06.18", db.getOrdersByProduct("0799637096608").get(6).getOrderDate());
        assertEquals("2019.05.13", db.getOrdersByProduct("8806085948587").get(0).getOrderDate());
        assertEquals(2, db.getOrdersByProduct("0799637096608").get(6).getAmount());
        assertEquals(7, db.getOrdersByProduct("0799637096608").get(2).getAmount());
       
        
    }
    
    
}
