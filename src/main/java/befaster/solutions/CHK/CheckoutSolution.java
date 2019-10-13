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
            Map<java.lang.String, Long> skuGroups = groupSkus(skus);
            skuGroups.forEach((sku, q) -> {
                Integer quantity = q.intValue();
                Product product = products.get(sku);
                if (sku.equals("E")) {
                    processFreeProductOffers(q.intValue(), products.get(sku), skuGroups);
                } else if (sku.equals("A") || sku.equals("B")) {
                    total[0] = total[0] + calculateValueForSpecialOffer(quantity, products.get(sku));
                } else {
                    total[0] = total[0] + quantity * product.getPrice();
                }
            });
            return total[0];
        }
        return -1;
    }

    private void processFreeProductOffers(Integer quantity, Product product, Map<String, Long> skuGroups) {
        List<SpecialOffer> offers = product.getSpecialOffers();
        final Integer[] remaining = {quantity};
        offers.forEach(offer -> {
            if (offer.getType().equals(OfferType.FREE_PRODUCT)) {
                int availableAmount = (int) Math.floor(remaining[0] / offer.getQuantity());
                while (availableAmount  > 0 && skuGroups.get(offer.getFreeProductSku()) > 0) {
                    skuGroups.put(offer.getFreeProductSku(), skuGroups.get(offer.getFreeProductSku()) - 1);
                    remaining[0] = remaining[0] - availableAmount * offer.getQuantity();
                    availableAmount = (int) Math.floor(remaining[0] / offer.getQuantity());
                }
            }
        });
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
            if (offer.getType().equals(OfferType.SPECIAL_PRICE)) {
                total[0] = total[0] + availableAmount * offer.getSpecialPrice();
                remaining[0] = remaining[0] - availableAmount * offer.getQuantity();
            }
        });
        total[0] = total[0] + remaining[0] * product.getPrice();
        return total[0];
    }

    private Map<java.lang.String, Long> groupSkus(String skus) {
        return Arrays.stream(skus.split(""))
                .filter(c -> !c.isEmpty())
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    private boolean isValid(String skus) {
        return skus != null
                && skus.replaceAll("[A-E]+", "").isEmpty();
    }
}


