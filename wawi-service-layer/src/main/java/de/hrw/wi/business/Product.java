/**
 * 
 */
package de.hrw.wi.business;


/**
 * @author andriesc, Laura Eberhard
 *
 */
public class Product {
	/**
	 * name speichert die Bezeichnung des Produkts.
	 */
	private String name;

	/**
	 * productCode speichert die 13stellige EAN-Nummer. productCode muss immer
	 * eine 13stellige Zeichenkette aus den Ziffern 0-9 sein.
	 */
	private String productCode;

	/**
	 * size speichert die Groesse des Produktes als Zahl. Ein Produkt kann
	 * beispielsweise die Groesse 1, 5, 8 oder 24 haben. Die Groesse darf nicht 0
	 * und nicht negativ sein.
	 */
	private int size;
	
	/**
	 * status speichert den Status eines Produktes ab. Der Status wird anhand einer 
	 * Analyse des Verkaufsverhaltens bestimmt. Produkte, die sich gut verkaufen werden
	 * "Renner" genannt. Produkte, die sich schlecht verkaufen werden "Penner genannt". 
	 * Alle anderen Produkte haben den Status "normal". 
	 */
	private ProductStateInterface status; 
	
	/**
	 * @param name
	 * @param productCode
	 */
	public Product(String name, String productCode) {
		super();
		this.name = name;
		this.productCode = productCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * @return
	 *         Status des Produkts
	 * @Laura Eberhard
	 */
	public ProductStateInterface getState() {
	    return status; 
	}
	
	/**
	 * macht aus dem Produkt ein Produkt mit dem Status "Renner"
	 * @author Laura Eberhard
	 */
	public void setProductToRenner() {
	    new Renner(this); 
	}
	
	 /**
     * macht aus dem Produkt ein Produkt mit dem Status "normal"
     * @author Laura Eberhard
     */
	public void setProductToNormal() {
	    new Normal(this); 
	}
	
	 /**
     * macht aus dem Produkt ein Produkt mit dem Status "Penner"
     * @author Laura Eberhard
     */
	public void setProductToPenner() {
	    new Penner(this); 
	}
	
	/**
	 * @param newState
	 *         Status des Produkts
	 * @author Laura Eberhard
	 */
	public void setCurrentState(ProductStateInterface newState) {
	    this.status = newState; 
	}
	
	
}
