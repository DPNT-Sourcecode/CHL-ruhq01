package befaster.solutions.CHK;

import java.util.Comparator;

public class SpecialOffer {

    public SpecialOffer(Integer specialPrice, Integer quantity) {
        this.type = OfferType.SPECIAL_PRICE;
        this.specialPrice = specialPrice;
        this.quantity = quantity;
    }

    public SpecialOffer(Integer quantity, Product freeProduct) {
        this.type = OfferType.FREE_PRODUCT;
        this.quantity = quantity;
        this.freeProduct = freeProduct;
    }

    private OfferType type;

    private Integer specialPrice;

    private Integer quantity;

    private Product freeProduct;

    public OfferType getType() {
        return type;
    }

    public void setType(OfferType type) {
        this.type = type;
    }

    public Integer getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(Integer specialPrice) {
        this.specialPrice = specialPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getFreeProduct() {
        return freeProduct;
    }

    public void setFreeProduct(Product freeProduct) {
        this.freeProduct = freeProduct;
    }

    public static class OfferComparator implements Comparator<SpecialOffer> {
        @Override
        public int compare(SpecialOffer o1, SpecialOffer o2) {
            return -o1.getQuantity().compareTo(o2.getQuantity());
        }
    }
}
