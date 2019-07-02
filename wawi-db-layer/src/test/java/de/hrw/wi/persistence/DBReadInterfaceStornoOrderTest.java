package de.hrw.wi.persistence;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;

public class DBReadInterfaceStornoOrderTest {

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

    @Test
    public void testGetStornoOrderByNumber() {
        assertEquals("DEFEKT", db.getStornoOrderByNumber(19).getReason());
    }

    @Test
    public void testGetAllStornoOrders() {
        assertEquals(11, db.getAllStornoOrders().size());

    }
    
    /**
     * @author Dennis
     *
     */
    @Test
    public void testGetStornoOrdersByCustomerId() {

        // Vergleich auf Anzahl der stornierten Bestellungen des Kunden
        assertEquals(1, db.getStornoOrdersByCustomerId(1003).size());

        // Vergleich auf die OrderNr der stornierten Bestellunge des Kunden
        assertEquals(21, db.getStornoOrdersByCustomerId(1003).get(0).getOrdernumber());

        // Vergleich auf die Kundennummer
        assertEquals(1003, db.getStornoOrdersByCustomerId(1003).get(0).getCustomerId());

        // Vergleich auf produktcode des in der stornierten Bestellung enthaltenen Produkts
        assertEquals("0636926062442", db.getStornoOrdersByCustomerId(1003).get(0).getArticleCode());

        // Vergleich auf das Datum der Stornierung
        assertEquals("2018.05.28", db.getStornoOrdersByCustomerId(1003).get(0).getStornoDate());

        // Vergleich auf den Grund der Stornierung
        assertEquals("Falscher Artikel", db.getStornoOrdersByCustomerId(1003).get(0).getReason());

        // Vergleich auf den Gesamtpreis
        assertEquals(400.0, db.getStornoOrdersByCustomerId(1003).get(0).getPrice(), 0.00);

    }
    

}
