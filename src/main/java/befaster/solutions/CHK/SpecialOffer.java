package befaster.solutions.CHK;

public class SpecialOffer {

    public SpecialOffer(Integer specialPrice, Integer quantity) {
        this.type = OfferType.SPECIAL_PRICE;
        this.specialPrice = specialPrice;
        this.quantity = quantity;
    }

    public SpecialOffer(Integer quantity, String freeProduct) {
        this.type = OfferType.FREE_PRODUCT;
        this.quantity = quantity;
        this.freeProduct = freeProduct;
    }

    private OfferType type;

    private Integer specialPrice;

    private Integer quantity;

    private String freeProduct;

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

    public String getFreeProduct() {
        return freeProduct;
    }

    public void setFreeProduct(String freeProduct) {
        this.freeProduct = freeProduct;
    }
}

