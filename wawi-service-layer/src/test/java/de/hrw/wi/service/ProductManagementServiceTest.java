package de.hrw.wi.service;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.hrw.wi.business.Product;
import de.hrw.wi.persistence.DBReadInterfaceProduct;
import de.hrw.wi.persistence.DBWriteInterfaceProduct;
import de.hrw.wi.persistence.dto.ProductDTO;

/**
 * 
 * @author Mateo Kevric
 *
 */
public class ProductManagementServiceTest {
    private DBReadInterfaceProduct dbReadMock;
    private DBWriteInterfaceProduct dbWriteMock;
    // ProductManagementService productManagement = new ProductManagementService();
    private ProductManagementServiceInterface pmService;

    @Before
    public void setup() {
        dbReadMock = Mockito.mock(DBReadInterfaceProduct.class);
        dbWriteMock = Mockito.mock(DBWriteInterfaceProduct.class);

        ProductDTO pEins = new ProductDTO("2345909560490", "Apple iSWEP", 3, "normal");
        ProductDTO pZwei = new ProductDTO("7695909560462", "Samsung SWEP9", 5, "normal");

        when(dbReadMock.getProductByArticleCode("2345909560490")).thenReturn(pEins);
        when(dbReadMock.getProductByArticleCode("7695909560462")).thenReturn(pZwei);

        pmService = new ProductManagementService(dbReadMock, dbWriteMock);

    }

    /**
     * Testet Name und Größe eines Produkts
     * 
     * @author Mateo Kevric
     */
    @Test
    public void getProductByArticleCodeTest() {
        Product product = pmService.getProductByArticleCode("2345909560490");
        Product product2 = pmService.getProductByArticleCode("7695909560462");

        assertEquals("Apple iSWEP", product.getName());
        assertEquals("Samsung SWEP9", product2.getName());
        assertEquals(3, product.getSize());
        assertEquals(5, product2.getSize());
    }

}
