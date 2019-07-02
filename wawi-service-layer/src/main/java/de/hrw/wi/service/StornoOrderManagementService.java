package de.hrw.wi.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.hrw.wi.business.Customer;
import de.hrw.wi.business.Order;
import de.hrw.wi.business.StornoOrder;
import de.hrw.wi.persistence.DBReadInterfaceStornoOrder;
import de.hrw.wi.persistence.DBWriteInterfaceStornoOrder;
import de.hrw.wi.persistence.RealDatabase;
import de.hrw.wi.persistence.dto.AddressDTO;
import de.hrw.wi.persistence.dto.CustomerDTO;
import de.hrw.wi.persistence.dto.OrderDTO;
import de.hrw.wi.persistence.dto.StornoOrderDTO;

/**
 * 
 * @author Alexander Böhm, Sinan Ayten
 *
 */
public class StornoOrderManagementService implements StornoOrderManagementServiceInterface {

    private DBWriteInterfaceStornoOrder dbWrite = new RealDatabase();
    private DBReadInterfaceStornoOrder dbRead = new RealDatabase();

    String space = " ";
    String bestelldatum = " Bestelldatum: ";
    String anzahl = " Anzahl: ";
    String bestellnummer = " Bestellnummer: ";
    String umsatz = " Umsatz: ";
    String eurozeichen = "€ ";
    String gesamtumsatz = " Gesamtumsatz: ";
    String artikelnummer = " Artikelnummer: ";
    String kundennummer = " Kundennummer: ";

    /**
     * @param dbRead
     *            DatabaseReadInterface
     */

    public StornoOrderManagementService(DBReadInterfaceStornoOrder dbRead) {
        this.dbRead = dbRead;
    }

    /**
     * @param dbWrite
     *            DatabaseWriteInterface
     */

    public StornoOrderManagementService(DBWriteInterfaceStornoOrder dbWrite) {
        this.dbWrite = dbWrite;
    }

    /**
     * Default-Konstruktor
     */
    public StornoOrderManagementService() {

    }

    /**
     * 
     * @param dbRead
     *            Ließt Datenbank
     * @param dbWrite
     *            Schreibt Datenbank
     */
    public StornoOrderManagementService(DBReadInterfaceStornoOrder dbRead,
            DBWriteInterfaceStornoOrder dbWrite) {

        this.dbRead = dbRead;
        this.dbWrite = dbWrite;
    }

    @Override
    public void cancelOrder(Order order, String reason, String stornoDate) throws SQLException {
        int orderId = order.getOrdernumber();
        dbWrite.cancelOrder(orderId, reason, stornoDate);
    }

    @Override
    public StornoOrder getStornoOrderByNumber(int orderNumber) {

        StornoOrder so = null;

        StornoOrderDTO soDTO;

        soDTO = dbRead.getStornoOrderByNumber(orderNumber);

        if (soDTO == null) {
            return null;
        }

        so = new StornoOrder();
        so.setOrdernumber(soDTO.getOrdernumber());
        so.setCustomerId(soDTO.getCustomerId());
        so.setAmount(soDTO.getAmount());
        so.setOrderDate(soDTO.getOrderDate());
        so.setArticleCode(soDTO.getArticleCode());
        so.setPrice(soDTO.getPrice());
        so.setReason(soDTO.getReason());
        so.setStornoDate(soDTO.getStornoDate());
        so.setPriceTotal(soDTO.getPriceTotal());

        return so;
    }

    @Override
    public void getStornoOrderByCustomer(Customer customer) {
        AddressDTO addressDTO = new AddressDTO(customer.getAddress().getStreet(),
                customer.getAddress().getHouseNr(), customer.getAddress().getCity(),
                customer.getAddress().getPostalCode(), customer.getAddress().getCountry(),
                customer.getAddress().geteMail(), customer.getAddress().getPhoneNumber());

        CustomerDTO custDTO = new CustomerDTO(customer.getId(), customer.getFirstName(),
                customer.getLastName(), addressDTO, customer.getYearOfBirth(), customer.getGender(),
                customer.getSales());

        List<StornoOrderDTO> stornoOrderByCustomer = new ArrayList<StornoOrderDTO>(
                dbRead.getStornoOrdersByCustomerId(custDTO.getCustomerID()));

        for (OrderDTO orderDTO : stornoOrderByCustomer) {

            System.out.println(bestellnummer + orderDTO.getOrdernumber() + kundennummer
                    + orderDTO.getCustomerId() + artikelnummer + orderDTO.getArticleCode()
                    + bestelldatum + orderDTO.getOrderDate() + anzahl + orderDTO.getAmount()
                    + umsatz + orderDTO.getPrice() + eurozeichen + gesamtumsatz
                    + orderDTO.getPriceTotal() + eurozeichen);

            System.out.println(space);
        }

    }

    @Override
    public List<StornoOrderDTO> getStornoOrderByCustomer2(Customer customer) {
        AddressDTO addressDTO = new AddressDTO(customer.getAddress().getStreet(),
                customer.getAddress().getHouseNr(), customer.getAddress().getCity(),
                customer.getAddress().getPostalCode(), customer.getAddress().getCountry(),
                customer.getAddress().geteMail(), customer.getAddress().getPhoneNumber());

        CustomerDTO custDTO = new CustomerDTO(customer.getId(), customer.getFirstName(),
                customer.getLastName(), addressDTO, customer.getYearOfBirth(), customer.getGender(),
                customer.getSales());

        List<StornoOrderDTO> stornoOrderByCustomer = new ArrayList<StornoOrderDTO>(
                dbRead.getStornoOrdersByCustomerId(custDTO.getCustomerID()));

        for (StornoOrderDTO stornOrdDTO : stornoOrderByCustomer) {

            System.out.println("Bestellnummer: " + stornOrdDTO.getOrdernumber() + space
                    + "Kundennummer: " + stornOrdDTO.getCustomerId() + space + "Bestelldatum: "
                    + stornOrdDTO.getOrderDate() + space + "Artikelnummer: "
                    + stornOrdDTO.getArticleCode() + space + "Preis: " + stornOrdDTO.getPrice()
                    + space + "Menge: " + stornOrdDTO.getAmount() + space + "Gesamtpreis "
                    + stornOrdDTO.getPrice() + space + "Stornierungsgrund: "
                    + stornOrdDTO.getReason() + space + "Stornierungsdatum: "
                    + stornOrdDTO.getStornoDate());

        }

        return stornoOrderByCustomer;
    }
}
