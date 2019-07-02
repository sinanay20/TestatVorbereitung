package de.hrw.wi.business;

/**
 * 
 * @author Mateo Kevric
 *
 */

public class Customer {

    /**
     * CustomerID speichert die Identifikationsnummer des Kunden.
     */
    private int customerID;

    /**
     * firstName speichert den Vornamen des Kunden.
     */
    private String firstName;

    /**
     * lastName speichert den Nachnamen des Kunden.
     */
    private String lastName;

    /**
     * address speichert Adresse des Kunden.
     */
    private Address address;

    /**
     * gender speichert das Geschlecht des Kunden.
     */

    private int sales;

    private String gender;

    /**
     * yearOfBirth speichert das Geburtsjahr des Kunden.
     */
    private int yearOfBirth;

    /**
     * 
     * @param customerID
     *            KundenID
     * @param firstName
     *            vorname
     * @param lastName
     *            nachname
     * @param address
     *            adresse
     * @param sales
     *            Umsätze
     * @param gender
     *            Geschlecht
     * @param yearOfBirth
     *            Geburtsjahr
     */

    /**
     * Default Konstruktor
     */
    public Customer() {
        // TODO Auto-generated constructor stub
    }
/**
 * 
 * @param customerID
 *          Kundennummer
 * @param firstName
 *          Vorname des Kunden
 * @param lastName
 *          Nachname des Kunden
 * @param address
 *          Adresse des Kunden
 * @param sales
 *          Umsätze des Kunden
 * @param gender
 *          Geschlecht des Kunden
 * @param yearOfBirth
 *          Geburtsjahr des Kunden
 */
    public Customer(int customerID, String firstName, String lastName, Address address, int sales,
            String gender, int yearOfBirth) {
        super();
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sales = sales;
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
    }

    /**
     * 
     * @return id
     */
    public int getId() {
        return customerID;
    }

    /**
     * 
     * @param customerID
     *           Kundennummer
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * 
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * @param firstName
     *            setter
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 
     * @param lastName
     *            setter
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 
     * @return address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *            setter
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * 
     * @return sales
     */
    public int getSales() {
        return sales;
    }

    /**
     * 
     * @param sales
     *            Kundenumsätze
     */
    public void setSales(int sales) {
        this.sales = sales;
    }

    /**
     * 
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 
     * @param gender
     *            Setter
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 
     * @return yearOfBirth
     */
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    /**
     * 
     * @param yearOfBirth
     *            Setter
     */
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

}