package de.hrw.wi.persistence.dto;

/**
 *
 * @author Sinan Ayten
 *
 */

public class AddressDTO {

    /**
     * street speichert die Straße des Kunden.
     */
    private String street;
    /**
     * houseNr speichert die Hausnummer des Kunden.
     */
    private int houseNr;
    /**
     * city speichert die stadt des Kunden.
     */
    private String city;
    /**
     * postalCode speichert die Postleitzahl des Kunden.
     */
    private int postalCode;
    /**
     * country speichert das Land des Kunden.
     */
    private String country;
    
    /**
     * eMail speichert die E-Mail des Kunden.
     */
    private String eMail;
    /**
     * phoneNumber speichert die Telefonnummer des Kunden.
     */
    private String phoneNumber;
    /**
     * 
     * @param street
     *            Straße
     * @param houseNr
     *            Hausnummer
     * @param city
     *            Stadt
     * @param postalCode
     *            Postleitzahl
     * @param country
     *            Land
     * @param eMail
     *            Email
     * @param phoneNumber
     *            Telefon
     * 
     */
    public AddressDTO(String street, int houseNr, String city, int postalCode, String country,
            String eMail, String phoneNumber) {

        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.houseNr = houseNr;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
    }

    /**
     * leerer Konstruktor
     */
    public AddressDTO() {
    }

    /**
     * 
     * @return street
     */
    public String getStreet() {
        return street;
    }

    /**
     * 
     * @param street
     *            setter
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * 
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *            setter
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return postalCode
     */
    public int getPostalCode() {
        return postalCode;
    }

    /**
     * 
     * @param postalCode
     *            setter
     */
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * 
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *            setter
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * @return houseNr
     */
    public int getHouseNr() {
        return houseNr;
    }

    /**
     * 
     * @param houseNr
     *            setter
     */
    public void setHouseNr(int houseNr) {
        this.houseNr = houseNr;
    }
    /**
     * 
     * @return phoneNumer
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * 
     * @param phoneNumber
     * setter
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * 
     * @return eMail
     */
    public String geteMail() {
        return eMail;
    }
    /**
     * 
     * @param eMail
     * setter
     */
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

}
