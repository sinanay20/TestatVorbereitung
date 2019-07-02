package de.hrw.wi.business;

/**
 * 
 * @author lauraeberhard
 *
 */
public class Normal implements ProductStateInterface {
    
    private Product product; 
    
    /**
     * @param product
     *          Produkt, mit dem Stauts "normal"
     * @author Laura Eberhard
     */
    public Normal(Product product) {
        this.product = product;
    }

    @Override
    public void setProductToRenner() {
        product.setCurrentState(new Renner(product));
        
    }

    @Override
    public void setProductToNormal() {
        // muss nicht implementiert werden,
        // da sich das Produkt bereits im Status "normal" befindet
    }

    @Override
    public void setProductToPenner() {
        product.setCurrentState(new Penner(product));
        
    }


    @Override
    public boolean isRenner() {
        return false;
    }

    @Override
    public boolean isNormal() {
        return true;
    }

    @Override
    public boolean isPenner() {
        return false;
    }

    
}
