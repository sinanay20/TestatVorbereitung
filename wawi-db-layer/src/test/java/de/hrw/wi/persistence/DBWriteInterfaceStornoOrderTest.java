package de.hrw.wi.persistence;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.sql.SQLException;

import org.dbunit.Assertion;
//import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
//import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;

import de.hrw.wi.persistence.dto.StornoOrderDTO;

/**
 * 
 * @author Sinan Ayten, Alexander Böhm
 *
 */

public class DBWriteInterfaceStornoOrderTest {

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
     * @author Sinan Ayten
     * @throws SQLException
     *             Wirft SQLException
     * @throws Exception
     *             Wirft Exception
     */
    @Test
    public void testAddStornoOrder() throws SQLException, Exception {
        StornoOrderDTO sOrderDTO = new StornoOrderDTO();

        sOrderDTO.setOrdernumber(30);
        sOrderDTO.setCustomerId(1005);
        sOrderDTO.setArticleCode("8806085948587");
        sOrderDTO.setOrderDate("2019.04.22");
        sOrderDTO.setAmount(3);
        sOrderDTO.setPrice(400);
        sOrderDTO.setPriceTotal(1200);
        sOrderDTO.setReason("Garantie-Fall");
        sOrderDTO.setStornoDate("2019.06.22");

        assertEquals(11, db.getAllStornoOrders().size());

        db.addStornoOrder(sOrderDTO);

        assertEquals(12, db.getAllStornoOrders().size());

        IDataSet dataSet = databaseTester.getConnection().createDataSet();
        Assertion.assertEquals(new FlatXmlDataSetBuilder().build(new File("addStornoOrder.xml")),
                dataSet);

    }

    /**
     * @author Alexander Böhm
     * @throws Exception
     *             Wirft Exception
     */
    @Test
    public void testDeleteOrderTest() throws Exception {

        // Vorher 20 Bestellungen:
        assertEquals(20, db.getAllOrders().size());
        // Vorher 11 Stornierungen
        assertEquals(11, db.getAllStornoOrders().size());

        db.cancelOrder(1, "Zu Späte Lieferung", "25.01.19");

        // Danach 19 Bestellungen:
        assertEquals(19, db.getAllOrders().size());
        // Danach 12 Stornierungen:
        assertEquals(12, db.getAllStornoOrders().size());

        IDataSet dataSet = databaseTester.getConnection().createDataSet();
        Assertion.assertEquals(new FlatXmlDataSetBuilder().build(new File("DeleteOrder.xml")),
                dataSet);
    }
}
