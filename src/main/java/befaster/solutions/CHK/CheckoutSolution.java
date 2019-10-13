package befaster.solutions.CHK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CheckoutSolution {

    private Map<String, Product> products = new HashMap<String, Product>(){{
        put("A", new Product("A", 50, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(200, 5));
            add(new SpecialOffer(130, 3));
        }}));
        put("B", new Product("B", 20, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(45, 2));
        }}));
        put("C", new Product("C", 30, null));
        put("D",new Product("D", 15, null));
        put("E", new Product("E", 40, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(1, "A"));
        }}));
    }};

    public Integer checkout(String skus) {
        if (isValid(skus)) {
            final Integer[] total = {0};
            groupSkus(skus).forEach((sku, q) -> {
                Integer quantity = q.intValue();
                Product product = products.get(sku);
                if (sku.equals("A") || sku.equals("B") || sku.equals("E")) {
                    total[0] = total[0] + calculateValueForSpecialOffer(quantity, products.get(sku));
                } else {
                    total[0] = total[0] + quantity * product.getPrice();
                }
            });
            return total[0];
        }
        return -1;
    }

    private Integer calculateValueForSpecialOffer(
            Integer quantity,
            Product product
    ) {
        final Integer[] total = {0};
        List<SpecialOffer> specialOffers = product.getSpecialOffers();
        specialOffers.sort(new SpecialOffer.OfferComparator());
        final Integer[] remaining = {quantity};
        specialOffers.forEach(offer -> {
            Integer availableAmount = (int) Math.floor(remaining[0] / offer.getQuantity());
            if (offer.getType().equals(OfferType.FREE_PRODUCT)) {
                total[0] = total[0] + availableAmount * product.getPrice();
                total[0] = total[0] - products.get(offer.getFreeProductSku()).getPrice();
            } else if (offer.getType().equals(OfferType.SPECIAL_PRICE)) {
                total[0] = total[0] + availableAmount * offer.getSpecialPrice();
                remaining[0] = remaining[0] - availableAmount * offer.getQuantity();
            }
        });
        total[0] = total[0] + remaining[0] * product.getPrice();
        return total[0];
    }

    private Map<java.lang.String, Long> groupSkus(java.lang.String skus) {
        return Arrays.stream(skus.split(""))
                .filter(c -> !c.isEmpty())
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    private boolean isValid(java.lang.String skus) {
        return skus != null
                && skus.replaceAll("[A-D]+", "").isEmpty();
    }
}

