package befaster.solutions.CHK;

import java.util.List;

public class Product {

    public Product(String sku, Integer price, List<SpecialOffer> specialOffers) {
        this.sku = sku;
        this.price = price;
        this.specialOffers = specialOffers;
    }

    private String sku;
    
    private Integer price;
    
    private List<SpecialOffer> specialOffers;

    public List<SpecialOffer> getSpecialOffers() {
        return specialOffers;
    }

    public void setSpecialOffers(List<SpecialOffer> specialOffers) {
        this.specialOffers = specialOffers;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }
}
