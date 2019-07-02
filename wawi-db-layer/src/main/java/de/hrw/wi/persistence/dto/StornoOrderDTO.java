package de.hrw.wi.persistence.dto;

/**
 * 
 * @author Sinan Ayten, Alexander Böhm
 *
 */
public class StornoOrderDTO extends OrderDTO {

    private int ordernumber;

    private int customerId;

    private String articleCode;

    private int amount;

    private double price;

    private String orderDate;

    private double priceTotal;

    private String reason;

    private String stornoDate;

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
     * @param price
     *            price of ordered product
     * @param orderDate
     *            date of order
     * @param priceTotal
     *            sum total price
     */
    public StornoOrderDTO(int ordernumber, int customerId, String articleCode, String orderDate,
            int amount, double price, double priceTotal) {
        this.ordernumber = ordernumber;
        this.customerId = customerId;
        this.articleCode = articleCode;
        this.amount = amount;
        this.price = price;
        this.orderDate = orderDate;
        this.priceTotal = priceTotal;
    }

    /**
     * 
     * @param reason
     *            Stornoreason
     * @param stornoDate
     *            Date of Storno
     */
    public StornoOrderDTO(String reason, String stornoDate) {
        this.reason = reason;
        this.stornoDate = stornoDate;
    }

    /**
     * def constructor
     */
    public StornoOrderDTO() {
        super();
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

    /**
     * 
     * @return Storno Reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * 
     * @param reason
     *            Storno Reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 
     * @return Stornodate
     */
    public String getStornoDate() {
        return stornoDate;
    }

    /**
     * 
     * @param stornoDate
     *            Stornodate
     */
    public void setStornoDate(String stornoDate) {
        this.stornoDate = stornoDate;
    }

}