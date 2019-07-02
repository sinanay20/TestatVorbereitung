package de.hrw.wi.persistence.dto;

/**
 *
 * @author Sinan Ayten
 *
 */
public class CustomerDTO {

    /**
     * customerID speichert die Kunden-Nr. des Kunden.
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
     * address speichert die Adresse des Kunden.
     */
    private AddressDTO address;
    /**
     * yearOfBirth speichert das Geburtsjahr des Kunden.
     */
    private int yearOfBirth;
    /**
     * gender speichert das Geschlecht des Kunden.
     */
    private String gender;
    /**
     * sales speichert den Umsatz des Kunden.
     */
    private int sales;
 

    /**
     * 
     * @param customerID
     *            Kundenummer
     * @param firstName
     *            Vorname
     * @param lastName
     *            Nachname
     * @param address
     *            Adresse
     * @param yearOfBirth
     *            Geburtsjahr
     * @param gender
     *            Geschlecht
     * @param sales
     *            Umsatz
     */

    public CustomerDTO(int customerID, String firstName, String lastName, AddressDTO address,
            int yearOfBirth, String gender, int sales) {
        super();
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.yearOfBirth = yearOfBirth;
        this.gender = gender;
        this.sales = sales;
        
    }
    /**
     * leerer Konstruktor
     */
    public CustomerDTO() {
        // TODO Auto-generated constructor stub
    }
    /**
     * 
     * @return customerID
     */
    public int getCustomerID() {
        return customerID;
    }
    /**
     * 
     * @param customerID
     * setter
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
     * setter
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
     * setter
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * 
     * @return address
     */
    public AddressDTO getAddress() {
        return address;
    }
    /**
     * 
     * @param address
     * setter
     */
    public void setAddress(AddressDTO address) {
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
     * setter
     */
    public void setSales(int sales) {
        this.sales = sales;
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
     * setter
     */
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
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
     * setter
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

}
