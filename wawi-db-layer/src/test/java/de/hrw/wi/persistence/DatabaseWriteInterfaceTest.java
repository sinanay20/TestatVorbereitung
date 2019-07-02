package de.hrw.wi.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.sql.SQLException;

import org.dbunit.Assertion;
//import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;

public class DatabaseWriteInterfaceTest {
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

    @Test
    public void testAddIllegalWarehouse() throws SQLException, Exception {
        try {
            db.addWarehouse(-1, 0, 0);
        } catch (PersistenceException e) {
            IDataSet actual = databaseTester.getConnection().createDataSet();
            Assertion.assertEquals(
                    new FlatXmlDataSetBuilder().build(new File("db_full_export.xml")), actual);
            return;
        }
        fail();
    }

    @Test
    public void testDeleteProduct() throws SQLException {
        int numberOfProducts = db.getAllProducts().size();
        db.deleteProduct("0885909560462");
        assertEquals(numberOfProducts - 1, db.getAllProducts().size());

        String s = db.getNameOfProduct("0885909560462");
        assertEquals(null, s);
    }

    @Test
    public void testAddWarehouse() throws SQLException {
        db.addWarehouse(2, 5, 20);
        assertEquals(3, db.getAllWarehouses().size());
    }

    @Test
    public void testAddProduct() throws SQLException {
        db.addProduct("4260001038167", "XORO HRS 8560 digitaler Satelliten-Receiver", 2, "normal");
        db.addProduct("8806085983083",
                "Samsung UE48H6470 121 cm (48 Zoll) 3D LED-Backlight-Fernseher", 9, "normal");
        assertEquals("XORO HRS 8560 digitaler Satelliten-Receiver",
                db.getNameOfProduct("4260001038167"));
        assertEquals("Samsung UE48H6470 121 cm (48 Zoll) 3D LED-Backlight-Fernseher",
                db.getNameOfProduct("8806085983083"));
    }

    @Test
    public void testSetStock() throws SQLException {
        try {
            // illegale Operation auf Datenbank ausfuehren
            db.setStock(1, 3, "4260001038167", 2);
        } catch (PersistenceException e) {
            // Erwartete Exception wurde geworfen, nun pruefen ob die
            // Fehlermeldung auch die erwartete ist und ob die Datenbank im
            // erwarteten richtigen Zustand ist.
            assertEquals("Product does not exist.", e.getMessage());
            db.setStock(1, 3, "8806085948587", 3);
            assertEquals(3, db.getAmountForBin(1, 3));
            assertEquals("8806085948587", db.getArticleCodeForBin(1, 3));
            return;
        }
        fail();
    }
    
    /**
     * testet ob der Produktstatus erfolgreich geändert wurde
     * @throws SQLException
     *          SQLException
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
     * testet ob eine Fehlermeldung erscheint,
     * wenn bei einem nicht existierenden Produkt 
     * der Status geändert werden soll. 
     * @throws Exception
     *          Exception
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