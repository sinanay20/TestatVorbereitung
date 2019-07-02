package de.hrw.wi.persistence.dto;

/**
 * @author lauraeberhard
 *
 */
public class ProductDTO {
    
    private String articleCode; 
    
    private String name; 
    
    private int size; 
    
    private String status;

    /**
     * @param articleCode
     *          Artikelnummer
     * @param name
     *          Name
     * @param size
     *          Produktgröße
     * @param status
     *          Status des Produkts 
     */
    public ProductDTO(String articleCode, String name, int size, String status) {
        super();
        this.articleCode = articleCode;
        this.name = name;
        this.size = size;
        this.status = status;
    }

    /**
     * leerer Konstruktor
     */
    public ProductDTO() {
        super();
    }


    /**
     * @return
     *      Artikelnummer
     */
    public String getArticleCode() {
        return articleCode;
    }

    /**
     * @param articleCode
     *          Artikelnummer
     */
    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
    }

    /**
     * @return
     *      Produktname
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *          Produktname
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     *      Produktgröße
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size
     *          Produktgröße
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return
     *      Status des Produkts
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *          Status des Produkts
     */
    public void setStatus(String status) {
        this.status = status;
    } 
    
    

}
