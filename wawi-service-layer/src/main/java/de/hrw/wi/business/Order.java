package de.hrw.wi.business;

/**
 * 
 * @author Mateo Kevric, Sinan Ayten, Alexander Böhm, Laura Eberhard
 *
 */

public class Order {

    private int ordernumber;

    private int customerId;

    private String articleCode;

    private String orderDate;

    private int amount;

    private double price;

    private double priceTotal;

    /**
     * Default Konstruktor
     */
    public Order() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @param ordernumber
     *            order nr of
     * @param customerId
     *            id of ordering customer
     * @param articleCode
     *            EAN-Code of product
     * @param amount
     *            ordered amount
     * @param d
     *            price of ordered product
     * @param orderDate
     *            date of order
     * @param e
     *            sum total price
     */
    public Order(int ordernumber, int customerId, String articleCode, String orderDate, int amount,
            double d, double e) {
        this.ordernumber = ordernumber;
        this.customerId = customerId;
        this.articleCode = articleCode;
        this.orderDate = orderDate;
        this.amount = amount;
        this.price = d;
        this.priceTotal = e;
    }

    /**
     * @param ordernumber
     * @return order nr
     */
    public int getOrdernumber() {
        return ordernumber;
    }

    /**
     * 
     * @param ordernumber
     *            order nr
     */
    public void setOrdernumber(int ordernumber) {
        this.ordernumber = ordernumber;
    }

    /**
     * 
     * @return customerId of ordering customer
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * 
     * @param customerId
     *            ordering customer
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * 
     * @return EAN-Code of product
     */
    public String getArticleCode() {
        return articleCode;
    }

    /**
     * 
     * @param articleCode
     *            EAN-Code of product
     */
    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
    }

    /**
     * 
     * @return amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * 
     * @param amount
     *            amount
     * 
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * 
     * @return date of order
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * 
     * @param orderDate
     *            date of order
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * 
     * @return total price
     */
    public double getPriceTotal() {
        return priceTotal;
    }

    /**
     * 
     * @param priceTotal
     *            total price
     */
    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    /**
     * 
     * @return price per piece
     */
    public double getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     *            price per piece
     */
    public void setPrice(double price) {
        this.price = price;
    }
}