package de.hrw.wi.business;

/**
 * 
 * @author lauraeberhard
 *
 */
public class Renner implements ProductStateInterface {

    private Product product;

    /**
     * @param product
     *            Produkt, mit dem Stauts "Renner"
     * @author Laura Eberhard
     */
    public Renner(Product product) {
        this.product = product;
    }

    @Override
    public void setProductToRenner() {
        // muss nicht implementiert werden,
        // da sich das Produkt bereits im Status "Renner" befindet

    }

    @Override
    public void setProductToNormal() {
        product.setCurrentState(new Normal(product));

    }

    @Override
    public void setProductToPenner() {
        product.setCurrentState(new Penner(product));

    }

    @Override
    public boolean isRenner() {
        return true;
    }

    @Override
    public boolean isNormal() {
        return false;
    }

    @Override
    public boolean isPenner() {
        return false;
    }

}
