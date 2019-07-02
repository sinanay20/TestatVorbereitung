package de.hrw.wi.persistence;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;

import de.hrw.wi.persistence.dto.OrderDTO;

/**
 * 
 * @author Alexander Böhm
 *
 */
public class DBWriteInterfaceOrderTest {

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
     * @autor Sinan Ayten, Alexander Böhm, Laura Eberhard, Mateo Kevric
     */

    @Test
    public void addOrderTest() throws Exception {
        OrderDTO order = new OrderDTO(32, 1003, "0636926062442", "2019.05.05", 2, 200, 400);

        db.addOrder(order);

        assertEquals(21, db.getAllOrders().size());

        IDataSet dataSet = databaseTester.getConnection().createDataSet();
        Assertion.assertEquals(new FlatXmlDataSetBuilder().build(new File("addOrder.xml")),
                dataSet);
    }

}
