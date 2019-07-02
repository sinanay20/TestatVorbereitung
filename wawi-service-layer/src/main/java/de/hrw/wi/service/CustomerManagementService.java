package de.hrw.wi.service;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import de.hrw.wi.business.Address;
import de.hrw.wi.business.Customer;
import de.hrw.wi.persistence.DBReadInterfaceCustomer;
import de.hrw.wi.persistence.DBWriteInterfaceCustomer;
import de.hrw.wi.persistence.RealDatabase;
import de.hrw.wi.persistence.dto.AddressDTO;
import de.hrw.wi.persistence.dto.CustomerDTO;

/**
 * @author Mateo Kevirc, Laura Eberhard
 *
 */
public class CustomerManagementService implements CustomerManagementServiceInterface {
    private DBWriteInterfaceCustomer dbWrite = new RealDatabase();
    private DBReadInterfaceCustomer dbRead = new RealDatabase();

    String space = " ";

    /**
     * @author Mateo Kevric
     * 
     * @param dbRead
     *            DatabaseReadInterface
     */
    public CustomerManagementService(DBReadInterfaceCustomer dbRead) {
        this.dbRead = dbRead;
    }

    /**
     * @param dbWrite
     *            DatabaseWriteInterface
     */
    public CustomerManagementService(DBWriteInterfaceCustomer dbWrite) {
        this.dbWrite = dbWrite;
    }

    /**
     * Default-Konstruktor
     */
    public CustomerManagementService() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @param dbReadMock
     *            Liest Datenbank
     * @param dbWriteMock
     *            Schreibt Datenbank
     */
    public CustomerManagementService(DBReadInterfaceCustomer dbReadMock,
            DBWriteInterfaceCustomer dbWriteMock) {
        this.dbRead = dbReadMock;
        this.dbWrite = dbWriteMock;
    }

