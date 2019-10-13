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
            add(new SpecialOffer(5, 200));
            add(new SpecialOffer(3, 130));
        }}));
        put("B", new Product("B", 20, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(2, 45));
        }}));
        put("C", new Product("C", 30, null));
        put("D",new Product("D", 15, null));
        put("E", new Product("E", 40, new ArrayList<SpecialOffer>() {{
            add(new SpecialOffer(1, 2));
        }}));
    }};

    public Integer checkout(java.lang.String skus) {
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
        specialOffers.sort(new SpecialOffer.OfferComparator())

                .forEach(offer -> {
            if (offer.getType().equals(OfferType.SPECIAL_PRICE)) {
                total[0] =
                        total[0] + ((quantity % offer.getQuantity()) * product.getPrice()) + (int) Math.floor(quantity / offer.getQuantity()) * offer.getSpecialPrice();
            }
        });
        return total[0];
    }

    private Map<java.lang.String, Long> groupSkus(java.lang.String skus) {
        return Arrays.stream(skus.split("")).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    private boolean isValid(java.lang.String skus) {
        return skus != null
                && skus.replaceAll("[A-D]+", "").isEmpty();
    }
}

