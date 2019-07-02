package de.hrw.wi.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.sql.SQLException;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;

import de.hrw.wi.business.Product;
import de.hrw.wi.persistence.DBReadInterfaceCustomer;
import de.hrw.wi.persistence.DBReadInterfaceOrder;
import de.hrw.wi.persistence.DBReadInterfaceProduct;
import de.hrw.wi.persistence.DBWriteInterfaceCustomer;
import de.hrw.wi.persistence.DBWriteInterfaceOrder;
import de.hrw.wi.persistence.DBWriteInterfaceProduct;
import de.hrw.wi.persistence.RealDatabase;

/**
 * 
 * @author Mateo
 *
 */
public class ProductManagementServiceIntegrationsTest {

    OrderManagementService orderManagement = new OrderManagementService();
    private final String dbURL = "jdbc:hsqldb:file:../wawi-db-layer/database/wawi_db";
    private final String user = "sa";
    private final String password = "";

    // private DBReadInterfaceOrder dbR;
    // private DBWriteInterfaceOrder dbW;

    private DBReadInterfaceCustomer dbRC;
    private DBWriteInterfaceCustomer dbWC;

    private DBReadInterfaceProduct dbRP;
    private DBWriteInterfaceProduct dbWP;

    IDatabaseTester databaseTester;
    DBReadInterfaceOrder dbRead;
    DBWriteInterfaceOrder dbWrite;
    OrderManagementServiceInterface omService;

    CustomerManagementServiceInterface cmService;

    ProductManagementServiceInterface pmService;

    // private OrderManagementServiceInterface osService;

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

        // dbR = new RealDatabase();
        // dbW = new RealDatabase();

        dbRC = new RealDatabase();
        dbWC = new RealDatabase();

        dbRP = new RealDatabase();
        dbWP = new RealDatabase();

        // osService = new OrderManagementService(dbR, dbW);

        cmService = new CustomerManagementService(dbRC, dbWC);

        pmService = new ProductManagementService(dbRP, dbWP);

    }

    /**
     * @throws SQLException
     *             Exception
     * @author Laura Eberhard, Mateo Kevric
     */
    @Test
    public void testEvaluateProductState() throws SQLException {

        Product product = pmService.getProductByArticleCode("8806085948587");

        pmService.evaluateProductState(product);

        assertEquals(false, product.getState().isNormal());
        assertEquals(true, product.getState().isPenner());
        assertEquals(false, product.getState().isRenner());
        assertEquals("Penner", dbRP.getProductStatus("8806085948587"));

        Product product2 = pmService.getProductByArticleCode("0885909560462");

        pmService.evaluateProductState(product2);

        assertEquals(true, product2.getState().isNormal());
        assertEquals(false, product2.getState().isPenner());
        assertEquals(false, product2.getState().isRenner());
        assertEquals("normal", dbRP.getProductStatus("0885909560462"));

        Product product3 = pmService.getProductByArticleCode("8806084893826");

        pmService.evaluateProductState(product3);

        assertEquals(false, product3.getState().isNormal());
        assertEquals(false, product3.getState().isRenner());
        assertEquals(true, product3.getState().isPenner());
        assertEquals("Penner", dbRP.getProductStatus("8806084893826"));

        Product product4 = pmService.getProductByArticleCode("4250366833286");

        pmService.evaluateProductState(product4);

        assertEquals(false, product4.getState().isNormal());
        assertEquals(true, product4.getState().isPenner());
        assertEquals(false, product4.getState().isRenner());
        assertEquals("Penner", dbRP.getProductStatus("4250366833286"));

        Product product5 = pmService.getProductByArticleCode("0799637096608");

        pmService.evaluateProductState(product5);

        assertEquals(true, product5.getState().isNormal());
        assertEquals(false, product5.getState().isRenner());
        assertEquals(false, product5.getState().isPenner());
        assertEquals("normal", dbRP.getProductStatus("0799637096608"));

        Product product6 = pmService.getProductByArticleCode("0636926062442");
        pmService.evaluateProductState(product6);

        assertEquals(true, product6.getState().isNormal());
        assertEquals(false, product6.getState().isPenner());
        assertEquals(false, product6.getState().isRenner());
        assertEquals("normal", dbRP.getProductStatus("0636926062442"));

    }
    /**
     * @author Mateo Kevric
     */
    @Test
    public void getProductByArticleCodeTest() {
        Product product = pmService.getProductByArticleCode("8806085948587");

        assertEquals("Samsung BD-H5500 3D Blu-ray-Player", product.getName());
        assertEquals(3, product.getSize());

        Product product2 = pmService.getProductByArticleCode("0885909560462");

        assertEquals("Apple TV MD199FD/A", product2.getName());
        assertEquals(2, product2.getSize());

        Product product3 = pmService.getProductByArticleCode("8806084893826");

        assertEquals("LG 40UB800V 101 cm (40 Zoll) LED-Backlight-Fernseher", product3.getName());
        assertEquals(8, product3.getSize());

        Product product4 = pmService.getProductByArticleCode("4250366833286");

        assertEquals("Gigaset C430 A Duo Dect-Schnurlostelefon mit Anrufbeantworter",
                product4.getName());
        assertEquals(2, product4.getSize());

        Product product5 = pmService.getProductByArticleCode("0799637096608");

        assertEquals("Ipow schwarz Selfie Stange", product5.getName());
        assertEquals(1, product5.getSize());

        Product product6 = pmService.getProductByArticleCode("0636926062442");

        assertEquals("TomTom Start 25 M Europe Traffic", product6.getName());
        assertEquals(2, product6.getSize());

    }

    /**
     * @author Aykut Topal
     */
    
    @Test
    public void getOrdersByProductTest() {
        Product product = pmService.getProductByArticleCode("0799637096608");
        pmService.getOrdersByProduct(product);
        Product product1 = pmService.getProductByArticleCode("0636926062442");
        pmService.getOrdersByProduct(product1);
        Product product2 = pmService.getProductByArticleCode("0885909560462");
        pmService.getOrdersByProduct(product2);
        Product product3 = pmService.getProductByArticleCode("8806085948587");
        pmService.getOrdersByProduct(product3);
    }
}
