package de.hrw.wi.business;

/**
 * 
 * @author Mateo Kevric
 *
 */
public class Address {

    // private int customerID;
    private String street;
    private int houseNr;
    private String city;
    private int postalCode;
    private String country;
    private String phoneNumber;
    private String eMail;

    /**
     */

    //
    // /**
    // *
    // * @return Gibt die Kundennummer zurueck
    // */
    // public int getCustomerID() {
    // return customerID;
    // }
    //
    // /**
    // * Setzt die Kundennummer
    // *
    // * @param customerID
    // * Kundennumer
    // */
    // public void setCustomerId(int customerID) {
    // this.customerID = customerID;
    // }

    /**
     * 
     * @return Gibt die Straße zurueck
     */
    public String getStreet() {
        return street;
    }

    /**
     * 
     * @param street
     *            Strasse des Kunden
     * @param houseNr
     *            Hausnummer des Kunden
     * @param city
     *            Stadt des Kunden
     * @param postalCode
     *            Postleitzahl des Kunden
     * @param country
     *            Land des Kunden
     * @param phoneNumber
     *            Telefonnummer des Kunden
     * @param eMail
     *            Emailadresse des Kunden
     */
    public Address(String street, int houseNr, String city, int postalCode, String country,
            String phoneNumber, String eMail) {
        super();
        this.street = street;
        this.houseNr = houseNr;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
    }

    /**
     * Setzt die Straße
     * 
     * @param street
     *            Straße
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * 
     * @return Gibt die Hausnummer zurueck
     */
    public int getHouseNr() {
        return houseNr;
    }

    /**
     * Setzt die Hausnummer
     * 
     * @param houseNr
     *            Hausnummer
     */
    public void setHouseNr(int houseNr) {
        this.houseNr = houseNr;
    }

    /**
     * 
     * @return Gibt die Stadt zurueck
     */
    public String getCity() {
        return city;
    }

    /**
     * Setzt die Stadt
     * 
     * @param city
     *            Stadt
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return Gibt die PLZ zurueck
     */
    public int getPostalCode() {
        return postalCode;
    }

    /**
     * Setzt die PLZ
     * 
     * @param postalCode
     *            PLZ
     */
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * 
     * @return Gibt das Land zurueck
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setzt das LAnd
     * 
     * @param country
     *            Land
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * @return Gibt die Telefonnummer zurueck
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Setzt die Telefonnummer
     * 
     * @param phoneNumber
     *            Telefonnummer
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 
     * @return Gibt die E-Mail zurueck
     */
    public String geteMail() {
        return eMail;
    }

    /**
     * Setzt die E-Mail
     * @param eMail
     *         E-Mail
     */
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    // @Override
    // public String toString() {
    // return "CustomerAdress [customerId=" + customerId + ", streetName=" + streetName
    // + ", postCode=" + postCode + ", city=" + city + ", countryCode=" + countryCode
    // + "]";
    // }
}