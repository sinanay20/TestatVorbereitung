/**
 * 
 */
package de.hrw.wi.persistence;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.hrw.wi.persistence.dto.AddressDTO;
import de.hrw.wi.persistence.dto.CustomerDTO;
import de.hrw.wi.persistence.dto.OrderDTO;
import de.hrw.wi.persistence.dto.StornoOrderDTO;
import de.hrw.wi.persistence.dto.ProductDTO;

/**
 * @author Sinan Ayten, Laura Eberhard
 *
 */
public class RealDatabase implements DatabaseReadInterface, DatabaseWriteInterface,
        DBWriteInterfaceCustomer, DBReadInterfaceCustomer, DBWriteInterfaceStornoOrder,
        DBReadInterfaceStornoOrder, DBWriteInterfaceOrder, DBReadInterfaceOrder,
        DBReadInterfaceProduct, DBWriteInterfaceProduct {

    private final String dbURL = "jdbc:hsqldb:file:../wawi-db-layer/database/wawi_db";
    private final String user = "sa";
    private final String password = "";

    private ResultSet executeQuery(String sql) throws SQLException {
        Connection c = null;
        try {
            c = DriverManager.getConnection(dbURL, user, password);
            ResultSet rs = c.createStatement().executeQuery(sql);
            c.commit();
            return rs;
        } finally {
            try {
                if (c != null)
                    c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private int executeUpdate(String sql) throws SQLException {
        Connection c = null;
        try {
            c = DriverManager.getConnection(dbURL, user, password);
            int result = c.createStatement().executeUpdate(sql);
            c.commit();
            return result;
        } finally {
            try {
                if (c != null)
                    c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private List<String> getResultAsString(String sql) {
        List<String> list = new ArrayList<String>();
        try {
            ResultSet result = executeQuery(sql);
            while (result.next())
                list.add(result.getString(1));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    private List<Integer> getResultAsInt(String sql) {
        List<Integer> list = new ArrayList<Integer>();
        try {
            ResultSet result = executeQuery(sql);
            while (result.next())
                list.add(result.getInt(1));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    private int getInt(String sql) {
        try {
            ResultSet result = executeQuery(sql);
            if (result.next())
                return result.getInt(1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 
     * @param sql
     * @return Gibt einen Wert vom Typ Double Zurück
     */
    private double getDouble(String sql) {
        try {
            ResultSet result = executeQuery(sql);
            if (result.next())
                return result.getDouble(1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

    private String getString(String sql) {
        try {
            ResultSet result = executeQuery(sql);
            if (result.next())
                return result.getString(1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public Set<Integer> getAllWarehouses() {
        return new HashSet<Integer>(getResultAsInt("SELECT number FROM WAREHOUSES"));
    }

    public int getMaxAmountOfBins(int numberOfWarehouse) {
        return getInt("SELECT maxBin FROM WAREHOUSES WHERE number =" + numberOfWarehouse);
    }

    public int getMaxSizeOfBins(int numberOfWarehouse) {
        return getInt("SELECT maxSize FROM WAREHOUSES WHERE number =" + numberOfWarehouse);
    }

    public String getArticleCodeForBin(int numberOfWarehouse, int numberOfBin) {
        return getString("SELECT articleCode FROM STOCK WHERE number=" + numberOfWarehouse
                + " AND bin=" + numberOfBin);
    }

    public int getAmountForBin(int numberOfWarehouse, int numberOfBin) {
        return getInt("SELECT amount FROM STOCK WHERE number=" + numberOfWarehouse + " AND bin="
                + numberOfBin);
    }

    public Set<String> getAllProducts() {
        return new HashSet<String>(getResultAsString("SELECT articleCode FROM PRODUCTS"));
    }

    public String getNameOfProduct(String articleCode) {
        return getString("SELECT name FROM PRODUCTS WHERE articleCode=\'" + articleCode + "\'");
    }

    public int getSizeOfProduct(String articleCode) {
        return getInt("SELECT size FROM PRODUCTS WHERE articleCode=\'" + articleCode + "\'");
    }

    public void addProduct(String articleCode, String name, int size, String status)
            throws PersistenceException {

        if (articleCode.length() != 13)
            throw new PersistenceException("Article code does not have 13 digits.");

        try {
            String s = "INSERT INTO PRODUCTS VALUES(\'" + articleCode + "\', \'" + name + "\', \'"
                    + size + "\', \'" + status + "\')";
            int res = executeUpdate(s);
            if (res == 0)
                throw new PersistenceException("Product could not be added.");
        } catch (SQLException e) {
            throw new PersistenceException("Product could not be added.", e);
        }
    }

    public void deleteProduct(String articleCode) throws PersistenceException {
        try {
            executeUpdate("DELETE FROM STOCK WHERE articleCode=\'" + articleCode + "\'");
            int res = executeUpdate(
                    "DELETE FROM PRODUCTS WHERE articleCode=\'" + articleCode + "\'");
            if (res == 0)
                throw new PersistenceException("Product could not be deleted.");
        } catch (SQLException e) {
            throw new PersistenceException("Product could not be deleted.");
        }
    }

    public void setStock(int numberOfWarehouse, int bin, String articleCode, int amount)
            throws PersistenceException {
        HashSet<Integer> warehouses = new HashSet<Integer>(getAllWarehouses());

        // Nur abspeichern, wenn es das Lager auch gibt
        if (warehouses.contains(numberOfWarehouse)) {
            HashSet<String> products = new HashSet<String>(getAllProducts());

            // Nur abspeichern, wenn es das Produkt bereits in der Datenbank
            // gibt
            if (products.contains(articleCode)) {
                int maxSize = getMaxSizeOfBins(numberOfWarehouse);

                // Lagerplatz leer?
                if (getArticleCodeForBin(numberOfWarehouse, bin) == null) {
                    // Nur abspeichern, wenn die gewuenschte Menge auch passt
                    if (maxSize >= amount * getSizeOfProduct(articleCode)) {
                        try {
                            executeUpdate("INSERT INTO STOCK VALUES(" + numberOfWarehouse + ", "
                                    + bin + ", " + "\'" + articleCode + "\', " + amount + ")");
                        } catch (SQLException e) {
                            throw new PersistenceException("Stock could not be set.", e);
                        }
                    } else
                        throw new PersistenceException("Bin is too small.");
                } else {
                    // Lagerplatz nicht leer
                    // Es sind keine zwei verschiedenen Produkte pro Platz
                    // moeglich,
                    // liegt bereits das richtige Produkt auf dem Platz?
                    String exArtCode = getArticleCodeForBin(numberOfWarehouse, bin);
                    if (exArtCode.equals(articleCode)) {
                        if (maxSize >= amount * getSizeOfProduct(articleCode)) {
                            // Genuegend Platz, dann ueberschreiben
                            try {
                                executeUpdate("UPDATE STOCK SET(" + numberOfWarehouse + ", " + bin
                                        + ", " + "\'" + articleCode + "\', " + amount + ")");
                            } catch (SQLException e) {
                                throw new PersistenceException("Stock could not be set.", e);
                            }
                        } else
                            throw new PersistenceException("Bin is too small.");
                    } else
                        throw new PersistenceException(
                                "Bin is already taken for different product.");
                }
            } else
                throw new PersistenceException("Product does not exist.");
        } else
            throw new PersistenceException("Warehouse does not exist.");

    }

    public void addWarehouse(int number, int maxNumberOfBins, int maxSizeOfBins)
            throws PersistenceException {
        if (number >= 0 && maxNumberOfBins >= 1 && maxSizeOfBins >= 1) {
            try {
                int res = executeUpdate("INSERT INTO WAREHOUSES VALUES(" + number + ", "
                        + maxNumberOfBins + ", " + maxSizeOfBins + ")");
                if (res == 0)
                    throw new PersistenceException("Warehouse could not be added.");
            } catch (SQLException e) {
                throw new PersistenceException("Warehouse could not be added.", e);
            }
        } else
            throw new PersistenceException("Warehouse could not be added.");
    }

    /**
     * 
     * @return Set mit allen Customer IDs
     */

    public Set<Integer> getAllCustomerIds() {
        return new HashSet<Integer>(getResultAsInt("SELECT customerID FROM CUSTOMER"));
    }

    /**
     * 
     * anhand der ID wird mittels SQL-Befehlen ein Customer aus der DB geholt
     * 
     */

    public CustomerDTO getCustomerById(int customerID) {

        CustomerDTO customer = new CustomerDTO();
        AddressDTO address = new AddressDTO();
        customer.setCustomerID(
                getInt("SELECT customerID FROM CUSTOMER WHERE customerID=\'" + customerID + "\'"));
        customer.setFirstName(getString(
                "SELECT firstName FROM CUSTOMER WHERE customerID=\'" + customerID + "\'"));
        customer.setLastName(
                getString("SELECT lastName FROM CUSTOMER WHERE customerID=\'" + customerID + "\'"));
        address.setStreet(
                getString("SELECT street FROM CUSTOMER WHERE customerID=\'" + customerID + "\'"));
        address.setHouseNr(
                getInt("SELECT houseNr FROM CUSTOMER WHERE customerID = \'" + customerID + "\'"));
        address.setCity(
                getString("SELECT city FROM CUSTOMER WHERE customerID=\'" + customerID + "\'"));
        address.setPostalCode(
                getInt("SELECT postalCode FROM CUSTOMER WHERE customerID=\'" + customerID + "\'"));
        address.setCountry(
                getString("SELECT country FROM CUSTOMER WHERE customerID=\'" + customerID + "\'"));
        address.setPhoneNumber(getString(
                "SELECT phoneNumber FROM CUSTOMER WHERE customerID=\'" + customerID + "\'"));
        customer.setGender(
                getString("SELECT gender FROM CUSTOMER WHERE customerID=\'" + customerID + "\'"));
        address.seteMail(
                getString("SELECT eMail FROM CUSTOMER WHERE customerID=\'" + customerID + "\'"));
        customer.setSales(
                getInt("SELECT sales FROM CUSTOMER WHERE customerID=\'" + customerID + "\'"));
        customer.setYearOfBirth(
                getInt("SELECT yearOfBirth FROM CUSTOMER WHERE customerID=\'" + customerID + "\'"));
        customer.setAddress(address);

        return customer;
    }

    /**
     * 
     * neuer Customer wird in die DB geschrieben
     * 
     */

    public void addCustomer(CustomerDTO custDTO) throws SQLException {

        if (custDTO.getYearOfBirth() < 1930 && custDTO.getYearOfBirth() > 9999
                && custDTO.getYearOfBirth() != -1) {
            throw new PersistenceException("Customer could not be added.");
        }

        try {

            String s = "INSERT INTO CUSTOMER VALUES" + "(\'" + custDTO.getCustomerID() + "\', \'"
                    + custDTO.getFirstName() + "\', \'" + custDTO.getLastName() + "\', \'"
                    + custDTO.getAddress().getStreet() + "\', \'"
                    + custDTO.getAddress().getHouseNr() + "\', \'" + custDTO.getAddress().getCity()
                    + "\', \'" + custDTO.getAddress().getCountry() + "\', \'"
                    + custDTO.getAddress().getPostalCode() + "\', \'" + custDTO.getYearOfBirth()
                    + "\', \'" + custDTO.getGender() + "\', \'" + custDTO.getSales() + "\', \'"
                    + custDTO.getAddress().geteMail() + "\', \'"
                    + custDTO.getAddress().getPhoneNumber() + "\')";

            int res = executeUpdate(s);
            if (res == 0)
                throw new PersistenceException("Customer could not be added.");

        } catch (SQLException e) {
            throw new PersistenceException("Customer could not be added.", e);
        }
    }

    public List<String> getAllCustomer() {
        // TODO Auto-generated method stub
        return new ArrayList<String>(getResultAsString("SELECT customerID FROM CUSTOMER"));
    }

    public Set<String> getLastNameById(String id) {
        // TODO Auto-generated method stub
        return new HashSet<String>(
                getResultAsString("SELECT lastName FROM CUSTOMER WHERE customerID =" + id));
    }

    public Set<String> getFirstNameById(String id) {
        // TODO Auto-generated method stub
        return new HashSet<String>(
                getResultAsString("SELECT firstName FROM CUSTOMER WHERE customerID=" + id));
    }

    public Set<String> getMailById(String id) {
        // TODO Auto-generated method stub
        return new HashSet<String>(
                getResultAsString("SELECT eMail FROM CUSTOMER WHERE customerID = " + id));
    }

    public Set<String> getTelephonNumberById(String id) {
        // TODO Auto-generated method stub
        return new HashSet<String>(
                getResultAsString("SELECT phoneNumber FROM CUSTOMER WHERE customerID = " + id));

    }

    public Set<String> getGenderById(String id) {
        // TODO Auto-generated method stub
        return new HashSet<String>(
                getResultAsString("SELECT gender FROM CUSTOMER WHERE customerID = " + id));

    }

    public Set<String> getSalesById(String id) {
        // TODO Auto-generated method stub
        return new HashSet<String>(
                getResultAsString("SELECT sales FROM CUSTOMER WHERE customerID = " + id));

    }

    // Orders:

    /**
     * Author Alexander Böhm
     */
    public List<String> getAllOrders() {
        return new ArrayList<String>(getResultAsString("SELECT ordernumber FROM ORDERS"));
    }

    public OrderDTO getOrderByNumber(int orderNumber) {

        OrderDTO order = new OrderDTO();

        order.setCustomerId(
                getInt("SELECT customerID FROM ORDERS WHERE ordernumber=\'" + orderNumber + "\'"));
        order.setOrdernumber(
                getInt("SELECT ordernumber FROM ORDERS WHERE ordernumber=\'" + orderNumber + "\'"));
        order.setArticleCode(getString(
                "SELECT articleCode FROM ORDERS WHERE ordernumber=\'" + orderNumber + "\'"));
        order.setOrderDate(getString(
                "SELECT orderDate FROM ORDERS WHERE ordernumber=\'" + orderNumber + "\'"));
        order.setAmount(
                getInt("SELECT amount FROM ORDERS WHERE ordernumber = \'" + orderNumber + "\'"));
        order.setPrice(
                getDouble("SELECT price FROM ORDERS WHERE ordernumber=\'" + orderNumber + "\'"));
        order.setPriceTotal(getDouble(
                "SELECT priceTotal FROM ORDERS WHERE ordernumber=\'" + orderNumber + "\'"));

        return order;
    }

    /**
     * Author Aykut
     * 
     * @return
     */

    public List<CustomerDTO> getSortedCustomerBySales() {
        List<CustomerDTO> customerList = new ArrayList<CustomerDTO>();
        List<Integer> id = new ArrayList<Integer>(
                getResultAsInt("SELECT customerID FROM CUSTOMER ORDER BY sales DESC"));

        for (int custid : id) {

            customerList.add(getCustomerById(custid));

        }

        return customerList;

    }

    /**
     * Gibt alle Kunden sortiert nach dem Nachnamen aus Author Alexander
     * 
     * @return customerList
     */

    public List<CustomerDTO> kundenNamenSortiert() {
        List<CustomerDTO> kundenNamenSortiert = new ArrayList<CustomerDTO>();
        List<Integer> id = new ArrayList<Integer>(
                getResultAsInt("SELECT customerID FROM CUSTOMER ORDER BY LASTNAME ASC"));

        for (int custid : id) {

            kundenNamenSortiert.add(getCustomerById(custid));

        }

        return kundenNamenSortiert;

    }

    /**
     * @author Laura Eberhard
     *
     */
    @Override
    public void changeCustomerLastName(int customerID, String lastNameNew) throws SQLException {

        try {

            // test if the customer is deleted, throw Exception if yes
            if (getCustomerById(customerID).getFirstName().equals("-")) {
                throw new PersistenceException("Customer is deleted.");

            }

            // SQL Statement to update the value of the last name

            String s = "UPDATE CUSTOMER SET lastName =\'" + lastNameNew + "\' where customerID =\'"
                    + customerID + "\';";

            int res = executeUpdate(s);

            if (res == 0) {
                throw new PersistenceException("Customer could not be changed.");
            }

        } catch (SQLException e) {
            throw new PersistenceException("Customer could not be changed.");
        }

    }

    /**
     * @author Laura Eberhard
     *
     */
    @Override
    public void changeCustomerStreet(int customerID, String streetNew) throws SQLException {

        try {

            // test if the customer is deleted, throw Exception if yes
            if (getCustomerById(customerID).getFirstName().equals("-")) {
                throw new PersistenceException("Customer is deleted.");

            }

            // SQL Statement to update the value of the street

            String s = "UPDATE CUSTOMER SET street =\'" + streetNew + "\' where customerID =\'"
                    + customerID + "\';";

            int res = executeUpdate(s);

            if (res == 0) {
                throw new PersistenceException("Customer could not be changed.");
            }

        } catch (SQLException e) {
            throw new PersistenceException("Customer could not be changed.");
        }

    }

    /**
     * @author Laura Eberhard
     *
     */
    @Override
    public void changeCustomerHouseNr(int customerID, int houseNrNew) throws SQLException {

        try {

            // test if the customer is deleted, throw Exception if yes
            if (getCustomerById(customerID).getFirstName().equals("-")) {
                throw new PersistenceException("Customer is deleted.");

            }

            // SQL Statement to update the value of the house number

            String s = "UPDATE CUSTOMER SET houseNr =\'" + houseNrNew + "\' where customerID =\'"
                    + customerID + "\';";

            int res = executeUpdate(s);

            if (res == 0) {
                throw new PersistenceException("Customer could not be changed.");
            }

        } catch (SQLException e) {
            throw new PersistenceException("Customer could not be changed.");
        }

    }

    /**
     * @author Laura Eberhard
     *
     */
    @Override
    public void changeCustomerCity(int customerID, String cityNew) throws SQLException {

        try {

            // test if the customer is deleted, throw Exception if yes
            if (getCustomerById(customerID).getFirstName().equals("-")) {
                throw new PersistenceException("Customer is deleted.");

            }

            // SQL Statement to update the value of the city

            String s = "UPDATE CUSTOMER SET city =\'" + cityNew + "\' where customerID =\'"
                    + customerID + "\';";

            int res = executeUpdate(s);

            if (res == 0) {
                throw new PersistenceException("Customer could not be changed.");
            }

        } catch (SQLException e) {
            throw new PersistenceException("Customer could not be changed.");
        }
    }

    /**
     * @author Laura Eberhard
     *
     */
    @Override
    public void changeCustomerPostalCode(int customerID, int postalCodeNew) throws SQLException {

        try {

            // test if the customer is deleted, throw Exception if yes
            if (getCustomerById(customerID).getFirstName().equals("-")) {
                throw new PersistenceException("Customer is deleted.");

            }
            // SQL Statement to update the value of the postal code

            String s = "UPDATE CUSTOMER SET postalCode =\'" + postalCodeNew
                    + "\' where customerID =\'" + customerID + "\';";

            int res = executeUpdate(s);

            if (res == 0) {
                throw new PersistenceException("Customer could not be changed.");
            }

        } catch (SQLException e) {
            throw new PersistenceException("Customer could not be changed.");
        }

    }

    @Override
    public void changeCustomerCountry(int customerID, String countryNew) throws SQLException {

        try {

            // test if the customer is deleted, throw Exception if yes
            if (getCustomerById(customerID).getFirstName().equals("-")) {
                throw new PersistenceException("Customer is deleted.");

            }

            // SQL Statement to update the value of the country

            String s = "UPDATE CUSTOMER SET country =\'" + countryNew + "\' where customerID =\'"
                    + customerID + "\';";

            int res = executeUpdate(s);

            if (res == 0) {
                throw new PersistenceException("Customer could not be changed.");
            }

        } catch (SQLException e) {
            throw new PersistenceException("Customer could not be changed.");
        }

    }

    /**
     * @author Laura Eberhard
     *
     */
    @Override
    public void changeCustomerMail(int customerID, String mailNew) throws SQLException {

        try {

            // test if the customer is deleted, throw Exception if yes
            if (getCustomerById(customerID).getFirstName().equals("-")) {
                throw new PersistenceException("Customer is deleted.");

            }

            // SQL Statement to update the value of the eMail

            String s = "UPDATE CUSTOMER SET eMail =\'" + mailNew + "\' where customerID =\'"
                    + customerID + "\';";

            int res = executeUpdate(s);

            if (res == 0) {
                throw new PersistenceException("Customer could not be changed.");
            }

        } catch (SQLException e) {
            throw new PersistenceException("Customer could not be changed.");
        }

    }

    /**
     * @author Laura Eberhard
     *
     */
    @Override
    public void changeCustomerPhone(int customerID, String numberNew) throws SQLException {

        try {

            // test if the customer is deleted, throw Exception if yes
            if (getCustomerById(customerID).getFirstName().equals("-")) {
                throw new PersistenceException("Customer is deleted.");

            }

            // SQL Statement to update the value of the phone number

            String s = "UPDATE CUSTOMER SET phoneNumber =\'" + numberNew + "\' where customerID =\'"
                    + customerID + "\';";

            int res = executeUpdate(s);

            if (res == 0) {
                throw new PersistenceException("Customer could not be changed.");
            }

        } catch (SQLException e) {
            throw new PersistenceException("Customer could not be changed.");
        }

    }

    /**
     * delete the customer
     * 
     * @param customer
     *            customer
     * @throws SQLException
     *             Exception
     * @author Laura Eberhard
     */
    @Override
    public void deleteCustomer(CustomerDTO customer) throws SQLException {

        // get the ID of the customer
        int customerID = customer.getCustomerID();

        // get the maximal customer ID
        int maxID = getResultAsInt("SELECT MAX(customerID) FROM Customer").iterator().next();

        // check if the customer ID of the wanted customer is
        if (customerID > maxID) {
            throw new PersistenceException("Customer doesn't exist");
        }

        // values to replace the attributes of the customer
        String deletedS = "-";
        int deletedI = -1;

        try {

            // overwrite the string-type attributes with "---"
            // and the integer-type attributes with "-1"
            // so that if someone tries to access the attributes of a deleted customer
            // it will be recognizable that the customer doesn't exist
            // but there is access to the customerID if someone need it
            // even when the customer is deleted

            String s = "UPDATE CUSTOMER SET firstName = \'" + deletedS + "\'," + "lastName =\'"
                    + deletedS + "\'," + "street =\'" + deletedS + "\'," + "houseNr =\'" + deletedI
                    + "\'," + "city =\'" + deletedS + "\'," + "country =\'" + deletedS + "\',"
                    + "postalCode =\'" + deletedI + "\'," + "yearOfBirth =\'" + deletedI + "\',"
                    + "gender =\'" + deletedS + "\'," + "sales =\'" + deletedI + "\'," + "eMail =\'"
                    + deletedS + "\'," + "phoneNumber =\'" + deletedS + "\'"
                    + "WHERE customerID = \'" + customerID + "\';";

            int res = executeUpdate(s);

            if (res == 0) {
                throw new PersistenceException("Customer could not be deleted");
            }

        } catch (SQLException e) {
            throw new PersistenceException("Customer could not be deleted");
        }

    }

    public void addOrder(OrderDTO order) {
        try {

            String s = "INSERT INTO ORDERS VALUES(\'" + order.getOrdernumber() + "\', \'"
                    + order.getCustomerId() + "\', \'" + order.getArticleCode() + "\', \'"
                    + order.getOrderDate() + "\', \'" + order.getAmount() + "\', \'"
                    + order.getPrice() + "\', \'" + order.getPriceTotal() + "\')";

            int res = executeUpdate(s);
            if (res == 0)
                throw new PersistenceException("Order could not be added.");
        } catch (SQLException e) {
            throw new PersistenceException("Order could not be added.", e);
        }
    }

    public List<OrderDTO> getOrdersByDate(String orderDate) {
        List<OrderDTO> orderListeByDate = new ArrayList<OrderDTO>();
        List<Integer> id = new ArrayList<Integer>(
                getResultAsInt("SELECT ordernumber FROM Order where orderDate=" + orderDate));

        for (int custid : id) {
            orderListeByDate.add(getOrderByNumber(custid));

        }

        return orderListeByDate;

    }

    @Override
    public OrderDTO getOrderById(int customerID) {

        OrderDTO order = new OrderDTO();

        order.setCustomerId(
                getInt("SELECT customerID FROM ORDERS WHERE customerID=\'" + customerID + "\'"));
        order.setOrdernumber(
                getInt("SELECT ordernumber FROM ORDERS WHERE customerID=\'" + customerID + "\'"));
        order.setArticleCode(getString(
                "SELECT articleCode FROM ORDERS WHERE customerID=\'" + customerID + "\'"));
        order.setOrderDate(
                getString("SELECT orderDate FROM ORDERS WHERE customerID=\'" + customerID + "\'"));
        order.setAmount(
                getInt("SELECT amount FROM ORDERS WHERE customerID = \'" + customerID + "\'"));
        order.setPrice(
                getDouble("SELECT price FROM ORDERS WHERE customerID=\'" + customerID + "\'"));
        order.setPriceTotal(
                getDouble("SELECT priceTotal FROM ORDERS WHERE customerID=\'" + customerID + "\'"));

        return order;

    }

    /**
     * @param customer
     *            Kunde
     * @return List Liste mit allen Bestellungen eines ausgewählten Kunden
     * @author Aykut Topal
     */
    @Override
    public List<OrderDTO> getAllOrdersFromCustomer(CustomerDTO customer) {

        int customerID = customer.getCustomerID();

        List<OrderDTO> orderListByCustomer = new ArrayList<OrderDTO>();
        List<Integer> orderNumber = new ArrayList<Integer>(
                getResultAsInt("SELECT ordernumber FROM ORDERS where customerID=" + customerID
                        + "ORDER BY orderDate ASC"));

        for (int orderID : orderNumber) {

            orderListByCustomer.add(getOrderByNumber(orderID));
        }

        return orderListByCustomer;
    }

    @Override
    public List<OrderDTO> getAllOrdersByDate(String orderDate) {
        List<OrderDTO> orderListByDate = new ArrayList<OrderDTO>();
        List<Integer> id = new ArrayList<Integer>(getResultAsInt(
                "SELECT ordernumber FROM ORDERS WHERE orderDate=\'" + orderDate + "\'"));

        for (int counter : id) {
            orderListByDate.add(getOrderByNumber(counter));

        }
        return orderListByDate;

    }

    @Override
    public List<OrderDTO> dateSortedOrdersByPrice(String orderDate) {

        List<OrderDTO> orderListByDate = new ArrayList<OrderDTO>();
        List<Integer> id = new ArrayList<Integer>(
                getResultAsInt("SELECT ordernumber FROM ORDERS WHERE orderDate=\'" + orderDate
                        + "\'" + "ORDER BY priceTotal DESC"));

        for (int counter : id) {
            orderListByDate.add(getOrderByNumber(counter));

        }
        return orderListByDate;

    }

    @Override
    public List<OrderDTO> getOrdersBySales(CustomerDTO customer) {

        int customerID = customer.getCustomerID();

        List<OrderDTO> orderListBySales = new ArrayList<OrderDTO>();
        List<Integer> priceTotal = new ArrayList<Integer>(
                getResultAsInt("SELECT ordernumber FROM ORDERS where customerID=" + customerID
                        + "ORDER BY priceTotal ASC"));

        for (int orderID : priceTotal) {

            orderListBySales.add(getOrderByNumber(orderID));
        }

        return orderListBySales;
    }

    /**
     * @param articleCode
     *            Artikelnummer des Produkts
     * @param status
     *            Status des Produkts
     * @throws SQLException
     *             SQLException
     * @author Laura Eberhard
     */
    @Override
    public void changeProductStatus(String articleCode, String status) throws SQLException {

        try {

            // SQL Statement to update the status

            String s = "UPDATE PRODUCTS SET status =\'" + status + "\' where articleCode =\'"
                    + articleCode + "\'";

            int res = executeUpdate(s);

            if (res == 0) {
                throw new PersistenceException("Product status could not be changed.");
            }

        } catch (SQLException e) {
            throw new PersistenceException("Product status could not be changed.");
        }

    }

    /**
     * @param articleCode
     *            Artikelnummer
     * @return Status des Produkts
     * @author Laura Eberhard
     */
    @Override
    public String getProductStatus(String articleCode) {
        return getString("SELECT status FROM PRODUCTS WHERE articleCode=\'" + articleCode + "\'");
    }

    @Override
    public ProductDTO getProductByArticleCode(String articleCode) {

        ProductDTO product = new ProductDTO();

        product.setArticleCode(getString(
                "SELECT articleCode FROM PRODUCTS WHERE articleCode=\'" + articleCode + "\'"));

        product.setName(
                getString("SELECT name FROM PRODUCTS WHERE articleCode=\'" + articleCode + "\'"));

        product.setSize(
                getInt("SELECT size FROM PRODUCTS WHERE articleCode=\'" + articleCode + "\'"));

        product.setStatus(
                getString("SELECT status FROM PRODUCTS WHERE articleCode=\'" + articleCode + "\'"));

        return product;
    }

    @Override
    public StornoOrderDTO getStornoOrderByNumber(int orderNumber) {
        StornoOrderDTO order = new StornoOrderDTO();

        order.setCustomerId(getInt(
                "SELECT customerID FROM STORNOORDERS WHERE ordernumber=\'" + orderNumber + "\'"));
        order.setOrdernumber(getInt(
                "SELECT ordernumber FROM STORNOORDERS WHERE ordernumber=\'" + orderNumber + "\'"));
        order.setArticleCode(getString(
                "SELECT articleCode FROM STORNOORDERS WHERE ordernumber=\'" + orderNumber + "\'"));
        order.setOrderDate(getString(
                "SELECT orderDate FROM STORNOORDERS WHERE ordernumber=\'" + orderNumber + "\'"));
        order.setAmount(getInt(
                "SELECT amount FROM STORNOORDERS WHERE ordernumber = \'" + orderNumber + "\'"));
        order.setPrice(getDouble(
                "SELECT price FROM STORNOORDERS WHERE ordernumber=\'" + orderNumber + "\'"));
        order.setPriceTotal(getDouble(
                "SELECT priceTotal FROM STORNOORDERS WHERE ordernumber=\'" + orderNumber + "\'"));
        order.setReason(getString(
                "SELECT reason FROM STORNOORDERS WHERE ordernumber=\'" + orderNumber + "\'"));
        order.setStornoDate(getString(
                "SELECT stornoDate FROM STORNOORDERS WHERE ordernumber=\'" + orderNumber + "\'"));
        return order;
    }

    @Override
    public List<String> getAllStornoOrders() {
        return new ArrayList<String>(getResultAsString("SELECT ordernumber FROM STORNOORDERS"));

    }

    @Override
    public void addStornoOrder(StornoOrderDTO sOrder) {
        try {

            String s = "INSERT INTO STORNOORDERS VALUES(\'" + sOrder.getOrdernumber() + "\', \'"
                    + sOrder.getCustomerId() + "\', \'" + sOrder.getArticleCode() + "\', \'"
                    + sOrder.getOrderDate() + "\', \'" + sOrder.getAmount() + "\', \'"
                    + sOrder.getPrice() + "\', \'" + sOrder.getPriceTotal() + "\', \'"
                    + sOrder.getReason() + "\', \'" + sOrder.getStornoDate() + "\')";

            int res = executeUpdate(s);
            if (res == 0)
                throw new PersistenceException("Order could not be added.");
        } catch (SQLException e) {
            throw new PersistenceException("Order could not be added.", e);
        }

    }

    public int getCustomerID(int orderID) {
        return getInt("SELECT customerId FROM ORDERS WHERE orderNumber=" + space + orderID + space);
    }

    public double getPrice(int orderID) {
        return getDouble("SELECT price FROM ORDERS WHERE orderNumber=" + space + orderID + space);
    }

    public int getAmount(int orderID) {
        return getInt("SELECT amount FROM ORDERS WHERE orderNumber=" + space + orderID + space);
    }

    public double getTotalPrice(int orderID) {
        return getDouble(
                "SELECT priceTotal FROM ORDERS WHERE orderNumber=" + space + orderID + space);
    }

    public String getOrderDate(int orderID) {
        return getString(
                "SELECT orderDate FROM ORDERS WHERE orderNumber=" + space + orderID + space);
    }

    public String getArticlecode(int orderID) {
        return getString(
                "SELECT articleCode FROM ORDERS WHERE orderNumber=" + space + orderID + space);
    }

    private final String space = "\'";
    private final String space2 = "\', \'";

    @Override
    public void cancelOrder(int orderID, String reason, String stornoDate) throws SQLException {

        String articlecode = getArticlecode(orderID);
        int amount = getAmount(orderID);
        String Orderdate = getOrderDate(orderID);
        double price = getPrice(orderID);
        int customerID = getCustomerID(orderID);
        double priceTotal = getTotalPrice(orderID);

        String s = "INSERT INTO STORNOORDERS VALUES(\'" + orderID + space2 + customerID + space2
                + articlecode + space2 + Orderdate + space2 + amount + space2 + price + space2
                + priceTotal + space2 + reason + space2 + stornoDate + space + ")";

        int res = executeUpdate(s);
        if (res == 0) {
            throw new PersistenceException("s=0");
        }

        String z = "DELETE FROM ORDERS WHERE orderNumber=" + orderID;
        try {
            executeUpdate(z);
        } catch (SQLException e) {
            throw new PersistenceException("z=0");
        }
    }

    /**
     * @author Aykut Topal
     */
    @Override
    public List<OrderDTO> getOrdersByProduct(String articleCode) {

        List<OrderDTO> orderList = new ArrayList<OrderDTO>();
        List<Integer> id = new ArrayList<Integer>(getResultAsInt(
                "SELECT ordernumber FROM ORDERS WHERE articleCode=\'" + articleCode + "\'"));
        for (int counter : id) {
            orderList.add(getOrderByNumber(counter));

        }
        return orderList;

    }

    @Override
    public List<StornoOrderDTO> getStornoOrdersByCustomerId(int customerId) {

        List<Integer> id = new ArrayList<Integer>(getResultAsInt(
                "SELECT ordernumber FROM STORNOORDERS WHERE customerID=\'" + customerId + "\'"));
        List<StornoOrderDTO> stornoOrderListByOrdernumber = new ArrayList<StornoOrderDTO>();

        for (int counter : id) {
            stornoOrderListByOrdernumber.add(getStornoOrderByNumber(counter));

        }

        return stornoOrderListByOrdernumber;

    }
}
