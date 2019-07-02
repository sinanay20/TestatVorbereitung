package de.hrw.wi.service;

import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import de.hrw.wi.business.Order;
import de.hrw.wi.persistence.DBReadInterfaceOrder;
import de.hrw.wi.persistence.DBWriteInterfaceOrder;
import de.hrw.wi.persistence.dto.OrderDTO;

/**
 * @author Mateo Kevric
 *
 */

public class OrderManagementServiceTest {

    private DBReadInterfaceOrder dbReadMock;
    private DBWriteInterfaceOrder dbWriteMock;
    OrderManagementService orderManagement = new OrderManagementService();
    private OrderManagementServiceInterface osService;
    
    @Before
    public void setup() {
        dbReadMock = Mockito.mock(DBReadInterfaceOrder.class);
        dbWriteMock = Mockito.mock(DBWriteInterfaceOrder.class);
        
        

        // Mockobjekte Bestellungen

        OrderDTO eins = new OrderDTO(11, 1104, "2345909560462", "2020.02.19", 1, 400, 600);
        OrderDTO zwei = new OrderDTO(12, 1204, "3425909560462", "2021.02.19", 2, 500, 700);
        OrderDTO drei = new OrderDTO(13, 1304, "5675909560462", "2022.02.19", 3, 600, 800);
        OrderDTO vier = new OrderDTO(14, 1404, "7695909560462", "2023.02.19", 4, 700, 900);

        when(dbReadMock.getOrderByNumber(11)).thenReturn(eins);
        when(dbReadMock.getOrderByNumber(12)).thenReturn(zwei);
        when(dbReadMock.getOrderByNumber(13)).thenReturn(drei);
        when(dbReadMock.getOrderByNumber(14)).thenReturn(vier);
        
        osService = new OrderManagementService(dbReadMock, dbWriteMock);
    }

    @Test
    public void getOrderTest() {
        Order o = osService.getOrderByNumber(11);
        assertEquals(11, o.getOrdernumber());
    }
}