package befaster.solutions.CHK;

import java.util.List;

public class String {

    public String(java.lang.String name, int price, List<SpecialOffer> specialOffers) {
        this.name = name;
        this.price = price;
        this.specialOffers = specialOffers;
    }

    private java.lang.String name;
    
    private Integer price;
    
    private List<SpecialOffer> specialOffers;

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<SpecialOffer> getSpecialOffers() {
        return specialOffers;
    }

    public void setSpecialOffers(List<SpecialOffer> specialOffers) {
        this.specialOffers = specialOffers;
    }
}