    @Override
    public void addCustomer(Customer customer) {
        AddressDTO adr = new AddressDTO(customer.getAddress().getStreet(),
                customer.getAddress().getHouseNr(), customer.getAddress().getCity(),
                customer.getAddress().getPostalCode(), customer.getAddress().getCountry(),
                customer.getAddress().geteMail(), customer.getAddress().getPhoneNumber());

        CustomerDTO cust = new CustomerDTO(customer.getId(), customer.getFirstName(),
                customer.getLastName(), adr, customer.getYearOfBirth(), customer.getGender(),
                customer.getSales());

        try {
            dbWrite.addCustomer(cust);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer getCustomerById(int cid) throws Exception {
        Address a = null;
        Customer c = null;
        CustomerDTO cust;

        cust = dbRead.getCustomerById(cid);
        if (cust == null) {
            return null;
        }
        a = new Address(cust.getAddress().getStreet(), cust.getAddress().getHouseNr(),
                cust.getAddress().getCity(), cust.getAddress().getPostalCode(),
                cust.getAddress().getCountry(), cust.getAddress().geteMail(),
                cust.getAddress().getPhoneNumber());
        c = new Customer(cid, cust.getFirstName(), cust.getLastName(), a, cust.getYearOfBirth(),
                cust.getGender(), cust.getSales());

        return c;

    }

    /**
     * @author Aykut Topal
     */

    @Override
    public void sotiertNachSales() {

        List<CustomerDTO> kundenListe = new ArrayList<CustomerDTO>(
                dbRead.getSortedCustomerBySales());

        for (CustomerDTO customerDTO : kundenListe) {
            System.out.println(customerDTO.getLastName() + space + customerDTO.getFirstName()
                    + space + customerDTO.getSales());
        }

    }

    @Override
    public void sotiertNachNamen() {

        List<CustomerDTO> kundenListe = new ArrayList<CustomerDTO>(dbRead.kundenNamenSortiert());

        for (CustomerDTO customerDTO : kundenListe) {
            System.out.println(customerDTO.getLastName() + space + customerDTO.getFirstName()
                    + " KundenID: " + customerDTO.getCustomerID() + space + customerDTO.getGender()
                    + space + " Geburtsjahr: " + customerDTO.getYearOfBirth() + " Umsatz: "
                    + customerDTO.getSales() + "€");
            System.out.println("------> Adresse: " + customerDTO.getAddress().getCountry() + space
                    + customerDTO.getAddress().getPostalCode() + space
                    + customerDTO.getAddress().getCity() + space
                    + customerDTO.getAddress().getStreet() + " Nr: "
                    + customerDTO.getAddress().getHouseNr() + space
                    + customerDTO.getAddress().geteMail() + " Telefonnummer: "
                    + customerDTO.getAddress().getPhoneNumber());
            System.out.println("  ");
        }

    }

    /**
     * @author Laura Eberhard
     *
     */
    @Override
    public void changeCustomerLastName(Customer customer, String lastNameNew) {

        try {
            // get the ID of the customer
            int customerID = customer.getId();

            // change the last name of the customer
            dbWrite.changeCustomerLastName(customerID, lastNameNew);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * @author Laura Eberhard
     *
     */
    @Override
    public void changeCustomerStreet(Customer customer, String streetNew) {
        try {
            // get the ID of the customer
            int customerID = customer.getId();

            // change the street of the customer
            dbWrite.changeCustomerStreet(customerID, streetNew);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * @author Laura Eberhard
     *
     */
    @Override
    public void changeCustomerHouseNr(Customer customer, int houseNrNew) {
        try {
            // get the ID of the customer
            int customerID = customer.getId();

            // change the house number of the customer
            dbWrite.changeCustomerHouseNr(customerID, houseNrNew);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * @author Laura Eberhard
     *
     */
    @Override
    public void changeCustomerCity(Customer customer, String cityNew) {
        try {
            // get the ID of the customer
            int customerID = customer.getId();

            // change the city of the customer
            dbWrite.changeCustomerCity(customerID, cityNew);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * @author Laura Eberhard
     *
     */
    @Override
    public void changeCustomerPostalCode(Customer customer, int postalCodeNew) {
        try {
            // get the ID of the customer
            int customerID = customer.getId();

            // change the postal code of the customer
            dbWrite.changeCustomerPostalCode(customerID, postalCodeNew);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * @author Laura Eberhard
     *
     */
    @Override
    public void changeCustomerCountry(Customer customer, String countryNew) {
        try {
            // get the ID of the customer
            int customerID = customer.getId();

            // change the country of the customer
            dbWrite.changeCustomerCountry(customerID, countryNew);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * @author Laura Eberhard
     *
     */
    @Override
    public void changeCustomerMail(Customer customer, String mailNew) {
        try {
            // get the ID of the customer
            int customerID = customer.getId();

            // change the e-Mail address of the customer
            dbWrite.changeCustomerMail(customerID, mailNew);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * @author Laura Eberhard
     *
     */
    @Override
    public void changeCustomerPhone(Customer customer, String numberNew) {
        try {

            // get the ID of the customer
            int customerID = customer.getId();

            // change the phone number of the customer
            dbWrite.changeCustomerPhone(customerID, numberNew);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * delete the selected customer
     * 
     * @param customer
     *            customer
     * @author Laura Eberhard
     */
    @Override
    public void deleteCustomer(Customer customer) {

        try {

            // create CustomerDTO with the attributes of the customer
            AddressDTO addressDTO = new AddressDTO(customer.getAddress().getStreet(),
                    customer.getAddress().getHouseNr(), customer.getAddress().getCity(),
                    customer.getAddress().getPostalCode(), customer.getAddress().getCountry(),
                    customer.getAddress().geteMail(), customer.getAddress().getPhoneNumber());

            CustomerDTO custDTO = new CustomerDTO(customer.getId(), customer.getFirstName(),
                    customer.getLastName(), addressDTO, customer.getYearOfBirth(),
                    customer.getGender(), customer.getSales());

            // delete the customer
            dbWrite.deleteCustomer(custDTO);

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

}