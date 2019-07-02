package de.hrw.wi.business;

/**
 * 
 * @author lauraeberhard
 *
 */
public class Penner implements ProductStateInterface {
    
    private Product product; 
    
    /**
     * @param product
     *          Produkt, mit dem Stauts "Penner"
     * @author Laura Eberhard
     */
    public Penner(Product product) {
        this.product = product;
    }

    @Override
    public void setProductToRenner() {
        product.setCurrentState(new Renner(product));
        
    }

    @Override
    public void setProductToNormal() {
        product.setCurrentState(new Normal(product));
        
    }

    @Override
    public void setProductToPenner() {
        // muss nicht implementiert werden,
        // da sich das Produkt bereits im Status "Penner" befindet
        
    }

    @Override
    public boolean isRenner() {
        return false;
    }

    @Override
    public boolean isNormal() {
        return false;
    }

    @Override
    public boolean isPenner() {
        return true;
    }

}
