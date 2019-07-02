package de.hrw.wi.service;

import java.util.ArrayList;
import java.util.List;

import de.hrw.wi.business.Customer;
import de.hrw.wi.business.Order;

import de.hrw.wi.persistence.DBReadInterfaceOrder;
import de.hrw.wi.persistence.DBWriteInterfaceOrder;
import de.hrw.wi.persistence.RealDatabase;
import de.hrw.wi.persistence.dto.AddressDTO;
import de.hrw.wi.persistence.dto.CustomerDTO;
import de.hrw.wi.persistence.dto.OrderDTO;

/**
 * 
 * @author Mateo Kevric
 *
 */

public class OrderManagementService implements OrderManagementServiceInterface {

    private DBWriteInterfaceOrder dbWrite = new RealDatabase();
    private DBReadInterfaceOrder dbRead = new RealDatabase();

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

    public OrderManagementService(DBReadInterfaceOrder dbRead) {
        this.dbRead = dbRead;
    }

    /**
     * @param dbWrite
     *            DatabaseWriteInterface
     */

    public OrderManagementService(DBWriteInterfaceOrder dbWrite) {
        this.dbWrite = dbWrite;
    }

    /**
     * Default-Konstruktor
     */
    public OrderManagementService() {

    }

    /**
     * 
     * @param dbReadMock
     *            Ließt Datenbank
     * @param dbWriteMock
     *            Schreibt Datenbank
     */
    public OrderManagementService(DBReadInterfaceOrder dbReadMock,
            DBWriteInterfaceOrder dbWriteMock) {
        this.dbRead = dbReadMock;
        this.dbWrite = dbWriteMock;
    }

    @Override
    public Order getOrderByNumber(int orderNumber) {
        Order o = null;
        OrderDTO ord;

        ord = dbRead.getOrderByNumber(orderNumber);
        if (ord == null) {
            return null;
        }

        o = new Order(ord.getOrdernumber(), ord.getCustomerId(), ord.getArticleCode(),
                ord.getOrderDate(), ord.getAmount(), ord.getPrice(), ord.getPriceTotal());

        return o;

    }

    @Override
    public void addOrder(Order order) {
        OrderDTO ord = new OrderDTO(order.getOrdernumber(), order.getCustomerId(),
                order.getArticleCode(), order.getOrderDate(), order.getAmount(), order.getPrice(),
                order.getPriceTotal());

        dbWrite.addOrder(ord);

    }

    /**
     * @param customer
     *            Kunde
     * @author Aykut Topal
     */
    @Override
    public void ordersByCustomer(Customer customer) {

        AddressDTO addressDTO = new AddressDTO(customer.getAddress().getStreet(),
                customer.getAddress().getHouseNr(), customer.getAddress().getCity(),
                customer.getAddress().getPostalCode(), customer.getAddress().getCountry(),
                customer.getAddress().geteMail(), customer.getAddress().getPhoneNumber());

        CustomerDTO custDTO = new CustomerDTO(customer.getId(), customer.getFirstName(),
                customer.getLastName(), addressDTO, customer.getYearOfBirth(), customer.getGender(),
                customer.getSales());

        List<OrderDTO> orderByDate = new ArrayList<OrderDTO>(
                dbRead.getAllOrdersFromCustomer(custDTO));

        for (OrderDTO orderDTO : orderByDate) {
            System.out.println("Bestellnummer: " + orderDTO.getOrdernumber() + space
                    + "Kundennummer: " + orderDTO.getCustomerId() + space + "Bestelldatum: "
                    + orderDTO.getOrderDate() + space + "Artikelnummer: "
                    + orderDTO.getArticleCode() + space + "Preis: " + orderDTO.getPrice() + space
                    + "Menge: " + orderDTO.getAmount() + space + "Gesamtpreis: "
                    + orderDTO.getPriceTotal());
        }

    }

    @Override
    public void printOrdersByDate(String orderDate) {
        List<OrderDTO> orderListByDate = new ArrayList<OrderDTO>(
                dbRead.getAllOrdersByDate(orderDate));

        for (OrderDTO orderDTO : orderListByDate) {

            System.out.println(bestellnummer + orderDTO.getOrdernumber() + kundennummer
                    + orderDTO.getCustomerId() + artikelnummer + orderDTO.getArticleCode()
                    + bestelldatum + orderDTO.getOrderDate() + anzahl + orderDTO.getAmount()
                    + umsatz + orderDTO.getPrice() + eurozeichen + gesamtumsatz
                    + orderDTO.getPriceTotal() + eurozeichen);

            System.out.println(space);
        }

    }

    @Override
    public void dateOrdersByPrice(String orderDate) {
        List<OrderDTO> orderListByDate = new ArrayList<OrderDTO>(
                dbRead.dateSortedOrdersByPrice(orderDate));

        for (OrderDTO orderDTO : orderListByDate) {

            System.out.println(bestellnummer + orderDTO.getOrdernumber() + kundennummer
                    + orderDTO.getCustomerId() + artikelnummer + orderDTO.getArticleCode()
                    + bestelldatum + orderDTO.getOrderDate() + anzahl + orderDTO.getAmount()
                    + umsatz + orderDTO.getPrice() + eurozeichen + gesamtumsatz
                    + orderDTO.getPriceTotal() + eurozeichen);

            System.out.println(space);
        }

    }

    @Override
    public void getOrdersBySales(Customer customer) {

        AddressDTO addressDTO = new AddressDTO(customer.getAddress().getStreet(),
                customer.getAddress().getHouseNr(), customer.getAddress().getCity(),
                customer.getAddress().getPostalCode(), customer.getAddress().getCountry(),
                customer.getAddress().geteMail(), customer.getAddress().getPhoneNumber());

        CustomerDTO custDTO = new CustomerDTO(customer.getId(), customer.getFirstName(),
                customer.getLastName(), addressDTO, customer.getYearOfBirth(), customer.getGender(),
                customer.getSales());

        List<OrderDTO> orderListBySales = new ArrayList<OrderDTO>(dbRead.getOrdersBySales(custDTO));

        for (OrderDTO orderDTO : orderListBySales) {

            System.out.println(bestellnummer + orderDTO.getOrdernumber() + kundennummer
                    + orderDTO.getCustomerId() + artikelnummer + orderDTO.getArticleCode()
                    + bestelldatum + orderDTO.getOrderDate() + anzahl + orderDTO.getAmount()
                    + umsatz + orderDTO.getPrice() + eurozeichen + gesamtumsatz
                    + orderDTO.getPriceTotal() + eurozeichen);

            System.out.println(space);
        }

    }

}