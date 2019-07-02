package de.hrw.wi.persistence;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.sql.SQLException;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;

public class DBWriteInterfaceProductTest {

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
     * testet ob der Produktstatus erfolgreich geändert wurde
     * 
     * @throws SQLException
     *             SQLException
     * @author Laura Eberhard
     */
    @Test
    public void changeProductStatusTest() throws SQLException {

        db.changeProductStatus("8806085948587", "Renner");
        assertEquals("Renner", db.getProductStatus("8806085948587"));

        db.changeProductStatus("0885909560462", "Penner");
        assertEquals("Penner", db.getProductStatus("0885909560462"));

        db.changeProductStatus("0636926062442", "Penner");
        assertEquals("Penner", db.getProductStatus("0636926062442"));

        db.changeProductStatus("0799637096608", "Renner");
        assertEquals("Renner", db.getProductStatus("0799637096608"));
    }

    /**
     * testet ob eine Fehlermeldung erscheint, wenn bei einem nicht existierenden Produkt der Status
     * geändert werden soll.
     * 
     * @throws Exception
     *             Exception
     * @author Laura Eberhard
     */
    @Test
    public void changeNotExistingProductTest() throws Exception {

        try {

            db.changeProductStatus("1234567891234", "Penner");

        } catch (PersistenceException e) {

            assertEquals("Product status could not be changed.", e.getMessage());
        }
    }
}
