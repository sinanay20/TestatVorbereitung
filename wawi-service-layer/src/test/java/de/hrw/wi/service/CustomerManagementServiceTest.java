package de.hrw.wi.service;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.hrw.wi.business.Customer;
import de.hrw.wi.persistence.DBReadInterfaceCustomer;
import de.hrw.wi.persistence.DBWriteInterfaceCustomer;
import de.hrw.wi.persistence.dto.AddressDTO;
import de.hrw.wi.persistence.dto.CustomerDTO;

/**
 * 
 * @author Alexander
 *
 */
public class CustomerManagementServiceTest {

    // private CustomerManagmentServiceInterface csService;
    private DBReadInterfaceCustomer dbReadMock;
    private DBWriteInterfaceCustomer dbWriteMock;
    CustomerManagementService customerManagement = new CustomerManagementService();
    private CustomerManagementServiceInterface csService;
    private CustomerDTO sinan;
    private CustomerDTO mateo;
    private CustomerDTO aykut;
    private CustomerDTO laura;
    private CustomerDTO denis;
    private CustomerDTO alex;

    @Before
    public void setup() {
        // Mockobjekte adressen
        dbReadMock = Mockito.mock(DBReadInterfaceCustomer.class);
        dbWriteMock = Mockito.mock(DBWriteInterfaceCustomer.class);
        AddressDTO address = new AddressDTO("Ebenstrasse", 22, "Nürtingen", 72622, "Deutschland",
                "ebengami@gmx.net", "01767262222");

        AddressDTO address2 = new AddressDTO("Ravensburgerstrasse", 69, "Ravensburg", 82622,
                "Deutschland", "alex.böhm@gmx.net", "0176123456");

        AddressDTO address3 = new AddressDTO("Blenderstrasse", 31, "Weingarten", 88250, "Kroatien",
                "m.kevric@web.de", "0172626652");

        AddressDTO address4 = new AddressDTO("Traubenstrasse", 3, "Baienfurt", 72622, "Türkei",
                "a.topal@gmail.com", "015767736");

        AddressDTO address5 = new AddressDTO("Im Höfle", 11, "Bad Waldsee", 88251, "Deutschland",
                "dramm@gmx.de", "016260982");

        AddressDTO address6 = new AddressDTO("Jordery Platz", 56, "Trauben", 88250, "Österreich",
                "laura.mond@web.de", "0172967215");

        // Mockobjekte Kunden
        sinan = new CustomerDTO(1001, "Sinan", "Ayten", address, 1995, "M", 222);
        alex = new CustomerDTO(1002, "Alexander", "Böhm", address2, 1994, "M", 11007);
        mateo = new CustomerDTO(1003, "Mateo", "Kevric", address3, 1997, "M", 106);
        aykut = new CustomerDTO(1004, "Aykut", "Topal", address4, 1996, "M", 22);
        denis = new CustomerDTO(1005, "Denis", "Rammstein", address5, 1995, "M", 69);
        laura = new CustomerDTO(1006, "Laura", "Mond", address6, 1998, "M", 29);

        when(dbReadMock.getCustomerById(1001)).thenReturn(sinan);
        when(dbReadMock.getCustomerById(1002)).thenReturn(alex);
        when(dbReadMock.getCustomerById(1003)).thenReturn(mateo);
        when(dbReadMock.getCustomerById(1004)).thenReturn(aykut);
        when(dbReadMock.getCustomerById(1005)).thenReturn(denis);
        when(dbReadMock.getCustomerById(1006)).thenReturn(laura);
        csService = new CustomerManagementService(dbReadMock, dbWriteMock);
    }

    @Test
    public void getCustomerTest() throws Exception {
        Customer c = csService.getCustomerById(1001);
        assertEquals(1001, c.getId());

    }

}
